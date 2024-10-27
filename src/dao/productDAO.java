package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Product;

public class productDAO {

	/**JANコードで検索する**/
	public List<Product> Search(String productId) throws Exception {

		//リストを宣言
		List<Product> list = new ArrayList<>();

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		Statement st = con.createStatement();

		/**1.(JANコード→商品詳細)**/
		//SQL作成
		StringBuilder sb = new StringBuilder(
				"SELECT A.product_id , A.color_num , A.color_name , A.color_photo , GROUP_CONCAT(G.pc_name) AS pc_name , A.product_situation , \r\n");
		sb.append(
				"B.group_id , B.product_name , FORMAT(B.product_price,0) AS price , DATE_FORMAT(B.product_date,'%Y/%m/%d') AS date , \r\n");
		sb.append("B.product_url , B.group_photo , B.product_memo , C.brand_name , D.manufacturer_name , \r\n");
		sb.append("E.category_name , E.category_id , H.colorCategory_name \r\n");
		//FROM
		sb.append("FROM ((((product AS A LEFT OUTER JOIN  product_group AS B ON A.group_id = B.group_id ) \r\n");
		sb.append("LEFT OUTER JOIN brand AS C ON B.brand_id = C.brand_id ) \r\n");
		sb.append("LEFT OUTER JOIN manufacturer AS D ON C.manufacturer_id = D.manufacturer_id ) \r\n");
		sb.append("LEFT OUTER JOIN category AS E ON B.category_id = E.category_id ) ");
		sb.append("LEFT OUTER JOIN color_category AS H ON A.colorCategory_id = H.colorCategory_id \r\n");
		sb.append(", color_detail AS F \r\n");
		sb.append("LEFT OUTER JOIN  color AS G ON F.pc_id = G.pc_id \r\n");
		//WHERE
		sb.append("WHERE A.product_id = F.product_id AND A.product_id = '" + productId + "' \r\n");
		sb.append("GROUP BY A.product_id \r\n");
		sb.append("ORDER BY D.manufacturer_name ASC , C.brand_name ASC , B.product_name ASC , A.color_name ASC;");

		String sql = new String(sb);
		System.out.println(sql);

		//SQL実行
		ResultSet rs;
		ResultSet sub_rs;
		rs = st.executeQuery(sql);

		while (rs.next()) {
			Product p = new Product();
			p.setProductId(rs.getString("product_id"));
			p.setColorNum(rs.getString("color_num"));
			p.setColorName(rs.getString("color_name"));
			p.setPhoto(rs.getString("color_photo"));
			p.setSituation(rs.getString("product_situation"));

			p.setGroupId(rs.getInt("group_id"));
			p.setProductName(rs.getString("product_name"));
			p.setOutPrice(rs.getString("price"));
			p.setOutDate(rs.getString("date"));
			p.setMemo(rs.getString("product_memo"));
			p.setUrl(rs.getString("product_url"));
			p.setGroupPhoto(rs.getString("group_photo"));
			p.setBrandName(rs.getString("brand_name"));
			p.setManufacturerName(rs.getString("manufacturer_name"));
			p.setCategoryName(rs.getString("category_name"));
			p.setPcName1(rs.getString("pc_name"));
			p.setColorCategoryName(rs.getString("colorCategory_name"));

			list.add(p);
		}

		return list;

	}

	/**商品ごとのカラー詳細**/
	public List<Product> Color(String productName, String Brand) throws Exception {

		//リストを宣言
		List<Product> list = new ArrayList<>();

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		Statement st = con.createStatement();

		//SQL作成
		StringBuilder sb = new StringBuilder("SELECT A.product_id , B.product_name , A.color_name , A.color_num , A.color_photo , A.product_situation , \r\n");
		sb.append("GROUP_CONCAT(D.pc_name ORDER BY D.pc_name) AS pc_name \r\n");
		//FROM
		sb.append("FROM product AS A INNER JOIN product_group  AS B ON A.group_id = B.group_id \r\n");
		sb.append("LEFT OUTER JOIN color_detail AS C ON A.product_id = C.product_id \r\n");
		sb.append("LEFT OUTER JOIN color AS D ON C.pc_id = D.pc_id \r\n");
		sb.append("LEFT OUTER JOIN brand AS E ON B.brand_id = E.brand_id \r\n");
		//WHERE
		sb.append("WHERE product_name = '" + productName + "' AND brand_name = '" + Brand + "' \r\n");
		sb.append("GROUP BY A.product_id \r\n");
		sb.append("ORDER BY A.color_name ASC;");
		String sql = new String(sb);
		//		System.out.println(sql);

		//SQL実行
		ResultSet rs;
		rs = st.executeQuery(sql);

		while (rs.next()) {
			Product p = new Product();
			p.setProductId(rs.getString("product_id"));
			p.setProductName(rs.getString("product_name"));
			p.setColorNum(rs.getString("color_num"));
			p.setColorName(rs.getString("color_name"));
			p.setPhoto(rs.getString("color_photo"));
			p.setSituation(rs.getString("product_situation"));
			p.setPcName1(rs.getString("pc_name"));

			list.add(p);
		}

		return list;

	}

