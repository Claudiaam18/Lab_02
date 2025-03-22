package ed.lab;

import java.util.Comparator;

public class E03AVLTree<T> {
    private static class Node<T> {
        T value;
        Node<T> left, right;
        int height;

        Node(T value) {
            this.value = value;
            this.height = 1;
        }
    }

    private final Comparator<T> comparator;
    private Node<T> root;
    private int size;

    public E03AVLTree(Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("no puede ser null");
        }
        this.comparator = comparator;
        this.size = 0;
        this.root = null;
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            size++;
            return new Node<>(value);
        }

        int cmp = comparator.compare(value, node.value);
        if (cmp < 0) {
            node.left = insert(node.left, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, value);
        } else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        System.out.println("Balanceando nodo: " + node.value);
        return balance(node);
    }

    public void delete(T value) {
        root = delete(root, value);
    }

    private Node<T> delete(Node<T> node, T value) {
        if (node == null) return null;

        int cmp = comparator.compare(value, node.value);
        if (cmp < 0) {
            node.left = delete(node.left, value);
        } else if (cmp > 0) {
            node.right = delete(node.right, value);
        } else {
            size--;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node<T> minLargerNode = findMin(node.right);
            node.value = minLargerNode.value;
            node.right = delete(node.right, minLargerNode.value);
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        System.out.println("Balanceando nodo tras eliminación: " + node.value);
        return balance(node);
    }

    public T search(T value) {
        Node<T> node = search(root, value);
        return (node != null) ? node.value : null;
    }

    private Node<T> search(Node<T> node, T value) {
        if (node == null) return null;

        int cmp = comparator.compare(value, node.value);
        if (cmp < 0) {
            return search(node.left, value);
        } else if (cmp > 0) {
            return search(node.right, value);
        } else {
            return node;
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {

        return (node == null) ? 0 : node.height;
    }

    public int size() {
        return size;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private int getBalance(Node<T> node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node<T> rotateLeft(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    private Node<T> balance(Node<T> node) {
        int balanceFactor = getBalance(node);

        if (balanceFactor > 1) {
            if (getBalance(node.left) < 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balanceFactor < -1) {
            if (getBalance(node.right) > 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }
}

