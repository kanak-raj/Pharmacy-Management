package Medicine_Data;

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
@WebServlet("/DataAdd")
public class add_medicine extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    		String id=req.getParameter("medicine_id");
    		String title=req.getParameter("title");
    		int quantity=Integer.parseInt(req.getParameter("qty")) ;
    		int unit_price=Integer.parseInt(req.getParameter("unit_price"));
    		res.setContentType("text/html"); 
    		String url = "jdbc:mysql://localhost:3306/users";
    		String sql="insert into medicines(id,title,quantity,unit_price) values(?,?,?,?)";
    		Connection con=null;
    		try
    		{		
    			Class.forName("com.mysql.jdbc.Driver");
    			
    			System.out.println("connection established.");
    			con = DriverManager.getConnection(url,"root","admin");
    			PreparedStatement p1 = con.prepareStatement(sql);
    			p1.setString(1, id);
    			p1.setString(2, title);
    			p1.setInt(3, quantity);
    			p1.setInt(4, unit_price);
    			int count=p1.executeUpdate();
    			RequestDispatcher rs = req.getRequestDispatcher("/add_5.html");
    			if(count>0){
    				req.setAttribute("status", "added");}
    			else
    				req.setAttribute("status", "not_added");
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
