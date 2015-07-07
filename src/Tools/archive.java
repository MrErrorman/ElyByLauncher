package Tools;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by John on 06.07.2015.
 */

public class archive {

    public static void unZip(final String zipFileName) {

        String destinationDirectory = "C:/Users/John/AppData/Roaming/.minecraft/";
        int BUFFER_SIZE = 1024;

        byte[] buffer = new byte[BUFFER_SIZE];

        // ������� �������, ���� ����� ����������� �����
        final String dstDirectory = destinationDirectory;
        final File dstDir = new File(dstDirectory);
        if (!dstDir.exists()) {
            dstDir.mkdir();
        }

        try {
            // �������� ���������� ZIP ������
            final ZipInputStream zis = new ZipInputStream(
                    new FileInputStream(zipFileName));
            ZipEntry ze = zis.getNextEntry();
            String nextFileName;
            while (ze != null) {
                nextFileName = ze.getName();
                File nextFile = new File(dstDirectory + File.separator
                        + nextFileName);
                System.out.println("unzip file: "
                        + nextFile.getAbsolutePath());
                // ���� �� ����� ���� � ��������� - ���� ��� �������. ����
                // ����� �� �������, �� �� ����� ������� ������ ��������
                // ������
                if (ze.isDirectory()) {
                    nextFile.mkdir();
                } else {
                    // ������� ��� ������������ ��������
                    new File(nextFile.getParent()).mkdirs();
                    // ���������� ���������� �����
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
            Logger.getLogger(archive.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(archive.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
