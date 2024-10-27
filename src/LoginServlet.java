
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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

		//htmlから値を受け取る
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		//		System.out.println(id);
		//		System.out.println(pass);

		//セッション
		HttpSession session = request.getSession(true);

		/**データベース処理**/
		String sql;
		RequestDispatcher disp;
		String page = "";

		//URLをセットする
		String url = "jdbc:mysql://localhost:3306/cosme?characterEncoding=utf8";
		Connection con = null;
		Statement st = null;

		//Class.forName
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("err1");

			page = "/err.jsp";
			disp = request.getRequestDispatcher(page);
			disp.forward(request, response);
			return;
		}

		try {
			con = DriverManager.getConnection(url, "root", "");
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("err2");

			page = "/err.jsp";
			disp = request.getRequestDispatcher(page);
			disp.forward(request, response);
			return;
		}

		//SQL作成
		StringBuilder sb = new StringBuilder("SELECT e.employee_id , e.employee_name , s.store_id , s.store_name \r\n");
		sb.append("FROM employee AS e LEFT OUTER JOIN store AS s ON e.store_id = s.store_id \r\n");
		sb.append("WHERE employee_id = " + id);
		sql = new String(sb);
//		System.out.println(sql);

		//SQL実行
		try {
			ResultSet rs;
			rs = st.executeQuery(sql);

			if (rs.next()) { //IDとパスワードが一致 ＝ SELECT文で取得できている
				if (pass.equals("cosume")) {
					page = "/menu.jsp";
					//セッションに追加
					session.setAttribute("e_id", rs.getInt("employee_id"));
					session.setAttribute("e_name", rs.getString("employee_name"));
					session.setAttribute("s_id", rs.getInt("store_id"));
					session.setAttribute("s_name", rs.getString("store_name"));
					System.out.println("---ログインのセッション情報---");
					System.out.println("従業員番号 → " + session.getAttribute("e_id"));
					System.out.println("従業員名 → " + session.getAttribute("e_name"));
					System.out.println("店舗番号 → " + session.getAttribute("s_id"));
					System.out.println("店舗名 → " + session.getAttribute("s_name"));
				} else {
					page = "/login.jsp";
				}

			} else { //IDとパスワードが不一致 ＝ SELECT文で取得できていない
				page = "/login.jsp";

			}
		} catch (SQLException e) {
			System.out.println("sql err!");
			page = "/login.jsp";
		}

		//JSPに移動
		disp = request.getRequestDispatcher(page);
		disp.forward(request, response);

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
