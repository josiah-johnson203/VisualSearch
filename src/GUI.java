import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application
{
	private final int WIDTH = 450;
	private final int HEIGHT = 450;
	
	private Canvas canvas;
	
	private Scene scene;
	
	private Maze maze;
	
	public GUI()
	{
		initializeGUI();
	}
	
	private void initializeGUI()
	{
		VBox buttons = new VBox();
		
		Button btnReset = new Button("Reset Maze");
		Button btnImport = new Button("Import Maze");
		Button btnFindPath = new Button("Find Path");
		
		Button btnQuit = new Button("Quit");
		
	}
	
	@Override
	public void start(Stage primary) throws Exception 
	{	
		primary.setTitle("Visual Search Algorithm");
		primary.setScene(scene);
		
		primary.show();
	}
	
}
