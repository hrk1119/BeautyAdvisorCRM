
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

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
 * Servlet implementation class SaleRegistServlet
 */
@WebServlet("/SaleRegistServlet")
public class SaleRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaleRegistServlet() {
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
		String c_code = (String) session.getAttribute("c_code");
		int storeId = (Integer) session.getAttribute("s_id");

		//htmlから値を受け取る
		String strCustomerCode = request.getParameter("customer_code");
		String strDate = request.getParameter("date");

		//int・date型に変換
		int customerCode = Integer.parseInt(strCustomerCode);
		Date date = Date.valueOf(strDate);

		System.out.println("---購入履歴の登録情報---");
		System.out.println("顧客番号　→　" + customerCode);
		System.out.println("店舗番号　→　" + storeId);
		System.out.println("日付　→　" + date);

		/**商品情報を受け取る**/
		ArrayList<String> products = new ArrayList<String>(); //JANコード
		ArrayList<Integer> nums = new ArrayList<Integer>(); //個数
		boolean flg = true;

		//個数
		String strNum;
		int num = 0;
		int i = 0;

		while (flg) {
			if (!(request.getParameter("product_code" + i) == null
					|| request.getParameter("product_code" + i).equals(""))) {
				products.add(request.getParameter("product_code" + i));

				strNum = request.getParameter("num" + i);
				num = Integer.parseInt(strNum);
				nums.add(num);

				System.out.println(i + "つ目の商品番号　→　" + products.get(i));
				System.out.println(i + "つ目の個数　→　" + nums.get(i));
			} else {
				System.out.println("end");
				flg = false;
			}
			i++;
		}

		//宣言
		RequestDispatcher disp;
		String page = "";

		/**saleテーブル**/
		//値をbeanに追加
		Sale s = new Sale();
		s.setCustomerCode(customerCode);
		s.setStoreId(storeId);
		s.setInSaleDate(date);

		int sale_id = 0;

		//DAO
		try {
			SaleDAO dao = new SaleDAO();
			sale_id = dao.saleInsert(s);
			//			System.out.println("sale_id→" + sale_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**sale_detailテーブル**/
		for (int j = 0; j < products.size(); j++) {

			s.setProductId(products.get(j));
			s.setSaleNum(nums.get(j));

			//				System.out.println("ProductId→" + s.getProductId());
			//				System.out.println("num→" + s.getSaleNum());

			//DAO
			try {
				SaleDAO dao = new SaleDAO();
				int line = dao.sale_detailInsert(s, sale_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("c_code", c_code);
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
