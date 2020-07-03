public class Tree {
    private char root = '\0';
    Tree left;
    Tree right;

    public Tree(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        str = str.substring(1, str.length() - 1);
        if (str.length() == 0) {
            return;
        }
        if ((str.length() >= 1) && (str.charAt(0) != '(') && (str.charAt(0) != ')') && (str.charAt(0) != '#')){
            this.root = str.charAt(0);
            if (str.length() > 1) {
                if (str.charAt(1) == '#') {
                    right = new Tree(str.substring(2));
                } else if (str.charAt(1) == '(') {
                    int br = 1, j;
                    for (j = 2; j < str.length(); j++) {
                        if (str.charAt(j) == '(') {
                            br++;
                        }
                        if (str.charAt(j) == ')') {
                            br--;
                        }
                        if (br == 0) {
                            break;
                        }
                    }
                    left = new Tree(str.substring(1, j + 1));
                    right = new Tree(str.substring(j + 1));
                }
            }
        }
    }

    public boolean isEmpty() {
        return ((root == '\0') && (left == null) && (right == null));
    }

    public boolean similar(Tree oth) {
        if (oth == null) {
            return false;
        }
        if (oth.isEmpty()) {
            return (this.isEmpty());
        }
        return (Tree.similar(right, oth.right) && Tree.similar(left, oth.left));
    }

    public static boolean similar(Tree A, Tree B) {
        if (A == null) {
            return (B == null);
        }
        return A.similar(B);
    }
}
