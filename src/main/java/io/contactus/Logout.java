package io.contactus;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/contactus/logout")
public class Logout extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.removeAttribute("password");
		session.invalidate();
		response.setHeader("Cache-Contrlo", "no-cache,no-store,must-revalidate");
		response.sendRedirect("/WebProjectContactUs/admin/login");
	}
}
