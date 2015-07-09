import config.Config;
import java.io.IOException;

/**
 * Created by John on 06.07.2015.
 */
public class Main {
    public static config.Config config = new Config();

    public static void main(String[] args) throws IOException {
        String mainUrl = config.getDownloadURIString();
        String[] files = config.getDownloadFiles();

        /*
         * Now it works!
         *
         * You must know that it is a infinity cycle
         * If you want to launch tems mode (test.java) use StateCode = -1
         *
         * State = 0 - exiting
         * State = 1 - waiting for a command
         * We can add a new state in StateManager.java!
         */
        if (args[0] == "test") {
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