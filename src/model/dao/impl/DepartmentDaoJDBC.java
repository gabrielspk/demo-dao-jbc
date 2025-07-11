package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department dep) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
		            "INSERT INTO department "
		            + "(Name) "
		            + "VALUES (?)",
					Statement.RETURN_GENERATED_KEYS
			);
			
	        st.setString(1, dep.getName());
			
	        int rowsAffected = st.executeUpdate();
	        
	        if (rowsAffected > 0) {
	        	ResultSet rs = st.getGeneratedKeys();
	        	if (rs.next()) {
	        		int id = rs.getInt(1);
	        		dep.setId(id);
	        		
	        	}
	        	DB.closeResultSet(rs);
	        } else { 
	        	throw new DbException("Unexpected error! No rows affected!");
	        }
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		}
		finally {
			DB.closeStatement(st);
			
		}
		
	}

	@Override
	public void update(Department dep) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
		            "UPDATE department "
		            + "SET Name = ? "
		            + "WHERE Id = ?"
			);
			
			st.setString(1, dep.getName());
			st.setInt(2, dep.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);	
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);	
		}
		
	}

	@Override
	public Department findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"SELECT department.* "
					+ "FROM department "
					+ "WHERE department.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				return dep;
			}
			return null;	
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

	@Override
	public List<Department> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT department.* "
					+ "FROM department "
					+ "ORDER BY Id"
			);
			
			rs = st.executeQuery();
			
			List<Department> list = new ArrayList<Department>();
			
			while (rs.next()) {
				Department dep = instantiateDepartment(rs);
				list.add(dep);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		
	}
	}

}
