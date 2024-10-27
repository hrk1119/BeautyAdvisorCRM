
import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Product;
import dao.productDAO;

/**
 * Servlet implementation class ProductRegistServlet
 */
@WebServlet("/ProductRegistServlet")
public class ProductRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductRegistServlet() {
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

		/**product_groupテーブル**/
		//htmlから値を受け取る
		String productName = request.getParameter("product_name");
		String strBrand = request.getParameter("brand");
		String strPrice = request.getParameter("price");
		String strDate = request.getParameter("date");
		String groupPhoto = request.getParameter("group_photo");
		String url = request.getParameter("url");
		String memo = request.getParameter("memo");
		String strCategory = request.getParameter("category");

//		System.out.println("商品名 → " + productName);
//		System.out.println("ブランド → " + strBrand);
//		System.out.println("価格 → " + strPrice);
//		System.out.println("発売日 → " + strDate);
//		System.out.println("URL → " + url);
//		System.out.println("メモ → " + memo);
//		System.out.println("カテゴリ → " + strCategory);

		//int・date型に変換
		int price = 0;
		if (!(strPrice == null || strPrice.equals(""))) {
			price = Integer.parseInt(strPrice);
		}
		int brand = 0;
		if (!(strBrand == null || strBrand.equals(""))) {
			brand = Integer.parseInt(strBrand);
		}
		int category = 0;
		if (!(strCategory == null || strCategory.equals(""))) {
			category = Integer.parseInt(strCategory);
		}
		Date date = null;
		if (!(strDate == null || strDate.equals(""))) {
			date = Date.valueOf(strDate);
		}

		//宣言
		RequestDispatcher disp;
		String page = "";

		//値をbeanに追加
		Product p = new Product();
		p.setProductName(productName);
		if (price != 0) {
			p.setInPrice(price);
		}
		if (date != null) {
			p.setInDate(date);
		}
		p.setUrl(url);
		p.setGroupPhoto(groupPhoto);
		p.setMemo(memo);
		if (category != 0) {
			p.setCategoryId(category);
		}
		p.setBrandId(brand);

		int group_id = 0;

		//DAO
		try {
			productDAO dao = new productDAO();
			group_id = dao.productGroupInsert(p);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**productテーブル**/
		boolean flg = true;
		int i = 0;

		while (flg) {
			//			System.out.println(request.getParameter("product_code" + i));
			if (!(request.getParameter("product_code" + i) == null
					|| request.getParameter("product_code" + i).equals(""))) {
				//htmlから値を受け取る
				String productCode = request.getParameter("product_code" + i);
				String colorNum = request.getParameter("color_num" + i);
				String colorName = request.getParameter("color_name" + i);
				String photo = request.getParameter("photo");
				String strColorCategory = request.getParameter("color_category" + i);
				String[] strPcColors = request.getParameterValues("pc_color" + i);

				//				System.out.println("JANコード → " + productCode);
				//				System.out.println("カラー番号 → " + colorNum);
				//				System.out.println("カラー名 → " + colorName);
				//				System.out.println("写真 → " + photo);
				//				System.out.println("カラーカテゴリ → " + strColorCategory);

				//int型に変換
				int colorCategory = 0;
				if (!(strColorCategory == null || strColorCategory.equals(""))) {
					colorCategory = Integer.parseInt(strColorCategory);
				}

				//パーソナルカラー
				int[] intPcColors = new int[0];
				if (strPcColors != null) {
					intPcColors = new int[strPcColors.length];
					for (int j = 0; j < intPcColors.length; j++) {
						intPcColors[j] = Integer.parseInt(strPcColors[j]);
						System.out.println(intPcColors[j]);
					}
				}

				//値をbeanに追加
				p.setProductId(productCode);
				p.setColorNum(colorNum);
				p.setColorName(colorName);
				p.setPhoto("a"); //変える
				p.setColorCategoryId(colorCategory);

				//DAO
				try {
					productDAO dao = new productDAO();
					int line = dao.productInsert(p, group_id, intPcColors);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("end");
				flg = false;
			}
			i++;
			//			System.out.println(i);
		}

		page = "/product_regist.jsp";
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
