
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Customer;
import bean.Sale;
import bean.Support;
import dao.CustomerDAO;
import dao.SaleDAO;
import dao.SupportDAO;

/**
 * Servlet implementation class CustomerDetailServlet
 */
@WebServlet("/CustomerDetailServlet")
public class CustomerDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerDetailServlet() {
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
		String customerCode = "";
		if (!(request.getParameter("customer_code") == null || request.getParameter("customer_code").equals(""))) {
			customerCode = request.getParameter("customer_code");
		} else {
			customerCode = (String) request.getAttribute("c_code");
		}

		String pageFlg = request.getParameter("page");

//		System.out.println("顧客番号 → " + customerCode);

		//セッション
		HttpSession session = request.getSession(true);
		session.setAttribute("c_code", customerCode);

		/**データベース処理**/
		RequestDispatcher disp;
		String page = "";

		//1ページ目
		CustomerDAO cDAO = new CustomerDAO();
		List<Customer> cList = null;

		//2ページ目
		SupportDAO suDAO = new SupportDAO();
		List<Support> suList = null;
		int suListNum = 0;

		//3ページ目
		SaleDAO saDAO = new SaleDAO();
		List<Sale> saList = null;
		int saListNum = 0;

		try {
			cList = cDAO.search(customerCode);
			suList = suDAO.search(customerCode);
			saList = saDAO.search(customerCode);

			suListNum = suList.size();
			System.out.println("サポートの件数→" + suListNum);

			saListNum = saList.size();
			System.out.println("購入の件数→" + saListNum);

			if (pageFlg != null && pageFlg.equals("e")) {
				page = "/customer_edit2.jsp";
			} else {
				page = "/customer_detail.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			page = "/customer_search.jsp";
		}

		//		//確認
		//		for (Customer c : cList) {
		//			System.out.println(c.getBirthday());
		//		}
		//
		//		for (Support su : suList) {
		//			System.out.println(su.getDate());
		//		}
		//
				for (Sale sa : saList) {
					System.out.println(sa.getSaleId());
				}

		//JSPに移動
		request.setAttribute("cList", cList);
		request.setAttribute("saList", saList);
		request.setAttribute("suList", suList);
		request.setAttribute("saListNum", saListNum);
		request.setAttribute("suListNum", suListNum);
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
