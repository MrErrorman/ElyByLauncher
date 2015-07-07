import Tools.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by John on 06.07.2015.
 */
public class main {

    public static void main(String[] args) throws IOException, ParseException {
        String mainUrl = "http://files.dmine.esy.es/";
        String[] files = {"versions.json", "config.zip", "core.zip", "libraries.zip", "mods.zip", "tlauncher.zip"};
        //updater.checkUpdates(mainUrl, files);
        //archive.unZip(files[1]);

        String config = "";

        //account account = new account();
        //account.setUser("login","password");
        //config = account.authPassword();

        System.out.println(config);

    }
}