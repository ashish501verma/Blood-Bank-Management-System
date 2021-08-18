<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.sql.*" %>
<%
    String user = request.getParameter("uname");
    String dobirth = request.getParameter("birthdate");
    String age = request.getParameter("age");
    String bgroup = request.getParameter("bgroup");
    String address = request.getParameter("address");
    String mnumber = request.getParameter("mnumber");
    String email = request.getParameter("email");
    String gender = request.getParameter("sex");
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bbms",
            "root", "");
    Statement st = con.createStatement();
    //ResultSet rs;
    int i = st.executeUpdate("insert into donor(uname, birthdate , age, bgroup,address,mnumber,email,sex, regdate) values('" + user + "','" + dobirth + "','" + age + "','" + bgroup + "','" + address + "','" + mnumber + "','" + email + "','" + gender + "', CURDATE())");
    if (i > 0) {
        //session.setAttribute("userid", user);
        response.sendRedirect("indexwelcome.jsp");
       // out.print("Registration Successfull!"+"<a href='index.jsp'>Go to Login</a>");
    } else {
        response.sendRedirect("index.jsp");
    }
%>
