<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% String s=(String)request.getAttribute("error");
     if(s!=null) {
     out.println(s); }%>
     <% String s1=(String)request.getAttribute("msg");
     if(s1!=null) {
     out.println(s1); }%>
     
<form action="LoginServlet" method="post">
<table>
<tr><td>User ID:</td> <td> <input type="text" name="uid"></td></tr>
<tr><td>Password:</td><td><input type="password" name="pass" ></td></tr>
<tr><td><input type="submit" value="Login" ></td></tr>
</table>

</form>

</body>
</html>