package auth;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

/**
 * Created by dyakovri on 06.07.15.
 */
public class account {
    private String username;
    private String password;
    private String clientTocken = "jR2XknQCCCSkpagJ99xIGZiClzNqAn";
    private String accessToken;
    private String uuid;
    private String name;

    public final String defaultUrl= "http://minecraft.ely.by";
    public final String authenticateSub= "/auth/authenticate";
    public final String refreshSub= "/auth/refresh";

    public void account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String authPassword() {
        String urlParameters =
                "Username=" + username +
                "&Password=" + password +
                "&ClientToken=" + clientTocken;
        String answer = networking.excutePost(defaultUrl + authenticateSub,urlParameters);
        return answer;
    }
}
