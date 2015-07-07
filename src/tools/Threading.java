package tools;

import java.io.File;

/**
 * Created by John on 07.07.2015.
 */
public class Threading extends Thread {
    String url;
    String destPath;

    public Threading(String srcURL, String dest) {
        url = srcURL;
        destPath = dest;
    }

    @Override
    public void run() {
        System.out.println("[Threading] " + "New " + this.getName() + " " + destPath);
        Download dow = new Download();
        dow.downloadFile(url, destPath);
        /*Archive ar = new Archive();
        if (new File(destPath).exists()) {
            ar.unZip(destPath);
        }*/
        System.out.println("[Threading] " + "Close " + this.getName() + " " + destPath);
    }
}
