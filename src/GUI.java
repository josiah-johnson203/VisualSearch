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
import javafx.stage.Stage;

public class GUI extends Application
{
	private final double WIDTH;
	private final double HEIGHT;
	
	private final double PANE_X;
	private final double PANE_Y;
	
	private final double MAZE_START_X;
	private final double MAZE_START_Y;
	
	
	private Pane pane;
	
	private Scene scene;
	
	private Maze maze;
	
	/*
	 * default empty constructor
	 */
	public GUI()
	{
		WIDTH = 750;
		HEIGHT = 500;
		
		PANE_X = 0.6*WIDTH;
		PANE_Y = HEIGHT;
		
		MAZE_START_X = 0.2*PANE_X;
		MAZE_START_Y = 0.2*PANE_Y;
	}
	
	public GUI(int width, int height)
	{
		WIDTH = width;
		HEIGHT = height;
		
		PANE_X = (int)0.6*WIDTH;
		PANE_Y = HEIGHT;
		
		MAZE_START_X = (int)0.2*PANE_X;
		MAZE_START_Y = (int)0.2*PANE_Y;
	}
	
	private void initializeGUI(Stage primary)
	{
		BorderPane root = new BorderPane();
		
		pane = new Pane();
		pane.setMinSize(PANE_X, PANE_Y);
		
		Line line = new Line();
		
		line.setStartX(MAZE_START_X);
		line.setStartY(MAZE_START_Y);
		
		line.setEndX(MAZE_START_X + 40);
		line.setEndY(MAZE_START_Y + 40);
		
		pane.getChildren().add(line);
		
		
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
		btnQuit.setOnAction(event -> {
			primary.close();
		});
		
		root.setCenter(pane);
		root.setRight(buttons);
		
		scene = new Scene(root, WIDTH, HEIGHT);
		
	}
	
	private void drawMaze()
	{
		
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
