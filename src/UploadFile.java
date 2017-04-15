
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Upload
 */

@WebServlet("/UploadFile")
@MultipartConfig(maxFileSize = 16177215)
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();  
         session.invalidate();  
         response.sendRedirect("signin.jsp");// request.getRequestDispatcher("signin.jsp").include(request, response);  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String title=request.getParameter("title");
        Part filePart = request.getPart("file_uploaded");
        PrintWriter out=response.getWriter();
        InputStream inputStream = null;
        Random rand = new Random();
        int  n = rand.nextInt(9999) + 1;
        String idTemp=(String.valueOf(n));
        String type_file=null;
        HttpSession session=request.getSession(); 
        
        Integer id=(Integer) session.getAttribute("user_id");
        if(session != null){
	        if (filePart != null) 
	        {
	            //out.println(filePart.getName());
	            //out.println(filePart.getSize());
	            type_file=filePart.getContentType();
	
	            inputStream = filePart.getInputStream();
	            
	           
	        } else {
	        	out.println("File part is null");
	        }
	
	        try 
	        {
	            //Db_Connection dbconn=new Db_Connection();
	            //Connection conn= dbconn.Connection();
	        	Class.forName("com.mysql.jdbc.Driver");
	        	//out.println("Connecting to database");
	            String TechWorld3g_2 = "jdbc:mysql://localhost:3306/test";
	         
	            Connection conn = DriverManager.getConnection(TechWorld3g_2,"root","root");
	            //out.println("Connection made");
	            String sql = "INSERT INTO fil(id, title, file,userid,type) values (?, ?, ?,?,?)";
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setString(1, idTemp);
	         
	            statement.setString(2, title);
	           
	            statement.setInt(4,id);
	            
	            statement.setString(5, type_file);
	            //out.println(type_file);
	            if (inputStream != null) 
	            {
	                statement.setBinaryStream(3, inputStream, (int) filePart.getSize());
	                //statement.setBlob(3,inputStream);
	               // out.println("xls inserted");
	            }
	            
	            
	            int row = statement.executeUpdate();
	           //out.println("row number"+row);
	            if (row > 0) 
	            {
	                //out.println("File uploaded!!!");
	                
	                conn.close();
	                
	                RequestDispatcher rs = request.getRequestDispatcher("lsuccess.jsp");
	                rs.include(request, response);
	            
	            }
	            else
	            {
	                out.println("Couldn't upload your file!!!");
	                
	                conn.close();
	                
	                RequestDispatcher rs = request.getRequestDispatcher("lsuccess.jsp");
	                rs.include(request, response);
	            }    
	
	        }catch(Exception e){e.printStackTrace();}  
        }else{
        	response.sendRedirect("signin.jsp");
        }
		
	}

}
