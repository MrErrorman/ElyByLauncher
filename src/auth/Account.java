package auth;

import config.Config;

import java.io.IOException;
import java.net.*;


/**
 * Created by dyakovri on 06.07.15.
 */
public class Account {
    private String password;
    private Config config;

    public Account(Config config) {
        this.config = config;
    }

    public void setUser(String username, String password) {
        config.setUsername(username);
        this.password = password;
    }

    public String authenticate()
            throws IOException
    {
        String urlParameters = "{ \"agent\": {\"name\": \"Minecraft\",\"version\": 1}," +
                "\"username\": \"" + config.getUsername() +
                "\",\"password\": \"" + password +
                "\",\"clientToken\":\"" + config.getClientTocken() + "\"}";

        String answer = "";
        try {
            URL url = new URL(config.getAuthenticateURIString());
            answer = Networking.performPost(url, urlParameters, config.getProxy(), "application/json", true);
        }
        catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
        return answer;
    }

    public String refresh()
            throws IOException
    {
        String urlParameters = "{\"accessToken\": \"" + config.getAccessToken() +
                "\",\"clientToken\": \"" + config.getClientTocken() +
                "\"selectedProfile\": {\"id\": \"" + config.getUuidString() +
                "\",\"name\": \"" + config.getName() + "\", \"legacy\":false}}";

        String answer = "";
        try {
            URL url = new URL(config.getAuthenticateURIString());
            answer = Networking.performPost(url, urlParameters, config.getProxy(), "application/json", true);
        }
        catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
        return answer;
    }

    public String validate()
            throws IOException
    {
        String urlParameters = "{ \"accessToken\": \"" + config.getAccessToken() + "\",}";

        String answer = "";
        try {
            URL url = new URL(config.getAuthenticateURIString());
            answer = Networking.performPost(url, urlParameters, config.getProxy(), "application/json", true);
        }
        catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
        return answer;
    }

    public String signout() {return "";}
    public String invalidate() {return "";}

}
