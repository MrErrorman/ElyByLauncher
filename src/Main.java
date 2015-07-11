import config.Config;
import config.ConfigReader;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import tools.OSValidator;

import java.io.*;
import java.security.MessageDigest;

/**
 * Created by John on 06.07.2015.
 */
public class Main {

    public static void main(String[] args) throws IOException, ParseException, InterruptedException, Exception {
        System.out.println("Starting a launcher");
        config.Config config = new Config();

        /*
         * You must know that it is a infinity cycle
         * If you want to launch tems mode (test.java) use StateCode = -1 or "test" ("debug") launch argument
         *
         * State = 0 - exiting
         * State = 1 - waiting for a command
         * We can add a new state in StateManager.java!
         */

        if (!(new File(config.getMcDirectoryPath() + "versions.json").exists())) {
            new StateManager(config, 3);
        }
        else if ((args.length > 0) && ((args[0].toString().contains("test")) || (args[0].toString().contains("debug")))) {
            new StateManager(config, -1);
        }
        else if ((args.length > 0) && args[0].toString().contains("nogui")) {
            System.out.println("We are always in nogui!");
            new StateManager(config, 1);
        }
        else {
            new StateManager(config, config.getLaunchStateNum());
        }
    }
}