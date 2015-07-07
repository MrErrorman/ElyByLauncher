import config.Config;
import org.json.simple.parser.ParseException;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.File;
import java.io.IOException;

/**
 * Created by dyakovri on 08.07.15.
 */
public class StateManager {
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

        public int GetState() {
            return StateCode;
        }

        public int GetPrevState () {
            return PrevStateCode;
        }

        public boolean currentState(int state) {
            if (StateCode == state) {
                return true;
            } else {
                return false;
            }
        }
    }



    public StateManager(Config config, int StateCode ) throws IOException {
        State state = new State(StateCode);
        byte[] lastCommand = {};

        while (!state.currentState(0)) {
            switch (state.GetState()) {
                case 1:
                    int inState = System.in.read(lastCommand);
                    break;
                case 2:
                    System.out.println("login:");
                    System.out.println("password:");
                    break;
                default:
                    state.ChangeState(1);
            }
            Commander(lastCommand.toString(),new String[] {""});
        }
    }

    public State Commander(String command, String[] args) {
        switch (command)
        {
            case "q":
            case "quit":
                System.out.println("Exiting...");
                return new State(0);
            case "login":
            case "l":
                return new State(2);
            default:
                return new State(1);
        }
    }
}
