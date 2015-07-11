package config;

import com.sun.java.util.jar.pack.*;
import tools.OSValidator;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.net.URI;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyakovri on 07.07.15.
 */

public class Config {
    public enum Type {
        STRING, INTEGER, FILE;
        }

    public class Property {
        private String strg;
        private int intg;
        private File file;

        public Type type;
        public String stringValue;
        public String name;
        public boolean createOnStart;

        public Property(String name, String stringValue, Type type) {
            this.stringValue = stringValue;
            this.name = name;
            this.type = type;
            createOnStart = true;
        }

        public Property(String name, String stringValue, Type type, boolean createOnStart) {
            this.stringValue = stringValue;
            this.name = name;
            this.type = type;
            this.createOnStart = createOnStart;
        }

        public Object getTipizedValue() {
            switch (type) {
                case STRING:
                    try {
                        strg = stringValue;
                        return strg;
                    } catch (Exception e) {
                        System.out.println("[Config] Filed to read string " + name);
                        return null;
                    }
                case FILE:
                    try {
                        file = new File(stringValue);
                        return file;
                    } catch (Exception e) {
                        System.out.println("[Config] Filed to read file path " + name);
                        return null;
                    }
                case INTEGER:
                    try {
                        intg = Integer.parseInt(stringValue);
                        return intg;
                    } catch (Exception e) {
                        System.out.println("[Config] Filed to read number " + name);
                        return null;
                    }
                default:
                    return stringValue;
            }
        }
    }


    /*
     * Variables
     */
    public ConfigReader cr;

    public List<Property> properties;

    private File mcDirectory;
    private String mcVersion = "ForgeLiteLoader 1.7.10";
    private File launcherConfig;

    private URI downloadRoot = URI.create("http://files.dmine.esy.es/");
    private String[] downloadFiles = {"versions.json", "config.zip", "core.zip", "libraries.zip", "mods.zip", "ElyByLauncher.zip"};

    private URI authRoot = URI.create("http://minecraft.ely.by");
    private String authenticateSubauth = "/auth/authenticate";
    private String refreshSubauth = "/auth/refresh";
    private String validateSubauth = "/auth/validate";
    private String signoutSubauth = "/auth/signout";
    private String invalidateSubauth = "/auth/invalidate";

    private Proxy proxy = Proxy.NO_PROXY;
    private OSValidator.OS OS = OSValidator.OS.UNKNOWN;
    private int launchStateNum = 1;

    private String clientTocken = "jR2XknQCCCSkpagJ99xIGZiClzNqAn";
    private String username;
    private String accessToken;
    private String uuid;
    private String name;

    private File java = new File("");
    private String javaArguments = "-Xmx2G -XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode -XX:-UseAdaptiveSizePolicy -Xmn128M";
    private String minecraftArguments = "";

    private int gameWidth = 854;
    private int gameHeight = 480;
    private int RAM = 1024;
    /*
     * End of variables
     */


    public Config() throws IOException, ParseException, Exception {
        cr = new ConfigReader();

        OS = OSValidator.getPlatform();
        mcDirectory = OSValidator.getWorkingDirectory();

        launcherConfig = new File(mcDirectory.getPath() + "/lconfig.json");

        updateProperties();
        readProperties();


    }

    public void updateProperties() {
        properties = new ArrayList<>();

        //User settings
        properties.add(new Property("Username", username, Type.STRING));
        properties.add(new Property("Name", name, Type.STRING));
        properties.add(new Property("Client tocken", clientTocken, Type.STRING, false));
        properties.add(new Property("Access token", accessToken, Type.STRING));
        properties.add(new Property("UUID", uuid, Type.STRING));

        //Launch settings
        properties.add(new Property("Java path",java.getPath(),Type.FILE, false));
        properties.add(new Property("Java arguments",javaArguments, Type.STRING, false));
        properties.add(new Property("Minecraft arguments",minecraftArguments, Type.STRING,false));
        properties.add(new Property("Minecraft version",mcVersion, Type.STRING, false));
        properties.add(new Property("Window width",String.valueOf(gameWidth), Type.INTEGER));
        properties.add(new Property("Window height",String.valueOf(gameHeight), Type.INTEGER));
        properties.add(new Property("Game RAM",String.valueOf(RAM), Type.INTEGER));

        //Launcher settings
        properties.add(new Property("Launch state",String.valueOf(launchStateNum), Type.INTEGER));
    }

