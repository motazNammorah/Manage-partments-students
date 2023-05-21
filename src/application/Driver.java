package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class Driver {
	public static void main(String [] args) throws IOException {
		Scanner input=new Scanner(System.in);
		AvlTree av=new AvlTree();
		for(int i=0;i<2;i++) {
			System.out.println("Entre name and name file");
			Node newNode=new Node(input.next(),input.next());
			av.insert(newNode);
			
			
		}
		System.out.println(av.printIn(av.getRoot()));
		System.out.println(av.height());
		System.out.println("Enter name file ");
		String na=input.next();
		System.out.println(av.ReadFromFile(na));
		System.out.println(av.printHash(av.getRoot()));
	
	
	}

}
