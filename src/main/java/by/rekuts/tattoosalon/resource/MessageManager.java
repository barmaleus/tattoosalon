package by.rekuts.tattoosalon.resource;

import java.util.ResourceBundle;

//this class extracts info from file config.properties
public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    private MessageManager() {}
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
