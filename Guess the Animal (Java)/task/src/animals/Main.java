package animals;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import static animals.Program.type;
import static animals.Program.lang;

public class Main {
    public static void main(String[] args) {
        type = "json";
        lang = System.getProperty("user.language");

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-type")) {
                type = args[i + 1];
            }
        }
        new Program().start();


    }
}

