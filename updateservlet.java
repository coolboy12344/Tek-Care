import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class updateservlet extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		
		Connection dbcon=null;
    	PreparedStatement pstmt = null;
		String url= "jdbc:mysql://localhost:3306/teksystems";
		String username = "root";
		String password = "kunal";
		ResultSet rs=null;
		
		String query = "CREATE TABLE csv_table_dummy SELECT * FROM dataset";
		
		try {
    		Class.forName("com.mysql.jdbc.Driver");
			dbcon=DriverManager.getConnection(url,username,password);
			pstmt= dbcon.prepareStatement(query);
			pstmt.executeUpdate();
			
			query = "ALTER TABLE csv_table_dummy ADD temp INTEGER";
			pstmt= dbcon.prepareStatement(query);
			pstmt.executeUpdate();
			
			query = "UPDATE csv_table_dummy SET temp = 0";
			pstmt= dbcon.prepareStatement(query);
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("exception occoured while reading a file: " + e);
			   e.printStackTrace();
		}
		
		//PrintWriter out = response.getWriter();
		
		String[] paramValues = request.getParameterValues("emp_checkbox");
		if (paramValues==null) {
			
			try{
				
			query = "UPDATE csv_table_dummy SET leaves = '' ";
			pstmt= dbcon.prepareStatement(query);
			pstmt.executeUpdate();
			response.sendRedirect("home.jsp");
			}catch (Exception e) {
    			System.out.println("exception occoured while reading a file: " + e);
 			   e.printStackTrace();
 		    }
			
			//RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
			//rd.forward(request, response);
			
			//response.sendRedirect("home.jsp");
        }
		else{
	        for (int i = 0; i < paramValues.length; i++) 
	        {
	            String paramValue = paramValues[i];
	            
	            int a = Integer.valueOf(paramValue.substring(0, paramValue.length()));
	            
	            try {
		            query = "UPDATE csv_table_dummy SET temp = 1 WHERE Emp_ID = " + a + " ";
		            pstmt= dbcon.prepareStatement(query);
					pstmt.executeUpdate();
					
	            }catch (Exception e) {
	    			System.out.println("exception occoured while reading a file: " + e);
	    			   e.printStackTrace();
	    		}
	            
	            //out.write(a);
	            //out.write("\n");
	            System.out.println(a);
	        }
		}
        
        try {
        	query = "UPDATE csv_table_dummy SET leaves = '' WHERE temp = 0 ";
			pstmt= dbcon.prepareStatement(query);
			pstmt.executeUpdate();
			
        }catch (Exception e) {
			System.out.println("exception occoured while reading a file: " + e);
			   e.printStackTrace();
		}
        
        response.sendRedirect("home.jsp");
    }
}
