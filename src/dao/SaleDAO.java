package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Sale;

public class SaleDAO {

	public List<Sale> search(String customerCode) throws Exception {

		/**SELECT文（顧客番号）**/

		//リストを宣言
		List<Sale> list = new ArrayList<>();

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		Statement st = con.createStatement();

		//SQL文の作成
		StringBuilder sb = new StringBuilder(
				"SELECT A.sale_id , DATE_FORMAT(A.sale_date,'%Y/%m/%d') AS date , A.customer_code , B.sale_num , C.store_name , \r\n");
		sb.append("D.product_id , D.color_num  , D.color_name  , D.color_photo , \r\n");
		sb.append("E.product_name , F.brand_name , G.manufacturer_name \r\n");
		sb.append("FROM(((((sale AS A LEFT OUTER JOIN  sale_detail AS B ON A.sale_id = B.sale_id) \r\n");
		sb.append("LEFT OUTER JOIN store AS C ON A.store_id = C.store_id) \r\n");
		sb.append("LEFT OUTER JOIN product AS D ON B.product_id = D.product_id) \r\n");
		sb.append("LEFT OUTER JOIN product_group AS E ON D.group_id = E.group_id) \r\n");
		sb.append("LEFT OUTER JOIN brand AS F ON E.brand_id = F.brand_id) \r\n");
		sb.append("LEFT OUTER JOIN manufacturer AS G ON F.manufacturer_id = G.manufacturer_id \r\n");
		sb.append("WHERE A.customer_code = " + customerCode);
		sb.append(" ORDER BY sale_date DESC , product_name ASC;");
		String sql = new String(sb);

		//		System.out.println(sql);

		//SQL実行
		ResultSet rs;
		rs = st.executeQuery(sql);

		while (rs.next()) {
			Sale s = new Sale();
			s.setSaleId(rs.getInt("sale_id"));
			s.setOutSaleDate(rs.getString("date"));
			s.setCustomerCode(rs.getInt("customer_code"));
			s.setSaleNum(rs.getInt("sale_num"));
			s.setStoreName(rs.getString("store_name"));
			s.setProductId(rs.getString("product_id"));
			s.setColorNum(rs.getString("color_num"));
			s.setColorName(rs.getString("color_name"));
			s.setColorPhoto(rs.getString("color_photo"));
			s.setProductName(rs.getString("product_name"));
			s.setBrandName(rs.getString("brand_name"));
			s.setManufacturerName(rs.getString("manufacturer_name"));
			list.add(s);

		}

		st.close();
		con.close();

		return list;

	}

	public List<Sale> detailSearch(String saleId, String jan) throws Exception {

		/**SERECT文（モーダル用）**/

		//リストを宣言
		List<Sale> list = new ArrayList<>();

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		Statement st = con.createStatement();

		//SQL文の作成
		StringBuilder sb = new StringBuilder(
				"SELECT A.sale_id , DATE_FORMAT(A.sale_date,'%Y/%m/%d') AS date , A.customer_code , B.sale_num , C.store_name , \r\n");
		sb.append(
				"D.product_id , D.color_num  , D.color_name  , D.color_photo , FORMAT(E.product_price,0) AS price , \r\n");
		sb.append("E.product_name , F.brand_name , G.manufacturer_name \r\n");
		sb.append("FROM(((((sale AS A LEFT OUTER JOIN  sale_detail AS B ON A.sale_id = B.sale_id) \r\n");
		sb.append("LEFT OUTER JOIN store AS C ON A.store_id = C.store_id) \r\n");
		sb.append("LEFT OUTER JOIN product AS D ON B.product_id = D.product_id) \r\n");
		sb.append("LEFT OUTER JOIN product_group AS E ON D.group_id = E.group_id) \r\n");
		sb.append("LEFT OUTER JOIN brand AS F ON E.brand_id = F.brand_id) \r\n");
		sb.append("LEFT OUTER JOIN manufacturer AS G ON F.manufacturer_id = G.manufacturer_id \r\n");
		sb.append("WHERE D.product_id = '" + jan + "' AND A.sale_id = " + saleId);
		sb.append(" ORDER BY sale_date DESC , product_name ASC;");
		String sql = new String(sb);

		//				System.out.println(sql);

		//SQL実行
		ResultSet rs;
		rs = st.executeQuery(sql);

		while (rs.next()) {
			Sale s = new Sale();
			s.setSaleId(rs.getInt("sale_id"));
			s.setOutSaleDate(rs.getString("date"));
			s.setCustomerCode(rs.getInt("customer_code"));
			s.setSaleNum(rs.getInt("sale_num"));
			s.setStoreName(rs.getString("store_name"));
			s.setProductId(rs.getString("product_id"));
			s.setColorNum(rs.getString("color_num"));
			s.setColorName(rs.getString("color_name"));
			s.setColorPhoto(rs.getString("color_photo"));
			s.setProductName(rs.getString("product_name"));
			s.setBrandName(rs.getString("brand_name"));
			s.setManufacturerName(rs.getString("manufacturer_name"));
			s.setOutPrice(rs.getString("price"));
			list.add(s);

		}

		st.close();
		con.close();

		return list;

	}

