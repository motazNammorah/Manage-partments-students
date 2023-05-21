package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;



public class AvlTree {
	private Node root;
	String out = "";
	
	ArrayList<Department>array=new ArrayList<>();

	public AvlTree() {

	}

	public void insert(Node x) {
		if(x==null){
			return;
		}
		if (root == null)
			this.root = x;
		else
			root.insert(x, this.root);
		root.Balance(x);
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	/*public Node find(String m) {

		if (root != null) {
			return root.find(m);
		} else {
			return null;
		}
	}*/

	public String printIn(Node T) { 

		if (T != null) {
			printIn(T.left);
			out += T.name+"\r\n";
			
			printIn(T.right);
		}

		return out;

	}
	public String printHash(Node T) { 

		if (T != null) {
			printHash(T.left);
			for(int i=0;i<array.size();i++) {
				if(array.get(i).depName.equals(T.name)) {
					out+=T.name+" : \n"+array.get(i).st.printAll()+"\r\n";
				}
			}
			
			
			printHash(T.right);
		}

		return out;

	}
	

	public Node getRoot() {
		return root;
	}

	

	public void delete(String data) {
		Node current = this.root;
		Node parent = this.root;
		boolean isLeftChild = false;
		if (current == null)
			return;

		while (current != null
				&& current.name.equals(data) == false) {
			parent = current;
			if (data.compareToIgnoreCase(current.name) < 0) {
				current = current.left;
				isLeftChild = true;
			} else {
				current = current.right;
				isLeftChild = false;
			}
		}
		if (current == null)
			return ;

		if (current.left == null && current.right == null) {
			if (current == root) {
				root = null;
			} else {
				if (isLeftChild)
					parent.left = null;
				else
					parent.right = null;
			}
		} else if (current.right == null) {
			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = (current.left);
			}
		} else if (current.left == null) {// current has right child
			if (current == root) {
				root = current.right;
			} else if (isLeftChild) {
				parent.left = (current.right);
			} else {
				parent.right = (current.right);
			}
		} else {

			Node successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			successor.left = current.left;

		}
		this.root = BL(root);
	}

	

	private Node getSuccessor(Node node) {
		Node parentOfSuccessor = node;
		Node successor = node;
		Node current = node.right;
		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.left;
		}
		if (successor != node.right) {
			parentOfSuccessor.left = (successor.right);
			successor.right = node.right;
		}
		return successor;
	}

	public Node BL(Node N) {
		if (N != null) {
			N.left = (BL(N.left));

			N = BR(N);
			N = N.Balance(N);
			N.right = (BL(N.right));
		}
		return N;
	}

	public Node BR(Node N) {
		if (N != null) {
			N.right = (BR(N.right));
			Node r = N;
			N = N.Balance(N);
			if (r == root) {
				return N;
			}
			N.left = (BR(N.left));
		}
		return N;
	}
	
	public int height() {
		if(root==null){
			return 0;
		}
		return root.height();
	}
	Node find(String name ) {
	    Node current = root;
	    while (current != null) {
	        if (current.name.equals(name)) {
	        	return current;
	          
	        }
	 
	        if(current.name.equals(name)==false) {
	        	current=current.right;
	        }
	        else {
	        	current=current.left;
	        }
	      
	    }
	    return null;
	}
	
	public String findStudenthash(String name,String studentName) {
		String ii="";
		for(int i=0;i<array.size();i++) {
			if(array.get(i).depName.equals(name)) {
				ii=array.get(i).st.serch(studentName);
			}
		}
		return ii;
	}
	
	public boolean ReadFromFile(String name ) throws FileNotFoundException {
		Node cu=find(name);
		if(cu==null) {
			System.out.println("File does not exit");
			return false;
		}
		else {
			File file=new File(cu.fileName);
			if(!file.exists()) {
				System.out.println("file does not exit");
				System.exit(0);
				return false;
			}
			Scanner out=new Scanner(file);
			HashTable arr=new HashTable(3);
			while(out.hasNext()) {
				String na=out.nextLine();
				String [] array=na.split(" ");
				String name1=array[0];
				int id=Integer.parseInt(array[1]);
				double avg=Double.parseDouble(array[2]);
				String ch=array[3];
				Students st=new Students(name1, id, avg, ch);
				arr.addRecord(st);
				arr.check();
			}
			array.add(new Department(name, arr));
			
			
		}
		return true;
	}
	public boolean addRecored(String name,Students st) throws IOException {

		for(int i=0;i<array.size();i++) {
			if(array.get(i).depName.equals(name)) {
				System.out.println("======================");

				Node fin=find(name);
			
				FileWriter fil=new FileWriter(new File(fin.fileName),true);
				fil.write("\n" + st.toString());
				array.get(i).st.addRecord(st);
				fil.close();
				return true;
			}
		}
		return false;
	}
	
	
}
