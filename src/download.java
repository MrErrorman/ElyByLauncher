import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by John on 06.07.2015.
 */
public class download {

    public static void downloadFile(String srcURL, String destPath, int bufferSize) {
        InputStream in = null;
        OutputStream out = null;
        try {
            URLConnection conn = connection(srcURL);
            conn.connect();
            in = conn.getInputStream();
            out = new FileOutputStream(destPath);
            byte buffer[] = new byte[bufferSize];
            int c = 0;
            while ((c = in.read(buffer)) > 0) {
                out.write(buffer, 0, c);
            }
            out.flush();
        } catch (IOException e) {
            System.out.println("File " + srcURL + " not found at server");
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }

            } catch (IOException ex) {

            }
        }
    }

    public static String readHeader(InputStream strm) throws IOException {
        byte[] buff = new byte[64 * 1024];
        int length = strm.read(buff);
        String res = new String(buff, 0, length);
        return res;
    }

    public static URLConnection connection(String srcURL) throws IOException {
        URL url = new URL(srcURL);
        return url.openConnection();
    }
}