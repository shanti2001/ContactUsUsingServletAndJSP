package io.contactus;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/login")
public class LogIn extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String password = (String) session.getAttribute("password");
		if (password == null) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/logIn.html");
			requestDispatcher.forward(request, response);
		} else {
			response.sendRedirect("/WebProjectContactUs/admin/contactus/requests");
		}
	}
}
