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

@WebServlet("/Deletemeds")
public class delete_medicine extends HttpServlet {
	private static final long serialVersionUID = 1L;
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("medicine_id");
		response.setContentType("text/html"); 
		String url = "jdbc:mysql://localhost:3306/users";
		Connection con = null;
		boolean a = false;
		String get ="Select * from medicines where id=?";
			
			try {
		 		Class.forName("com.mysql.jdbc.Driver");
				con=  DriverManager.getConnection(url,"root","admin");
				System.out.println("connection established successfully");

				PreparedStatement p = con.prepareStatement(get);
				p.setString(1, id);
				ResultSet rs = p.executeQuery();
				a=rs.next();
				
				if(a==true) {
					
					String delete = "DELETE FROM medicines WHERE id=?";				
					PreparedStatement pt = con.prepareStatement(delete);			
					pt.setString(1, id);
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

