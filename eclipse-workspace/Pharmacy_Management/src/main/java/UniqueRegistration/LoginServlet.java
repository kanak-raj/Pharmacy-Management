package UniqueRegistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name=req.getParameter("username");
		String pass=req.getParameter("password");
		HttpSession session=req.getSession();
		String url = "jdbc:mysql://localhost:3306/users";
		String sql="select * from user_data where name=? and pass=?";
		Connection con=null;
		PrintWriter pr = res.getWriter();
		RequestDispatcher dispatcher = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,"root","admin");
			PreparedStatement p2 = con.prepareStatement(sql);
			p2.setString(1, name);
			p2.setString(2, pass);
			ResultSet rs=p2.executeQuery();
			if(rs.next()) {
				session.setAttribute("name",rs.getString("name"));   //change the name to "name"
				dispatcher=req.getRequestDispatcher("/homepage_4.html");
			}else {
				session.setAttribute("status","failed");   //change the name to "name"
				dispatcher=req.getRequestDispatcher("/login.jsp");
			}
			dispatcher.forward(req, res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
