package ldt.com.base;

import com.google.common.io.Resources;
import ldt.com.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Properties;

public class ConfigReader {
    private static final ConfigReader instance = new ConfigReader();
    public static String env = null;
    private static Properties properties = null;
    private final static Logger log = LoggerFactory.getLogger(ConfigReader.class);

    public static ConfigReader getInstance() {
        if (properties == null) {
            loadConfigPropertiesFile();
        }
        return instance;
    }

    public static void loadConfigPropertiesFile() {
        env = System.getProperty("env");

        if (env == null) {
            throw new ApplicationException("env is not set, do eg. -Denv=test");
        }

        String configuration = "config." + env + ".properties";
        final URL url = Resources.getResource(configuration);

        properties = new Properties();

        try {
            properties.load(url.openStream());
        } catch (Exception e) {
            throw new ApplicationException("failed to load properties " + configuration);
        }
    }

    public String getValueByKey(String key) {
        String value = properties.get(key).toString();
        if (value == null) {
            log.error("failed to find value for key : {}", key);
        }
        return value;
    }

    public static String getEnv() {
        return env;
    }
}
