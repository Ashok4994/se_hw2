

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String f_name= request.getParameter("fname");
         String l_name=request.getParameter("lname");
         String email=request.getParameter("eid");
         String uid=request.getParameter("uid");
        
         String passwd=request.getParameter("pwd");
         final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	             + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
         PrintWriter out=response.getWriter();
         if(!(uid.matches("[0-9]+") && passwd.matches("[a-zA-Z]+"))) {
        	 
              request.setAttribute("error", "Invalid userid  or password.They should be in specified format.");
                
                request.getRequestDispatcher("register.jsp").forward(request, response);
                }
        	 else {	 
        		 Integer a=Integer.parseInt(uid);
         
		try {
         Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
		    out.println("Connecting to database");
			/*Statement statement = connection.createStatement();
		      String sql="insert into user values('"+f_name+"','"+l_name+"','"+email+"','"+uid+"','"+passwd+"')";
		     
		      statement.executeUpdate(sql);
		      out.println("Registered successfully"); */
		    String insert="insert into user values"+"(?,?,?,?,?)";
		    PreparedStatement pstm=connection.prepareStatement(insert);
		      pstm.setString(1,f_name );
		      pstm.setString(2,l_name);
		      pstm.setString(3, email);
		      pstm.setInt(4, a);
		      pstm.setString(5,passwd);
		      pstm.executeUpdate();
		      out.println("Registered successfully");
		      
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
		      out.println("SQLException: " + e.getMessage());
	    }   
		request.setAttribute("msg", "You are successfully registered");
		//response.sendRedirect("signin.jsp");
		request.getRequestDispatcher("signin.jsp").forward(request, response);
         }
         
	}
		
	

}
