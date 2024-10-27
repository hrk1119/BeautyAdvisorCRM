
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

import bean.Product;
import dao.DAO;

/**
 * Servlet implementation class ProductSearchServlet
 */
@WebServlet("/ProductSearchServlet")
public class ProductSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductSearchServlet() {
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
		String manufacturer = request.getParameter("manufacturer");
		String brand = request.getParameter("brand");
		String productName = request.getParameter("product_name");
		String productColor = request.getParameter("product_color");
		String productNum = request.getParameter("product_num");
		String productCode = request.getParameter("product_code");
		String productWord = request.getParameter("product_word");
		String category = request.getParameter("category");
		String strColorCategory = request.getParameter("c_category");
		String[] pcColor = request.getParameterValues("pc_color");
		String lowPrice = request.getParameter("low_price");
		String highPrice = request.getParameter("high_price");

		String pageFlg = request.getParameter("page");

		//パーソナルカラー
		String pcColor1 = null;
		String pcColor2 = null;

		if (pcColor != null) {
			for (int i = 0; i < pcColor.length; i++) {
				if (pcColor1 == null) {
					if (pcColor[i].equals("yellow")) {
						pcColor1 = "イエローベース";
					} else if (pcColor[i].equals("blue")) {
						pcColor1 = "ブルーベース";
					} else {
						pcColor1 = "不明";
					}
				} else {
					if (pcColor[i].equals("yellow")) {
						pcColor2 = "イエローベース";
					} else if (pcColor[i].equals("blue")) {
						pcColor2 = "ブルーベース";
					}
				}
			}
		}

		//金額
		if (lowPrice == null || lowPrice.equals("")) {
			lowPrice = "0";
		}
		if (highPrice == null || highPrice.equals("")) {
			highPrice = "100000";
		}

		//カテゴリー
		int colorCategory = 0;
		if (strColorCategory != null && !strColorCategory.equals("0")) {
			colorCategory = Integer.parseInt(strColorCategory);
		}

		//確認
		//		System.out.println("---商品の検索条件---");
		//		System.out.println("メーカー → "+manufacturer);
		//		System.out.println("ブランド → "+brand);
		//		System.out.println("商品名 → "+productName);
		//		System.out.println("カラー名 → "+productColor);
		//		System.out.println("カラー番号 → "+productNum);
		//		System.out.println("JANコード → "+productCode);
		//		System.out.println("商品説明 → "+productWord);
		//		System.out.println("商品カテゴリ → "+category);
		//		System.out.println("カラーカテゴリ → "+colorCategory);
		//		System.out.println("パーソナルカラー → "+pcColor1);
		//		System.out.println("最低価格 → "+lowPrice);
		//		System.out.println("最高価格 → "+highPrice);

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
		StringBuilder sb = new StringBuilder(
				"SELECT A.product_id , A.color_num , A.color_name , A.color_photo , B.product_name , FORMAT(B.product_price,0) AS price , C.brand_name , D.manufacturer_name , GROUP_CONCAT(G.pc_name) AS pc_name \r\n");
		sb.append("FROM ((((product AS A LEFT OUTER JOIN  product_group AS B ON A.group_id = B.group_id )\r\n");
		sb.append("LEFT OUTER JOIN brand AS C ON B.brand_id = C.brand_id )\r\n");
		sb.append("LEFT OUTER JOIN manufacturer AS D ON C.manufacturer_id = D.manufacturer_id )\r\n");
		sb.append("LEFT OUTER JOIN category AS E ON B.category_id = E.category_id )\r\n");
		sb.append("LEFT OUTER JOIN color_category AS H ON A.colorCategory_id = H.colorCategory_id\r\n");
		sb.append(" , color_detail AS F\r\n");
		sb.append("LEFT OUTER JOIN  color AS G ON F.pc_id = G.pc_id \r\n");
		sb.append("WHERE A.product_id = F.product_id\r\n");

		if (!(manufacturer == null || manufacturer.equals(""))) {
			sb.append(" AND D.manufacturer_name LIKE '%" + manufacturer + "%'");
		}
		if (!(brand == null || brand.equals(""))) {
			sb.append(" AND C.brand_name LIKE '%" + brand + "%'");
		}
		if (!(productName == null || productName.equals(""))) {
			sb.append(" AND B.product_name LIKE '%" + productName + "%'");
		}
		if (!(productColor == null || productColor.equals(""))) {
			sb.append(" AND A.color_name LIKE '%" + productColor + "%'");
		}
		if (!(productNum == null || productNum.equals(""))) {
			sb.append(" AND A.color_num = '" + productNum + "'");
		}
		if (!(productCode == null || productCode.equals(""))) {
			sb.append(" AND A.product_id = '" + productCode + "'");
		}
		if (!(productWord == null || productWord.equals(""))) {
			sb.append(" AND B.product_memo LIKE '%" + productWord + "%'");
		}
		if (!(category == null || category.equals(""))) {
			sb.append(" AND E.category_name = '" + category + "'");
		}
		if (colorCategory != 0) {
			sb.append(" AND A.colorCategory_id = " + colorCategory);
		}
		//カラーが１つの場合
		if (pcColor1 != null) {
			if (pcColor2 == null) {
				sb.append(" AND pc_name = '" + pcColor1 + "'");
			}
		}

		sb.append(" AND " + lowPrice + " <= B.product_price AND B.product_price <= " + highPrice +
				" GROUP BY A.product_id \r\n");

		//カラーが２つの場合
		if (pcColor1 != null) {
			if (pcColor2 != null) {
				sb.append(" HAVING pc_name = '" + pcColor1 + "," + pcColor2 + "'");
			}
			sb.append(" ORDER BY D.manufacturer_name ASC , C.brand_name ASC , B.product_name ASC , A.color_name ASC;" +
					"");
		}

		sql = new String(sb);
		System.out.println(sql);

		//SQL実行
		List<Product> list = new ArrayList<>();
		int listNum = 0;

		try {
			ResultSet rs;
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Product p = new Product();
				p.setProductId(rs.getString("product_id"));
				p.setColorNum(rs.getString("color_num"));
				p.setColorName(rs.getString("color_name"));
				p.setProductName(rs.getString("product_name"));
				p.setOutPrice(rs.getString("price"));
				p.setPhoto(rs.getString("color_photo"));
				p.setBrandName(rs.getString("brand_name"));
				p.setManufacturerName(rs.getString("manufacturer_name"));

				list.add(p);
			}

			listNum = list.size();
			System.out.println("件数→" + listNum);

			//ページのチェック
			if (pageFlg != null && pageFlg.equals("c")) {
				page = "/product_search_c.jsp";
			} else if (pageFlg != null && pageFlg.equals("m")) {
				page = "/mail.jsp";
			} else {
				page = "/product_search.jsp";
			}
		} catch (SQLException e) {
			System.out.println("sql err!");
		}

		System.out.println("取得");
		for (Product p : list) {
			System.out.println(p.getProductName());
		}

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
