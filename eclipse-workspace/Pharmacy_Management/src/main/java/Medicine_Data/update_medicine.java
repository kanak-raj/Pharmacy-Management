package Medicine_Data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updatemeds")
public class update_medicine extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("medicine_id");
		String title=request.getParameter("title");
		int quantity=Integer.parseInt(request.getParameter("qty")) ;
		int unit_price=Integer.parseInt(request.getParameter("unit_price"));
		response.setContentType("text/html");
		String url = "jdbc:mysql://localhost:3306/users";
		Connection con = null;
		boolean a = false;
		String get ="Select * from medicines where id=?";
			
			try {
		 		Class.forName("com.mysql.jdbc.Driver");
				con=  DriverManager.getConnection(url,"root","admin");
				System.out.println("connection established successfully(update)");

				PreparedStatement p1 = con.prepareStatement(get);
				p1.setString(1, id);
				ResultSet rs = p1.executeQuery();
				a=rs.next();
				
				if(a==true) {
					String update = "UPDATE medicines SET title=? , quantity=?,unit_price=? where id=?";
					PreparedStatement pt = con.prepareStatement(update);
	    			pt.setString(1, title);
	    			pt.setInt(2, quantity);
	    			pt.setInt(3, unit_price);
	    			pt.setString(4, id);
					pt.executeUpdate();
				}
			
			}catch(Exception e) {
		        e.printStackTrace();
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
