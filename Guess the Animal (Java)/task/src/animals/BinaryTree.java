package animals;


public class BinaryTree {

    Node root;
    Node current;

    public BinaryTree() {
        this.root = null;
        this.current = null;
    }

    public void insert(String info, String move) {
        root = insertItem(root, info, move);
    }

    private Node insertItem(Node newRoot, String value, String move) {
        if (newRoot == null) {
            newRoot = new Node(value);
            return newRoot;
        } else if (move.equals("Yes")) {
            newRoot.right = insertItem(newRoot.right, value, move);
        } else if (move.equals("No")) {
            newRoot.left = insertItem(newRoot.left, value, move);
        }
        return newRoot;
    }

    public Node search(String name, Node node){
        if(node != null){
            if(node.getData().equals(name)){
                return node;
            } else {
                Node foundNode = search(name, node.left);
                if(foundNode == null) {
                    foundNode = search(name, node.right);
                }
                return foundNode;
            }
        } else {
            return null;
        }
    }

    int maxQuestionDepth(Node node) {
        if (node == null)
            return 0;
        else {
            int lDepth = maxQuestionDepth(node.left);
            int rDepth = maxQuestionDepth(node.right);

            if (node.getData().contains("?")) {
                if (lDepth > rDepth)
                    return (lDepth + 1);
                else
                    return (rDepth + 1);
            } else {
                return Math.max(lDepth, rDepth);
            }

        }
    }

    int minimumAnimalDepth(Node root) {
        if (root == null)
            return -1;
        int dist = -1;
        if (!(root.data.contains("?"))||
                (dist = minimumAnimalDepth(root.left)) >= 0 ||
                (dist = minimumAnimalDepth(root.right)) >= 0)
            return dist + 1;
        return dist;
    }

    int findAnimalDepth(Node root, String animal) {
        if (root == null)
            return -1;
        int dist = -1;
        if ((root.data.equals(animal))||
                (dist = findAnimalDepth(root.left, animal)) >= 0 ||
                (dist = findAnimalDepth(root.right, animal)) >= 0)
            return dist + 1;
        return dist;
    }

    public Node getParent(Node root, Node node) {

        Node lh , rh;
        if (null == root)
            return null;

        if (root.getLeft() == node || root.getRight() == node)
            return root;

        lh = getParent(root.getLeft(), node);
        rh = getParent(root.getRight(), node);

        return lh != null ? lh : rh;

    }

    public void setCurrentToStart() {
        current = root;
    }

    public void moveCurrentToNo() {
        current = current.left;
    }

    public void moveCurrentToYes() {
        current = current.right;
    }

    public boolean isEmpty() {
        if (root == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAtEnd() {
        if ((current != null) && (current.left == null && current.right == null)) {
            return true;
        } else {
            return false;
        }
    }

    public String getCurrentData() {
        return current.getData();
    }

    public int getTotalNodes(Node root) {
        if (root == null)
            return 0;

        int l = getTotalNodes(root.left);
        int r = getTotalNodes(root.right);

        return 1 + l + r;
    }


    void print() {
        System.out.println("*****");
        PrintTree(root);
        System.out.println("*****");
    }

    private void PrintTree(Node root){
        System.out.print("|");

        if (root != null){
            System.out.println(root.getData());
            PrintTree(root.getLeft());
            PrintTree(root.getRight());
        }
    }
}
