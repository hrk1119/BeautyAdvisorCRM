
import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Customer;
import dao.CustomerDAO;

/**
 * Servlet implementation class CustomerEditServlet
 */
@WebServlet("/CustomerEditServlet")
public class CustomerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerEditServlet() {
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
		String name1 = request.getParameter("customer_name1");
		String name2 = request.getParameter("customer_name2");
		String strCustomerCode = request.getParameter("customer_code");
		String strBirthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String postalCode = request.getParameter("zip");
		String address = request.getParameter("address");
		String work = request.getParameter("work");

		//int・date型に変換
		int customerCode = Integer.parseInt(strCustomerCode);
		Date birthday = Date.valueOf(strBirthday);

		//確認
//		System.out.println("名前 → " + name1);
//		System.out.println("フリガナ → " + name2);
//		System.out.println("顧客番号 → " + customerCode);
//		System.out.println("誕生日 → " + birthday);
//		System.out.println("性別 → " + gender);
//		System.out.println("電話 → " + tel);
//		System.out.println("メール → " + email);
//		System.out.println("郵便番号 → " + postalCode);
//		System.out.println("住所 → " + address);
//		System.out.println("仕事 → " + work);


		//宣言
		RequestDispatcher disp;
		String page = "";

		//値をbeanに追加
		Customer c = new Customer();
		c.setCustomerName1(name1);
		c.setCustomerName2(name2);
		c.setCustomerCode(customerCode);
		c.setInBirthday(birthday);
		c.setGender(gender);
		c.setTel(tel);
		c.setMail(email);
		c.setPostalcode(postalCode);
		c.setAddress(address);
		c.setWork(work);

		//DAO
		try {
			CustomerDAO dao = new CustomerDAO();
			int line = dao.edit(c);
		} catch (Exception e) {
			e.printStackTrace();
		}

		page = "/CustomerDetailServlet";
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
