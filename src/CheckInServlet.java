
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;

/**
 * Servlet implementation class CheckInServlet
 */
@WebServlet("/CheckInServlet")
public class CheckInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckInServlet() {
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

		//セッションの開始
		HttpSession session = request.getSession(true);

		/**データベース処理**/
		String sql;
		RequestDispatcher disp;
		String page = "";

		//URLをセットする
		DAO dao = new DAO();
		Statement st = null;
		try {
			Connection con = dao.getConnection();
			st = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		//SQL作成
		sql = "SELECT customer_name1 , customer_color FROM customer WHERE customer_code = " + text + ";";
		//		System.out.println(sql);

		//SQL実行
		String name = "";
		try {
			ResultSet rs;
			rs = st.executeQuery(sql);
			if (rs.next()) {
				name = rs.getString("customer_name1");

				//セッションに値を設定
				session.setAttribute("checkInCode", text);
				session.setAttribute("checkInName", rs.getString("customer_name1"));
				session.setAttribute("color", rs.getString("customer_color"));
				System.out.println("---チェックインのセッション情報---");
				System.out.println("顧客番号 → " + session.getAttribute("checkInCode"));
				System.out.println("顧客名 → " + session.getAttribute("checkInName"));
				System.out.println("パーソナルカラー → " + session.getAttribute("color"));

			}

		} catch (SQLException e) {
			System.out.println("sql err!");
		}

		//		// JSONに変換（HTMLで使いやすい型に変換する）
		//		Gson gson = new Gson();
		//		String jsonName = gson.toJson(name);
		//		System.out.println(gson.toJson(name));

		//		System.out.println(name);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// 結果の送信(response)
		PrintWriter out = response.getWriter();
		out.print(name);

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
