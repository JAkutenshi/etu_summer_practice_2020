public class HierarchyList {
    public Node root;

    HierarchyList(String str) {
        str = str.replaceAll(" ", "");
        root = new Node(str.substring(1, str.length() - 1));
    }

    static boolean isValidList(String str) {
        str = str.replaceAll(" ", "");
        int countOpen = 0;

        if (str.length() < 2 || str.charAt(0) != '(' || str.charAt(str.length() - 1) != ')') {
            return false;
        }

        for (char ch : str.toCharArray()) {
            if (ch == ')') {
                countOpen--;
            } else if (ch == '(') {
                countOpen++;
            }

            if (countOpen < 0) {
                return false;
            }
        }

        return (countOpen == 0);
    }

    public boolean equals(HierarchyList other) {
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        stack.push(other.root);

        while (!stack.isEmpty()) {
            Node left = stack.pop();
            Node right = stack.pop();

            if (!left.equals(right)) {
                return false;
            }

            if (left.next != null) {
                stack.push(left.next);
                stack.push(right.next);
            }

            if (left.type == NodeType.SUBLIST && left.sublist != null) {
                stack.push(left.sublist);
                stack.push(right.sublist);
            }
        }

        return true;
    }
}
