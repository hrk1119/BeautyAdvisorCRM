package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

	public Connection getConnection() {

		/**DBの接続**/

		//URLをセットする
		String url = "jdbc:mysql://localhost:3306/cosme?characterEncoding=utf8";
		Connection con = null;
		Statement st = null;

		//Class.forName
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("err1");
		}

		try {
			con = DriverManager.getConnection(url, "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("err2");
		}

		return con;
	}
}
