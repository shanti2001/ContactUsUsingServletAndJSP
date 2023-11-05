package io.contactus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/contactus/archive")
public class Archive extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (String) request.getParameter("requestEmail");
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/contactUs";
			String username = "postgres";
			String password = "Ss25092001@@";
			Connection connection = DriverManager.getConnection(url, username, password);
			String query = "update contactus set status=? where email =?";
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, "archive");
			prepareStatement.setString(2, email);
			prepareStatement.executeUpdate();

			connection.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		response.sendRedirect("/WebProjectContactUs/admin/contactus/requests");
	}
}
