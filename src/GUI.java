import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class GUI extends Application
{
	private final int WIDTH = 750;
	private final int HEIGHT = 500;
	
	private final int PANE_X = (int)0.6*WIDTH;
	private final int PANE_Y = HEIGHT;
	
	
	private Pane pane;
	
	private Scene scene;
	
	private Maze maze;
	
	public GUI()
	{

	}
	
	private void initializeGUI(Stage primary)
	{
		HBox root = new HBox();
		
		pane = new Pane();
		pane.setPrefSize(PANE_X, PANE_Y);
		
		Line line = new Line();
		
		line.setTranslateX(0);
		line.setTranslateY(0);
		
		line.setEndX(40);
		line.setEndY(40);
		
		pane.getChildren().add(line);
		
		
		VBox buttons = new VBox();
		
		Button btnReset = new Button("Reset Maze");
		Button btnImport = new Button("Import Maze");
		Button btnFindPath = new Button("Find Path");
		
		Button btnQuit = new Button("Quit");
		
		buttons.getChildren().addAll(btnReset, btnImport, btnFindPath, btnQuit);
		
		
		
		btnQuit.setOnAction(event -> {
			primary.close();
		});
		
		root.getChildren().addAll(pane, buttons);
		
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
