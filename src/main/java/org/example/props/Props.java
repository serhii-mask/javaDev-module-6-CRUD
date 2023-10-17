package org.example.props;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {
    public static String getConnectionUrl() {
        try (InputStream input = Props.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            prop.load(input);

            return "jdbc:" +
                    prop.getProperty("h2.db") +
                    ":./" +
                    prop.getProperty("h2.db.database");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Props() {
        throw new IllegalStateException("Props class");
    }
}
