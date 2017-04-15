<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import ="javax.sql.*" %>
<%@page import ="java.sql.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Hello 
<%=session.getAttribute("fname") %>
<%=session.getAttribute("lname") %>
<% Integer id=(Integer)session.getAttribute("user_id"); %>

<div style="position: absolute; top: 0; right: 0;"><a href="LogoutServlet"> <input type="button" value="Logout"></a></div>
<br>
<br>
<form method="post" action="UploadFile" enctype="multipart/form-data">
            <center>
                <table border="1" width="25%" cellpadding="5">
                    <thead> 
                            <th colspan="3">Upload File</th>        
                    </thead>
                    <tbody>
                        <tr>    
                            <td>Description : </td>
                            <td><input type="text" name="title" size="30"></td>
                        </tr>
                        <tr>
                            <td>Choose File : </td>
                            <td><input type="file" name="file_uploaded" /></td>
                        </tr>
                        <tr>
                            <td colspan="3"><input type="submit" value="Upload"></td>
                        </tr>
                    </tbody>             
                </table>
            </center>
        </form>
        
        <br><br>
        
        <table border="1" width="25%" cellpadding="5">
            <thead> 
               <th colspan="3">Uploaded Files</th>        
            </thead>
                <tbody>
                    <tr>
                        <td><center><b>Type</b></center><td><center><b>Title</b></center></td><td><center><b>File</b></center></td>
                    </tr>
                    
                    <%
                        try
                        {
                                //Db_Connection dbconn=new Db_Connection();
                                Class.forName("com.mysql.jdbc.Driver");
                                 String TechWorld3g_2 = "jdbc:mysql://localhost:3306/test";
                                Connection myConnection = DriverManager.getConnection(TechWorld3g_2,"root","root");
            
                               // Connection myconnection= dbconn.Connection();

                                String sqlString = "SELECT * FROM fil where userid=+"+id+"";
                                Statement myStatement = myConnection.createStatement();
                                ResultSet rs=myStatement.executeQuery(sqlString);
                                
                                if(!rs.isBeforeFirst())
                                {
                                    %>
                                        <tr>
                                        <td colspan="3"><center><%out.print("No Files!"); %></center></td>
                                        </tr>
                                    <%
                                }    
                                
                                while(rs.next())
                                {   
                            %>
                                    <tr>
                                        <td><center><%if(rs.getString("type").equals("audio/mp3")) {
                                        	out.print("Audio file"); }
                                        	else if(rs.getString("type").equals("video/mp4")){
                                        	out.print("Video file");
                                        	}else if(rs.getString("type").equals("application/pdf")){
                                        	out.print("PDF file");
                                        	}else if(rs.getString("type").equals("image/jpeg") || rs.getString("type").equals("image/png")){
                                        	out.print("Photo");
                                        	}else if(rs.getString("type").equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
                                        	out.print("EXCEL file");
                                        	} else {
                                        	out.print("Unknown");
                                        	} %></center></td>
                                        <td><center><%out.print(rs.getString("title")); %></center></td>
                                        <td><center><a href="view_file.jsp?id=<%out.print(rs.getString("id"));%>" target="_blank">View</a></center></td>
                                    </tr>
                            <%
                                }
                            %>
                            
                </tbody> 
        </table>
                            
                            <%
                                rs.close();
                                myStatement.close();
                                myConnection.close();
                        }catch(Exception e){e.printStackTrace();}    
                        
                    %>
</body>
</html>