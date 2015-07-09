package config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by dyakovri on 07.07.15.
 */

public class ConfigReader {

    public ConfigReader(Config config) throws IOException, ParseException {
        String file = config.getLauncherConfigPath();
        JSONParser parser = new JSONParser();
        if (new File(file).exists()) {
            JSONObject obj = (JSONObject) parser.parse(new FileReader(file));
            config.setUsername((String)obj.get("username"));
            config.setAccessToken((String)obj.get("accessToken"));
            config.setUuid((UUID) obj.get("uuid"));
            config.setName((String) obj.get("name"));
        }


        JSONObject obj2 = (JSONObject) parser.parse(new FileReader(file));
        String accessToken = (String) obj2.get("accessToken");
        String clientToken = (String) obj2.get("clientToken");
        JSONObject selected = (JSONObject) obj2.get("selectedProfile");
        String id = (String) selected.get("id");
        String name = (String) selected.get("name");
        boolean legacy = (boolean) selected.get("legacy");


        JSONObject obj3 = (JSONObject) parser.parse(new FileReader(file));
        String accessToken1 = (String) obj3.get("accessToken");

    }
}
