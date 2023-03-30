package animals;

import animals.stats.*;

import java.text.MessageFormat;
import java.util.*;


public class Program {
    private final Scanner scanner = new Scanner(System.in);
    public static BinaryTree binaryTree = new BinaryTree();
    public static ResourceBundle resourceBundleMessages;
    public static ResourceBundle resourceBundlePatterns;
    public static String type;
    public static String lang;

    public void menu() {
        boolean isOn = true;
        while (isOn) {
            System.out.println(resourceBundleMessages.getString("menu.property.title"));
            System.out.println("1. " + resourceBundleMessages.getString("menu.entry.play") + "\n" +
                    "2. " + resourceBundleMessages.getString("menu.entry.list") + "\n" +
                    "3. " + resourceBundleMessages.getString("menu.entry.search") + "\n" +
                    "4. " + resourceBundleMessages.getString("menu.entry.statistics") + "\n" +
                    "5. " + resourceBundleMessages.getString("menu.entry.print") + "\n" +
                    "0. " +resourceBundleMessages.getString("menu.property.exit") + "\n");

            int number = scanner.nextInt();
            scanner.nextLine();

            switch (number) {
                case 1: play();
                    break;
                case 2:
                    if (binaryTree.isEmpty()) {
                        System.out.println("No animals I know");
                    } else {
                        System.out.println(resourceBundleMessages.getString("tree.list.animals"));
                        getAnimals();
                    }
                    break;
                case 3:
                    System.out.println(resourceBundleMessages.getString("animal.prompt"));
                    String animal = scanner.nextLine();
                    findAnAnimal(animal);
                    break;
                case 4:
                    calculateStatistics();
                    break;
                case 5:
                    binaryTree.print();
                    break;
                case 0:
                    Answer.sayGoodBye();
                    isOn = false;
                    break;
                default:
                    System.out.println(resourceBundleMessages.getString("menu.property.error"));

            }
        }
    }

    private void calculateStatistics() {
        System.out.println(resourceBundleMessages.getString("tree.stats.title"));
        System.out.println();
        System.out.println(MessageFormat.format(resourceBundleMessages.getString("tree.stats.root"), Statement.getStatement(binaryTree.root.getData(), true)));
        System.out.println(MessageFormat.format(resourceBundleMessages.getString("tree.stats.nodes"), binaryTree.getTotalNodes(binaryTree.root)));
        System.out.println(MessageFormat.format(resourceBundleMessages.getString("tree.stats.animals"), countAnimals()));
        System.out.println(MessageFormat.format(resourceBundleMessages.getString("tree.stats.statements"), (binaryTree.getTotalNodes(binaryTree.root) - countAnimals())));
        System.out.println(MessageFormat.format(resourceBundleMessages.getString("tree.stats.height"), binaryTree.maxQuestionDepth(binaryTree.root)));
        System.out.println(MessageFormat.format(resourceBundleMessages.getString("tree.stats.minimum"), binaryTree.minimumAnimalDepth(binaryTree.root)));
        System.out.println(MessageFormat.format(resourceBundleMessages.getString("tree.stats.average"), calculateAverageAnimalDepth()));
        System.out.println();
    }

    private int countAnimals() {
        List<String> list = new ArrayList<>();
        countAnimals(binaryTree.root, list);
        return list.size();
    }

    private void countAnimals(Node root, List<String> list) {
        if (root != null) {
            String data = root.getData();
            if (!data.contains("?")) {
                list.add(data);
            }
            countAnimals(root.getLeft(), list);
            countAnimals(root.getRight(), list);
        }
    }

    private void getAnimals() {
        List<String> list = new ArrayList<>();
        getAnimals(binaryTree.root, list);
        Collections.sort(list);
        list.forEach(System.out::println);
    }

    private void getAnimals(Node root, List<String> animalList) {
        if (root != null) {
            String data = root.getData();
            if (!data.contains("?")) {
                animalList.add(AnimalIdentifier.getAnimalName(data, false));
            }
            getAnimals(root.getLeft(), animalList);
            getAnimals(root.getRight(), animalList);
        }
    }

    private double calculateAverageAnimalDepth() {
        double totalAnimalsDepth = 0;
        List<String> list = new ArrayList<>();
        countAnimals(binaryTree.root, list);
        int numberOfAnimals = list.size();
        for (String s: list) {
            totalAnimalsDepth += binaryTree.findAnimalDepth(binaryTree.root, s);
        }
        return (totalAnimalsDepth / numberOfAnimals);
    }

    private void findAnAnimal(String animal) {
        List<String> animalQuestionList = new ArrayList<>();

        animal = AnimalIdentifier.getAnimalNameIndef(animal);
        Node foundAnimal = binaryTree.search(animal, binaryTree.root);
        if (foundAnimal != null) {
            System.out.println(Statement.factsAboutTheAnimal(animal, true));
            Node parent = binaryTree.getParent(binaryTree.root, foundAnimal);
            while (parent != null) {
                if (parent.getRight().equals(foundAnimal)) {
                    animalQuestionList.add("- " + Statement.getStatement(parent.getData(), true));
                } else if (parent.getLeft().equals(foundAnimal)) {
                    animalQuestionList.add("- " + Statement.getStatement(parent.getData(), false));
                }
                foundAnimal = parent;
                parent = binaryTree.getParent(binaryTree.root, parent);
            }

            Collections.reverse(animalQuestionList);
            animalQuestionList.forEach(System.out::println);

        } else {
            System.out.println(Statement.factsAboutTheAnimal(animal, false));
        }
    }

