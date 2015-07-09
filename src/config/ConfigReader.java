package config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by dyakovri on 07.07.15.
 */

public class ConfigReader {
    JSONParser parser = new JSONParser();

    public ConfigReader(Config config) throws IOException, ParseException, Exception {
        String file = config.getLauncherConfigPath();
        if (new File(file).exists()) {
            JSONObject obj = new JSONObject();
            try {
                obj = (JSONObject) parser.parse(new FileReader(file));
                for (config.Config.Property property : config.properties)
                {
                    try {
                        if (property.value instanceof File) {
                            property.value = new File(obj.get(property.name).toString());
                            property.createOnStart = true;
                        } else if (property.value instanceof String) {
                            property.value = (String) obj.get(property.name);
                            property.createOnStart = true;
                        } else if (property.value instanceof String[]) {
                            property.value = (String[]) obj.get(property.name);
                            property.createOnStart = true;
                        } else {
                            property.value = obj.get(property.name);
                            property.createOnStart = true;
                        }
                    } catch (Exception e) {
                        if (!property.createOnStart) {
                            System.out.println(e.toString());
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                ConfigMaker cm = new ConfigMaker(config);
                cm.saveConfig();
            }
        }
        else {
            ConfigMaker cm = new ConfigMaker(config);
            cm.saveConfig();
        }
    }

    public void responseReader(Config config, String response) throws IOException, ParseException {
        JSONObject obj2 = (JSONObject) parser.parse(response);
        config.setAccessToken((String) obj2.get("accessToken"));
        config.setClientTocken((String) obj2.get("clientToken"));
        JSONObject selected = (JSONObject) obj2.get("selectedProfile");
        config.setUuid((String) selected.get("id"));
        config.setName((String) selected.get("name"));
        //boolean legacy = (boolean) selected.get("legacy");
    }

    public boolean validateReader(String response) throws IOException, ParseException {
        if (response.contains("error")) {
            return false;
        }
        else {
            return true;
        }
    }
}
