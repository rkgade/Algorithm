// Java program to find count of single valued subtrees
// A Tree node
class Node{
    Node left;
    Node right;
    int data;
        
    // Utility function to create a new node
    public static Node newNode(int data){
        Node n = new Node();
        n.left = null;
        n.right = null;
        n.data = data;
        return n;
    }
}

class BinaryTree {
    
        // This function increments count by number of single 
    // valued subtrees under root. It returns true if subtree 
    // under root is Singly, else false.
    static boolean countSingleRec(Node root, int[] count)
    {
        // Return false to indicate NULL
        if (root == null)
           return true;

        // Recursively count in left and right subtrees also
        boolean left = countSingleRec(root.left, count);
        boolean right = countSingleRec(root.right, count);

        // If any of the subtrees is not singly, then this
        // cannot be singly.
        if (left == false || right == false)
           return false;

        // If left subtree is singly and non-empty, but data
        // doesn't match
        if (root.left != null && root.data != root.left.data)
            return false;

        // Same for right subtree
        if (root.right != null && root.data != root.right.data)
            return false;

        // If none of the above conditions is true, then
        // tree rooted under root is single valued, increment
        // count and return true.
        ++count[0];
        return true;
    }

    // This function mainly calls countSingleRec()
    // after initializing count as 0
    static int countSingle(Node root)
    {
        // Initialize result
        int count[] = new int[1]; 
        count[0]=0;

        // Recursive function to count
        countSingleRec(root, count);
        return count[0];
        
    }
     
       
    public static void main(String args[]){
        Node root = null;
        root = Node.newNode(5);
        root.left = Node.newNode(4);
        root.right = Node.newNode(5);
        root.left.left = Node.newNode(4);
        root.left.right = Node.newNode(4);
        root.right.right = Node.newNode(5);
        System.out.println("Count of Single Valued Subtrees is " + countSingle(root));
 
    }
}
