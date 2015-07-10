import auth.Launcher;
import config.Config;
import auth.Account;
import org.json.simple.parser.ParseException;
import tools.Updater;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by dyakovri on 08.07.15.
 */
public class StateManager {
    private Config config;

    Scanner in = new Scanner(System.in);

    public class State {
        private int StateCode;
        private int PrevStateCode = 0;

        public State(int StateCode) {
            this.StateCode = StateCode;
        }

        public void ChangeState(int newStateCode) {
            this.PrevStateCode = this.StateCode;
            this.StateCode = newStateCode;
        }

        public void ChangeState(State newState) {
            this.PrevStateCode = this.StateCode;
            this.StateCode = newState.GetState();
        }

        public int GetState() {
            return StateCode;
        }

        public int GetPrevState () {
            return PrevStateCode;
        }

        public boolean currentState(int stateNum) {
            if (StateCode == stateNum) {
                return true;
            } else {
                return false;
            }
        }
    }

    public StateManager(Config config) {
        this.config = config;
    }



    public StateManager(Config config, int StateCode )
            throws IOException, ParseException, InterruptedException, Exception {
        this.config = config;
        State state = new State(StateCode);
        String command = "";
        String[] args;

        while (!state.currentState(0)) {
            switch (state.GetState()) {
                case -1:
                    System.out.println("Test mode is on:");
                    new Test(config);
                    command = "q";
                    break;
                case 1:
                    System.out.print("> ");
                    command = in.nextLine();
                    break;
                case 2:

                    break;
                default:
                    state.ChangeState(1);
            }

            String[] s = command.split(" ");
            command = s[0];
            args = new String[s.length-1];
            for (int i = 1;i<=args.length;i++) {
                args[i-1]=s[i];
            }

            state.ChangeState(Commander(command.toString(), args));
        }
    }

    public State Commander(String command, String[] args)
            throws IOException, ParseException, InterruptedException, Exception {
        switch (command)
        {
            case "q":
            case "quit":
                /*
                 * Command for quiting
                 */
                System.out.println("Saving changes...");
                config.saveProperties();
                System.out.println("Exiting...");
                return new State(0);
            case "login":
            case "l":
            case "user":
            case "u":
                /*
                 * Command for logging in
                 */
                Account accu = new Account(config);
                if (args.length == 2) {
                    accu.setUser(args[0], args[1]);
                    accu.authenticate();
                }
                else if (args.length == 0) {
                    System.out.print("login:");
                    String username =  in.nextLine();
                    System.out.print("password:");
                    accu.setUser(username, in.nextLine());
                    accu.authenticate();
                }
                else { System.out.println("Invalid number of arguments \n Use \"" + command + "\" or \"" + command + " <login> <password>\""); }
                return new State(1);


            case "d":
            case "download":
            case "upd":
            case "updates":
                /*
                 * Command for downloading updates
                 * TODO: download minecraft without version checking with "-f" argument
                 */
                if ((args.length>0) && ((args[0] == "-f") || (args[0] == "--force"))) {

                }
                Updater.checkUpdates(config);
                return new State(1);


            case "run":
            case "r":
            case "launch":
                /*
                 * Command to launch minecraft
                 */
                Account accl = new Account(config);
                if ((config.getUsername() != "") && (accl.validate())) {
                    new Launcher(config);
                } else {
                    System.out.println("Failed to auth \n Retry please \"l\\login\\u\\user [login] [password]\"");
                }
                return new State(1);
            default:
                System.out.println("Invalid command");
                return new State(1);
        }
    }
}