    public void readProperties() throws IOException, ParseException, Exception {
        cr.fileConfigReader(launcherConfig, properties);

        username = (String) properties.get(0).getTipizedValue();
        name = (String) properties.get(1).getTipizedValue();
        clientTocken = (String) properties.get(2).getTipizedValue();
        accessToken = (String) properties.get(3).getTipizedValue();
        uuid = (String) properties.get(4).getTipizedValue();

        java = (File) properties.get(5).getTipizedValue();
        javaArguments = (String) properties.get(6).getTipizedValue();
        minecraftArguments = (String) properties.get(7).getTipizedValue();
        mcVersion = (String) properties.get(8).getTipizedValue();
        gameWidth = (int) properties.get(9).getTipizedValue();
        gameHeight = (int) properties.get(10).getTipizedValue();
        RAM = (int) properties.get(11).getTipizedValue();

        launchStateNum = (int) properties.get(12).getTipizedValue();
    }

    public void saveProperties() throws IOException, ParseException, Exception {
        updateProperties();
        cr.saveConfig(launcherConfig, properties);
    }

    String arrToStr(String[] arr) {
        if (arr != null) {
            String ans = "";
            for (String str : arr) {
                ans += str;
                ans += " ";
            }
            ans.trim();
        }
        return null;
    }


    /*
     * Getters and setters
     */
    public OSValidator.OS getOS() { return OS;}
    public File getMcDirectory() {
        return mcDirectory;
    }
    public File getLauncherConfig() {return launcherConfig;}
    public String getMcDirectoryPath() {
        return mcDirectory.getPath() + "/";
    }
    public String getLauncherConfigPath() {
        return launcherConfig.getPath();
    }
    public String getMcVersion() {
        return mcVersion;
    }
    public URI getAuthURI() {
        return authRoot;
    }
    public URI getDownloadURI() {
        return downloadRoot;
    }
    public URI getAuthenticateURI() {
        return URI.create(authRoot.toString() + authenticateSubauth);
    }
    public URI getRefreshURI() {
        return URI.create(authRoot.toString() + refreshSubauth);
    }
    public URI getValidateURI() {
        return URI.create(authRoot.toString() + validateSubauth);
    }
    public URI getSignoutURI() {
        return URI.create(authRoot.toString() + signoutSubauth);
    }
    public URI getInvalidateURI() {
        return URI.create(authRoot.toString() + invalidateSubauth);
    }
    public String getAuthURIString() {
        return authRoot.toString();
    }
    public String getDownloadURIString() {
        return downloadRoot.toString();
    }
    public String getAuthenticateURIString() {
        return (authRoot.toString() + authenticateSubauth);
    }
    public String getRefreshURIString() {
        return (authRoot.toString() + refreshSubauth);
    }
    public String getValidateURIString() {
        return (authRoot.toString() + validateSubauth);
    }
    public String getSignoutURIString() {
        return (authRoot.toString() + signoutSubauth);
    }
    public String getInvalidateURIString() {
        return (authRoot.toString() + invalidateSubauth);
    }
    public String getClientTocken() {
        return clientTocken;
    }
    public String getUsername() {
        return username;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public String getName() {
        return name;
    }
    public String getUuidString() {return uuid.toString();}
    public String getUuid() {return uuid;}
    public boolean setUsername(String username) {this.username = username;return true;}
    public boolean setClientTocken(String clientTocken) {this.clientTocken = clientTocken;return true;}
    public boolean setAccessToken(String accessToken) {this.accessToken = accessToken;return true;}
    public boolean setName(String name) {this.name = name;return true;}
    public boolean setUuid(String uuid) {this.uuid = uuid;return true;}
    public String[] getDownloadFiles() {return downloadFiles;}
    public int getLaunchStateNum() {
        return launchStateNum;
    }
    public Proxy getProxy() {
        return proxy;
    }
    public boolean setProxy(Proxy proxy) {this.proxy = proxy;return true;}
    public String getJavaArguments() {return javaArguments;}
    public boolean setJavaArguments(String javaArgument) {this.javaArguments = javaArgument;return true;}
    public String getMinecraftArguments() {return minecraftArguments;}
    public boolean setMinecraftArguments(String javaArgument) {this.minecraftArguments = minecraftArguments;return true;}
    public File getJava() {return java;}
    public String getJavaPath() {
        return java.getPath();
    }
    public boolean setJavaPath(String javaPath) {this.java = new File(javaPath);return true;}
    public int getGameWidth() {return gameWidth;}
    public boolean setGameWidth(int gameWidth) {this.gameWidth = gameWidth;return true;}
    public int getGameHeight() {return gameHeight;}
    public boolean setGameHeight(int gameHeight) {this.gameHeight = gameHeight;return true;}
    public int getRAM() {return RAM;}
    public boolean setRAM(int RAM) {this.RAM = RAM;return true;}
    /*
     * End of getters and setters
     */
}