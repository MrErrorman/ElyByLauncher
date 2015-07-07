package tools;

/**
 * Created by John on 07.07.2015.
 */
public class OSValidator {

    public static int checkOS() {

        String OS = System.getProperty("os.name").toLowerCase();
        System.out.println(OS);

        if (isWindows(OS )) {
            return 1;
        } else if (isMac(OS )) {
            return 2;
        } else if (isUnix(OS )) {
            return 3;
        } else if (isSolaris(OS )) {
            return 4;
        } else {
            System.out.println("Your OS is not support!!");
        }
        return 0;
    }

    private static boolean isWindows(String OS) {

        return (OS.indexOf("win") >= 0);

    }

    private static boolean isMac(String OS) {

        return (OS.indexOf("mac") >= 0);

    }

    private static boolean isUnix(String OS) {

        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );

    }

    private static boolean isSolaris(String OS) {

        return (OS.indexOf("sunos") >= 0);

    }
}
