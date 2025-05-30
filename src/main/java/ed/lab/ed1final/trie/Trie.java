package ed.lab.ed1final.trie;

import org.springframework.stereotype.Component;
//final
@Component
public class Trie {

    private final Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;
        for (char c : word.toLowerCase().toCharArray()) {
            if (!isValidChar(c)) continue;
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Node();
            }
            node = node.children[index];
            node.prefixCount++;
        }
        node.isLast = true;
        node.count++; // palabra completa agregada
    }

    public int countWordsEqualTo(String word) {
        Node node = root;
        for (char c : word.toLowerCase().toCharArray()) {
            if (!isValidChar(c)) continue;
            int index = c - 'a';
            if (node.children[index] == null) return 0;
            node = node.children[index];
        }
        return node.isLast ? node.count : 0;
    }

    public int countWordsStartingWith(String prefix) {
        Node node = root;
        for (char c : prefix.toLowerCase().toCharArray()) {
            if (!isValidChar(c)) continue;
            int index = c - 'a';
            if (node.children[index] == null) return 0;
            node = node.children[index];
        }
        return node.prefixCount;
    }

    public void erase(String word) {
        if (countWordsEqualTo(word) == 0) return;
        Node node = root;
        for (char c : word.toLowerCase().toCharArray()) {
            if (!isValidChar(c)) continue;
            int index = c - 'a';
            node = node.children[index];
            node.prefixCount--;
        }
        node.count--;
        if (node.count == 0) node.isLast = false;
    }

    //validar caracteres raros
    private boolean isValidChar(char c) {
        return c >= 'a' && c <= 'z';
    }

    //Crear Nodo
    private static class Node {
        public Node[] children = new Node[26];
        public boolean isLast = false;
        public int count = 0;
        public int prefixCount = 0;
    }
}
