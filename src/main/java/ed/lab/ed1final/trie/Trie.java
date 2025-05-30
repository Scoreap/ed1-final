package ed.lab.ed1final.trie;

import org.springframework.stereotype.Component;

@Component
public class Trie {

    private Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node temp = root;
        for (char c : word.toCharArray()) {
            if (temp.children[c - 'a'] == null) {
                temp.children[c - 'a'] = new Node();
            }
            temp = temp.children[c - 'a'];
        }
        temp.isLast = true;
    }

    public int countWordsEqualTo(String word) {
        int result = 0;
        int i = 0;
        for (char c : word.toCharArray()) {
            if (c == word.charAt(i)) {
                result++;
                i++;
            } else {
                break;
            }
        }
        return result;
    }

    public int countWordsStartingWith(String prefix) {
        int result = 0;
        Node current = root;
            for(char c:prefix.toCharArray()){
               if(current.children[c-'a'] == null)
                    return 0;

               current=current.children[c-'a'];
               result++;
            }
        return result;
    }

    public void erase(String word) {


    }

    //Crear Nodo
    private static class Node {
        public Node[] children;
        public boolean isLast;

        public Node() {
            children = new Trie.Node[26];
            isLast = false;
        }
    }

}
