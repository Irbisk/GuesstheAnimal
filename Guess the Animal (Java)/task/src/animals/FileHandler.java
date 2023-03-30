package animals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;

import static animals.Program.binaryTree;

public class FileHandler {

    public static void writeFile(String type, String lang) {
        ObjectMapper objectMapper;
        String fileName = "animals";
        if (lang.equals("eo")) {
            fileName += "_eo";
        }

        switch (type) {
            case "xml":
                fileName += ".xml";
                objectMapper = new XmlMapper();
                break;
            case "yaml":
                fileName += ".yaml";
                objectMapper = new YAMLMapper();
                break;
            default:
                fileName += ".json";
                objectMapper = new JsonMapper();

        }

        try {
            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File(fileName), binaryTree.root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(String type, String lang) {
        ObjectMapper objectMapper;
        String fileName = "animals";
        if (lang.equals("eo")) {
            fileName += "_eo";
        }

        switch (type) {
            case "xml":
                fileName += ".xml";
                objectMapper = new XmlMapper();
                break;
            case "yaml":
                fileName += ".yaml";
                objectMapper = new YAMLMapper();
                break;
            default:
                fileName += ".json";
                objectMapper = new JsonMapper();
        }

        try {
            binaryTree.root = objectMapper.readValue(new File(fileName), Node.class);
            binaryTree.current = binaryTree.root;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean fileExists(String type, String lang) {
        String fileName = "animals";
        if (lang.equals("eo")) {
            fileName += "_eo";
        }
        switch (type) {
            case "xml":
                fileName += ".xml";
                break;
            case "yaml":
                fileName += ".yaml";
                break;
            default:
                fileName += ".json";
        }
        File file = new File(fileName);
        return file.exists();
    }
}
