package tools;


import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;

/**
 * Created by John on 06.07.2015.
 */
public class Download {

    public static final int MAX_BUFFER_SIZE = 64 * 1024;

    public static void downloadFile(final URL url, String f, String md5, int count) {
        if (count < 3) {

            long contentLength;
            String outputMD5;

            String inputMD5 = md5;
            String file = f;
            long downloaded = 0;
            boolean status = true;
            boolean checkQuartet = true;
            boolean checkHalf = true;
            boolean checkFinish = true;

            RandomAccessFile writeFile = null;
            InputStream stream = null;

            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Range", "bytes= " + downloaded + "-");
                connection.connect();
                connection.setConnectTimeout(200);

                if (connection.getResponseCode() / 100 != 2) {
                    status = false;
                    System.out.println("[Download/Connection error]" + connection.getResponseCode());
                    return;
                }

                contentLength = connection.getContentLength();
                if (contentLength < 1) {
                    status = false;
                    return;
                }

                writeFile = new RandomAccessFile(OSValidator.getWorkingDirectory() + "/" + file, "rw");
                writeFile.seek(downloaded);
                stream = connection.getInputStream();
                System.out.println("[Download] " + "Downloading: " + file);

                long load = 0;
                int res;

                while (status) {
                    byte[] buffer;
                    long remainDownload = contentLength - downloaded;

                    if (remainDownload > MAX_BUFFER_SIZE) {
                        buffer = new byte[MAX_BUFFER_SIZE];
                    } else {
                        buffer = new byte[(int) (remainDownload)];
                    }

                    int read = stream.read(buffer);
                    connection.setReadTimeout(200);
                    load += read;

                    res = (int) (100 - remainDownload * 100 / contentLength);
                    if (res == 25 && checkQuartet) {
                        System.out.println("[Download] " + file + " " + res + "%");
                        checkQuartet = false;
                    }
                    if (res == 50 && checkHalf) {
                        System.out.println("[Download] " + file + " " + res + "%");
                        checkHalf = false;
                    }
                    if (res == 100 && checkFinish) {
                        System.out.println("[Download] " + file + " " + res + "%");
                        checkFinish = false;
                    }
                    if (read == -1) {
                        status = false;
                        break;
                    }
                    writeFile.write(buffer, 0, read);
                    downloaded += read;
                }

                if (!file.contains("versions.json")) {
                    outputMD5 = DigestUtils.md5Hex(createChecksum(OSValidator.getWorkingDirectory() + "/" + file));
                    if (!inputMD5.contains(outputMD5)) {
                        System.out.println("[Download/Error] md5 sums");
                        downloadFile(url, file, inputMD5, count++);
                    } else {
                        System.out.println("[Download] " + "Downloaded: " + file);
                        Archive.unZip(file);
                    }
                }
            } catch (Exception ee) {
                System.out.println("[Exception]" + ee.getMessage());
            } finally {
                try {
                    if (stream != null) {
                        stream.close();
                    }
                    if (writeFile != null) {
                        writeFile.close();
                    }
                } catch (IOException ex) {

                }
            }
        }

    }

    private static byte[] createChecksum(String filename) throws Exception {
        InputStream fis = new FileInputStream(filename);

        byte[] buffer = new byte[MAX_BUFFER_SIZE];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;

        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        fis.close();
        return complete.digest();
    }

    public static String readHeader(InputStream strm) throws IOException {
        byte[] buff = new byte[MAX_BUFFER_SIZE];
        int length = strm.read(buff);
        String res = new String(buff, 0, length);
        return res;
    }

    public static HttpURLConnection connection(String srcURL) throws IOException {
        URL url = new URL(srcURL);
        URLConnection urlConnection = url.openConnection();

        if (!(urlConnection instanceof HttpURLConnection))
            throw new IOException("[Connection/Error] " + "Invalid protocol: " + url.getProtocol());

        HttpURLConnection connection = (HttpURLConnection) urlConnection;
        return connection;
    }
}
