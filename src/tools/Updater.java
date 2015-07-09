package tools;

import config.Config;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by John on 07.07.2015.
 */
public class Updater {

    public static void checkUpdates(Config config) throws IOException, ParseException, InterruptedException {

        String mainUrl = config.getDownloadURIString();
        String[] files = config.getDownloadFiles();
        Threading[] flows = new Threading[files.length];
        File path = OSValidator.getWorkingDirectory();

        System.out.println("[Updater] " + "Start checking updates");

        URLConnection conn = tools.Download.connection(mainUrl + files[0]);
        conn.connect();
        String version = tools.Download.readHeader(conn.getInputStream());
        if (version.substring(5, 14).equals("gamefiles")) {
            JSONParser parser = new JSONParser();
            String[] clientVersions;
            String[] serverVersions;
            if (new File(path +"/versions.json").exists()) {
                Object obj1 = parser.parse(new FileReader(path +"/versions.json"));
                clientVersions = parser(obj1, files);
            } else {
                clientVersions = new String[]{"", "", "", "", "", ""};
            }
            Object obj2 = parser.parse(version);
            serverVersions = parser(obj2, files);
            boolean check = false;
            boolean checkClientUpdate = false;
            for (int i = 0; i < files.length; i++) {
                if (!clientVersions[i].contains(serverVersions[i])) {
                    flows[i] = new Threading(new URL(mainUrl + files[i]), files[i], serverVersions[i]);
                    flows[i].start();
                    check = true;
                    if (i == 5) {
                        checkClientUpdate = true;
                    }
                }
            }
            for (int i = 0; i < flows.length; i++) {
                if (!clientVersions[i].contains(serverVersions[i])) {
                    flows[i].join();
                }
            }
            if (check) {
                Download.downloadFile(new URL(mainUrl + files[0]), files[0], serverVersions[0], 0);
                Runtime.getRuntime().exec("cmd /c start " + path + "/remove_download.bat");
            }
            if (checkClientUpdate) {
                System.out.println("[Updater] " + "Start update client");
                Runtime.getRuntime().exec("cmd /c start " + path + "/update_client.bat");
                System.exit(0);
            }
        } else {
            System.out.println("[Updater/Error] " + "No connection on the server");
        }
        System.out.println("[Updater] " + "Finish update client");
    }

    public static String[] parser(Object obj, String[] files) {

        JSONObject jsonObject = (JSONObject) obj;
        String[] result = new String[files.length];

        result[0] = (String) jsonObject.get(files[0]);

        JSONObject gamefiles = (JSONObject) jsonObject.get("gamefiles");

        for (int i = 1; i < files.length; i++) {
            result[i] = (String) gamefiles.get(files[i]);
        }

        return result;

    }
}
