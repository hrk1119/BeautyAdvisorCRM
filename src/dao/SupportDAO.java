package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Support;

public class SupportDAO {

	public List<Support> search(String customerCode) throws Exception {

		/**SELECT文**/

		//リストを宣言
		List<Support> list = new ArrayList<>();

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		Statement st = con.createStatement();

		//SQL文の作成
		StringBuilder sb = new StringBuilder(
				"SELECT A.support_id , DATE_FORMAT(A.support_date,'%Y/%m/%d') AS date , C.store_name , B.employee_name , A.support_memo \r\n");
		sb.append("FROM (support AS A LEFT OUTER JOIN employee AS B ON A.employee_id = B.employee_id) \r\n");
		sb.append("LEFT OUTER JOIN store AS C ON B.store_id = C.store_id \r\n");
		sb.append("WHERE customer_code = " + customerCode + " \r\n");
		sb.append("ORDER BY support_date DESC;");
		String sql = new String(sb);
		//		System.out.println(sql);

		//SQL実行
		ResultSet rs;
		rs = st.executeQuery(sql);

		while (rs.next()) {
			Support	s = new Support();
			s.setSupportId(rs.getInt("support_id"));
			s.setOutDate(rs.getString("date"));
			s.setStoreName(rs.getString("store_name"));
			s.setEmployeeName(rs.getString("employee_name"));
			s.setMemo(rs.getString("support_memo"));
			list.add(s);
		}

		st.close();
		con.close();

		return list;

	}

	public List<Support> search_suppId(String suppId) throws Exception {

		/**SELECT文(supportId)**/

		//リストを宣言
		List<Support> list = new ArrayList<>();

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		Statement st = con.createStatement();

		//SQL文の作成
		StringBuilder sb = new StringBuilder(
				"SELECT A.support_id , DATE_FORMAT(A.support_date,'%Y/%m/%d') AS date , c.store_id , C.store_name , B.employee_name , A.support_memo \r\n");
		sb.append("FROM (support AS A LEFT OUTER JOIN employee AS B ON A.employee_id = B.employee_id) \r\n");
		sb.append("LEFT OUTER JOIN store AS C ON B.store_id = C.store_id \r\n");
		sb.append("WHERE support_id = " + suppId + " \r\n");
		sb.append("ORDER BY support_date DESC;");
		String sql = new String(sb);
		//		System.out.println(sql);

		//SQL実行
		ResultSet rs;
		rs = st.executeQuery(sql);

		while (rs.next()) {
			Support s = new Support();
			s.setSupportId(rs.getInt("support_id"));
			s.setStoreId(rs.getInt("store_id"));
			s.setOutDate(rs.getString("date"));
			s.setStoreName(rs.getString("store_name"));
			s.setEmployeeName(rs.getString("employee_name"));
			s.setMemo(rs.getString("support_memo"));
			list.add(s);
		}

		st.close();
		con.close();

		return list;

	}

	public int insert(Support support) throws Exception {

		/**INSERT文**/

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		PreparedStatement st;
		int line = 0;
		String sql = "";

		//SQL作成・実行
		sql = "INSERT INTO support (support_id , customer_code , employee_id , support_date , support_memo) "
				+ "VALUES (NULL,?,?,?,?);";
		st = con.prepareStatement(sql);
		st.setInt(1, support.getCustomerCode());
		st.setInt(2, support.getEmployeeId());
		st.setDate(3, support.getInDate());
		st.setString(4, support.getMemo());
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

	public int update(Support support) throws Exception {

		/**UPDATE文**/

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		PreparedStatement st;
		int line = 0;
		String sql = "";

		//SQL作成・実行
		sql = "UPDATE support SET employee_id = ? , support_date = ? , support_memo = ? WHERE support_id = ?;";
		st = con.prepareStatement(sql);
		st.setInt(1, support.getEmployeeId());
		st.setDate(2, support.getInDate());
		st.setString(3, support.getMemo());
		st.setInt(4, support.getsupportId());
		//		System.out.println(sql);

		//変更した列の番号を記録
		line = st.executeUpdate();
		if (line > 0) {
			System.out.println("変更しました。");
		}

		st.close();
		con.close();

		return line;

	}

	public int delete(int id) throws Exception {

		/**DELETE文**/

		//インスタンス化
		DAO dao = new DAO();
		Connection con = dao.getConnection();
		PreparedStatement st;
		int line = 0;
		String sql = "";

		//SQL作成・実行
		sql = "DELETE FROM support WHERE id = ?;";
		st = con.prepareStatement(sql);
		st.setInt(1, id);
		//		System.out.println(sql);

		//変更した列の番号を記録
		line = st.executeUpdate();
		if (line > 0) {
			System.out.println("削除しました。");
		}

		st.close();
		con.close();

		return line;

	}
}
