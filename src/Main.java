import auth.Account;
import config.Config;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by John on 06.07.2015.
 */
public class Main {
    public static config.Config config = new Config();

    
    public static void main(String[] args) throws IOException, ParseException {
        String mainUrl = config.getDownloadURIString();
        String[] files = config.getDownloadFiles();

<<<<<<< HEAD
        String config = "Hello World";
        System.out.println(config);
        Updater.checkUpdates(mainUrl, files);
        System.out.println("Main off");
=======
        Test test = new Test(config);
>>>>>>> origin/master
    }
}