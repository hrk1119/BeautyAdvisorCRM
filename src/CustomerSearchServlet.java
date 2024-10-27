
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Customer;
import dao.DAO;

/**
 * Servlet implementation class CustomerSearchServlet
 */
@WebServlet("/CustomerSearchServlet")
public class CustomerSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerSearchServlet() {
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
		String customerCode = request.getParameter("customer_code");
		String customerName1 = request.getParameter("customer_name1");
		String customerName2 = request.getParameter("customer_name2");
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");
		String work = request.getParameter("work");
		String color = request.getParameter("color");
		String buyCode = request.getParameter("buy_code");
		String buyDate = request.getParameter("buy_date");
		String storeId = request.getParameter("store");
		String employeeId = request.getParameter("employee");

		String pageFlg = request.getParameter("page");

		//メール
		String mailColor = request.getParameter("m_pcColor");
		System.out.println("メール用 → " + mailColor);

		System.out.println(pageFlg);

		//colorを文字に
		if (color != null) {
			if (color.equals("yellow")) {
				color = "イエローベース";
			} else if (color.equals("blue")) {
				color = "ブルーベース";
			}
		}

		//genderを文字に
		if (gender != null) {
			if (gender.equals("men")) {
				gender = "男性";
			} else if (gender.equals("women")) {
				gender = "女性";
			}
		}

		//確認
		//		System.out.println("顧客番号 → " + customerCode);
		//		System.out.println("顧客名 → " + customerName1);
		//		System.out.println("顧客名（フリガナ） → " + customerName2);
		//		System.out.println("誕生日 → " + birthday);
		//		System.out.println("性別 → " + gender);
		//		System.out.println("仕事 → " + work);
		//		System.out.println("パーソナルカラー → " + color);
		//		System.out.println("JANコード → " + buyCode);
		//		System.out.println("購入日 → " + buyDate);
		//		System.out.println("店舗名 → " + storeId);
		//		System.out.println("従業員名 → " + employeeId);

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

		boolean flg = true;
		int intFlg = 0;

		StringBuilder sb = new StringBuilder(
				"SELECT DISTINCT A.customer_code ,A.customer_name1 , DATE_FORMAT(A.customer_birthday,'%Y/%m/%d') AS birthday , A.customer_color \r\n");
		sb.append("FROM (((((customer AS A LEFT OUTER JOIN support AS B ON A.customer_code = B.customer_code) \r\n");
		sb.append("LEFT OUTER JOIN employee AS C ON B.employee_id = C.employee_id) \r\n");
		sb.append("LEFT OUTER JOIN store AS D ON C.store_id = D.store_id ) \r\n");
		sb.append("LEFT OUTER JOIN sale AS E ON A.customer_code = E.customer_code) \r\n");
		sb.append("LEFT OUTER JOIN sale_detail AS F ON E.sale_id = F.sale_id) \r\n");
		sb.append("LEFT OUTER JOIN support AS G ON A.customer_code = G.customer_code \r\n");

