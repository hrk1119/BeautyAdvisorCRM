

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

import com.google.gson.Gson;

import bean.Sale;
import dao.SaleDAO;

/**
 * Servlet implementation class SupportProductServlet
 */
@WebServlet("/SupportProductServlet")
public class SupportProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupportProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = new BufferedReader(request.getReader()); //リクエストのメッセージボディを文字データとして取り出す
		String text = br.readLine(); //テキスト行を読み込む
		text = URLDecoder.decode(text, "UTF-8"); //デコード

		// JSON→java
		Gson gson = new Gson();
		Map map = gson.fromJson(text, Map.class);
		System.out.println(map.get("c_code"));
		System.out.println(map.get("date"));

		String c_code = (String) map.get("c_code");
		String date = (String) map.get("date");
		Double store = (Double) map.get("store");

		//dao
		SaleDAO dao = new SaleDAO();
		List<Sale> list = null;

		try {
			list = dao.supportSearch(c_code,date,store);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// ArrayListをJSONに変換（HTMLで使いやすい型に変換する）
		String jsonList = gson.toJson(list);
		System.out.println(jsonList);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// 結果の送信(response)
		PrintWriter out = response.getWriter();
		out.print(jsonList);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
