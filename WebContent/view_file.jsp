<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv
        ="Content-Type" content="text/html; charset=UTF-8">
        <title>View File</title>
    </head>
    <body>
        
        <%  
            String id=(request.getParameter("id"));
            
            Blob file = null;
            byte[] fileData = null ;
            String type=null;

            try
            {    
               
            Class.forName("com.mysql.jdbc.Driver");
               String TechWorld3g_2 = "jdbc:mysql://localhost:3306/test";
               Connection conn = DriverManager.getConnection(TechWorld3g_2,"root","root");
                String sqlString = "SELECT * FROM fil WHERE id = '"+id+"'";
                Statement myStatement = conn.createStatement();
                
                ResultSet rs=myStatement.executeQuery(sqlString);
                
                if (rs.next()) 
                {
                    file = rs.getBlob("file");
                    type=rs.getString("type");
                    fileData = file.getBytes(1,(int)file.length());
                } else 
                {
                    out.println("file not found!");
                    return;
                }
                /*
                if(type.equals("audio/mp3")) {
                response.setContentType("audio/mp3");
                }else if(type.equals("video/mp4")) {
                response.setContentType("video/mp4");
                }else if(type.equals("application/pdf")) {
                response.setContentType("application/pdf");
                }else if(type.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
                	response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                }else if(type.equals("image/jpeg")) {
                	response.setContentType("image/jpeg");
                } */
                
               response.setContentType(type); 
                response.setHeader("Content-Disposition", "inline");
                response.setContentLength(fileData.length);
                
                OutputStream output = response.getOutputStream();
                output.write(fileData);
                
                output.flush();
                
            } catch (SQLException ex) {Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);} 
        %>
                
    </body>
</html>