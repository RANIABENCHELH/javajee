package rania;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("name");
		String uemail=request.getParameter("email");
		String pswd=request.getParameter("pass");
		String mobile=request.getParameter("contact");
		/*PrintWriter out=response.getWriter();
		out.print(uname);
		out.print(uemail);
		out.print(pswd);
		out.print(mobile);*/
		RequestDispatcher dispatcher=null;
		try { Class.forName("com.mysql.cj.jdbc.Driver");
	    
		   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webstore","root","");  

		 PreparedStatement ps=con.prepareStatement("insert into t_users(Login,Password) values(?,?)")  ; 
		 ps.setString(1, uname);
		 ps.setString(2, uemail);
		 
		 int rowcount=ps.executeUpdate();
		 dispatcher=request.getRequestDispatcher("registration.jsp");
		 if(rowcount>0) {
			 request.setAttribute("status", "success");
		 }else {
			 request.setAttribute("status", "failed");
		 }
		 dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
