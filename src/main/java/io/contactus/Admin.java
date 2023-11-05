package io.contactus;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({ "/admin/admincontactus", "/admincontactus" })
public class Admin extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("username", request.getParameter("username"));
		session.setAttribute("password", request.getParameter("password"));
		response.sendRedirect("/WebProjectContactUs/admin/contactus/requests");
	}
}
