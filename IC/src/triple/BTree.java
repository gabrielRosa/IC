package triple;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * jBixbe debuggee: test insert and delete operation of a balanced tree data
 * structure. Using integer values read from keyboard as tree elements. //Copy
 * from http://www.jbixbe.com/doc/tutorial/BTree.html - Edited by Alencar
 * Hentges<alencarhentges@gmail.com>
 *
 * @author ds-emedia
 * @param <T>
 */
public class BTree<T extends Comparable<T>> {

    /* +++++++++++ instance declarations +++++++++++ */
    private Node root;
    private Integer elementsNumber = 0;

    /**
     * Creates an empty balanced tree.
     */
    public BTree() {
        root = null;
    }

    /**
     * Creates a balances tree using the given node as tree root.
     *
     * @param root
     */
    public BTree(Node root) {
        this.root = root;
    }

    /**
     * Inserts an element into the tree.
     *
     * @param info
     */
    public void insert(T info) {
        insert(info, root, null, false);
        elementsNumber++;
    }

    /**
     * Checks whether the given element is already in the tree.
     *
     * @param info
     * @return
     */
    public boolean isMember(T info) {
        return isMember(info, root);
    }

    /**
     * Checks whether the given element is already in the tree.
     *
     * @param info
     * @return
     */
    public boolean contains(T info) {
        return isMember(info, root);
    }

    /**
     * Removes an elememt from the tree.
     *
     * @param info
     */
    public void delete(T info) {
        delete(info, root);
    }

    /**
     * Returns a text representation of the tree.
     *
     * @return
     */
    @Override
    public String toString() {
        return inOrder();
    }

    /**
     * Returns all elements of the tree in in-order traversing.
     *
     * @return
     */
    public String inOrder() {
        return inOrder(root);
    }

    /**
     * Returns all elements of the tree in pre-order traversing.
     *
     * @return
     */
    public String preOrder() {
        return preOrder(root);
    }

    /**
     * Returns all elements of the tree in post-order traversing.
     *
     * @return
     */
    public String postOrder() {
        return postOrder(root);
    }

    /**
     * Returns the height of the tree.
     *
     * @return
     */
    public int getHeight() {
        return getHeight(root);
    }

    private void insert(T info, Node node, Node parent, boolean right) {
        if (node == null) {
            if (parent == null) {
                root = node = new Node(info, parent);

            } else if (right) {
                parent.right = node = new Node(info, parent);

            } else {
                parent.left = node = new Node(info, parent);

            }
            restructInsert(node, false);
        } else if (info.compareTo(node.information) == 0) {
            node.information = info;
            elementsNumber--;
        } else if (info.compareTo(node.information) > 0) {
            insert(info, node.right, node, true);
        } else {
            insert(info, node.left, node, false);
        }
    }

    private boolean isMember(T info, Node node) {

        boolean member = false;

        if (node == null) {
            member = false;
        } else if (info.compareTo(node.information) == 0) {
            member = true;
        } else if (info.compareTo(node.information) > 0) {
            member = isMember(info, node.right);
        } else {
            member = isMember(info, node.left);
        }

        return member;
    }

    private void delete(T info, Node node) throws NoSuchElementException {

        if (node == null) {
            throw new NoSuchElementException();
        } else if (info.compareTo(node.information) == 0) {
            deleteNode(node);
            elementsNumber--;
        } else if (info.compareTo(node.information) > 0) {
            delete(info, node.right);
        } else {
            delete(info, node.left);
        }
    }

