package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Customer;

public class CustomerDAO {

	public List<Customer> search(String customerCode) throws Exception {

		/**SELECT文**/

		//リストを宣言
		List<Customer> list = new ArrayList<>();

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		Statement st = con.createStatement();

		//SQL文の作成
		StringBuilder sb = new StringBuilder("SELECT customer_code , customer_name1 , customer_name2 , DATE_FORMAT(customer_birthday,'%Y/%m/%d') AS birthday , \r\n");
		sb.append("customer_gender , customer_tel , customer_mail , customer_postalcode , customer_address , customer_work , customer_color \r\n");
		sb.append("FROM customer WHERE customer_code =" + customerCode);
		String sql = new String(sb);

		System.out.println(sql);

		//SQL実行
		ResultSet rs;
		rs = st.executeQuery(sql);

		while (rs.next()) {
			Customer c = new Customer();
			c.setCustomerCode(rs.getInt("customer_code"));
			c.setCustomerName1(rs.getString("customer_name1"));
			c.setCustomerName2(rs.getString("customer_name2"));
			c.setOutBirthday(rs.getString("birthday"));
			c.setGender(rs.getString("customer_gender"));
			c.setTel(rs.getString("customer_tel"));
			c.setMail(rs.getString("customer_mail"));
			c.setPostalcode(rs.getString("customer_postalcode"));
			c.setAddress(rs.getString("customer_address"));
			c.setWork(rs.getString("customer_work"));
			c.setColor(rs.getString("customer_color"));
			list.add(c);
		}

		st.close();
		con.close();

		return list;

	}

	public int insert(Customer customer) throws Exception {

		/**INSERT文**/

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		PreparedStatement st;
		int line = 0;
		String sql = "";
		String color = "";

		//SQL作成・実行
		sql = "INSERT INTO customer (customer_code, customer_name1, customer_name2, customer_birthday, customer_gender, customer_tel, customer_mail, "
				+ "customer_postalcode, customer_address, customer_work, customer_color) \r\n" +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		st = con.prepareStatement(sql);
		st.setInt(1, customer.getCustomerCode());
		st.setString(2, customer.getCustomerName1());
		st.setString(3, customer.getCustomerName2());
		st.setDate(4, customer.getInBirthday());
		st.setString(5, customer.getGender());
		st.setString(6, customer.getTel());
		st.setString(7, customer.getMail());
		st.setString(8, customer.getPostalcode());
		st.setString(9, customer.getAddress());
		st.setString(10, customer.getWork());
		st.setString(11, color);
		//				System.out.println(sql);

		//変更した列の番号を記録
		line = st.executeUpdate();
		if (line > 0) {
			System.out.println("追加に成功しました。");
		}

		st.close();
		con.close();

		return line;
	}

	public int edit(Customer customer) throws Exception {

		/**UPDATE文**/

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		PreparedStatement st;
		int line = 0;
		String sql = "";

		//SQL作成・実行
		sql = "UPDATE customer SET customer_code = ?, customer_name1 = ?, customer_name2 = ?, customer_birthday = ?, customer_gender =?, \r\n"
				+ "customer_tel = ?, customer_mail = ?, customer_postalcode = ?, customer_address = ?, customer_work = ? \r\n"
				+ "WHERE customer_code = ?;";
		st = con.prepareStatement(sql);
		st.setInt(1, customer.getCustomerCode());
		st.setString(2, customer.getCustomerName1());
		st.setString(3, customer.getCustomerName2());
		st.setDate(4, customer.getInBirthday());
		st.setString(5, customer.getGender());
		st.setString(6, customer.getTel());
		st.setString(7, customer.getMail());
		st.setString(8, customer.getPostalcode());
		st.setString(9, customer.getAddress());
		st.setString(10, customer.getWork());
		st.setInt(11, customer.getCustomerCode());
		//				System.out.println(sql);

		//変更した列の番号を記録
		line = st.executeUpdate();
		if (line > 0) {
			System.out.println("変更が完了しました。");
		}

		st.close();
		con.close();

		return line;
	}

	public ArrayList<String> mail(ArrayList<String> checks) throws Exception {

		/**SELECT文（メール）**/

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		Statement st = con.createStatement();
		int i = 0;

		//SQL文の作成
		StringBuilder sb = new StringBuilder("SELECT customer_mail FROM customer\r\n" +
				"WHERE customer_code = " + checks.get(i));

		for (i = 1; i < checks.size(); i++) {
			sb.append(" OR customer_code = " + checks.get(i));
		}

		String sql = new String(sb);
		//		System.out.println(sql);

		//リストを宣言
		ArrayList<String> codes = new ArrayList<String>();

		//SQL実行
		ResultSet rs;
		rs = st.executeQuery(sql);

		while (rs.next()) {
			codes.add(rs.getString("customer_mail"));
		}

		st.close();
		con.close();

		return codes;
	}

}
