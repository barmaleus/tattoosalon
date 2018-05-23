package by.rekuts.tattoosalon.resource;

import java.util.ResourceBundle;

//this class extracts info from file config.properties
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
