package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dataSource;

	public StudentDbUtil(DataSource source) {
		this.dataSource = source;
	}

	public List<Student> getStudents() throws SQLException {

		List<Student> students = new ArrayList<>();

		Connection con = null;
		java.sql.Statement st = null;
		ResultSet rs = null;

		try {
			// con
			con = dataSource.getConnection();
			// st
			String sql = "select * from student order by last_name";
			st = con.createStatement();
			// execute

			rs = st.executeQuery(sql);
			// rs

			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");

				Student student = new Student(id, firstName, lastName, email);

				students.add(student);

			}
			return students;
		} finally {
			close(con, st,rs);
		}

	}

	private void close(Connection con, Statement st, ResultSet rs) {
		// TODO Auto-generated method stub
		try{
			if(rs != null){
				rs.close();
				
			}
			if(st != null){
				st.close();
			}
			
			//it puts the connection back to the pool (not closing it forever)
			if(con != null){
				con.close();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void addStudent(Student student) throws Exception {
		
		Connection con = null;
		PreparedStatement st = null;
		try{
			
			String sql = "Insert into student(first_name, last_name, email)"
					+ "values(?, ?, ?)";
			
			con = dataSource.getConnection();
			
			st = con.prepareStatement(sql);
			st.setString(1, student.getFirstName());
			st.setString(2, student.getLastName());
			st.setString(3, student.getEmail());
			
			st.execute();
			
		}finally{
			close(con, st, null);
		}
		
		
	}

	public Student getStudentByID(int id) throws Exception {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Student student = null;
		
		try{
			
			String sql = "select * from student where id = ?";
			
			con = dataSource.getConnection();
			
			st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()){
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				
				student = new Student(id, firstName, lastName, email);
			}else{
				throw new Exception("Could not find student with id: " + id);
			}
			
			
		}finally{
			close(con, st, rs);
		}
		return student;
	}

	public void updateStudent(Student student) throws SQLException {
		int id = student.getId();
		String firstName = student.getFirstName();
		String lastName = student.getLastName();
		String email = student.getEmail();
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			
			String sql = "update student set first_name =?, last_name=?, email=? where id=?";
			
			con = dataSource.getConnection();
			
			st = con.prepareStatement(sql);
			
			st.setString(1, firstName);
			st.setString(2, lastName);
			st.setString(3, email);
			st.setInt(4, id);
			
			st.execute();
		}finally{
			close(con,st,null);
		}
		
	}

	public void deleteStudent(int id) throws SQLException {

		Connection con = null;
		PreparedStatement st = null;
		
		try{
			
			String sql = "delete from student where id=?";
			
			con = dataSource.getConnection();
			
			st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			
			st.execute();
		}finally{
			close(con,st,null);
		}
		
	}

}