	/**INSERT文**/
	public int productGroupInsert(Product product) throws Exception {

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		PreparedStatement st = null;
		ResultSet rs;
		boolean flg = true;
		String sql = "";
		int group_id = 0;

		//		System.out.println(product.getProductName());
		//		System.out.println(product.getBrandId());

		/**レコードが既に無いかの確認**/
		//sql作成・実行
		sql = "SELECT group_id FROM product_group WHERE product_name = ? AND brand_id = ?;";

		st = con.prepareStatement(sql);
		st.setString(1, product.getProductName());
		st.setInt(2, product.getBrandId());
		//		System.out.println(sql);

		rs = st.executeQuery();
		if (rs.next()) {
			System.out.println("レコードが一致しました。");
			group_id = rs.getInt("group_id");
			flg = false;
		}

		/**レコードを追加**/
		if (flg) {
			//SQL作成・実行
			sql = "INSERT INTO product_group VALUES (NULL,?,?,?,?,?,?,?,?);";
			st = con.prepareStatement(sql);
			st.setString(1, product.getProductName());
			st.setInt(2, product.getInPrice());
			st.setDate(3, product.getInDate());
			st.setString(4, product.getMemo());
			st.setString(5, product.getUrl());
			st.setString(6, product.getGroupPhoto());
			st.setInt(7, product.getCategoryId());
			st.setInt(8, product.getBrandId());
			//			System.out.println(sql);

			//確認
			int line = st.executeUpdate();
			if (line > 0) {
				System.out.println("product_groupテーブルへの追加に成功しました。");
				rs = st.executeQuery("SELECT LAST_INSERT_ID() AS LAST");
				if (rs != null && rs.next()) {
					group_id = rs.getInt("LAST");
				}
			}
		}

		st.close();
		con.close();

		return group_id;

	}

	public int productInsert(Product product, int group_id, int[] pcColors) throws Exception {

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		PreparedStatement st;
		int line = 0;
		String sql = "";

		//SQL作成・実行
		sql = "INSERT INTO product VALUES (?,?,?,?,?,?,?);";
		st = con.prepareStatement(sql);
		st.setString(1, product.getProductId());
		st.setString(2, product.getColorNum());
		st.setString(3, product.getColorName());
//		st.setString(4, product.getPhoto());
		st.setString(4, "./img/products/");
		st.setString(5, "販売中");
		st.setInt(6, group_id);
		st.setInt(7, product.getColorCategoryId());

		//		System.out.println(sql);

		//変更した列の番号を記録
		line = st.executeUpdate();
		if (line > 0) {
			System.out.println("productテーブルへの追加に成功しました。");
		}

		/**パーソナルカラー**/
		for (int i = 0; i < pcColors.length; i++) {
			sql = "INSERT INTO color_detail VALUES (?,?);";
			st = con.prepareStatement(sql);
			st.setString(1, product.getProductId());
			st.setInt(2, pcColors[i]);

			line = st.executeUpdate();
			if (line > 0) {
				System.out.println("color_detailテーブルへの追加に成功しました。");
			}

		}

		st.close();
		con.close();

		return line;

	}

	/**UPDATE文**/
	public int productGroupUpdate(Product product) throws Exception {

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		PreparedStatement st = null;
		ResultSet rs;
		boolean flg = true;
		String sql = "";
		int group_id = 0;

		//SQL作成・実行
		sql = "UPDATE product_group SET product_name = ?,  product_price = ?,  product_date = ?,  product_memo =?, \r\n"
				+ " product_url = ?, group_photo = ?, category_id = ?, brand_id = ? \r\n"
				+ "WHERE product_name = ? AND brand_id = ?;";
		st = con.prepareStatement(sql);
		st.setString(1, product.getProductName());
		st.setInt(2, product.getInPrice());
		st.setDate(3, product.getInDate());
		st.setString(4, product.getMemo());
		st.setString(5, product.getUrl());
		st.setString(6, product.getGroupPhoto());
		st.setInt(7, product.getCategoryId());
		st.setInt(8, product.getBrandId());
		st.setString(9, product.getProductName());
		st.setString(10, product.getProductName());
		//		System.out.println(sql);

		//確認
		int line = st.executeUpdate();
		if (line > 0) {
			System.out.println("product_groupテーブルの変更が完了しました。");
			rs = st.executeQuery("SELECT LAST_INSERT_ID() AS LAST");
			if (rs != null && rs.next()) {
				group_id = rs.getInt("LAST");
			}
		}

		st.close();
		con.close();

		return group_id;

	}

	public int productUpdate(Product product, int group_id, int[] pcColors) throws Exception {

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		PreparedStatement st;
		int line = 0;
		String sql = "";

		//SQL作成・実行
		sql = "UPDATE product SET product_id = ?,  color_num = ?,  color_name = ?,  photo = ?, \r\n"
				+ " product_situation = ?, colorCategory_id = ? \r\n"
				+ "WHERE product_id = ?;";
		st = con.prepareStatement(sql);
		st.setString(1, product.getProductId());
		st.setString(2, product.getColorNum());
		st.setString(3, product.getColorName());
		st.setString(4, product.getPhoto());
		st.setString(5, "販売中");
		st.setInt(6, product.getColorCategoryId());
		st.setString(7, product.getProductId());

		//		System.out.println(sql);

		//変更した列の番号を記録
		line = st.executeUpdate();
		if (line > 0) {
			System.out.println("productテーブルの変更が完了しました。");
		}

		//後で編集する
		//		/**パーソナルカラー**/
		//		for (int i = 0; i < pcColors.length; i++) {
		//			sql = "INSERT INTO color_detail VALUES (?,?);";
		//			st = con.prepareStatement(sql);
		//			st.setString(1, product.getProductId());
		//			st.setInt(2, pcColors[i]);
		//
		//			line = st.executeUpdate();
		//			if (line > 0) {
		//				System.out.println("color_detailテーブルへの追加に成功しました。");
		//			}
		//
		//		}

		st.close();
		con.close();

		return line;

	}

}
