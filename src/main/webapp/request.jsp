<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*, io.contactus.RequestData"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request of ContactUs</title>
</head>
<body>
	<div>
		<div>
			<%
			List<RequestData> activeRequests = (List<RequestData>) request.getAttribute("activeRequests");
			List<RequestData> archiveRequests = (List<RequestData>) request.getAttribute("archiveRequests");
			%>
			<table border="1">
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Message</th>
					<th>Status</th>
				</tr>
				<%
				for (RequestData data : activeRequests) {
				%>
				<tr>
					<td><%=data.getName()%></td>
					<td><%=data.getEmail()%></td>
					<td><%=data.getMessage()%></td>
					<td><%=data.getStatus()%></td>
					<td><button>
							<a href="archive?requestEmail=<%=data.getEmail()%>">Archive</a>
						</button></td>
				</tr>

				<%
				}
				%>
			</table>
		</div>
		<br>
		<div>
			<%
			if (archiveRequests.size() > 0) {
			%>
			<table border="1">
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Message</th>
					<th>Status</th>
				</tr>
				<%
				for (RequestData data : archiveRequests) {
				%>
				<tr>
					<td><%=data.getName()%></td>
					<td><%=data.getEmail()%></td>
					<td><%=data.getMessage()%></td>
					<td><%=data.getStatus()%></td>
					<td><button>
							<a href="active?requestEmail=<%=data.getEmail()%>">Active</a>
						</button></td>
				</tr>
				<%
				}
				%>
			</table>
			<%
			}
			%>
		</div>
		<br>
		<br>
		<div>
			<button>
				<a href="logout">Logout</a>
			</button>
		</div>
	</div>






</body>
</html>