		//１つ目
		if (!(customerCode == null || customerCode.equals("")) && flg) {
			sb.append("WHERE A.customer_code = " + customerCode);
			flg = false;
			intFlg = 1;
		}
		if (!(customerName1 == null || customerName1.equals("")) && flg) {
			sb.append("WHERE A.customer_name1 LIKE '%" + customerName1 + "%'");
			flg = false;
			intFlg = 2;
		}
		if (!(customerName2 == null || customerName2.equals("")) && flg) {
			sb.append("WHERE A.customer_name2 LIKE '%" + customerName2 + "%'");
			flg = false;
			intFlg = 3;
		}
		if (!(birthday == null || birthday.equals("")) && flg) {
			sb.append("WHERE A.customer_birthday ='" + birthday + "'");
			flg = false;
			intFlg = 4;
		}
		if (gender != null && flg) {
			sb.append("WHERE A.customer_gender = '" + gender + "'");
			flg = false;
			intFlg = 5;
		}
		if (!(work == null || work.equals("")) && flg) {
			sb.append("WHERE A.customer_work = '" + work + "'");
			flg = false;
			intFlg = 6;
		}
		if (color != null && flg) {
			sb.append("WHERE A.customer_color = '" + color + "'");
			flg = false;
			intFlg = 7;
		}
		if (!(buyCode == null || buyCode.equals("")) && flg) {
			sb.append("WHERE F.product_id = '" + buyCode + "'");
			flg = false;
			intFlg = 8;
		}
		if (!(buyDate == null || buyDate.equals("")) && flg) {
			sb.append("WHERE E.sale_date = '" + buyDate + "'");
			flg = false;
			intFlg = 9;

		}
		if (!(storeId == null || storeId.equals("")) && flg) {
			sb.append("WHERE E.store_id = " + storeId);
			flg = false;
			intFlg = 10;
		}
		if (!(employeeId == null || employeeId.equals("")) && flg) {
			sb.append("WHERE G.employee_id =" + employeeId);
			flg = false;
			intFlg = 11;
		}

		//メール
		if (mailColor != null && (mailColor.equals("イエローベース") || mailColor.equals("ブルーベース"))) {
			sb.append("WHERE A.customer_color = '" + mailColor + "'");
		}

		//２つ目
		if (!(customerCode == null || customerCode.equals("")) && intFlg != 1) {
			sb.append(" AND A.customer_code = " + customerCode);
		}
		if (!(customerName1 == null || customerName1.equals("")) && intFlg != 2) {
			sb.append(" AND A.customer_name1 LIKE '%" + customerName1 + "%'");
		}
		if (!(customerName2 == null || customerName2.equals("")) && intFlg != 3) {
			sb.append(" AND A.customer_name2 LIKE '%" + customerName2 + "%'");
		}
		if (!(birthday == null || birthday.equals("")) && intFlg != 4) {
			sb.append(" AND A.customer_birthday ='" + birthday + "'");
		}
		if (gender != null && intFlg != 5) {
			sb.append(" AND A.customer_gender = '" + gender + "'");
		}
		if (!(work == null || work.equals("")) && intFlg != 6) {
			sb.append(" AND A.customer_work = '" + work + "'");
		}
		if (color != null && intFlg != 7) {
			sb.append(" A.customer_color = '" + color + "'");
		}
		if (!(buyCode == null || buyCode.equals("")) && intFlg != 8) {
			sb.append(" AND F.product_id = '" + buyCode + "'");
		}
		if (!(buyDate == null || buyDate.equals("")) && intFlg != 9) {
			sb.append(" AND E.sale_date = '" + buyDate + "'");
		}
		if (!(storeId == null || storeId.equals("")) && intFlg != 10) {
			sb.append(" AND E.store_id = " + storeId);
		}
		if (!(employeeId == null || employeeId.equals("")) && intFlg != 11) {
			sb.append(" AND G.employee_id =" + employeeId);
		}

		sql = new String(sb);
		System.out.println(sql);

		//SQL実行
		List<Customer> list = new ArrayList<>();
		int listNum = 0;

		try {
			ResultSet rs;
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Customer c = new Customer();
				c.setCustomerCode(rs.getInt("A.customer_code"));
				c.setCustomerName1(rs.getString("A.customer_name1"));
				c.setOutBirthday(rs.getString("birthday"));
				c.setColor(rs.getString("A.customer_color"));

				list.add(c);
			}

			listNum = list.size();
			System.out.println("件数→" + listNum);

			if (pageFlg != null && pageFlg.equals("m")) {
				page = "/mail_customer.jsp";
			} else {
				page = "/customer_search.jsp";
			}

		} catch (SQLException e) {
			System.out.println("sql err!");
		}

		//		//確認
		//		for (Customer c : list) {
		//			System.out.println(c.getCustomerCode());
		//		}

		//JSPに移動
		request.setAttribute("list", list);
		request.setAttribute("listNum", listNum);
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
