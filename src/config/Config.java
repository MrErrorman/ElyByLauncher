package config;

import com.sun.org.apache.xpath.internal.operations.Variable;
import tools.OSValidator;
import java.io.File;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.util.*;

/**
 * Created by dyakovri on 07.07.15.
 */

public class Config {
    public class Property {
        public Object value;
        public String name;
        public boolean createOnStart;

        public Property(String name, Object value) {
            this.value = value;
            this.name = name;
            createOnStart = true;
        }

        public Property(String name, Object value, boolean createOnStart) {
            this.value = value;
            this.name = name;
            this.createOnStart = createOnStart;
        }
    }

    private File mcDirectory;
    private String mcVersion = "ForgeLiteLoader 1.7.10";
    private File launcherConfig;
    private URI downloadRoot = URI.create("http://files.dmine.esy.es/");
    private URI authRoot = URI.create("http://minecraft.ely.by");
    private String authenticateSubauth = "/auth/authenticate";
    private String refreshSubauth = "/auth/refresh";
    private String validateSubauth = "/auth/validate";
    private String signoutSubauth = "/auth/signout";
    private String invalidateSubauth = "/auth/invalidate";
    private Proxy proxy = Proxy.NO_PROXY;
    private String[] downloadFiles = {"versions.json", "config.zip", "core.zip", "libraries.zip", "mods.zip", "ElyByLauncher.zip"};
    private OSValidator.OS OS = OSValidator.OS.UNKNOWN;
    private String clientTocken = "jR2XknQCCCSkpagJ99xIGZiClzNqAn";
    private String username;
    private String accessToken;
    private String uuid;
    private String name;
    private String javaArguments = "-Xmx2G -XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode -XX:-UseAdaptiveSizePolicy -Xmn128M";
    private String minecraftArguments = "";
    private File java = new File("");
    public ConfigReader cr;
    private int launchStateNum = 1;
    public Property[] properties;
    private int gameWidth=854;
    private int gameHeight=480;
    private int RAM=1024; //"-Xmx512M"

    public Config() {
        OS = OSValidator.getPlatform();
        mcDirectory = OSValidator.getWorkingDirectory();
        launcherConfig = new File(mcDirectory.getPath(), "lconfig.json");
    }

    public static void Main(boolean a) {
    }

    public OSValidator.OS getOS() {
        return OS;
    }

    public File getMcDirectory() {
        return mcDirectory;
    }


    public File getLauncherConfig() {
        return launcherConfig;
    }

    public String getMcDirectoryPath() {
        return mcDirectory.getPath();
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

    public String getUuidString() {
        return uuid.toString();
    }

    public String getUuid() {
        return uuid;
    }

    public boolean setUsername(String username) {
        this.username = username;
        return true;
    }

    public boolean setClientTocken(String clientTocken) {
        this.clientTocken = clientTocken;
        return true;
    }

    public boolean setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return true;
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public boolean setUuid(String uuid) {
        this.uuid = uuid;
        return true;
    }

    public String[] getDownloadFiles() {
        return downloadFiles;
    }

    public int getLaunchStateNum() {
        return launchStateNum;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public boolean setProxy(Proxy proxy) {
        this.proxy = proxy;
        return true;
    }

    public String getJavaArguments() {
        return javaArguments;
    }

    public boolean setJavaArguments(String javaArgument) {
        this.javaArguments = javaArgument;
        return true;
    }

    public String getMinecraftArguments() {
        return minecraftArguments;
    }

    public boolean setMinecraftArguments(String javaArgument) {
        this.minecraftArguments = minecraftArguments;
        return true;
    }

    public File getJava() {
        return java;
    }

    public String getJavaPath() {
        return java.getPath();
    }

    public boolean setJavaPath(String javaPath) {
        this.java = new File(javaPath);
        return true;
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public boolean setGameWidth(int gameWidth) {
        this.gameWidth = gameWidth;
        return true;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public boolean setGameHeight(int gameHeight) {
        this.gameHeight = gameHeight;
        return true;
    }

    public int getRAM() {
        return RAM;
    }

    public boolean setRAM(int RAM) {
        this.RAM = RAM;
        return true;
    }

    public void updateProperties() {
        Property[] properties1 = {
                new Property("launchState", launchStateNum),
                new Property("username", username),
                new Property("name", name),
                new Property("accessToken", accessToken),
                new Property("uuid", uuid),
                new Property("javaPath", java),
                new Property("javaArgument", javaArguments),
                new Property("minecraftArguments", minecraftArguments),
                new Property("mcVersion", mcVersion, false),
                new Property("downloadFiles", downloadFiles, false),
                new Property("clientTocken", clientTocken, false),
                new Property("Window width", gameWidth),
                new Property("Window height", gameHeight=480),
                new Property("Game RAM", RAM)
        };
        properties = properties1;
    }
}