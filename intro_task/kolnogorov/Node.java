public class Node {
    public NodeType type;
    public Node next = null;

    public char value = ' ';
    public Node sublist = null;

    Node(String str) {
        if (str.length() == 0) {
            return;
        }

        char symb = str.charAt(0);

        if (symb == '(') {
            this.type = NodeType.SUBLIST;

            int idxClosing = findClosing(str);

            String substringNext = str.substring(idxClosing + 1);
            if (substringNext.length() > 0) {
                this.next = new Node(substringNext);
            }

            if (idxClosing == 1) {
                this.sublist = null;
                return;
            }

            this.sublist = new Node(str.substring(1, idxClosing));
        } else {
            this.type = NodeType.ATOM;
            this.value = symb;
            if (str.length() > 1) {
                this.next = new Node(str.substring(1));
            } else {
                this.next = null;
            }
        }

    }

    public boolean equals(Node other) {
        // Type mismatch
        if (this.type != other.type) {
            return false;
        }

        // values do not match
        if (this.type == NodeType.ATOM && this.value != other.value) {
            return false;
        }

        // different next
        if ((this.next != null) ^ (other.next != null)) {
            return false;
        }

        // different sublist
        if ((this.sublist != null) ^ (other.sublist != null)) {
            return false;
        }

        return true;
    }

    private int findClosing(String str) {
        int countOpen = 0;

        for (int idx = 0; idx < str.length(); idx++) {
            char c = str.charAt(idx);

            if (c == '(') {
                countOpen++;
            } else if (c == ')') {
                countOpen--;
            }

            if (countOpen == 0) {
                return idx;
            }
        }

        return -1;
    }
}
