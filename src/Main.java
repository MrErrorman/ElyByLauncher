import config.Config;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by John on 06.07.2015.
 */
public class Main {
    public static config.Config config = new Config();
    
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {

        Test test = new Test(config);
        /*
         * DO NOT UNCOMMENT IT!
         * IT ISN'T WORKS!
         */
        //new StateManager(config, 1);
    }
}