    private void deleteNode(Node node) {

        Node eNode, minMaxNode, delNode = null;
        boolean rightNode = false;

        if (node.isLeaf()) {
            if (node.parent == null) {
                root = null;
            } else if (node.isRightNode()) {
                node.parent.right = null;
                rightNode = true;
            } else if (node.isLeftNode()) {
                node.parent.left = null;
            }
            delNode = node;
        } else if (node.hasLeftNode()) {
            minMaxNode = node.left;
            for (eNode = node.left; eNode != null; eNode = eNode.right) {
                minMaxNode = eNode;
            }
            delNode = minMaxNode;
            node.information = minMaxNode.information;

            if (node.left.right != null) {
                minMaxNode.parent.right = minMaxNode.left;
                rightNode = true;
            } else {
                minMaxNode.parent.left = minMaxNode.left;
            }

            if (minMaxNode.left != null) {
                minMaxNode.left.parent = minMaxNode.parent;
            }
        } else if (node.hasRightNode()) {
            minMaxNode = node.right;
            delNode = minMaxNode;
            rightNode = true;

            node.information = minMaxNode.information;

            node.right = minMaxNode.right;
            if (node.right != null) {
                node.right.parent = node;
            }
            node.left = minMaxNode.left;
            if (node.left != null) {
                node.left.parent = node;
            }
        }
        restructDelete(delNode.parent, rightNode);
    }

