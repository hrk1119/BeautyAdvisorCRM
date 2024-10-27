
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Product;
import dao.productDAO;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductDetailServlet() {
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

		String pageFlg = request.getParameter("page");

		System.out.println("JANコード → "+productCode);

		/**データベース処理**/
		RequestDispatcher disp;
		String page = "";

		//DAO処理・カラー詳細の取得
		productDAO dao = new productDAO();
		List<Product> pList = null;
		List<Product> cList = null;
		String productName = "";
		String brand = "";

		try {
			pList = dao.Search(productCode);

			for (Product p : pList) {
				productName = p.getProductName();
				brand = p.getBrandName();
			}

//			System.out.println(productName);
//			System.out.println(brand);

			cList = dao.Color(productName, brand);

			if (pageFlg != null && pageFlg.equals("e")) {
				page = "/product_edit2.jsp";
			} else {
				page = "/product_detail.jsp";
			}

		} catch (Exception e) {
			e.printStackTrace();
			page = "/product_search.jsp";
		}

//		//確認
//		for (Product p : cList) {
//			System.out.println(p.getPcName1());
//		}

		//JSPに移動
		request.setAttribute("pList", pList);
		request.setAttribute("cList", cList);
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
