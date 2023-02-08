package UniqueRegistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String pass=req.getParameter("pass");
		String pass2=req.getParameter("re_pass");
		String contact=req.getParameter("contact");
		res.setContentType("text/html"); 
		String url = "jdbc:mysql://localhost:3306/users";
		String sql="insert into user_data(name,email,pass,contact) values(?,?,?,?)";
		Connection con=null;
		PrintWriter pr = res.getWriter();
		if(pass!=pass2) {
			
			 String st="Password Mismatch";
			 PrintWriter out = res.getWriter();
			 out.print("<html><head>");
			 out.print("<script type=\"text/javascript\">alert(" + st + ");</script>");
			 out.print("</head><body></body></html>");	
		}
		try
		{		
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("connection established successfully");
			con = DriverManager.getConnection(url,"root","admin");
			PreparedStatement p2 = con.prepareStatement(sql);
			p2.setString(1, name);
			p2.setString(2, email);
			p2.setString(3, pass);
			p2.setString(4, contact);
			int count=p2.executeUpdate();
			RequestDispatcher rs = req.getRequestDispatcher("registration.jsp");
			if(count>0)
				req.setAttribute("status", "success");
			else
				req.setAttribute("status", "failed");
			rs.forward(req, res);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}	
}
