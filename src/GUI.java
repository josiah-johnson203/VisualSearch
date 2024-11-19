import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends Application
{
	private final double WIDTH;
	private final double HEIGHT;

	private Scene scene;
	
	private Maze maze;
	
	/*
	 * default empty constructor
	 */
	public GUI() throws FileNotFoundException
	{
		WIDTH = 750;
		HEIGHT = 500;
		
		maze = new Maze(0.6*WIDTH, HEIGHT);
	}
	
	private void initializeGUI(Stage primary)
	{
		BorderPane root = new BorderPane();
		
		// -------------- BUTTON VBOX --------------
		VBox buttons = new VBox();
		buttons.setAlignment(Pos.TOP_CENTER);
		buttons.setPadding(new Insets(15));
		buttons.setSpacing(15);
		
		Button btnReset = new Button("Reset Maze");
		Button btnImport = new Button("Import Maze");
		Button btnFindPath = new Button("Find Path");
		
		Button btnQuit = new Button("Quit");
		
		buttons.getChildren().addAll(btnReset, btnImport, btnFindPath, btnQuit);
		
		
		// -------------- BUTTON LOGIC --------------
		
		btnReset.setOnAction(event -> {
			maze.clear();
		});
		
		btnImport.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(new File("."));

			File selectedFile = fileChooser.showOpenDialog(primary);
			
			try 
			{
				maze.loadMaze(selectedFile.getAbsolutePath());
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println(e.getMessage());
			}
		});
		
		btnFindPath.setOnAction(event -> {
			maze.findPath();
		});
		
		btnQuit.setOnAction(event -> {
			primary.close();
		});
		
		root.setCenter(maze);
		root.setRight(buttons);
		
		scene = new Scene(root, WIDTH, HEIGHT);
		
	}
	
	@Override
	public void start(Stage primary) throws Exception 
	{	
		initializeGUI(primary);
		
		primary.setTitle("Visual Search Algorithm");
		primary.setScene(scene);
		
		primary.show();
	}
	
}
