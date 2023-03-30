package animals.stats;
import static animals.Program.resourceBundleMessages;
import static animals.Program.resourceBundlePatterns;

public class AnimalIdentifier {
    public static String getAnimalNameIndef(String animal) {
        if (animal.matches(resourceBundlePatterns.getString("animal.isCorrect"))) {
            if (animal.matches(resourceBundlePatterns.getString("animal.1.pattern"))) {
                animal = animal.replaceFirst(resourceBundlePatterns.getString("animal.1.pattern"), resourceBundlePatterns.getString("animal.1.replace"));
            } else if (animal.matches(resourceBundlePatterns.getString("animal.2.pattern"))) {
                animal = animal.replaceFirst(resourceBundlePatterns.getString("animal.2.pattern"), resourceBundlePatterns.getString("animal.2.replace"));
            } else if (animal.matches(resourceBundlePatterns.getString("animal.3.pattern"))) {
                animal = animal.replaceFirst(resourceBundlePatterns.getString("animal.3.pattern"), resourceBundlePatterns.getString("animal.3.replace"));
            }
        }
        return animal;
    }

    public static String getAnimalNameDef(String animal) {
        return animal.replaceFirst(resourceBundlePatterns.getString("definite.1.pattern"), resourceBundlePatterns.getString("definite.1.replace"));

    }

    public static String getAnimalName(String text, boolean withArticle) {
        StringBuilder stringBuilder = new StringBuilder();
        String article = null;

        String[] fullText = text.toLowerCase().split(" ");
        if (fullText.length == 1) {
            stringBuilder.append(fullText[0]);
        } else {
            int start = 0;
            switch (fullText[0]) {
                case "an":
                    article = "an";
                    start = 1;
                    break;
                case "a":
                    article = "a";
                    start = 1;
                    break;
                case "the":
                    start = 1;
                    break;
            }
            for (int i = start; i < fullText.length; i++) {
                if (i == fullText.length - 1) {
                    stringBuilder.append(fullText[i]);
                } else {
                    stringBuilder.append(fullText[i]).append(" ");
                }
            }
        }
        article = getArticle(stringBuilder.toString(), article);

        if (withArticle) {
            return article + " " + stringBuilder;
        } else {
            return stringBuilder.toString();
        }
    }

    private static String getArticle(String animal, String article) {
        String vowels = "[aeiou]+.*";
        if (article == null) {
            if (animal.matches(vowels)) {
                return "an";
            } else return "a";
        } else {
            return article;
        }
    }
}
