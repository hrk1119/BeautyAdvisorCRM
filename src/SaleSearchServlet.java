
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bean.Sale;
import dao.SaleDAO;

/**
 * Servlet implementation class SaleSearchServlet
 */
@WebServlet("/SaleSearchServlet")
public class SaleSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaleSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//セッション
		HttpSession session = request.getSession(true);

		BufferedReader br = new BufferedReader(request.getReader()); //リクエストのメッセージボディを文字データとして取り出す
		String text = br.readLine(); //テキスト行を読み込む
		text = URLDecoder.decode(text, "UTF-8"); //デコード

		// JSON→java
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Map map = gson.fromJson(text, Map.class);
		System.out.println(map.get("saleid"));
		System.out.println(map.get("jan"));

		String saleId = (String) map.get("saleid");
		String jan = (String) map.get("jan");

		//dao
		SaleDAO dao = new SaleDAO();
		List<Sale> list = null;

		try {
			list = dao.detailSearch(saleId,jan);
			for (Sale s : list) {
				session.setAttribute("saleEditId", s.getSaleId());
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// ArrayListをJSONに変換（HTMLで使いやすい型に変換する）
		String jsonList = gson.toJson(list);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// 結果の送信(response)
		PrintWriter out = response.getWriter();
		out.print(jsonList);

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