    public void start() {
        if (lang.equals("eo")) {
            resourceBundleMessages = ResourceBundle.getBundle("messages_eo");
            resourceBundlePatterns = ResourceBundle.getBundle("patterns_eo");
        } else {
            resourceBundleMessages = ResourceBundle.getBundle("messages");
            resourceBundlePatterns = ResourceBundle.getBundle("patterns");
        }

        TimeDefiner.getGreetings();
        System.out.println();
        if (FileHandler.fileExists(type, lang)) {
            FileHandler.readFile(type, lang);
        }

        if (binaryTree.isEmpty()) {
            System.out.println(resourceBundleMessages.getString("animal.wantLearn"));
            System.out.println(resourceBundleMessages.getString("animal.askFavorite"));
            Node root = new Node(AnimalIdentifier.getAnimalNameIndef(scanner.nextLine()));
            binaryTree.insert(root.getData(), "Yes");
            binaryTree.setCurrentToStart();
            System.out.println(resourceBundleMessages.getString("welcome"));
            menu();
        } else {
            System.out.println(resourceBundleMessages.getString("welcome"));
            menu();
        }
    }


    public void play() {

        boolean playAgain = true;

        while (true) {
            if (playAgain) {
                System.out.println(resourceBundleMessages.getString("game.think"));
                System.out.println(resourceBundleMessages.getString("game.enter"));
                scanner.nextLine();
            }

            playAgain = false;

            if (binaryTree.isAtEnd()) {
                System.out.println(Statement.askIfItsAnimal(binaryTree.getCurrentData()));
                String answer = askYesOrNo();
                if (answer.equals("Yes")) {

                    System.out.println(resourceBundleMessages.getString("game.win"));
                    Statement.playAgainQ();
                    answer = askYesOrNo();
                    if (answer.equals("No")) {
                        Answer.sayGoodBye();
                        break;
                    } else {
                        System.out.println("AGAIN");
                        playAgain = true;
                    }
                } else {
                    giveUp();
                    FileHandler.writeFile(type, lang);

                    Statement.playAgainQ();

                    answer = askYesOrNo();
                    if (answer.equals("No")) {
                        break;
                    } else {
                        playAgain = true;
                    }
                }

            } else {
                String question = binaryTree.getCurrentData();
                System.out.println(question);

                String answer = askYesOrNo();

                if (answer.equals("Yes")) {
                    binaryTree.moveCurrentToYes();

                } else if (answer.equals("No")) {
                    if (binaryTree.current.left == null) {
                        giveUp();
                    } else {
                        binaryTree.moveCurrentToNo();
                    }
                }
            }
        }
    }

    private void giveUp() {
        String oldAnimal = binaryTree.getCurrentData();
        String animal;
        String newQuestion;

        System.out.println(resourceBundleMessages.getString("game.giveUp"));
        animal = AnimalIdentifier.getAnimalNameIndef(scanner.nextLine());
        System.out.println(MessageFormat.format(resourceBundleMessages.getString("statement.prompt"), oldAnimal, animal));

        String statement = scanner.nextLine();
        System.out.println(MessageFormat.format(resourceBundleMessages.getString("game.isCorrect"), animal));
        String answer = askYesOrNo();
        newQuestion = Statement.getQuestion(statement);
        System.out.println(resourceBundleMessages.getString("game.learned"));

        switch (answer) {
            case "Yes":
                System.out.println(Statement.addPositiveStatementToAnimal(animal, statement));
                System.out.println(Statement.addNegStatementToAnimal(oldAnimal, statement));
                System.out.println(resourceBundleMessages.getString("game.distinguish"));
                System.out.println(newQuestion);

                binaryTree.current.data = newQuestion;
                binaryTree.current.right = new Node(animal);
                binaryTree.current.left = new Node(oldAnimal);
                binaryTree.setCurrentToStart();

                break;
            case "No":
                System.out.println(Statement.addPositiveStatementToAnimal(oldAnimal, statement));
                System.out.println(Statement.addNegStatementToAnimal(animal, statement));
                System.out.println(resourceBundleMessages.getString("game.distinguish"));
                System.out.println(newQuestion);

                binaryTree.current.data = newQuestion;
                binaryTree.current.right = new Node(oldAnimal);
                binaryTree.current.left = new Node(animal);
                binaryTree.setCurrentToStart();
                break;
            default:
                Answer.askAgain();
        }
    }

    private String askYesOrNo() {
        String respond = scanner.nextLine();
        String answer = Answer.handleYesOrNo(respond);

        while (!answer.equals("Yes") && !answer.equals("No")) {
            System.out.println(answer);
            respond = scanner.nextLine();
            answer = Answer.handleYesOrNo(respond);
        }
        return answer;
    }
}
