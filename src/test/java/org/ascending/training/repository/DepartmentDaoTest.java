package org.ascending.training.repository;

import org.ascending.training.model.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static junit.framework.Assert.assertEquals;

public class DepartmentDaoTest {
    DepartmentDao departmentDao;

    @Before
    public void setup() {
        departmentDao = new DepartmentDao();
    }

    @After
    public void teardown() {

    }

    @Test
    public void getDepartmentsTest() {
        List<Department> departmentList = departmentDao.getDepartments();

        assertEquals(4, departmentList.size());
    }
}