	public List<Sale> supportSearch(String c_code, String date, Double store) throws Exception {

		/**SERECT文（support用）**/

		//リストを宣言
		List<Sale> list = new ArrayList<>();

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		Statement st = con.createStatement();

		//SQL文の作成
		StringBuilder sb = new StringBuilder(
				"SELECT A.sale_id , DATE_FORMAT(A.sale_date,'%Y/%m/%d') AS date ,D.product_id , D.color_num  , D.color_name  , \r\n");
		sb.append("E.product_name , F.brand_name , G.manufacturer_name \r\n");
		sb.append("FROM(((((sale AS A LEFT OUTER JOIN  sale_detail AS B ON A.sale_id = B.sale_id) \r\n");
		sb.append("LEFT OUTER JOIN store AS C ON A.store_id = C.store_id) \r\n");
		sb.append("LEFT OUTER JOIN product AS D ON B.product_id = D.product_id) \r\n");
		sb.append("LEFT OUTER JOIN product_group AS E ON D.group_id = E.group_id) \r\n");
		sb.append("LEFT OUTER JOIN brand AS F ON E.brand_id = F.brand_id) \r\n");
		sb.append("LEFT OUTER JOIN manufacturer AS G ON F.manufacturer_id = G.manufacturer_id \r\n");
		sb.append("WHERE A.customer_code = " + c_code);
		sb.append(" AND A.sale_date = '" + date + "'");
		sb.append(" AND A.store_id = " + store);
		sb.append(" ORDER BY sale_date DESC , product_name ASC;");
		String sql = new String(sb);

		System.out.println(sql);

		//SQL実行
		ResultSet rs;
		rs = st.executeQuery(sql);

		while (rs.next()) {
			Sale s = new Sale();
			s.setSaleId(rs.getInt("sale_id"));
			s.setOutSaleDate(rs.getString("date"));
			s.setProductId(rs.getString("product_id"));
			s.setColorNum(rs.getString("color_num"));
			s.setColorName(rs.getString("color_name"));
			s.setProductName(rs.getString("product_name"));
			list.add(s);

		}

		st.close();
		con.close();

		return list;

	}

	public int price(String customerCode) throws Exception {

		/**購入金額の平均**/

		int price = 0;
		boolean flg = true;

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		Statement st = con.createStatement();

		//saleの件数
		ArrayList<String> nums = new ArrayList<String>();

		//SQL作成
		StringBuilder sb1 = new StringBuilder("SELECT * \r\n");
		sb1.append("FROM sale_detail AS A LEFT OUTER JOIN sale AS B ON A.sale_id = B.sale_id \r\n");
		sb1.append("WHERE customer_code = " + customerCode);
		String sql1 = new String(sb1);
		System.out.println(sql1);

		//SQL実行
		ResultSet rs;
		rs = st.executeQuery(sql1);

		if (rs.next()) {
			nums.add(getString("customer_code"));
		}

		if (nums.size() < 10) {
			flg = false;
			price = 100000;
			System.out.println("平均なし");
		}

		if (flg) {
			//SQL作成
			StringBuilder sb2 = new StringBuilder("SELECT AVG(product_price) AS price \r\n");
			sb2.append("FROM ((sale_detail AS A LEFT OUTER JOIN sale AS B ON A.sale_id = B.sale_id) \r\n");
			sb2.append("LEFT OUTER JOIN product AS C ON A.product_id = C.product_id) \r\n");
			sb2.append("LEFT OUTER JOIN product_group AS D ON C.group_id = D.group_id \r\n");
			sb2.append("WHERE customer_code = " + customerCode);
			String sql2 = new String(sb2);
			System.out.println(sql2);

			//SQL実行
			rs = st.executeQuery(sql2);

			if (rs.next()) {
				price = rs.getInt("price");
				System.out.println(price);
			}
		}

		return price;
	}

