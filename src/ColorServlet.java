
import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class ColorServlet
 */
@WebServlet("/ColorServlet")
public class ColorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ColorServlet() {
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

		////セッションの開始
		HttpSession session = request.getSession(true);
		String customerCode = (String) session.getAttribute("checkInCode");

		//htmlから値を受け取る
		String[] check = new String[5];

		check[0] = request.getParameter("q1");
		check[1] = request.getParameter("q2");
		check[2] = request.getParameter("q3");
		check[3] = request.getParameter("q4");
		check[4] = request.getParameter("q5");

		//		//確認
		//		for (int i = 0; i < check.length; i++) {
		//			System.out.println(check[i]);
		//		}

		//宣言
		RequestDispatcher disp;
		String page = "";

		//診断
		int yellow = 0;
		int blue = 0;
		String color;

		for (String s : check) {
			if (s.equals("yellow")) {
				yellow = yellow + 1;
			} else {
				blue = blue + 1;
			}
		}

		//イエベかブルベか？
		if (yellow > blue) {
			page = "color_result_yellow.jsp";
			color = "イエローベース";
		} else {
			page = "color_result_blue.jsp";
			color = "ブルーベース";
		}

		//データベース
		DAO dao = new DAO();
		Statement st = null;
		try {
			Connection con = dao.getConnection();
			st = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int line;

		//SQL作成
		String sql = "UPDATE customer SET customer_color='" + color + "' WHERE customer_code=" + customerCode;

		//実行
		try {
			line = st.executeUpdate(sql);

			if (line > 0) {
				System.out.println("変更が完了しました。");
				session.setAttribute("color", color);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
