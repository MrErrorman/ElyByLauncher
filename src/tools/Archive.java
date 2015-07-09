package tools;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by John on 06.07.2015.
 */

public class Archive {

    public static void unZip(final String zipFileName) {

        int BUFFER_SIZE = 1024;
        byte[] buffer = new byte[BUFFER_SIZE];

        final File dstDir = OSValidator.getWorkingDirectory();
        if (!dstDir.exists()) {
            dstDir.mkdir();
        }
        if (new File(dstDir + "/" + zipFileName).exists()) {
            try {
                final ZipInputStream zis = new ZipInputStream(
                        new FileInputStream(dstDir + "/" + zipFileName));
                ZipEntry ze = zis.getNextEntry();
                String nextFileName;
                while (ze != null) {
                    nextFileName = ze.getName();
                    File nextFile = new File(dstDir + File.separator
                            + nextFileName);
                    System.out.println("unzip file: "
                            + nextFile.getAbsolutePath());

                    if (ze.isDirectory()) {
                        nextFile.mkdir();
                    } else {
                        new File(nextFile.getParent()).mkdirs();
                        try (FileOutputStream fos
                                     = new FileOutputStream(nextFile)) {
                            int length;
                            while ((length = zis.read(buffer)) > 0) {
                                fos.write(buffer, 0, length);
                            }
                        }
                    }
                    ze = zis.getNextEntry();
                }
                zis.closeEntry();
                zis.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Archive.class.getName())
                        .log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Archive.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
}
