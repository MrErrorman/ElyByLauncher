import auth.account;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLConnection;

/**
 * Created by John on 06.07.2015.
 */
public class main {

    public static void main(String[] args) throws IOException, ParseException {
        String mainUrl = "http://files.dmine.esy.es/";
        String[] files = {"versions.json", "config.zip", "core.zip", "libraries.zip", "mods.zip", "tlauncher.zip"};
        checkUpdates(mainUrl, files);
        archive zip = new archive();
        zip.unZip(files[1]);

        String config = "";

        account account = new account();
        account.setUser("login","password");
        config = account.authPassword();

        System.out.println(config);

    }



    public static void checkUpdates(String mainUrl, String[] files) throws IOException, ParseException {

        download dw = new download();
        JSONParser parser = new JSONParser();

        double[] result;
        URLConnection conn = dw.connection(mainUrl + files[0]);
        conn.connect();
        String version = dw.readHeader(conn.getInputStream());
        if (new File("versions.json").exists()) {
            Object obj1 = parser.parse(new FileReader("versions.json"));
            result = parser(obj1, files);
        }
        else {
            result = new double[]{0, 0, 0, 0, 0, 0};
        }
        Object obj2 = parser.parse(version);
        double[] result2 = parser(obj2, files);
        boolean check = false;
        for (int i = 0; i < 6; i++) {
            if (result[i] != result2[i]) {
                dw.downloadFile(mainUrl + files[i], files[i], 512);
                check = true;
            }
        }
        if (check) {
            dw.downloadFile(mainUrl + files[0], files[0], 512);
        }
    }

    public static double[] parser(Object obj, String[] files) {

        JSONObject jsonObject = (JSONObject) obj;
        double[] result = new double[6];

        result[0] = (Double) jsonObject.get(files[0]);

        JSONObject gamefiles = (JSONObject) jsonObject.get("gamefiles");

        for (int i = 1; i < 6; i++) {
            result[i] = (Double) gamefiles.get(files[i]);
        }

        return result;

    }
}