package io.contactus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/storedata")
public class StoreData extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RequestData> activeRequests = new ArrayList<>();
		List<RequestData> archiveRequests = new ArrayList<>();

		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/contactUs";
			String username = "postgres";
			String password = "Ss25092001@@";
			Connection connection = DriverManager.getConnection(url, username, password);
			String query = "select * from contactus";
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				String name = resultSet.getString(1);
				String email = resultSet.getString(2);
				String message = resultSet.getString(3);
				String status = resultSet.getString(4);

				RequestData requests = new RequestData();
				requests.setName(name);
				requests.setEmail(email);
				requests.setMessage(message);
				requests.setStatus(status);

				if (status.equals("active")) {
					activeRequests.add(requests);
				} else {
					archiveRequests.add(requests);
				}
			}
			connection.close();

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		request.setAttribute("activeRequests", activeRequests);
		request.setAttribute("archiveRequests", archiveRequests);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/request.jsp");
		requestDispatcher.forward(request, response);
	}
}
