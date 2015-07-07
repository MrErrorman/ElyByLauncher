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
        Test test = new Test(config);
    }
}