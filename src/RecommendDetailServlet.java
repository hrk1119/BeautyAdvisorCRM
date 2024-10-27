
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bean.Product;
import dao.productDAO;

/**
 * Servlet implementation class RecommendDetailServlet
 */
@WebServlet("/RecommendDetailServlet")
public class RecommendDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecommendDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字化け対策
		request.setCharacterEncoding("utf-8");

		// requestの受け取り
		BufferedReader br = new BufferedReader(request.getReader()); //リクエストのメッセージボディを文字データとして取り出す
		String text = br.readLine(); //テキスト行を読み込む
		text = URLDecoder.decode(text, "UTF-8"); //デコード

		int size = text.length();
		int cut = 13;

		String productCode = text.substring(size - cut);

//		System.out.println("JANコード → " + productCode);

		/**データベース処理**/
		RequestDispatcher disp;
		String page = "";

		//DAO処理・カラー詳細の取得
		productDAO dao = new productDAO();
		List<Product> pList = null;

		try {
			pList = dao.Search(productCode);

			page = "/product_detail.jsp";

		} catch (Exception e) {
			e.printStackTrace();
			page = "/reco_category.jsp";
		}

		//確認
		for (Product p : pList) {
			System.out.println(p.getOutDate());
		}

		// JSONに変換（HTMLで使いやすい型に変換する）
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String jsonProduct = gson.toJson(pList);
		System.out.println(jsonProduct);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// 結果の送信(response)
		PrintWriter out = response.getWriter();
		out.print(jsonProduct);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
