package auth;

import config.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by dyakovri on 09.07.15.
 */
public class Launcher  {
    ProcessBuilder processBuilder;
    Process process;

    public Launcher(Config config) {
        processBuilder = new ProcessBuilder("");
        //process = processBuilder.start();
    }
}
