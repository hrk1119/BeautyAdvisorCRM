
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bean.Support;
import dao.SupportDAO;

/**
 * Servlet implementation class SupportSearchServlet
 */
@WebServlet("/SupportSearchServlet")
public class SupportSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupportSearchServlet() {
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

		//セッション
				HttpSession session = request.getSession(true);

		// requestの受け取り
		BufferedReader br = new BufferedReader(request.getReader()); //リクエストのメッセージボディを文字データとして取り出す
		String text = br.readLine(); //テキスト行を読み込む
		text = URLDecoder.decode(text, "UTF-8"); //デコード

		//dao
		SupportDAO dao = new SupportDAO();
		List<Support> list = null;

		try {
			list = dao.search_suppId(text);
			for (Support s : list) {
				session.setAttribute("suppEditId", s.getsupportId());
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// ArrayListをJSONに変換（HTMLで使いやすい型に変換する）
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
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
