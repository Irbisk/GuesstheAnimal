package animals.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static animals.Program.resourceBundleMessages;
import static animals.Program.resourceBundlePatterns;
public class Answer {

    public static String handleYesOrNo(String answer) {
        String isPositive = resourceBundlePatterns.getString("positiveAnswer.isCorrect");
        String isNegative = resourceBundlePatterns.getString("negativeAnswer.isCorrect");
        if (answer.strip().toLowerCase().matches(isPositive)) {
            return "Yes";
        } else if (answer.strip().toLowerCase().matches(isNegative)) {
            return "No";
        } else {
            return askAgain();
        }
    }

    public static String askAgain() {

        List<String> list = List.of(resourceBundleMessages.getString("ask.again").split("\f"));
        return list.get(new Random().nextInt(list.size()));
    }

    public static void sayGoodBye() {

        List<String> list = List.of(resourceBundleMessages.getString("farewell").split("\f"));
        System.out.println(list.get(new Random().nextInt(list.size())));
    }
}
