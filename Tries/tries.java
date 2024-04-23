package Tries;

public class tries {

    static class Node {
        Node[] children;
        boolean eow;

        public Node() {
            children = new Node[26];
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            eow = false;
        }

    }

    static Node root = new Node();

    private static void insert(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a'; // 'a' - 'a' = 0
            if (current.children[idx] == null) {
                current.children[idx] = new Node();
            }

            if (i == word.length() - 1) {
                current.children[idx].eow = true;
            }

            current = current.children[idx];
        }
    }

    private static boolean search(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (current.children[idx] == null) {
                return false;
            }

            if (i == word.length() - 1 && current.children[idx].eow == false) {
                return false;
            }

            current = current.children[idx];
        }

        return true;
    }

    // google
    private static boolean wordBreak(String word) {
        if (word.length() == 0) {
            return true;
        }

        for (int i = 1; i <= word.length(); i++) {
            if (search(word.substring(0, i)) && wordBreak(word.substring(i, word.length()))) {
                return true;
            }
        }

        return false;
    }

    private static boolean startsWith(String prefix) {
        Node curr = root;

        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                return false;
            }

            curr = curr.children[idx];
        }

        return true;
    }

    // Unique Substrings count
    // Microsoft, Google
    private static int countNodes(Node root) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                count += countNodes(root.children[i]);
            }
        }

        return count + 1;
    }

    private static String longestStringWithAllPrefix = "";

    private static void longestword(Node root, StringBuilder sb) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null && root.children[i].eow == true) {
                sb.append((char) (i + 'a'));
                if (sb.length() > longestStringWithAllPrefix.length()) {
                    longestStringWithAllPrefix = sb.toString();
                }
                longestword(root.children[i], sb);

                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {

        // Check if the given word could be broken into given words dict

        // String[] words = { "i", "like", "sam", "samsung", "mobile", "ice" };
        // for (String word : words) {
        // insert(word);
        // }
        // System.out.println(wordBreak("ilikesamsungmobile"));

        // -------------------------------------------
        // check if any of the word in the dic starts with given prefix

        System.out.println(startsWith("mo"));

        // -------------------------------------------
        // Count unique substrings of a given String
        // String str = "apple";
        // for (int i = 0; i < str.length(); i++) {
        // String substring = str.substring(i);
        // insert(substring);
        // }

        System.out.println(countNodes(root));

        // Longest word with all prefix
        String words[] = { "a", "banana", "app", "appl", "ap", "apply", "apple" };
        for (String word : words) {
            insert(word);
        }

        longestword(root, new StringBuilder(""));
        System.out.println(longestStringWithAllPrefix);

    }
}
