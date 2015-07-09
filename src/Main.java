import config.Config;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by John on 06.07.2015.
 */
public class Main {
    public static config.Config config = new Config();
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        /*
         * Now it works!
         *
         * You must know that it is a infinity cycle
         * If you want to launch tems mode (test.java) use StateCode = -1 or "test" ("debug") launch argument
         *
         * State = 0 - exiting
         * State = 1 - waiting for a command
         * We can add a new state in StateManager.java!
         */
        if (args.length == 0) { args = new String[1]; }

        if ((args[0] == "test") || (args[0] == "debug")) {
            new StateManager(config, -1);
        }
        if (args[0] == "nogui") {
            System.out.println("We are always in nogui!");
            new StateManager(config, 1);
        }
        else {
            new StateManager(config, 1);
        }
    }
}