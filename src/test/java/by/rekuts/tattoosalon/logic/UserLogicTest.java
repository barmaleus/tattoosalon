package by.rekuts.tattoosalon.logic;

import by.rekuts.tattoosalon.db.ConnectionPool;
import by.rekuts.tattoosalon.subject.SalonUser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;

public class UserLogicTest {
    @Test
    public void testLoadPersonalData() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        SalonUser admin = UserLogic.loadPersonalData("admin");
        if (connection != null) {
            ConnectionPool.getInstance().returnConnectionToPool(connection);
        }
        Assert.assertEquals(admin.getId(), 1);
    }

    @Test
    public void testCheckUserRole() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        int adminRole = UserLogic.checkUserRole("admin");
        if (connection != null) {
            ConnectionPool.getInstance().returnConnectionToPool(connection);
        }
        Assert.assertEquals(adminRole, 0);
    }
}