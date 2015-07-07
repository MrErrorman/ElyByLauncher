import Tools.*;
import Auth.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by John on 06.07.2015.
 */
public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        String mainUrl = "http://files.dmine.esy.es/";
        String[] files = {"versions.json", "config.zip", "core.zip", "libraries.zip", "mods.zip", "tlauncher.zip"};

        String config = "Hello World";
        System.out.println(config);
    }
}