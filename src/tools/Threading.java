package tools;

import java.net.URL;

/**
 * Created by John on 07.07.2015.
 */
public class Threading extends Thread {
    URL url;
    String destPath;
    String serverMD5;

    public Threading() {
    }
    public Threading(URL srcURL, String dest, String md5) {
        url = srcURL;
        destPath = dest;
        serverMD5 = md5;
    }

    @Override
    public void run() {
        System.out.println("[Threading] " + "New " + this.getName() + " " + destPath);
        Download dow = new Download();
        dow.downloadFile(url, destPath, serverMD5, 0);
        System.out.println("[Threading] " + "Close " + this.getName() + " " + destPath);
    }
}
