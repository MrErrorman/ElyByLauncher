import config.Config;
import config.ConfigReader;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

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
        if ( (args.length > 0) && ((args[0].toString() == "test") || (args[0].toString() == "debug"))) {
            new StateManager(config, -1);
        }
        if (args[0] == "nogui") {
            System.out.println("We are always in nogui!");
            new StateManager(config, 1);
        }
        else {
            new StateManager(config, config.getLaunchStateNum());

        }
    }
}