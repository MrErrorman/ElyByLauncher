package config;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by dyakovri on 07.07.15.
 */
public class ConfigMaker {
    private Config config;
    public ConfigMaker (Config config) {
        this.config = config;
    }

    public void saveConfig() throws Exception {
        FileWriter file = new FileWriter(config.getLauncherConfig());
        JSONObject configJson = new JSONObject();
        JSONObject obj = new JSONObject();

        config.updateProperties();

        for (Config.Property property : config.properties) {
            if ((property.createOnStart) && (property.value != null)) {
                if (property.value instanceof File) {
                    obj.put(property.name,((File) property.value).getPath());
                } else {
                    obj.put(property.name, property.value);
                }
            }
        }
        configJson.put("config", obj);
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
