package org.ascending.training.repository;

import org.ascending.training.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5431/training_db";
    private static final String USER = "admin";
    private static final String PASS = "Training123!";
    private static final Logger log = LoggerFactory.getLogger(DepartmentDao.class); //factory design pattern

    public List<Department> getDepartments() {
        log.info("Start to getDepartments from postgres via JDBC");

        List<Department> departments = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM departments";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            log.info("Connects to DB and execute the query");

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String location = rs.getString("location");
                log.debug("Get all the attribute and translate to java.");

                Department department = new Department();
                department.setId(id);
                department.setName(name);
                department.setDescription(description);
                department.setLocation(location);

                departments.add(department);
            }
        } catch(SQLException e) {
             log.error("Unable to connect to db or execute query", e);
        } finally {
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                log.error("Failed to close connection", e);
            }
        }

        return departments;
    }

    public void createDepartment() {

    }
}
