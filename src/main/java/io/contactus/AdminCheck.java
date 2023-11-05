package io.contactus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminCheck {

	public boolean doCheck(String name, String pass) {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/contactUs";
			String username = "postgres";
			String password = "Ss25092001@@";
			Connection connection = DriverManager.getConnection(url, username, password);
			String query = "select * from admin where username = ? and password = ?";
			PreparedStatement sql = connection.prepareStatement(query);
			sql.setString(1, name);
			sql.setString(2, pass);
			ResultSet data = sql.executeQuery();

			if (data.next()) {
				return true;
			}

			connection.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}
}
