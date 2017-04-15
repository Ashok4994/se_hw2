<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="RegServlet" method="post">
<% String err=(String) request.getAttribute("error");
 if(err!=null) {
	 out.println(err);
 }
      %>
      
<table>
 <tr><td>First name:</td> <td> <input type="text" name="fname"></td></tr> 
 <tr><td>Last name: </td> <td> <input type="text" name="lname" > </td></tr>
 <tr><td>Email id: </td> <td><input type="text" name="eid" ></td></tr> 
 <tr><td>User Id:</td> <td> <input type="text" name="uid" > </td></tr>
 <tr><td>Password: </td> <td><input type="password" name="pwd" > </td></tr>
 <tr><td><input type="submit" value="submit" ></td></tr>
 </table>
 </form>
</body>
</html>