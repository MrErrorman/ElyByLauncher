package config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by dyakovri on 07.07.15.
 */

public class ConfigReader {
    JSONParser parser = new JSONParser();

    public boolean fileConfigReader(File configFile, List<Config.Property> properties) throws Exception {

        if (configFile.exists()) {
            JSONObject obj = (JSONObject) parser.parse(new FileReader(configFile));
            for (Config.Property property : properties) {
                if (obj.containsKey(property.name)) {
                    property.stringValue = (String) obj.get(property.name);
                } else if (property.createOnStart) {
                    System.out.println("[Config] Key " + property.name + " is missing");
                    if (property.stringValue != null) System.out.println("\tUsing default value: " + property.stringValue);
                }
            }

        } else {
            System.out.println("[Config] Config file not found. Creating...");
            saveConfig(configFile, properties);
        }

        return true;
    }

    public void responseReader(Config config, String response) throws IOException, ParseException {
        JSONObject obj2 = (JSONObject) parser.parse(response);
        config.setAccessToken((String) obj2.get("accessToken"));
        config.setClientTocken((String) obj2.get("clientToken"));
        JSONObject selected = (JSONObject) obj2.get("selectedProfile");
        config.setUuid((String) selected.get("id"));
        config.setName((String) selected.get("name"));
    }

    public boolean validateReader(String response) throws IOException, ParseException {
        if (response.contains("error")) {
            return false;
        }
        else {
            return true;
        }
    }

    public void saveConfig(File configFile, List<Config.Property> properties) throws Exception {

        FileWriter file = new FileWriter(configFile);
        JSONObject configJson = new JSONObject();
        JSONObject obj = new JSONObject();

        for (Config.Property property : properties) {
            if ((property.createOnStart) && (property.stringValue != null)) {
                configJson.put(property.name,property.stringValue);
            }
        }

        try {
            file.write(configJson.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            file.flush();
            file.close();
        }

    }
}
