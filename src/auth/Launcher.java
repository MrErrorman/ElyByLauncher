package auth;

import config.Config;
import tools.OSValidator;

import java.io.*;

/**
 * Created by dyakovri on 09.07.15.
 */
public class Launcher  {
    ProcessBuilder processBuilder;
    Process process;

    public Launcher(Config config) throws IOException {

        /*String launchStr = config.getJavaPath() + " -Xmx" + config.getRAM() + "M " +
                "-Djava.library.path=\"" + config.getMcDirectoryPath() + "versions/" + config.getMcVersion() + "/natives\" " +
                "-cp \"" +
                config.getMcDirectoryPath() + "libraries/com/mumfrey/liteloader/1.7.10/liteloader-1.7.10.jar:" +
                config.getMcDirectoryPath() + "libraries/net/minecraft/launchwrapper/1.11/launchwrapper-1.11.jar:" +
                config.getMcDirectoryPath() + "libraries/org/ow2/asm/asm-all/5.0.3/asm-all-5.0.3.jar:" +
                config.getMcDirectoryPath() + "libraries/com/google/guava/guava/16.0/guava-16.0.jar:" +
                config.getMcDirectoryPath() + "libraries/net/minecraftforge/forge/1.7.10-10.13.2.1291/forge-1.7.10-10.13.2.1291.jar:" +
                config.getMcDirectoryPath() + "libraries/net/minecraft/launchwrapper/1.11/launchwrapper-1.11.jar:" +
                config.getMcDirectoryPath() + "libraries/org/ow2/asm/asm-all/5.0.3/asm-all-5.0.3.jar:" +
                config.getMcDirectoryPath() + "libraries/com/typesafe/akka/akka-actor_2.11/2.3.3/akka-actor_2.11-2.3.3.jar:" +
                config.getMcDirectoryPath() + "libraries/com/typesafe/config/1.2.1/config-1.2.1.jar:" +
                config.getMcDirectoryPath() + "libraries/org/scala-lang/scala-actors-migration_2.11/1.1.0/scala-actors-migration_2.11-1.1.0.jar:" +
                config.getMcDirectoryPath() + "libraries/org/scala-lang/scala-compiler/2.11.1/scala-compiler-2.11.1.jar:" +
                config.getMcDirectoryPath() + "libraries/org/scala-lang/plugins/scala-continuations-library_2.11/1.0.2/scala-continuations-library_2.11-1.0.2.jar:" +
                config.getMcDirectoryPath() + "libraries/org/scala-lang/plugins/scala-continuations-plugin_2.11.1/1.0.2/scala-continuations-plugin_2.11.1-1.0.2.jar:" +
                config.getMcDirectoryPath() + "libraries/org/scala-lang/scala-library/2.11.1/scala-library-2.11.1.jar:" +
                config.getMcDirectoryPath() + "libraries/org/scala-lang/scala-parser-combinators_2.11/1.0.1/scala-parser-combinators_2.11-1.0.1.jar:" +
                config.getMcDirectoryPath() + "libraries/org/scala-lang/scala-reflect/2.11.1/scala-reflect-2.11.1.jar:" +
                config.getMcDirectoryPath() + "libraries/org/scala-lang/scala-swing_2.11/1.0.1/scala-swing_2.11-1.0.1.jar:" +
                config.getMcDirectoryPath() + "libraries/org/scala-lang/scala-xml_2.11/1.0.2/scala-xml_2.11-1.0.2.jar:" +
                config.getMcDirectoryPath() + "libraries/lzma/lzma/0.0.1/lzma-0.0.1.jar:" +
                config.getMcDirectoryPath() + "libraries/com/google/guava/guava/16.0/guava-16.0.jar:" +
                config.getMcDirectoryPath() + "libraries/com/mojang/realms/1.3.5/realms-1.3.5.jar:" +
                config.getMcDirectoryPath() + "libraries/org/apache/commons/commons-compress/1.8.1/commons-compress-1.8.1.jar:" +
                config.getMcDirectoryPath() + "libraries/org/apache/httpcomponents/httpclient/4.3.3/httpclient-4.3.3.jar:" +
                config.getMcDirectoryPath() + "libraries/commons-logging/commons-logging/1.1.3/commons-logging-1.1.3.jar:" +
                config.getMcDirectoryPath() + "libraries/org/apache/httpcomponents/httpcore/4.3.2/httpcore-4.3.2.jar:" +
                config.getMcDirectoryPath() + "libraries/java3d/vecmath/1.3.1/vecmath-1.3.1.jar:" +
                config.getMcDirectoryPath() + "libraries/net/sf/trove4j/trove4j/3.0.3/trove4j-3.0.3.jar:" +
                config.getMcDirectoryPath() + "libraries/com/ibm/icu/icu4j-core-mojang/51.2/icu4j-core-mojang-51.2.jar:" +
                config.getMcDirectoryPath() + "libraries/net/sf/jopt-simple/jopt-simple/4.5/jopt-simple-4.5.jar:" +
                config.getMcDirectoryPath() + "libraries/com/paulscode/codecjorbis/20101023/codecjorbis-20101023.jar:" +
                config.getMcDirectoryPath() + "libraries/com/paulscode/codecwav/20101023/codecwav-20101023.jar:" +
                config.getMcDirectoryPath() + "libraries/com/paulscode/libraryjavasound/20101123/libraryjavasound-20101123.jar:" +
                config.getMcDirectoryPath() + "libraries/com/paulscode/librarylwjglopenal/20100824/librarylwjglopenal-20100824.jar:" +
                config.getMcDirectoryPath() + "libraries/com/paulscode/soundsystem/20120107/soundsystem-20120107.jar:" +
                config.getMcDirectoryPath() + "libraries/io/netty/netty-all/4.0.10.Final/netty-all-4.0.10.Final.jar:" +
                config.getMcDirectoryPath() + "libraries/com/google/guava/guava/15.0/guava-15.0.jar:" +
                config.getMcDirectoryPath() + "libraries/org/apache/commons/commons-lang3/3.1/commons-lang3-3.1.jar:" +
                config.getMcDirectoryPath() + "libraries/commons-io/commons-io/2.4/commons-io-2.4.jar:" +
                config.getMcDirectoryPath() + "libraries/commons-codec/commons-codec/1.9/commons-codec-1.9.jar:" +
                config.getMcDirectoryPath() + "libraries/net/java/jinput/jinput/2.0.5/jinput-2.0.5.jar:" +
                config.getMcDirectoryPath() + "libraries/net/java/jutils/jutils/1.0.0/jutils-1.0.0.jar:" +
                config.getMcDirectoryPath() + "libraries/com/google/code/gson/gson/2.2.4/gson-2.2.4.jar:" +
                config.getMcDirectoryPath() + "libraries/org/apache/logging/log4j/log4j-api/2.0-beta9/log4j-api-2.0-beta9.jar:" +
                config.getMcDirectoryPath() + "libraries/org/apache/logging/log4j/log4j-core/2.0-beta9/log4j-core-2.0-beta9.jar:" +
                config.getMcDirectoryPath() + "libraries/org/lwjgl/lwjgl/lwjgl/2.9.1/lwjgl-2.9.1.jar:" +
                config.getMcDirectoryPath() + "libraries/org/lwjgl/lwjgl/lwjgl_util/2.9.1/lwjgl_util-2.9.1.jar:" +
                config.getMcDirectoryPath() + "libraries/tv/twitch/twitch/5.16/twitch-5.16.jar:" +
                config.getMcDirectoryPath() + "libraries/by/ely/authlib/1.5.21/authlib-1.5.21.jar:" +
                config.getMcDirectoryPath() + "versions/" + config.getMcVersion() + "/" + config.getMcVersion() + ".jar" +
                "\" " +
                "-Dfml.ignoreInvalidMinecraftCertificates=true " +
                "-Dfml.ignorePatchDiscrepancies=true "+
                "-XX:+UseConcMarkSweepGC " +
                "-XX:+CMSIncrementalMode " +
                "-XX:-UseAdaptiveSizePolicy " +
                "-Xmn128M " +
                "\"net.minecraft.launchwrapper.Launch\" " +
                "--tweakClass com.mumfrey.liteloader.launch.LiteLoaderTweaker " +
                "--username " + config.getName() + " --version \"" + config.getMcVersion() + "\" --gameDir " + config.getMcDirectoryPath() +
                " --assetsDir " + config.getMcDirectoryPath() +
                "assets --assetIndex 1.7.10 --uuid " + config.getUuid() +
                " --accessToken " + config.getAccessToken() + " " +
                "--userProperties {} " +
                "--userType mojang " +
                "--tweakClass cpw.mods.fml.common.launcher.FMLTweaker " +
                "--width " + config.getGameWidth() + " " +
                "--height " + config.getGameHeight();*/

        if (OSValidator.getPlatform() == OSValidator.OS.WINDOWS) {

            String launchStr = config.getJavaPath() + " -Xmx" + config.getRAM() + "M " +
                    "-Djava.library.path=\"" + config.getMcDirectoryPath() + "versions\\" + config.getMcVersion() + "\\natives\" " +
                    "-cp \"" +
                    config.getMcDirectoryPath() + "libraries\\com\\mumfrey\\liteloader\\1.7.10\\liteloader-1.7.10.jar;" +
                    config.getMcDirectoryPath() + "libraries\\net\\minecraft\\launchwrapper\\1.11\\launchwrapper-1.11.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\ow2\\asm\\asm-all\\5.0.3\\asm-all-5.0.3.jar;" +
                    config.getMcDirectoryPath() + "libraries\\com\\google\\guava\\guava\\16.0\\guava-16.0.jar;" +
                    config.getMcDirectoryPath() + "libraries\\net\\minecraftforge\\forge\\1.7.10-10.13.2.1291\\forge-1.7.10-10.13.2.1291.jar;" +
                    config.getMcDirectoryPath() + "libraries\\net\\minecraft\\launchwrapper\\1.11\\launchwrapper-1.11.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\ow2\\asm\\asm-all\\5.0.3\\asm-all-5.0.3.jar;" +
                    config.getMcDirectoryPath() + "libraries\\com\\typesafe\\akka\\akka-actor_2.11\\2.3.3\\akka-actor_2.11-2.3.3.jar;" +
                    config.getMcDirectoryPath() + "libraries\\com\\typesafe\\config\\1.2.1\\config-1.2.1.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\scala-lang\\scala-actors-migration_2.11\\1.1.0\\scala-actors-migration_2.11-1.1.0.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\scala-lang\\scala-compiler\\2.11.1\\scala-compiler-2.11.1.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\scala-lang\\plugins\\scala-continuations-library_2.11\\1.0.2\\scala-continuations-library_2.11-1.0.2.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\scala-lang\\plugins\\scala-continuations-plugin_2.11.1\\1.0.2\\scala-continuations-plugin_2.11.1-1.0.2.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\scala-lang\\scala-library\\2.11.1\\scala-library-2.11.1.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\scala-lang\\scala-parser-combinators_2.11\\1.0.1\\scala-parser-combinators_2.11-1.0.1.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\scala-lang\\scala-reflect\\2.11.1\\scala-reflect-2.11.1.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\scala-lang\\scala-swing_2.11\\1.0.1\\scala-swing_2.11-1.0.1.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\scala-lang\\scala-xml_2.11\\1.0.2\\scala-xml_2.11-1.0.2.jar;" +
                    config.getMcDirectoryPath() + "libraries\\lzma\\lzma\\0.0.1\\lzma-0.0.1.jar;" +
                    config.getMcDirectoryPath() + "libraries\\com\\google\\guava\\guava\\16.0\\guava-16.0.jar;" +
                    config.getMcDirectoryPath() + "libraries\\com\\mojang\\realms\\1.3.5\\realms-1.3.5.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\apache\\commons\\commons-compress\\1.8.1\\commons-compress-1.8.1.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\apache\\httpcomponents\\httpclient\\4.3.3\\httpclient-4.3.3.jar;" +
                    config.getMcDirectoryPath() + "libraries\\commons-logging\\commons-logging\\1.1.3\\commons-logging-1.1.3.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\apache\\httpcomponents\\httpcore\\4.3.2\\httpcore-4.3.2.jar;" +
                    config.getMcDirectoryPath() + "libraries\\java3d\\vecmath\\1.3.1\\vecmath-1.3.1.jar;" +
                    config.getMcDirectoryPath() + "libraries\\net\\sf\\trove4j\\trove4j\\3.0.3\\trove4j-3.0.3.jar;" +
                    config.getMcDirectoryPath() + "libraries\\com\\ibm\\icu\\icu4j-core-mojang\\51.2\\icu4j-core-mojang-51.2.jar;" +
                    config.getMcDirectoryPath() + "libraries\\net\\sf\\jopt-simple\\jopt-simple\\4.5\\jopt-simple-4.5.jar;" +
                    config.getMcDirectoryPath() + "libraries\\com\\paulscode\\codecjorbis\\20101023\\codecjorbis-20101023.jar;" +
                    config.getMcDirectoryPath() + "libraries\\com\\paulscode\\codecwav\\20101023\\codecwav-20101023.jar;" +
                    config.getMcDirectoryPath() + "libraries\\com\\paulscode\\libraryjavasound\\20101123\\libraryjavasound-20101123.jar;" +
                    config.getMcDirectoryPath() + "libraries\\com\\paulscode\\librarylwjglopenal\\20100824\\librarylwjglopenal-20100824.jar;" +
                    config.getMcDirectoryPath() + "libraries\\com\\paulscode\\soundsystem\\20120107\\soundsystem-20120107.jar;" +
                    config.getMcDirectoryPath() + "libraries\\io\\netty\\netty-all\\4.0.10.Final\\netty-all-4.0.10.Final.jar;" +
                    config.getMcDirectoryPath() + "libraries\\com\\google\\guava\\guava\\15.0\\guava-15.0.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\apache\\commons\\commons-lang3\\3.1\\commons-lang3-3.1.jar;" +
                    config.getMcDirectoryPath() + "libraries\\commons-io\\commons-io\\2.4\\commons-io-2.4.jar;" +
                    config.getMcDirectoryPath() + "libraries\\commons-codec\\commons-codec\\1.9\\commons-codec-1.9.jar;" +
                    config.getMcDirectoryPath() + "libraries\\net\\java\\jinput\\jinput\\2.0.5\\jinput-2.0.5.jar;" +
                    config.getMcDirectoryPath() + "libraries\\net\\java\\jutils\\jutils\\1.0.0\\jutils-1.0.0.jar;" +
                    config.getMcDirectoryPath() + "libraries\\com\\google\\code\\gson\\gson\\2.2.4\\gson-2.2.4.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\apache\\logging\\log4j\\log4j-api\\2.0-beta9\\log4j-api-2.0-beta9.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\apache\\logging\\log4j\\log4j-core\\2.0-beta9\\log4j-core-2.0-beta9.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\lwjgl\\lwjgl\\lwjgl\\2.9.1\\lwjgl-2.9.1.jar;" +
                    config.getMcDirectoryPath() + "libraries\\org\\lwjgl\\lwjgl\\lwjgl_util\\2.9.1\\lwjgl_util-2.9.1.jar;" +
                    config.getMcDirectoryPath() + "libraries\\tv\\twitch\\twitch\\5.16\\twitch-5.16.jar;" +
                    config.getMcDirectoryPath() + "libraries\\by\\ely\\authlib\\1.5.21\\authlib-1.5.21.jar;" +
                    config.getMcDirectoryPath() + "versions\\" + config.getMcVersion() + "\\" + config.getMcVersion() + ".jar" +
                    "\" " +
                    "-Dfml.ignoreInvalidMinecraftCertificates=true " +
                    "-Dfml.ignorePatchDiscrepancies=true "+
                    "-XX:+UseConcMarkSweepGC " +
                    "-XX:+CMSIncrementalMode " +
                    "-XX:-UseAdaptiveSizePolicy " +
                    "-Xmn128M " +
                    "\"net.minecraft.launchwrapper.Launch\" " +
                    "--tweakClass com.mumfrey.liteloader.launch.LiteLoaderTweaker " +
                    "--username " + config.getName() + " --version \"" + config.getMcVersion() + "\" --gameDir " + config.getMcDirectoryPath() +
                    " --assetsDir " + config.getMcDirectoryPath() +
                    "assets --assetIndex 1.7.10 --uuid " + config.getUuid() +
                    " --accessToken " + config.getAccessToken() + " " +
                    "--userProperties {} " +
                    "--userType mojang " +
                    "--tweakClass cpw.mods.fml.common.launcher.FMLTweaker " +
                    "--width " + config.getGameWidth() + " " +
                    "--height " + config.getGameHeight();
            
            File file = new File(config.getMcDirectoryPath() + "/launch.bat");
            try {
                PrintWriter out = new PrintWriter(file.getAbsoluteFile());
                try {
                    out.print("cd " + config.getMcDirectoryPath() +"\n" + launchStr + "\n" + "exit");
                } finally {
                    out.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Start");
            Runtime.getRuntime().exec("cmd /c start " + "%appdata%\\.minecraft" + "\\launch.bat");
        }

        else if (OSValidator.getPlatform() == OSValidator.OS.LINUX) {

            String launchStr = config.getJavaPath() + " -Xmx" + config.getRAM() + "M " +
                    "-Djava.library.path=\"" + config.getMcDirectoryPath() + "versions/" + config.getMcVersion() + "/natives\" " +
                    "-cp \"" +
                    config.getMcDirectoryPath() + "libraries/com/mumfrey/liteloader/1.7.10/liteloader-1.7.10.jar:" +
                    config.getMcDirectoryPath() + "libraries/net/minecraft/launchwrapper/1.11/launchwrapper-1.11.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/ow2/asm/asm-all/5.0.3/asm-all-5.0.3.jar:" +
                    config.getMcDirectoryPath() + "libraries/com/google/guava/guava/16.0/guava-16.0.jar:" +
                    config.getMcDirectoryPath() + "libraries/net/minecraftforge/forge/1.7.10-10.13.2.1291/forge-1.7.10-10.13.2.1291.jar:" +
                    config.getMcDirectoryPath() + "libraries/net/minecraft/launchwrapper/1.11/launchwrapper-1.11.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/ow2/asm/asm-all/5.0.3/asm-all-5.0.3.jar:" +
                    config.getMcDirectoryPath() + "libraries/com/typesafe/akka/akka-actor_2.11/2.3.3/akka-actor_2.11-2.3.3.jar:" +
                    config.getMcDirectoryPath() + "libraries/com/typesafe/config/1.2.1/config-1.2.1.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/scala-lang/scala-actors-migration_2.11/1.1.0/scala-actors-migration_2.11-1.1.0.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/scala-lang/scala-compiler/2.11.1/scala-compiler-2.11.1.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/scala-lang/plugins/scala-continuations-library_2.11/1.0.2/scala-continuations-library_2.11-1.0.2.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/scala-lang/plugins/scala-continuations-plugin_2.11.1/1.0.2/scala-continuations-plugin_2.11.1-1.0.2.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/scala-lang/scala-library/2.11.1/scala-library-2.11.1.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/scala-lang/scala-parser-combinators_2.11/1.0.1/scala-parser-combinators_2.11-1.0.1.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/scala-lang/scala-reflect/2.11.1/scala-reflect-2.11.1.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/scala-lang/scala-swing_2.11/1.0.1/scala-swing_2.11-1.0.1.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/scala-lang/scala-xml_2.11/1.0.2/scala-xml_2.11-1.0.2.jar:" +
                    config.getMcDirectoryPath() + "libraries/lzma/lzma/0.0.1/lzma-0.0.1.jar:" +
                    config.getMcDirectoryPath() + "libraries/com/google/guava/guava/16.0/guava-16.0.jar:" +
                    config.getMcDirectoryPath() + "libraries/com/mojang/realms/1.3.5/realms-1.3.5.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/apache/commons/commons-compress/1.8.1/commons-compress-1.8.1.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/apache/httpcomponents/httpclient/4.3.3/httpclient-4.3.3.jar:" +
                    config.getMcDirectoryPath() + "libraries/commons-logging/commons-logging/1.1.3/commons-logging-1.1.3.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/apache/httpcomponents/httpcore/4.3.2/httpcore-4.3.2.jar:" +
                    config.getMcDirectoryPath() + "libraries/java3d/vecmath/1.3.1/vecmath-1.3.1.jar:" +
                    config.getMcDirectoryPath() + "libraries/net/sf/trove4j/trove4j/3.0.3/trove4j-3.0.3.jar:" +
                    config.getMcDirectoryPath() + "libraries/com/ibm/icu/icu4j-core-mojang/51.2/icu4j-core-mojang-51.2.jar:" +
                    config.getMcDirectoryPath() + "libraries/net/sf/jopt-simple/jopt-simple/4.5/jopt-simple-4.5.jar:" +
                    config.getMcDirectoryPath() + "libraries/com/paulscode/codecjorbis/20101023/codecjorbis-20101023.jar:" +
                    config.getMcDirectoryPath() + "libraries/com/paulscode/codecwav/20101023/codecwav-20101023.jar:" +
                    config.getMcDirectoryPath() + "libraries/com/paulscode/libraryjavasound/20101123/libraryjavasound-20101123.jar:" +
                    config.getMcDirectoryPath() + "libraries/com/paulscode/librarylwjglopenal/20100824/librarylwjglopenal-20100824.jar:" +
                    config.getMcDirectoryPath() + "libraries/com/paulscode/soundsystem/20120107/soundsystem-20120107.jar:" +
                    config.getMcDirectoryPath() + "libraries/io/netty/netty-all/4.0.10.Final/netty-all-4.0.10.Final.jar:" +
                    config.getMcDirectoryPath() + "libraries/com/google/guava/guava/15.0/guava-15.0.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/apache/commons/commons-lang3/3.1/commons-lang3-3.1.jar:" +
                    config.getMcDirectoryPath() + "libraries/commons-io/commons-io/2.4/commons-io-2.4.jar:" +
                    config.getMcDirectoryPath() + "libraries/commons-codec/commons-codec/1.9/commons-codec-1.9.jar:" +
                    config.getMcDirectoryPath() + "libraries/net/java/jinput/jinput/2.0.5/jinput-2.0.5.jar:" +
                    config.getMcDirectoryPath() + "libraries/net/java/jutils/jutils/1.0.0/jutils-1.0.0.jar:" +
                    config.getMcDirectoryPath() + "libraries/com/google/code/gson/gson/2.2.4/gson-2.2.4.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/apache/logging/log4j/log4j-api/2.0-beta9/log4j-api-2.0-beta9.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/apache/logging/log4j/log4j-core/2.0-beta9/log4j-core-2.0-beta9.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/lwjgl/lwjgl/lwjgl/2.9.1/lwjgl-2.9.1.jar:" +
                    config.getMcDirectoryPath() + "libraries/org/lwjgl/lwjgl/lwjgl_util/2.9.1/lwjgl_util-2.9.1.jar:" +
                    config.getMcDirectoryPath() + "libraries/tv/twitch/twitch/5.16/twitch-5.16.jar:" +
                    config.getMcDirectoryPath() + "libraries/by/ely/authlib/1.5.21/authlib-1.5.21.jar:" +
                    config.getMcDirectoryPath() + "versions/" + config.getMcVersion() + "/" + config.getMcVersion() + ".jar" +
                    "\" " +
                    "-Dfml.ignoreInvalidMinecraftCertificates=true " +
                    "-Dfml.ignorePatchDiscrepancies=true "+
                    "-XX:+UseConcMarkSweepGC " +
                    "-XX:+CMSIncrementalMode " +
                    "-XX:-UseAdaptiveSizePolicy " +
                    "-Xmn128M " +
                    "\"net.minecraft.launchwrapper.Launch\" " +
                    "--tweakClass com.mumfrey.liteloader.launch.LiteLoaderTweaker " +
                    "--username " + config.getName() + " --version \"" + config.getMcVersion() + "\" --gameDir " + config.getMcDirectoryPath() +
                    " --assetsDir " + config.getMcDirectoryPath() +
                    "assets --assetIndex 1.7.10 --uuid " + config.getUuid() +
                    " --accessToken " + config.getAccessToken() + " " +
                    "--userProperties {} " +
                    "--userType mojang " +
                    "--tweakClass cpw.mods.fml.common.launcher.FMLTweaker " +
                    "--width " + config.getGameWidth() + " " +
                    "--height " + config.getGameHeight();
            
            
            File file = new File(config.getMcDirectoryPath() + "/launch.sh");
            try {
                PrintWriter out = new PrintWriter(file.getAbsoluteFile());
                try {
                    out.print("#!/bin/bash\n\n" + "echo Launching mc\n" + "cd " + config.getMcDirectoryPath() + "\n" + launchStr + "\n");
                } finally {
                    out.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Start");
            Runtime.getRuntime().exec("sh " + config.getMcDirectoryPath() + "/launch.sh");
        }
    }
}
