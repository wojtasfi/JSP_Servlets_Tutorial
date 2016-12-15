package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Connection poool

	// javaEE featrue- inject connection pool for us. make use of context.xml
	@Resource(name = "jdbc/web_student_tracker")

	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// PrintWriter

		PrintWriter out = response.getWriter();

		response.setContentType("text/html");
		
		
		// Connect to database
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			
			// sql statemenst
			String sql = "select * from student";
			st = con.createStatement();
			
			// execute query
			rs = st.executeQuery(sql);
			
			// Process result set
			while(rs.next()){
				String email = rs.getString("email");
				out.println(email);
				out.println();
			};
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
