import tools.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by John on 06.07.2015.
 */
public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        String mainUrl = "http://files.dmine.esy.es/";
        String[] files = {"versions.json", "config.zip", "core.zip", "libraries.zip", "mods.zip", "tlauncher.zip"};
       // Updater.checkUpdates(mainUrl, files);
       // Archive.unZip(files[1]);

        String config = "";

        //Account Account = new Account();
        //Account.setUser("login","password");
        //config = Account.authPassword();

        System.out.println(config);

    }
}