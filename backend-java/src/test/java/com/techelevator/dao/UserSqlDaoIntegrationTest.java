package com.techelevator.dao;

import com.techelevator.model.Family;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserSqlDaoIntegrationTest extends DAOIntegrationTest {

    private UserSqlDAO userSqlDAO;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        userSqlDAO = new UserSqlDAO(jdbcTemplate);
    }

    @Test
    public void createNewUser() {
        boolean userCreated = userSqlDAO.create("TEST_USER","test_password");
        Assert.assertTrue(userCreated);
        Family user = userSqlDAO.findByFamilyName("TEST_USER");
        Assert.assertEquals("TEST_USER", user.getFamilyName());
    }
    


}
