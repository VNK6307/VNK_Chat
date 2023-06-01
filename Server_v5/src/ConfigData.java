import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigData {
    private static final File configFile = new File("config.properties");
    private static final Properties prop = new Properties();

    private static void getConfigData() {
        try {
            FileInputStream propsInput = new FileInputStream(configFile);
            prop.load(propsInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// getConfigData

    public static String getIPAddress() {
        getConfigData();
        return prop.getProperty("IP_ADDRESS");
    }

    public static int getPort() {
        getConfigData();
        return Integer.parseInt(prop.getProperty("PORT"));
    }
}// class