package application;
public class Node {
	Department element;
	Node left, right;
String name ,fileName;
	public Node(String name,String fileName) {
		this.name=name;
		this.fileName=fileName;

	}

	
	public Node() {
		
	}

	// insert method
	public void insert(Node data, Node root) {

		if (data.name.compareToIgnoreCase(
				root.name) >= 0) {
			if (root.right == null)
				root.right = data;
			else {
				insert(data, root.right);
				this.right = Balance(this.right);
			}
		} else {
			if (root.left == null)
				root.left = data;
			else {
				insert(data, root.left);
				this.left = Balance(this.left);
			}
		}
	}

	// return boolean to know if the node is leaf
	public boolean isLeaf() {
		return this.left == null && this.right == null;
	}

	// to compute height
	public int height() {
		int leftC = 0, RightC = 0;

		if (isLeaf()) {
			return 0;
		}
		if (left != null) {
			leftC = left.height();
		}
		if (right != null) {
			RightC = right.height();
		}
		if(leftC > RightC) {
			return leftC + 1;
		}else {
			return  RightC + 1;
		}
	}

	public Node rotateRightLeft(Node N) {
		Node c = N.right;
		N.right = rotateRight(c);
		return rotateLeft(N);
	}

	public Node rotateLeftRight(Node N) {
		Node c = N.left;
		N.left = rotateLeft(c);
		return rotateRight(N);
	}

	public Node rotateRight(Node N) {
		Node c = N.left;
		N.left = c.right;
		c.right = N;
		return c;

	}

	public Node rotateLeft(Node N) {
		Node c = N.right;
		N.right = c.left;
		c.left = N;
		return c;
	}

	public Node Balance(Node N) {
		int heightDiff = heightDiff(N);
		if (heightDiff > 1) {
			if (heightDiff(N.left) > 0) {
				N = rotateRight(N);
			} else {
				N = rotateLeftRight(N);
			}
		} else if (heightDiff < -1) {
			if (heightDiff(N.right) < 0) {
				N = rotateLeft(N);
			} else {
				N = rotateRightLeft(N);
			}
		}
		return N;
	}

	// compute the different between left and right height
	public int heightDiff(Node N) {
		int first = 0, second = 0;
		if (N == null) {
			return 0;
		}
		if (N.left != null) {
			first = N.left.height();
		}
		if (N.right != null) {
			second = N.right.height();
		}
		int diff = first - second;
		return diff;
	}

	// find method that talk a string and return node
	public Node find(String data) {
		if (this.element.depName.compareTo(data) == 0)
			return this;
		if (data.compareTo(this.element.depName) < 0 && left != null) {
			return left.find(data);
		}
		if (right != null)
			return right.find(data);
		return null;

	}

}
