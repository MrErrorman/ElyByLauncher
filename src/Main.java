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

        Test test = new Test(config);

        /*
         * DO NOT UNCOMMENT IT!
         * IT ISN'T WORKS!
         */
        //new StateManager(config, 1);
    }
}