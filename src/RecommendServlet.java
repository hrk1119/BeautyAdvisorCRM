
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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bean.Product;
import dao.DAO;
import dao.SaleDAO;

/**
 * Servlet implementation class RecommendServlet
 */
@WebServlet("/RecommendServlet")
public class RecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecommendServlet() {
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

		//セッションの開始
		HttpSession session = request.getSession(true);

		//個人の情報
		String customerCode = (String) session.getAttribute("checkInCode");
		String pcColor = (String) session.getAttribute("color");
		//		System.out.println("---おすすめ商品検索の情報---");
		//		System.out.println(customerCode);
		//		System.out.println(pcColor);

		//htmlから値を受け取る
		String strCategory = request.getParameter("category");
		String[] strColors = request.getParameterValues("color");
		String[] strKeys = request.getParameterValues("key");

		int category = Integer.parseInt(strCategory);

		//		System.out.println("カテゴリー → "+category);

		//カラー
		int[] intColors = new int[0];
		if (strColors != null) {
			intColors = new int[strColors.length];
			for (int i = 0; i < intColors.length; i++) {
				intColors[i] = Integer.parseInt(strColors[i]);
				//				System.out.println("チェックされたカラーの番号");
				//				System.out.println(intColors[i]);
			}
		}

		//キーワード
		int[] intKeys = new int[0];
		if (strKeys != null) {
			intKeys = new int[strKeys.length];
			for (int i = 0; i < intKeys.length; i++) {
				intKeys[i] = Integer.parseInt(strKeys[i]);
				//				System.out.println("チェックされたキーワードの番号");
				//				System.out.println(intKeys[i]);
			}
		}

		/**データベース処理**/
		String sql;
		RequestDispatcher disp;
		String page = "";

