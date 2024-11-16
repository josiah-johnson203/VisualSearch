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
	private final int WIDTH;
	private final int HEIGHT;
	
	private final int PANE_X;
	private final int PANE_Y ;
	
	
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
		
		PANE_X = (int)0.6*WIDTH;
		PANE_Y = HEIGHT;
	}
	
	public GUI(int width, int height)
	{
		WIDTH = width;
		HEIGHT = height;
		
		PANE_X = (int)0.6*WIDTH;
		PANE_Y = HEIGHT;
	}
	
	private void initializeGUI(Stage primary)
	{
		BorderPane root = new BorderPane();
		
		pane = new Pane();
		pane.setMinSize(PANE_X, PANE_Y);
		
		Line line = new Line();
		
		line.setTranslateX(0);
		line.setTranslateY(0);
		
		line.setEndX(40);
		line.setEndY(40);
		
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
	
	@Override
	public void start(Stage primary) throws Exception 
	{	
		initializeGUI(primary);
		
		primary.setTitle("Visual Search Algorithm");
		primary.setScene(scene);
		
		primary.show();
	}
	
}
