package petStore.utils;

import java.util.ResourceBundle;

public class PropertyLoader {

    private static String env = System.getProperty("env", "dev");
    public static ResourceBundle res = ResourceBundle.getBundle(env);

    public static String getProperty(String key){
        return res.getString(key);
    }
}
