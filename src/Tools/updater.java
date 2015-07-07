package Tools;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLConnection;

/**
 * Created by John on 07.07.2015.
 */
public class updater {
    public static void checkUpdates(String mainUrl, String[] files) throws IOException, ParseException {

        System.out.println("Start checking updates");
        int BUFFER_SIZE = 64*1023;
        URLConnection conn = download.connection(mainUrl + files[0]);
        conn.connect();
        String version = download.readHeader(conn.getInputStream());
        if (version.substring(5,14).equals("gamefiles")) {
            JSONParser parser = new JSONParser();
            double[] result = new double [files.length];
            double[] result2 = new double [files.length];
            if (new File("versions.json").exists()) {
                Object obj1 = parser.parse(new FileReader("versions.json"));
                result = parser(obj1, files);
            }
            else {
                result = new double[]{0, 0, 0, 0, 0, 0};
            }
            Object obj2 = parser.parse(version);
            result2 = parser(obj2, files);
            boolean check = false;
            for (int i = 0; i < 6; i++) {
                if (result[i] != result2[i]) {
                    download.downloadFile(mainUrl + files[i], files[i], BUFFER_SIZE);
                    check = true;
                }
            }
            if (check) {
                download.downloadFile(mainUrl + files[0], files[0], BUFFER_SIZE);
            }
        } else {
            System.out.println("Error checking versions");
            System.out.println("No connection on the server");
        }
        System.out.println("Finish checking updates");
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
