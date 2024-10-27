

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Sale;
import dao.SaleDAO;

/**
 * Servlet implementation class SaleEditServlet
 */
@WebServlet("/SaleEditServlet")
public class SaleEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
				request.setCharacterEncoding("utf-8");

				//セッション
				HttpSession session = request.getSession(true);
				String c_code = (String) session.getAttribute("c_code");
				int store = (Integer) session.getAttribute("s_id");
				int saleId =  (Integer) session.getAttribute("saleEditId");

				//htmlから値を受け取る
				String strCustomerCode = request.getParameter("customer_code");
				String strDate = request.getParameter("date");
				String productId = request.getParameter("product_id");
				String strNum = request.getParameter("num");
				String bId = request.getParameter("b_id");

				System.out.println(strCustomerCode);
				System.out.println(strDate);
				System.out.println(productId);
				System.out.println(strNum);

				//int・date型に変換
				int customerCode = Integer.parseInt(c_code);
				Date date = Date.valueOf(strDate);
				int num = Integer.parseInt(strNum);

				//宣言
				RequestDispatcher disp;
				String page = "";

				//値をbeanに追加
				Sale s = new Sale();
				s.setSaleId(saleId);
				s.setCustomerCode(customerCode);
				s.setInSaleDate(date);
				s.setStoreId(store);
				s.setSaleNum(num);

				//DAO
				try {
					SaleDAO dao = new SaleDAO();
					int line = dao.update(s,bId);
				} catch (Exception e) {
					e.printStackTrace();
				}

				request.setAttribute("c_code", c_code);
				page = "/CustomerDetailServlet";
				disp = request.getRequestDispatcher(page);
				disp.forward(request, response);

			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
