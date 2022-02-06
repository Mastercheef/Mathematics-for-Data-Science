import java.util.NoSuchElementException;
import java.util.Random;

public class Suchbaum<T extends Comparable<T>> extends Binaerbaum<T> {
	public void insert(T e) {
		if(root == null) root = new Node(e);
		else insert(root, e);
	}

	private void insert(Node n, T e) {
		assert n != null;

		int comp = e.compareTo(n.content);

		if(comp == 0) throw new IllegalArgumentException(e + " ist bereits enthalten!");
		else if(comp < 0) {
			if(n.left == null) n.left = new Node(e);
			else insert(n.left, e);
		} else {
			if(n.right == null) n.right = new Node(e);
			else insert(n.right, e);
		}
	}

	public void remove(T e) {
		if(root == null) throw new NoSuchElementException(e + " ist nicht enthalten!");

		int comp = e.compareTo(root.content);

		if (comp == 0) {
			if (root.left == null) {
				root = root.right;
			} else if (root.right == null) {
				root = root.left;
			} else {
				Node min = min(root.right);

				root.content = min.content;
				remove(root.right, root, min.content);
			}
		} else if (comp < 0){
			remove(root.left, root, e);
		} else {
			remove(root.right, root, e);
		}
	}

	private void remove(Node n, Node parent, T e) {
		if(n == null) throw new NoSuchElementException(e + " ist nicht enthalten!");

		int comp = e.compareTo(n.content);

		if(comp == 0) {
			if(n.left == null || n.right == null) { // leaf or half leaf
				Node rep = n.left == null ? n.right : n.left; // null iff n is a leaf

				assert parent.left == n || parent.right == n : "One of the recursive calls is messed up.";

				// replace the reference to n in n's parent
				if(parent.left == n) parent.left = rep;
				else parent.right = rep;
			} else { // inner node
				Node min = min(n.right); // find the direct successor

				n.content = min.content; // put the successor's value into n, overwriting the value to delete
				remove(n.right, n, min.content); // remove the node, the successor was in originally
			}
		} else if(comp < 0) { // traverse down to the left
			remove(n.left, n, e);
		} else { // traverse down to the right
			remove(n.right, n, e);
		}
	}

	private Node min(Node n) {
		while(n.left != null) { // move left until we find a half leaf
			n = n.left;
		}

		return n;
	}

	public boolean contains(T e) {
		return contains(root, e);
	}

	private boolean contains(Node n, T e) {
		if(n == null) return false; // we moved past a leaf node, without finding the value. => its not in the tree

		int comp = e.compareTo(n.content);

		if(comp == 0) return true; // element found
		else if(comp < 0) return contains(n.left, e); // traverse down to the left
		else return contains(n.right, e); // traverse down to the right
	}

	public static void main(String[] args) {
		random(10);
	}
	public static void random(int n) {
		Suchbaum<Integer> s = new Suchbaum<>();
		Random r = new Random();

		for(int i = 0; i < n; ) {
			int num = r.nextInt(n);

			if(!s.contains(num)) {
				i++;
				s.insert(num);
			}
		}

		System.out.println(s.myToString());

		for(int i = 0; i < n; ) {
			int num = r.nextInt(n);
			if(s.contains(num)) {
				i++;
				System.out.println("Removing " + num);
				s.remove(num);
				System.out.println(s.myToString());
			}
		}
	}
}
