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
<<<<<<< HEAD
=======
       // Updater.checkUpdates(mainUrl, files);
       // Archive.unZip(files[1]);
>>>>>>> c59ac2fa9bc78c4bad85f6ee7ab470040d1e57ba


<<<<<<< HEAD
        //mrerro's files testing
        //checkUpdates(mainUrl, files);
        //archive zip = new archive();
        //zip.unZip(files[1]);
=======
        //Account Account = new Account();
        //Account.setUser("login","password");
        //config = Account.authPassword();
>>>>>>> c59ac2fa9bc78c4bad85f6ee7ab470040d1e57ba

        //MrEM's auth testing
        String config = "";
        account account = new account();
        account.setUser("login","password");
        config = account.authPassword();
        System.out.println(config);

    }
}