	private String getString(String string) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public int saleInsert(Sale sale) throws Exception {

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		PreparedStatement st;
		ResultSet rs;
		boolean flg = true;
		String sql = "";

		/**レコードが既に無いかの確認**/
		//sql作成・実行
		sql = "SELECT sale_id FROM sale WHERE customer_code = ? AND sale_date = ?;";

		System.out.println(sale.getCustomerCode());
		System.out.println(sale.getOutSaleDate());

		st = con.prepareStatement(sql);
		st.setInt(1, sale.getCustomerCode());
		st.setDate(2, sale.getInSaleDate());
		System.out.println(sql);

		rs = st.executeQuery();
		int sale_id = 0;
		if (rs.next()) {
			System.out.println("レコードが一致しました。");
			sale_id = rs.getInt("sale_id");
			flg = false;
		}

		/**レコードを追加**/
		if (flg) {
			//SQL作成・実行
			sql = "INSERT INTO sale (sale_id , customer_code , store_id , sale_date) "
					+ "VALUES (NULL,?,?,?);";
			st = con.prepareStatement(sql);
			st.setInt(1, sale.getCustomerCode());
			st.setInt(2, sale.getStoreId());
			st.setDate(3, sale.getInSaleDate());
			System.out.println(sql);

			//確認
			int line = st.executeUpdate();
			if (line > 0) {
				System.out.println("追加に成功しました。");
				rs = st.executeQuery("SELECT LAST_INSERT_ID() AS LAST");
				if (rs != null && rs.next()) {
					sale_id = rs.getInt("LAST");
				}
			}
		}

		st.close();
		con.close();

		return sale_id;

	}

	public int sale_detailInsert(Sale sale, int sale_id) throws Exception {

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		PreparedStatement st;
		int line = 0;
		String sql = "";

		//SQL作成・実行
		sql = "INSERT INTO sale_detail (sale_id , product_id , sale_num) VALUES (?,?,?);";
		st = con.prepareStatement(sql);
		st.setInt(1, sale_id);
		st.setString(2, sale.getProductId());
		st.setInt(3, sale.getSaleNum());
		//		System.out.println(sql);

		//変更した列の番号を記録
		line = st.executeUpdate();
		if (line > 0) {
			System.out.println("追加に成功しました。");
		}

		st.close();
		con.close();

		return line;

	}

	public int update(Sale sale, String bId) throws Exception {

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		PreparedStatement st;
		ResultSet rs;
		boolean flg = true;
		int line = 0;
		int saleId = 0;
		String sql = "";

		//SQL作成・実行
		sql = "SELECT * FROM sale WHERE sale_date = ? AND customer_code = ?;";
		st = con.prepareStatement(sql);
		st.setDate(1, sale.getInSaleDate());
		st.setInt(2, sale.getCustomerCode());
		//		System.out.println(sql);

		rs = st.executeQuery();
		if (rs.next()) {
			System.out.println("レコードが一致しました。");
			saleId = rs.getInt("sale_id");
			flg = false;
		}

		/**レコードを追加**/
		if (flg) {
			sql = "INSERT INTO sale (sale_id , customer_code , store_id , sale_date) "
					+ "VALUES (NULL,?,?,?);";
			st = con.prepareStatement(sql);
			st.setInt(1, sale.getCustomerCode());
			st.setInt(2, sale.getStoreId());
			st.setDate(3, sale.getInSaleDate());
			System.out.println(sql);

			//確認
			line = st.executeUpdate();
			if (line > 0) {
				System.out.println("saleテーブルへの追加に成功しました。");
				rs = st.executeQuery("SELECT LAST_INSERT_ID() AS LAST");
				if (rs != null && rs.next()) {
					saleId = rs.getInt("LAST");
				}
			}

		}

		//SQL作成・実行(detail)
		sql = "UPDATE sale_detail SET product_id = ? , sale_num = ? WHERE product_id = ? AND sale_id = ?;";
		st = con.prepareStatement(sql);
		st.setString(1, sale.getProductId());
		st.setInt(2, sale.getSaleNum());
		st.setString(3, bId);
		st.setInt(4, saleId);

		//変更した列の番号を記録
		line = st.executeUpdate();
		if (line > 0) {
			System.out.println("sale_detailテーブルの変更が完了しました。");
		}

		st.close();
		con.close();

		return line;

	}
}
