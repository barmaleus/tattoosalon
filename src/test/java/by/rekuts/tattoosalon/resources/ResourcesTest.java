package by.rekuts.tattoosalon.resources;

import by.rekuts.tattoosalon.resource.MessageManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResourcesTest {
    @Test
    public void testMessages() {
        String messageLoginError = MessageManager.getProperty("message.loginerror");
        Assert.assertEquals(messageLoginError, "Incorrect login or password.");
    }
}
