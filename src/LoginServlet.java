

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();  
         session.invalidate();  
         response.sendRedirect("signin.jsp");
         // request.getRequestDispatcher("signin.jsp").include(request, response);  
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uid=request.getParameter("uid");
		String pass=request.getParameter("pass");
		
		String pswd=null;
		String fname = null;
		String lname=null;
		PrintWriter out=response.getWriter();
		
		
		 if(uid.isEmpty() && pass.isEmpty()){
        	request.setAttribute("error", "No data");
            
            request.getRequestDispatcher("signin.jsp").forward(request, response);
        	
        }
		
		 else if(!(uid.matches("[0-9]+") && pass.matches("[a-zA-Z]+"))) {
				request.setAttribute("error", "User id and password not in specified format(Invalid data).");
				request.getRequestDispatcher("signin.jsp").forward(request, response);	
		 }else {
			 
			Integer a=Integer.parseInt(uid);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
		      Statement statement = connection.createStatement();
		      ResultSet rs = statement.executeQuery("SELECT * from user where userid="+a+"");
		      if(rs.next()){
		       pswd=rs.getString("password");
		       fname=rs.getString(1);
		       lname=rs.getString(2);		      }
		    
		      if(pass.equals(pswd)) {
		    	  //out.println("Successful login");
		    	  
		    	  
		    	  HttpSession session=request.getSession();  
		    	   session.setAttribute("user_id",a);
		    	   session.setAttribute("fname", fname);
		    	   session.setAttribute("lname", lname);
		    	  request.setAttribute("name", uid);
		    	  String fn = null;
				request.setAttribute("fn", fname);
				String ln = null;
				request.setAttribute("ln", lname);
		  		request.getRequestDispatcher("lsuccess.jsp").forward(request, response);
		      } else { 
		    	  request.setAttribute("error", "Invalid credentials");
		    		request.getRequestDispatcher("signin.jsp").forward(request, response);
		      }
		      
		    		  
		
			/*String sql="select * from user where userid= ?";
			PreparedStatement pstm=connection.prepareStatement(sql);
			pstm.setInt(1,1357);
			ResultSet rs=pstm.executeQuery();
			if(rs.next()){
			out.print(rs.getString("password"));
			}
			out.println("login successful"); */
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
		      out.println("SQLException: " + e.getMessage());
	    }
		}
    }
}
