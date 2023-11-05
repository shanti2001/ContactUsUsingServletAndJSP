package io.contactus;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/contactus")
public class ContactUs extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rs = request.getRequestDispatcher("contactUs.html");
		rs.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String message = request.getParameter("message");
		String status = "active";
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/contactUs";
			String username = "postgres";
			String password = "Ss25092001@@";
			Connection connection = DriverManager.getConnection(url, username, password);
			String query = "insert into contactus values(?,?,?,?)";
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, name);
			prepareStatement.setString(2, email);
			prepareStatement.setString(3, message);
			prepareStatement.setString(4, status);
			prepareStatement.executeUpdate();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<div style='width:100%'>");
			out.println("<h2 style='color:green;text-align:center'>Your response upload successfully</h2>");
			out.println("</div>");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("contactUs.html");
			requestDispatcher.include(request, response);
			
			connection.close();
			

		} catch (Exception exception) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<div style='width:100%'>");
			out.println("<h2 style='color:green;text-align:center'>Your already send a request</h2>");
			out.println("</div>");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("contactUs.html");
			requestDispatcher.include(request, response);
		}
		
	}
}
