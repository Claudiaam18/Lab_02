package ed.lab;

public class Main {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(4);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(7);
        root.left.left = new TreeNode<>(1);
        root.left.right = new TreeNode<>(3);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(9);

        System.out.println("EJERCICIO 1");
        System.out.println("Árbol original:");
        printTree(root);

        int k = 3;
        E02KthSmallest kthFinder = new E02KthSmallest();
        int kthSmallest = kthFinder.kthSmallest(root, k);

        E01InvertBT inverter = new E01InvertBT();
        TreeNode<Integer> invertedRoot = inverter.invertTree(root);

        System.out.println("\nÁrbol invertido:");
        printTree(invertedRoot);

        System.out.println("EJERCICIO 2");
        System.out.println("\nEl " + k + "° elemento más pequeño es: " + kthSmallest);

        System.out.println("EJERCICIO 3");

        E03AVLTree<Integer> avlTree = new E03AVLTree<>(Integer::compare);

        System.out.println("Buscar 5 en AVL: " + avlTree.search(5));
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(1);
        System.out.println("Buscar 5 en AVL: " + avlTree.search(5));
        System.out.println("Buscar 1 en AVL: " + avlTree.search(1));
        System.out.println("Tamaño del AVL: " + avlTree.size());
        System.out.println("Altura del AVL: " + avlTree.height());

        avlTree.delete(3);
        System.out.println("Buscar 3 en AVL tras eliminar: " + avlTree.search(3));

        avlTree.insert(4);
        System.out.println("Buscar 4 en AVL tras insertar: " + avlTree.search(4));
    }

    private static void printTree(TreeNode<Integer> node) {
        if (node == null) return;
        System.out.printf("(%d) [left: %s] [right: %s]\n",
                node.value,
                (node.left != null) ? node.left.value : "null",
                (node.right != null) ? node.right.value : "null");
        printTree(node.left);
        printTree(node.right);

    }
}
