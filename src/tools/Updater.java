package tools;

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
public class Updater {

    public static void updateClient(String mainUrl, String[] files) throws IOException, ParseException {
        checkUpdates(mainUrl, files);
        for (int i = 1; i < files.length; i++) {
            if (new File(files[i]).exists()) {
                Archive.unZip(files[i]);
            }
        }
    }

    private static void checkUpdates(String mainUrl, String[] files) throws IOException, ParseException {

        System.out.println("[Updater] "+"Start checking updates");
        URLConnection conn = tools.Download.connection(mainUrl + files[0]);
        conn.connect();
        String version = tools.Download.readHeader(conn.getInputStream());
        if (version.substring(5, 14).equals("gamefiles")) {
            JSONParser parser = new JSONParser();
            double[] result;
            double[] result2;
            if (new File("versions.json").exists()) {
                Object obj1 = parser.parse(new FileReader("versions.json"));
                result = parser(obj1, files);
            } else {
                result = new double[]{0, 0, 0, 0, 0, 0};
            }
            Object obj2 = parser.parse(version);
            result2 = parser(obj2, files);
            boolean check = false;
            for (int i = 0; i < 6; i++) {
                if (result[i] != result2[i]) {
                    tools.Download.downloadFile(mainUrl + files[i], files[i]);
                    check = true;
                }
            }
            if (check) {
                tools.Download.downloadFile(mainUrl + files[0], files[0]);
            }
        } else {
            System.out.println("[Updater/Error] "+"No connection on the server");
        }
        System.out.println("[Updater] "+"Finish checking updates");
    }

    public static double[] parser(Object obj, String[] files) {

        JSONObject jsonObject = (JSONObject) obj;
        double[] result = new double[files.length];

        result[0] = (Double) jsonObject.get(files[0]);

        JSONObject gamefiles = (JSONObject) jsonObject.get("gamefiles");

        for (int i = 1; i < files.length; i++) {
            result[i] = (Double) gamefiles.get(files[i]);
        }

        return result;

    }
}
