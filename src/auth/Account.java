package auth;

import config.Config;
import java.io.IOException;
import java.net.*;
import java.text.ParseException;


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
            throws IOException, org.json.simple.parser.ParseException
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

        try {
            config.cr.responseReader(config,answer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String refresh()
            throws IOException, org.json.simple.parser.ParseException
    {
        String urlParameters = "{\"accessToken\": \"" + config.getAccessToken() +
                "\",\"clientToken\": \"" + config.getClientTocken() + "\"," +
                "\"selectedProfile\": {\"id\":\"" + config.getUuidString() +
                "\",\"name\": \"" + config.getName() + "\", \"legacy\":false}}";

        String answer = "";
        try {
            URL url = new URL(config.getRefreshURIString());
            answer = Networking.performPost(url, urlParameters, config.getProxy(), "application/json", true);
        }
        catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
        config.cr.responseReader(config,answer);
        return null;
    }

    public boolean validate()
            throws IOException, org.json.simple.parser.ParseException
    {
        String urlParameters = "{ \"accessToken\": \"" + config.getAccessToken() + "\"}";

        String answer = "";
        try {
            URL url = new URL(config.getValidateURIString());
            answer = Networking.performPost(url, urlParameters, config.getProxy(), "application/json", true);
        }
        catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }

        return  config.cr.validateReader(answer);
    }

    public String signout() {return "";}
    public String invalidate() {return "";}

}
