package config;

import tools.OSValidator;
import java.io.File;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.util.UUID;

/**
 * Created by dyakovri on 07.07.15.
 */

public class Config {
    private static File mcDirectory;
    private static File launcherConfig;
    private static URI downloadRoot = URI.create("http://files.dmine.esy.es");
    private static URI authRoot = URI.create("http://minecraft.ely.by");
    private static String authenticateSubauth = "/auth/authenticate";
    private static String refreshSubauth = "/auth/refresh";
    private static String validateSubauth = "/auth/validate";
    private static String signoutSubauth = "/auth/signout";
    private static String invalidateSubauth = "/auth/invalidate";
    private static Proxy proxy = Proxy.NO_PROXY;
    private static String[] downloadFiles = {"versions.json", "config.zip", "core.zip", "libraries.zip", "mods.zip", "tlauncher.zip"};
    private static int OS = 0;
    private static String clientTocken = "jR2XknQCCCSkpagJ99xIGZiClzNqAn";
    private static String username;
    private static String accessToken;
    private static UUID uuid;
    private static String name;

    static {
        mcDirectory = OSValidator.getWorkingDirectory();
        launcherConfig = new File(mcDirectory.getPath(), "lconfig.json");
    }

    public static void Main(boolean a) {}

    public int getOS() { return OS; }

    public File getMcDirectory() { return mcDirectory; }
    public File getLauncherConfig() { return launcherConfig; }

    public String getMcDirectoryPath() { return mcDirectory.getPath(); }
    public String getLauncherConfigPath() { return launcherConfig.getPath(); }

    public URI getAuthURI() { return authRoot; }
    public URI getDownloadURI() { return downloadRoot; }
    public URI getAuthenticateURI() { return URI.create(authRoot.toString() + authenticateSubauth); }
    public URI getRefreshURI() { return URI.create(authRoot.toString() + refreshSubauth); }
    public URI getValidateURI() { return URI.create(authRoot.toString() + validateSubauth); }
    public URI getSignoutURI() { return URI.create(authRoot.toString() + signoutSubauth); }
    public URI getInvalidateURI() { return URI.create(authRoot.toString() + invalidateSubauth); }

    public String getAuthURIString() { return authRoot.toString(); }
    public String getDownloadURIString() { return downloadRoot.toString(); }
    public String getAuthenticateURIString() { return (authRoot.toString() + authenticateSubauth);}
    public String getRefreshURIString() { return (authRoot.toString() + refreshSubauth); }
    public String getValidateURIString() { return (authRoot.toString() + validateSubauth); }
    public String getSignoutURIString() { return (authRoot.toString() + signoutSubauth); }
    public String getInvalidateURIString() { return (authRoot.toString() + invalidateSubauth); }

    public String getClientTocken() { return clientTocken; }
    public String getUsername() { return username; }
    public String getAccessToken() { return accessToken; }
    public String getName() { return name; }
    public String getUuidString() { return uuid.toString(); }
    public UUID getUuid() { return uuid; }

    public boolean setUsername(String username) { this.username = username; return true; }
    public boolean setClientTocken(String clientTocken) { this.clientTocken = clientTocken; return true; }
    public boolean setAccessToken(String accessToken) { this.accessToken = accessToken; return true; }
    public boolean setName(String name) { this.name = name; return true; }
    public boolean setUuid(UUID uuid) { this.uuid = uuid; return true; }

    public String[] getDownloadFiles() { return downloadFiles; }

    public Proxy getProxy() { return proxy; }
    public boolean setProxy(Proxy proxy) { this.proxy = proxy; return true; }

}