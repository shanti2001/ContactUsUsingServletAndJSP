package io.contactus;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({ "/admin/contactus/requests", "/contactus/requests" })
public class Request extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminCheck adminCheck = new AdminCheck();

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		if (username != null && password != null && adminCheck.doCheck(username, password)) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/storedata");
			requestDispatcher.forward(request, response);
		} else {
			session.removeAttribute("username");
			session.removeAttribute("password");
			session.invalidate();
			response.setHeader("Cache-Contrlo", "no-cache,no-store,must-revalidate");
			response.sendRedirect("/WebProjectContactUs/admin/login");
		}
	}
}