		/**金額のチェック**/
		int avePrice = 0;
		//DAO
		try {
			SaleDAO dao = new SaleDAO();
			avePrice = dao.price(customerCode);
			System.out.println("平均金額 → " + avePrice);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**おすすめ品の検索**/
		//URLをセットする
		DAO dao = new DAO();
		Statement st = null;
		try {
			Connection con = dao.getConnection();
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//SQL作成
		StringBuilder sb = new StringBuilder(
				"SELECT A.product_id , A.color_num , A.color_name , A.color_photo , B.product_name ,FORMAT(B.product_price,0) AS price , C.brand_name , D.manufacturer_name , "
				+ "GROUP_CONCAT(G.pc_name) AS pc_name , SUM(I.sale_num) \r\n");
		sb.append("FROM ((((((product AS A LEFT OUTER JOIN  product_group AS B ON A.group_id = B.group_id )\r\n");
		sb.append("LEFT OUTER JOIN brand AS C ON B.brand_id = C.brand_id )\r\n");
		sb.append("LEFT OUTER JOIN manufacturer AS D ON C.manufacturer_id = D.manufacturer_id )\r\n");
		sb.append("LEFT OUTER JOIN category AS E ON B.category_id = E.category_id )\r\n");
		sb.append("LEFT OUTER JOIN color_category AS H ON A.colorCategory_id = H.colorCategory_id) \r\n");
		sb.append("LEFT OUTER JOIN  sale_detail AS I ON A.product_id = I.product_id \r\n");
		sb.append(" , color_detail AS F\r\n");
		sb.append("LEFT OUTER JOIN  color AS G ON F.pc_id = G.pc_id) \r\n");
		sb.append("WHERE A.product_id = F.product_id \r\n");

		//パーソナルカラー
		if (category == 1 || category == 2 || category == 1 ) {
			sb.append(" AND pc_name = '" + pcColor + "'");
		}

		//カテゴリー
		sb.append(" AND B.category_id = " + category);

		//ポイント？ベース？
		if (category == 1 || category == 2 || category == 3) {
			for (int i = 0; i < strColors.length; i++) {
				if (i == 0) {
					sb.append(" AND (A.colorCategory_id = " + strColors[i]);
				} else {
					sb.append(" OR A.colorCategory_id = " + strColors[i] + "\r\n");
				}
			}
			sb.append(")");
		} else {
			for (int i = 0; i < intKeys.length; i++) {

				if (intKeys[i] == 1) { //透明感
					if (i == 0) {
						sb.append(" AND (B.product_memo LIKE '%透明感%' ");
					} else {
						sb.append(" OR B.product_memo LIKE '%透明感%' ");
					}
					sb.append(" OR B.product_memo LIKE '%色ムラ%' ");
					sb.append(" OR B.product_memo LIKE '%くすみ%' ");
					sb.append(" OR B.product_memo LIKE '%血色%' ");
				} else if (intKeys[i] == 2) { //毛穴
					if (i == 0) {
						sb.append(" AND (B.product_memo LIKE '%毛穴%' ");
					} else {
						sb.append(" OR B.product_memo LIKE '%毛穴%' ");
					}
				} else if (intKeys[i] == 3) { //乾燥肌
					if (i == 0) {
						sb.append(" AND (B.product_memo LIKE '%乾燥%' ");
					} else {
						sb.append(" OR B.product_memo LIKE '%乾燥%' ");
					}
					sb.append(" OR B.product_memo LIKE '%保湿%' ");
					sb.append(" OR B.product_memo LIKE '%美容液%' ");
					sb.append(" OR B.product_memo LIKE '%しっとり%' ");
				} else if (intKeys[i] == 4) { //シワ
					if (i == 0) {
						sb.append(" AND (B.product_memo LIKE '%シワ%' ");
					} else {
						sb.append(" OR B.product_memo LIKE '%シワ%' ");
					}
					sb.append(" OR B.product_memo LIKE '%小ジワ%' ");
					sb.append(" OR B.product_memo LIKE '%シミ%' ");
				} else if (intKeys[i] == 5) { //ハリ
					if (i == 0) {
						sb.append(" AND (B.product_memo LIKE '%ハリ%' ");
					} else {
						sb.append(" OR B.product_memo LIKE '%ハリ%' ");
					}
					sb.append(" OR B.product_memo LIKE '%キメ%' ");
				} else if (intKeys[i] == 6) { //薄づき
					if (i == 0) {
						sb.append(" AND (B.product_memo LIKE '%薄づき%' ");
					} else {
						sb.append(" OR B.product_memo LIKE '%薄づき%' ");
					}
					sb.append(" OR B.product_memo LIKE '%薄付き%' ");
				} else if (intKeys[i] == 7) { //つや
					if (i == 0) {
						sb.append(" AND (B.product_memo LIKE '%つや%' ");
					} else {
						sb.append(" OR B.product_memo LIKE '%つや%' ");
					}
					sb.append(" OR B.product_memo LIKE '%ツヤ%' ");
					sb.append(" OR B.product_memo LIKE '%艶%' ");
				} else if (intKeys[i] == 8) { //マット
					if (i == 0) {
						sb.append(" AND (B.product_memo LIKE '%マット%' ");
					} else {
						sb.append(" OR B.product_memo LIKE '%マット%' ");
					}
				} else if (intKeys[i] == 9) { //カバー力
					if (i == 0) {
						sb.append(" AND (B.product_memo LIKE '%カバー%' ");
					} else {
						sb.append(" OR B.product_memo LIKE '%カバー%' ");
					}
				} else if (intKeys[i] == 10) { //シミ・そばかす・美白
					if (i == 0) {
						sb.append(" AND (B.product_memo LIKE '%シミ%' ");
					} else {
						sb.append(" OR B.product_memo LIKE '%シミ%' ");
					}
					sb.append(" OR B.product_memo LIKE '%シワ%' ");
					sb.append(" OR B.product_memo LIKE '%小ジワ%' ");
					sb.append(" OR B.product_memo LIKE '%くすみ%' ");
					sb.append(" OR B.product_memo LIKE '%美白%' ");
				} else if (intKeys[i] == 11) { //化粧崩れ・テカり
					if (i == 0) {
						sb.append(" AND (B.product_memo LIKE '%毛穴%' ");
					} else {
						sb.append(" OR B.product_memo LIKE '%毛穴%' ");
					}
					sb.append(" OR B.product_memo LIKE '%テカり%' ");
					sb.append(" OR B.product_memo LIKE '%化粧持ち%' ");
					sb.append(" OR B.product_memo LIKE '%化粧崩れ%' ");
				}

			}
			if (intKeys.length != 0) {
				sb.append(")");
			}
		}

		sb.append(" AND B.product_price <= " + (avePrice + 500));
		sb.append(" GROUP BY A.product_id \r\n");
		sb.append(" ORDER BY SUM(I.sale_num) DESC");
		sb.append(" LIMIT 10 \r\n");

		sql = new String(sb);
				System.out.println(sql);

		//SQL実行
		List<Product> list = new ArrayList<>();
		ArrayList<String> id = new ArrayList<String>();
		ArrayList<ArrayList<String>> photo = new ArrayList<ArrayList<String>>();
		int listNum = 0;

		//1ページ目
		ArrayList<String> sample = new ArrayList<String>();
		sample.add("./img/top.jpg"); //写真
		sample.add("sample"); //写真
		photo.add(sample);

		id.add("top");

		try {
			ResultSet rs;
			rs = st.executeQuery(sql);

			while (rs.next()) {
				Product p = new Product();
				p.setProductId(rs.getString("product_id"));
				p.setProductName(rs.getString("product_name"));
				p.setColorName(rs.getString("color_name"));
				p.setColorNum(rs.getString("color_num"));
				p.setOutPrice(rs.getString("price"));
				p.setPhoto(rs.getString("color_photo"));
				p.setBrandName(rs.getString("brand_name"));
				list.add(p);
			}

			rs.beforeFirst();

			while (rs.next()) {
				id.add(rs.getString("product_id"));
			}

			rs.beforeFirst();

			while (rs.next()) {
				ArrayList<String> rec = new ArrayList<String>();
				rec.add(rs.getString("color_photo"));
				rec.add(rs.getString("product_name") + " " + rs.getString("color_name") + "-"
						+ rs.getString("product_id"));
				photo.add(rec);
			}

			if (list.size() == 0) {
				page = "/reco_category.jsp";
			} else {
				page = "/reco_result.jsp";
				listNum = list.size();
			}

		} catch (SQLException e) {
			System.out.println("sql err!");
		}

		//		for (Product p : list) {
		//			System.out.println(p.getProductName());
		//		}

		//jsonに変換
		Gson gson = new Gson();
		String photojson = gson.toJson(photo);

		request.setAttribute("PHOTO", photojson);

		//JSPに移動
		page = "/reco_result.jsp";
		request.setAttribute("LISTNUM", listNum);
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
