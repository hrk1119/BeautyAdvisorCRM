
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Product;
import dao.productDAO;

/**
 * Servlet implementation class MailCustomerServlet
 */
@WebServlet("/MailCustomerServlet")
public class MailCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MailCustomerServlet() {
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
		String productCode = request.getParameter("product_code");

		System.out.println(productCode);

		//セッション
		HttpSession session = request.getSession(true);
		session.setAttribute("p_code", productCode);

		System.out.println("メールのセッション情報");
		System.out.println("JANコード → " + session.getAttribute("p_code"));

		//宣言
		RequestDispatcher disp;
		String page = "";

		//DAO処理・カラー詳細の取得
		productDAO dao = new productDAO();
		List<Product> pList = null;
		String pcColor = "";

		try {
			pList = dao.Search(productCode);

			for (Product p : pList) {
				pcColor = p.getPcName1();
			}

			System.out.println(pcColor);

		} catch (Exception e) {
			e.printStackTrace();
			page = "/product_search.jsp";
		}

		//JSPに移動
		page = "./CustomerSearchServlet?page=m";
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
