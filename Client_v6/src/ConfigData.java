import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigData {
    private static final File configFile = new File("config.properties");
    private static final File nickConfigFile = new File("nickName.properties");
    private static final Properties prop = new Properties();

    private static void getConfigData() {
        try {
            FileInputStream propsInput = new FileInputStream(configFile);
            prop.load(propsInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//

    private static void getNickName() {
        try {
            FileInputStream propsInput = new FileInputStream(nickConfigFile);
            prop.load(propsInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//

    public static String getIPAddress() {
        getConfigData();
        return prop.getProperty("IP_ADDRESS");
    }

    public static int getPort() {
        getConfigData();
        return Integer.parseInt(prop.getProperty("PORT"));
    }

    public static int getHeight() {
        getConfigData();
        return Integer.parseInt(prop.getProperty("FRM_HEIGHT"));
    }

    public static int getWidth() {
        getConfigData();
        return Integer.parseInt(prop.getProperty("FRM_WIDTH"));
    }

    public static String getNick() {
        getNickName();
        return prop.getProperty("NICK");
    }
}// class