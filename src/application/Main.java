package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
	Stage s1;
	Department department;
	ComboBox<String> comp = new ComboBox<>();
	AvlTree av = new AvlTree();
	@Override

	public void start(Stage stage) throws FileNotFoundException {
		s1 = stage;

		stage.setTitle("Departments");
		BorderPane root = new BorderPane();

		Button b1 = new Button("Department  ");
		b1.setPrefSize(200, 40);
		b1.setPrefSize(400,50);
		b1.setStyle("-fx-background-color:blue ");
		Button b2 = new Button("  Student    ");
		b2.setPrefSize(200, 40);
		b2.setPrefSize(400,50);
		b2.setStyle("-fx-background-color:blue ");
		Button b3 = new Button("  Exit       ");
		b3.setPrefSize(200, 40);
		b3.setPrefSize(400,50);
		b3.setStyle("-fx-background-color:red ");

		b1.setOnAction(e -> {
			Department(new Stage());
			s1.close();
		});
		b2.setOnAction(e -> {
			student(new Stage());
			s1.close();
		});
		b3.setOnAction(e -> {
			s1.close();
		});


		VBox v1 = new VBox(30);
		v1.setAlignment(Pos.CENTER);
		v1.getChildren().addAll(b1, b2, b3);
		root.setCenter(v1);

		Scene sc = new Scene(root, 400, 400);

		stage.setScene(sc);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void Department(Stage stage) {
		VBox ro = new VBox(20);
		ro.setAlignment(Pos.CENTER);


		Button b1 = new Button("insert new Department");
		b1.setPrefSize(160, 40);
		b1.setOnAction(e -> {
			insert(new Stage());
			stage.close();
		});
		b1.setPrefSize(400,50);
		b1.setStyle("-fx-background-color:blue ");
		Button b2 = new Button("print department : ");
		b2.setPrefSize(160, 40);
		b2.setPrefSize(400,50);
		b2.setStyle("-fx-background-color:blue ");

		b2.setOnAction(e -> {
			print(new Stage());
			stage.close();
		});


		Button b3 = new Button("Serch for department ");
		b3.setPrefSize(160, 40);

		b3.setPrefSize(400,50);
		b3.setStyle("-fx-background-color:blue ");
		b3.setOnAction(e -> {
			serch(new Stage());
			stage.close();
		});
		Button b4 = new Button("Delete a specific departmen ");
		b4.setPrefSize(160, 40);

		b4.setPrefSize(400,50);
		b4.setStyle("-fx-background-color:blue ");
		b4.setOnAction(e -> {
			delet(new Stage());
			stage.close();
		});

		Button b5 = new Button("Calculate tree height ");
		b5.setPrefSize(160, 40);

		b5.setOnAction(e -> {
			findCalculit(new Stage());
			stage.close();
		});
		b5.setPrefSize(400,50);
		b5.setStyle("-fx-background-color:blue ");


		Button b6=new Button("back");
		b6.setPrefSize(400,50);
		b6.setStyle("-fx-background-color:red ");



		b6.setOnAction(e -> {

			try {
				start(new Stage());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			stage.close();
		});

		ro.getChildren().addAll(b1, b2, b3, b4, b5, b6);

		Scene sc = new Scene(ro, 500, 500, Color.ALICEBLUE);
		stage.setScene(sc);
		stage.show();

	}

	public void insert(Stage st) {
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		Label l1 = new Label("Insert Name department");
		TextField tex = new TextField();
		Label l2 = new Label("Name department file");
		TextField tex1 = new TextField();
		root.setVgap(20);
		root.setHgap(20);
		Label l3 = new Label("Is Add!!!");
		TextField tex2 = new TextField();
		Button back = new Button("back");
		back.setPrefSize(200,50);
		back.setStyle("-fx-background-color:red ");


		back.setOnAction(e -> {
			Department(new Stage());
			st.close();
		});
		Button add = new Button("add ");
		add.setPrefSize(200,50);
		add.setStyle("-fx-background-color:blue ");
		Button delet = new Button("clear");

		delet.setPrefSize(200,50);
		delet.setStyle("-fx-background-color:blue ");
		delet.setOnAction(e -> {
			tex1.clear();
			tex.clear();
			tex2.clear();
		});
		add.setOnAction(e -> {

			try {

				File myObj = new File(tex1.getText() + ".txt");
				
				if (myObj.createNewFile()) {
					FileWriter myWriter = new FileWriter(myObj);
					System.out.println("0");
					Node T = new Node(tex.getText(), tex1.getText());
					av.insert(T);
					System.out.println("File created: " + myObj.getName());
					comp.getItems().add(tex1.getText());
					tex2.setText("Add is done");
					myWriter.close();
				} else {
					System.out.println("1");
					System.out.println("File already exists.");
					tex2.setText("file is already exit");

				}
			} catch (IOException o) {
				System.out.println("An error occurred.");
				o.printStackTrace();
			}
		});

		root.addRow(0, l1, tex);
		root.addRow(2, l2, tex1);
		root.addRow(4, l3, tex2);
		root.addRow(6, back, add, delet);

		Scene sc = new Scene(root, 400, 400);
		st.setScene(sc);
		st.show();
	}

	public void print(Stage st) {
		BorderPane root1 = new BorderPane();
		root1.setPadding(new Insets(20));
		VBox Vbox = new VBox(40);
		Vbox.setAlignment(Pos.CENTER);

		Button l1 = new Button("print :");
		l1.setPrefSize(200,50);
	    l1.setStyle("-fx-background-color:blue ");
		TextArea tex = new TextArea();
		tex.setPrefSize(500,300);
		l1.setOnAction(e -> {
			tex.appendText(av.printIn(av.getRoot()));
			av.out="";

		});
		Button back = new Button("back ");
		Vbox.getChildren().addAll(l1, back);
		back.setPrefSize(200,50);
     	back.setStyle("-fx-background-color:red ");

		root1.setCenter(tex);
		root1.setLeft(Vbox);
		back.setOnAction(e -> {
			Department(new Stage());
			st.close();
		});
		Scene sc = new Scene(root1, 600, 500,Color.BLACK);
		st.setScene(sc);
		st.show();
		

	}

	public void serch(Stage st) {
		GridPane root = new GridPane();
		root.setVgap(20);
		root.setHgap(20);
		root.setAlignment(Pos.CENTER);
		Label l1 = new Label("The Name Of Department:");
		TextField tex = new TextField();
		root.addRow(2, l1, tex);
		Button b1 = new Button("Serch : ");
		b1.setPrefSize(400,50);
		b1.setStyle("-fx-background-color:blue ");
		TextArea te = new TextArea();
		
		te.setPrefSize(400,100);
		root.addRow(4, b1, te);
		b1.setOnAction(e -> {
			if (av.find(tex.getText()) == null) {
				te.setText("not found");
				return;
			}
			te.setText(av.find(tex.getText()).name);
		});
		Button back = new Button("back ");
		back.setPrefSize(400,50);
     	back.setStyle("-fx-background-color:red ");
		root.addRow(6, back);
		back.setOnAction(e -> {
			Department(new Stage());
			st.close();
		});
		Scene sc = new Scene(root, 850, 500);
		st.setScene(sc);
		st.show();
	}

	public void delet(Stage st) {
		
		GridPane root = new GridPane();
		Label l1 = new Label("Name Of Department : ");
		TextArea tex1 = new TextArea();
		tex1.setPrefSize(500,300);
		TextField tex = new TextField();
		root.setVgap(20);
		root.setHgap(20);
		root.setAlignment(Pos.CENTER);
		root.addRow(2, l1, tex);
		Button deleat = new Button("delet the Department");
		deleat.setPrefSize(400,50);
		deleat.setStyle("-fx-background-color:blue ");
		deleat.setOnAction(e -> {
			av.delete(tex.getText());
			String b=tex.getText()+".txt";
			File file=new File(b);
			file.delete();
		});
		Button check = new Button("check ");
		check.setPrefSize(400,50);
		check.setStyle("-fx-background-color:blue ");
		check.setOnAction(e -> {

			if (av.find(tex.getText()) == null) {
				tex1.setText("true ");

				return;
			}
			else {
				tex1.setText("false");
			}
		});
		Button back = new Button("back");
		back.setPrefSize(400,50);
     	back.setStyle("-fx-background-color:red ");
		back.setOnAction(e -> {
			Department(new Stage());
			st.close();
		});
		root.addRow(3, deleat);
		root.addRow(5, check, tex1);
		root.addRow(6, back);
		Scene sc = new Scene(root, 950, 500);
		st.setScene(sc);
		st.show();
	}

	public void findCalculit(Stage st) {
		GridPane root = new GridPane();
		root.setVgap(20);
		root.setHgap(20);
		Label l1 = new Label("           Calculate tree height:");
		TextField tex = new TextField();
		Button find = new Button("find ");
		find.setPrefSize(200,50);
		find.setStyle("-fx-background-color:blue ");
		
		find.setOnAction(e -> {
			tex.setText(String.valueOf(av.height()));
		});
		Button back = new Button("back");
		back.setPrefSize(200,50);
     	back.setStyle("-fx-background-color:red ");
		back.setOnAction(e -> {
			Department(new Stage());
			st.close();
		});
		root.addRow(2, l1, tex);
		root.addRow(4, find, back);
		Scene sc = new Scene(root, 400, 400);
		st.setScene(sc);
		st.show();

	}

	public void student(Stage st) {
		System.out.println(av.getRoot() +" root st");
		VBox root = new VBox(10);
		root.setAlignment(Pos.CENTER);

		Label l1 = new Label("Student : ");
		l1.setAlignment(Pos.CENTER);
		l1.setFont(new Font(20));

		Button b1 = new Button("Print hashed table ");
		b1.setPrefSize(500,50);
		b1.setStyle("-fx-background-color:blue ");
		

		b1.setOnAction(e -> {
			printGHashedTable(new Stage());
			st.close();
		});
		Button b0=new Button("print out used hash Table ");
		b0.setPrefSize(500,50);
		b0.setStyle("-fx-background-color:blue ");
		b0.setOnAction(e->{
			printOutHashTable(new Stage());
			st.close();
		});
		Button b=new Button("read from file ");
		b.setPrefSize(500,50);
		b.setStyle("-fx-background-color:blue ");
		b.setOnAction(e->{
			readFromFile(new Stage());
			st.close();
		});
		
		Button b2 = new Button("Print out table size. ");
		b2.setPrefSize(500,50);
		b2.setStyle("-fx-background-color:blue ");
	

		b2.setOnAction(e -> {
			printSize(new Stage());
			st.close();
		});

		Button b3 = new Button("Insert a new record to hash table ");
	

		b3.setOnAction(e -> {
			insertStudent(new Stage());
			st.close();
		});
		b3.setPrefSize(500,50);
		b3.setStyle("-fx-background-color:blue ");
		Button b4 = new Button("Search for a specific record. ");
		

		b4.setPrefSize(500,50);
		b4.setStyle("-fx-background-color:blue ");
		b4.setOnAction(e -> {
			SearchForStudent(new Stage());
			st.close();
		});
//		Button b5 = new Button("Delete a specific record ");
//		
//
//		b5.setPrefSize(500,50);
//		b5.setStyle("-fx-background-color:blue ");
		Button b6 = new Button("back ");
		b6.setPrefSize(500,50);
		b6.setStyle("-fx-background-color:blue ");
		b6.setOnAction(e -> {
			try {
				start(new Stage());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			st.close();
		});

	
		root.getChildren().addAll(b,b0,b1, b2, b3, b4, b6);
		BorderPane pane = new BorderPane();
		pane.setCenter(root);
		root.setAlignment(Pos.CENTER);
		Scene sc = new Scene(pane, 700, 500, Color.ALICEBLUE);
		st.setScene(sc);
		st.show();
	}
	public void printOutHashTable(Stage st) {
		GridPane root=new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setVgap(20);
		root.setHgap(20);
		Button b1=new Button("print ");
		b1.setPrefSize(200,50);
		b1.setStyle("-fx-background-color:blue ");
		TextField tex=new TextField();
		b1.setOnAction(e->{
			tex.setText("qaudratic function");

		});


		Button  back=new Button("Back");
		back.setPrefSize(200,50);
		back.setStyle("-fx-background-color:red ");
		back.setOnAction(e->{
			student(new Stage());
			st.close();
		});
		root.addRow(2, b1,tex);
		root.addRow(4,back);
		Scene sc=new Scene(root,400,300);
		st.setScene(sc);
		st.show();
	}
	public void readFromFile(Stage st){

		GridPane root=new GridPane();
		root.setHgap(20);
		root.setVgap(20);
		root.setAlignment(Pos.CENTER);
		Label l1=new Label("      select file Department");
		l1.setPrefSize(200,50);
		l1.setStyle("-fx-background-color:blue ");
		Button b1=new Button(" read from file ");
		b1.setPrefSize(200,50);
		b1.setStyle("-fx-background-color:blue ");
		TextField tex=new TextField();
		root.addRow(2, l1,comp);
		root.addRow(4, b1,tex);
		Button back=new Button("back ");
		back.setPrefSize(200,50);
		back.setStyle("-fx-background-color:blue ");

		root.addRow(5, back);
		b1.setOnAction(e->{
			String h=comp.getValue();


			try {

				System.out.println(av.ReadFromFile(h));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// TODO Auto-generated catch block


		});
		back.setOnAction(e->{
			student(new Stage());
			st.close();
		});
		Scene sc=new Scene(root,400,400);
		st.setScene(sc);
		st.show();



	}

	public void insertStudent(Stage st) {

		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		Label l1 = new Label("Full Name");
		TextField tex = new TextField();
		Label l2 = new Label("Student Id");
		TextField tex1 = new TextField();
		Label l4 = new Label("Averge");
		TextField tex4 = new TextField();
		Label l5 = new Label("Gender");
		TextField tex5 = new TextField();
		Label l6 = new Label("Department");

		RadioButton r1 = new RadioButton("Male");
		RadioButton r2 = new RadioButton("Female");
		ToggleGroup tg = new ToggleGroup();

		r1.setToggleGroup(tg);
		r2.setToggleGroup(tg);
		root.setVgap(20);
		root.setHgap(20);
		Label l3 = new Label("is Add ");
		TextField tex2 = new TextField();
		Button back = new Button("back");
		back.setPrefSize(200,50);
		back.setStyle("-fx-background-color:red");

		back.setOnAction(e -> {
				student(new Stage());
			st.close();
		});
		Button add = new Button("add ");
		add.setPrefSize(200,50);
		add.setStyle("-fx-background-color:blue ");
		add.setOnAction(e->{
			String fullname=tex.getText();
			int id=Integer.parseInt(tex1.getText());
			double avg=Double.parseDouble(tex4.getText());
			String gander;
			if(r1.isSelected()) {
				gander="m";
			}
			else {
				gander="f";

			}
			Students stt=new Students(fullname, id, avg, gander);
			String dep=comp.getValue();
			try {
				av.addRecored(dep, stt);
				FileWriter fil=new FileWriter(comp.getValue()+".txt",true);
				fil.write(stt.toString());
				fil.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		root.addRow(0, l1, tex);
		root.addRow(1, l2, tex1);
		root.addRow(2, l4, tex4);
		root.addRow(3, l5, r1, r2);

		root.addRow(4, back, add);
		root.addRow(5, l6, comp);



		Scene sc = new Scene(root, 600, 400);
		st.setScene(sc);
		st.show();
	}

	public void printGHashedTable(Stage st) {

		GridPane root = new GridPane();
		Button pri = new Button("print ");
		pri.setPrefSize(200,50);
		pri.setStyle("-fx-background-color:blue ");
		TextArea tex = new TextArea();
		tex.setPrefSize(300,300);
		root.setVgap(20);
		root.setHgap(20);
		root.setAlignment(Pos.CENTER);
		root.addRow(2, pri, tex);
		pri.setOnAction(e->{
			tex.setText(av.printHash(av.getRoot()));
			av.out="";
		});
		Button back = new Button("back ");
		back.setPrefSize(200,50);
		back.setStyle("-fx-background-color:red ");
		back.setOnAction(e -> {
			student(new Stage());
			st.close();
		});
		root.addRow(3, back);


		Scene sc = new Scene(root, 850, 500);
		st.setScene(sc);
		st.show();
	}

	public void printSize(Stage st) {
		GridPane root = new GridPane();
		root.setVgap(20);
		root.setHgap(20);
		Button b1 = new Button("print ");
		b1.setPrefSize(200,50);
		b1.setStyle("-fx-background-color:blue ");
		TextArea tex = new TextArea();
		b1.setOnAction(e->{
				
		});
		root.addRow(4, b1, tex);
		Button b2 = new Button("back ");
		b2.setPrefSize(200,50);
		b2.setStyle("-fx-background-color:red ");
		b2.setOnAction(e -> {
			student(new Stage());
			st.close();
		});

		root.addRow(5, b2);
		Scene sc = new Scene(root, 850, 500);
		st.setScene(sc);
		st.show();
	}

	public void SearchForStudent(Stage st) {
		GridPane root = new GridPane();
		root.setVgap(20);
		root.setHgap(20);
		root.setAlignment(Pos.CENTER);
		Label l1 = new Label("name department ");

		root.addRow(2, l1, comp);
		Label l2 = new Label("name student ");
		TextField tex2 = new TextField();
		root.addRow(3, l2, tex2);
		Button b = new Button("search  ");
		TextArea tex3 = new TextArea();
		b.setOnAction(e->{
			String na=comp.getValue();
			String stName=tex2.getText();

			tex3.setText(av.findStudenthash(na, stName));


		});


		root.addRow(4, b, tex3);
		Button back = new Button("back");
		root.addRow(5, back);
		back.setOnAction(e -> {
			student(new Stage());
			st.close();
		});
		Scene sc = new Scene(root, 500, 500);
		st.setScene(sc);
		st.show();

	}
}