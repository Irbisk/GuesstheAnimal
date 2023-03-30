package animals.stats;

import java.text.MessageFormat;
import java.util.List;
import java.util.Random;

import static animals.Program.resourceBundleMessages;
import static animals.Program.resourceBundlePatterns;

public class Statement {
    public static void playAgainQ() {
        List<String> list = List.of(resourceBundleMessages.getString("game.again").split("\f"));
        System.out.println(list.get(new Random().nextInt(list.size())));
    }

    public static String getQuestion(String statement) {
        if (statement.matches(resourceBundlePatterns.getString("statement.isCorrect"))) {
            if (statement.matches(resourceBundlePatterns.getString("question.1.pattern"))) {
                statement = statement.replaceFirst(resourceBundlePatterns.getString("question.1.pattern"), resourceBundlePatterns.getString("question.1.replace"));
            } else if (statement.matches(resourceBundlePatterns.getString("question.2.pattern"))) {
                statement = statement.replaceFirst(resourceBundlePatterns.getString("question.2.pattern"), resourceBundlePatterns.getString("question.2.replace"));
            }
        }
        return statement.substring(0, 1).toUpperCase() + statement.substring(1);
    }

    public static String getStatement(String question, boolean positive) {
        question =  question.toLowerCase();
        String statement = null;
        if (question.matches(resourceBundlePatterns.getString("statement.isCorrect"))) {
            if (question.matches(resourceBundlePatterns.getString("statement.1.pattern"))) {
                statement = question.replaceFirst(resourceBundlePatterns.getString("statement.1.pattern"), resourceBundlePatterns.getString("statement.1.replace"));
            } else if (question.matches(resourceBundlePatterns.getString("statement.2.pattern"))) {
                statement = question.replaceFirst(resourceBundlePatterns.getString("statement.2.pattern"), resourceBundlePatterns.getString("statement.2.replace"));
            } else if (question.matches(resourceBundlePatterns.getString("statement.3.pattern"))) {
                statement = question.replaceFirst(resourceBundlePatterns.getString("statement.3.pattern"), resourceBundlePatterns.getString("statement.3.replace"));
            }
        }
        if (statement != null) {
            if (positive) {
                return statement.substring(0, 1).toUpperCase() + statement.substring(1);
            } else {
                return makeNegativeStatement(statement);
            }
        } else {
            return "MISTAKE";
        }
    }

    public static String addPositiveStatementToAnimal(String animal, String statement) {
        statement = statement.toLowerCase().replaceFirst(resourceBundlePatterns.getString("animalFact.1.pattern"),
                String.format(resourceBundlePatterns.getString("animalFact.1.replace"), AnimalIdentifier.getAnimalNameDef(animal)));
        return statement.substring(0, 1).toUpperCase() + statement.substring(1);

    }
    public static String addNegStatementToAnimal(String animal, String statement) {
        String negativeStatement = makeNegativeStatement(statement);
        if (negativeStatement != null) {
            negativeStatement = negativeStatement.replaceFirst(resourceBundlePatterns.getString("animalFact.1.pattern"),
                    String.format(resourceBundlePatterns.getString("animalFact.1.replace"), AnimalIdentifier.getAnimalNameDef(animal)));
            return negativeStatement.substring(0, 1).toUpperCase() + negativeStatement.substring(1);
        } else return "MISTAKE";
    }

    private static String makeNegativeStatement(String statement) {
        statement = statement.toLowerCase();
        String negativeStatement = null;
        if (statement.matches(resourceBundlePatterns.getString("negative.1.pattern"))) {
            negativeStatement = statement.replaceFirst(resourceBundlePatterns.getString("negative.1.pattern"), resourceBundlePatterns.getString("negative.1.replace"));
        } else if (statement.matches(resourceBundlePatterns.getString("negative.2.pattern"))) {
            negativeStatement = statement.replaceFirst(resourceBundlePatterns.getString("negative.2.pattern"), resourceBundlePatterns.getString("negative.2.replace"));
        } else if (statement.matches(resourceBundlePatterns.getString("negative.3.pattern"))) {
            negativeStatement = statement.replaceFirst(resourceBundlePatterns.getString("negative.3.pattern"), resourceBundlePatterns.getString("negative.3.replace"));
        }
        return negativeStatement;
    }

    public static String factsAboutTheAnimal(String animal, boolean exists) {
        if (exists) {
            return MessageFormat.format(resourceBundleMessages.getString("tree.search.facts"),
                    animal.replaceFirst(resourceBundlePatterns.getString("animalName.1.pattern"), resourceBundlePatterns.getString("animalName.1.replace")));
        } else {
            return MessageFormat.format(resourceBundleMessages.getString("tree.search.noFacts"),
                    animal.replaceFirst(resourceBundlePatterns.getString("animalName.1.pattern"), resourceBundlePatterns.getString("animalName.1.replace")));
        }
    }

    public static String askIfItsAnimal(String animal) {
        return animal.replaceFirst(resourceBundlePatterns.getString("guessAnimal.1.pattern"),
                resourceBundlePatterns.getString("guessAnimal.1.replace"));
    }
}