    private int getHeight(Node node) {
        int height = 0;

        if (node == null) {
            height = -1;
        } else {
            height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
        return height;
    }

    private String inOrder(Node node) {

        String result = "";
        if (node != null) {
            result = result + inOrder(node.left) + " ";
            result = result + node.information.toString();
            result = result + inOrder(node.right);
        }
        return result;
    }

    private String preOrder(Node node) {

        String result = "";
        if (node != null) {
            result = result + node.information.toString() + " ";
            result = result + preOrder(node.left);
            result = result + preOrder(node.right);
        }
        return result;
    }

    private String postOrder(Node node) {

        String result = "";
        if (node != null) {
            result = result + postOrder(node.left);
            result = result + postOrder(node.right);
            result = result + node.information.toString() + " ";
        }
        return result;
    }

    private void restructInsert(Node node, boolean wasRight) {

        if (node != root) {
            if (node.parent.balance == '_') {
                if (node.isLeftNode()) {
                    node.parent.balance = '/';
                    restructInsert(node.parent, false);
                } else {
                    node.parent.balance = '\\';
                    restructInsert(node.parent, true);
                }
            } else if (node.parent.balance == '/') {
                if (node.isRightNode()) {
                    node.parent.balance = '_';
                } else if (!wasRight) {
                    rotateRight(node.parent);
                } else {
                    doubleRotateRight(node.parent);
                }
            } else if (node.parent.balance == '\\') {
                if (node.isLeftNode()) {
                    node.parent.balance = '_';
                } else if (wasRight) {
                    rotateLeft(node.parent);
                } else {
                    doubleRotateLeft(node.parent);
                }
            }
        }
    }

    private void restructDelete(Node z, boolean wasRight) {

        Node parent;
        boolean isRight = false;
        boolean climb = false;
        boolean canClimb;

        if (z == null) {
            return;
        }

        parent = z.parent;
        canClimb = (parent != null);

        if (canClimb) {
            isRight = z.isRightNode();
        }

        if (z.balance == '_') {
            if (wasRight) {
                z.balance = '/';
            } else {
                z.balance = '\\';
            }
        } else if (z.balance == '/') {
            if (wasRight) {
                if (z.left.balance == '\\') {
                    doubleRotateRight(z);
                    climb = true;
                } else {
                    rotateRight(z);
                    if (z.balance == '_') {
                        climb = true;
                    }
                }
            } else {
                z.balance = '_';
                climb = true;
            }
        } else if (wasRight) {
            z.balance = '_';
            climb = true;
        } else if (z.right.balance == '/') {
            doubleRotateLeft(z);
            climb = true;
        } else {
            rotateLeft(z);
            if (z.balance == '_') {
                climb = true;
            }
        }

        if (canClimb && climb) {
            restructDelete(parent, isRight);
        }
    }

    private void rotateLeft(Node a) {

        Node b = a.right;

        if (a.parent == null) {
            root = b;
        } else if (a.isLeftNode()) {
            a.parent.left = b;
        } else {
            a.parent.right = b;
        }

        a.right = b.left;
        if (a.right != null) {
            a.right.parent = a;
        }

        b.parent = a.parent;
        a.parent = b;
        b.left = a;

        if (b.balance == '_') {
            a.balance = '\\';
            b.balance = '/';
        } else {
            a.balance = '_';
            b.balance = '_';
        }
    }

    private void rotateRight(Node a) {

        Node b = a.left;

        if (a.parent == null) {
            root = b;
        } else if (a.isLeftNode()) {
            a.parent.left = b;
        } else {
            a.parent.right = b;
        }

        a.left = b.right;
        if (a.left != null) {
            a.left.parent = a;
        }

        b.parent = a.parent;
        a.parent = b;
        b.right = a;

        if (b.balance == '_') {
            a.balance = '/';
            b.balance = '\\';
        } else {
            a.balance = '_';
            b.balance = '_';
        }
    }

    private void doubleRotateLeft(Node a) {

        Node b = a.right;
        Node c = b.left;

        if (a.parent == null) {
            root = c;
        } else if (a.isLeftNode()) {
            a.parent.left = c;
        } else {
            a.parent.right = c;
        }

        c.parent = a.parent;

        a.right = c.left;
        if (a.right != null) {
            a.right.parent = a;
        }
        b.left = c.right;
        if (b.left != null) {
            b.left.parent = b;
        }

        c.left = a;
        c.right = b;

        a.parent = c;
        b.parent = c;

        switch (c.balance) {
            case '/':
                a.balance = '_';
                b.balance = '\\';
                break;
            case '\\':
                a.balance = '/';
                b.balance = '_';
                break;
            default:
                a.balance = '_';
                b.balance = '_';
                break;
        }

        c.balance = '_';
    }

    private void doubleRotateRight(Node a) {

        Node b = a.left;
        Node c = b.right;

        if (a.parent == null) {
            root = c;
        } else if (a.isLeftNode()) {
            a.parent.left = c;
        } else {
            a.parent.right = c;
        }

        c.parent = a.parent;

        a.left = c.right;
        if (a.left != null) {
            a.left.parent = a;
        }
        b.right = c.left;
        if (b.right != null) {
            b.right.parent = b;
        }

        c.right = a;
        c.left = b;

        a.parent = c;
        b.parent = c;

        switch (c.balance) {
            case '/':
                b.balance = '_';
                a.balance = '\\';
                break;
            case '\\':
                b.balance = '/';
                a.balance = '_';
                break;
            default:
                b.balance = '_';
                a.balance = '_';
                break;
        }
        c.balance = '_';
    }

    public T getInfo(T info) {
        return getInfo(root, info);
    }

    private T getInfo(Node node, T info) {
        if (node == null) {
            return null;
        }
        int comparation = info.compareTo(node.information);
        if (comparation == 0) {
            return node.information;
        }
        if (comparation > 0) {
            return getInfo(node.right, info);
        } else {
            return getInfo(node.left, info);
        }
    }

    public int elementsNumber() {
        return elementsNumber(root);
    }

    private int elementsNumber(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + elementsNumber(node.right) + elementsNumber(node.left);
    }

    public List<T> getAllAsList() {
        List<T> array = new ArrayList<>();
        getAllAsList(array, root);
        return array;
    }

    private void getAllAsList(List<T> array, Node node) {
        if (node == null) {
            return;
        }
        getAllAsList(array, node.right);
        array.add(node.information);
        getAllAsList(array, node.left);
    }

    public Integer getElementsNumber() {
        return elementsNumber;
    }

    public T getElement(T info) {
        return this.getElement(info, root);
    }

    private T getElement(T info, Node node) {
        T element = null;
        if (node == null) {
            element = null;
        } else if (info.compareTo(node.information) == 0) {
            element = node.information;
        } else if (info.compareTo(node.information) > 0) {
            element = getElement(info, node.right);
        } else {
            element = getElement(info, node.left);
        }
        return element;
    }

    public class Node {

        T information;

        Node parent;

        Node left;

        Node right;

        char balance;

        public Node(T information, Node parent) {
            this.information = information;
            this.parent = parent;
            this.left = null;
            this.right = null;
            this.balance = '_';
        }

        boolean isLeaf() {
            return ((left == null) && (right == null));
        }

        boolean isNode() {
            return !isLeaf();
        }

        boolean hasLeftNode() {
            return (null != left);
        }

        boolean hasRightNode() {
            return (right != null);
        }

        boolean isLeftNode() {
            return (parent.left == this);
        }

        boolean isRightNode() {
            return (parent.right == this);
        }
    }
}
