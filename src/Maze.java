import java.util.Scanner;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;

/*
DESCRIPTION:
This class represents a maze containing a 2 dimensional array of cells. This class
contains methods to search through the maze and find the solution
*/
public class Maze extends Pane
{
	// first index is the row of the cell, and the second is the column is resides in
	private Cell[][] maze;
	
	// the number of rows and columns
	private int rows;
	private int columns;
	
	private final double WIDTH;
	private final double HEIGHT;
	
	private final double START_X;
	private final double START_Y;
	
	private static final double CELL_WIDTH = 30;
	private static final double CELL_HEIGHT = 30;
	
	private static final double RADIUS = 7.5;
	
	// true if a path has been found, false if not
	private boolean pathFound;
	
	/*
	 * initializes a Maze with a sent width and height for the GUI
	 */
	public Maze(double width, double height)
	{
		super();
		
		WIDTH = width;
		HEIGHT = height;
		
		setMinSize(WIDTH, HEIGHT);
		
		START_X = 0.2*WIDTH;
		START_Y = 0.2*HEIGHT;
		
		maze = null;
	}
	
	/*
	 * initializes a maze from the sent file name
	 */
	public Maze(String s, double width, double height) throws FileNotFoundException
	{
		super();
		
		WIDTH = width;
		HEIGHT = height;
		
		setMinSize(WIDTH, HEIGHT);

		START_X = 0.2*WIDTH;
		START_Y = 0.2*HEIGHT;
		
		pathFound = false;
		
		rows = 0;
		columns = 0;
		
		loadMaze(new Scanner(new File(s)));
	}
	
	/*
	 * initializes a maze from the sent file
	 */
	public Maze(Scanner s, double width, double height)
	{
		super();
		
		WIDTH = width;
		HEIGHT = height;
		
		setMinSize(WIDTH, HEIGHT);
		
		START_X = 0.2*WIDTH;
		START_Y = 0.2*HEIGHT;
		
		pathFound = false;
		
		rows = 0;
		columns = 0;
		
		loadMaze(s);
	}
	
	/*
	 * returns the maze this object represents as a string
	 */
	public String asText()
	{
		// character array for the text representation of the maze
		// the text array has numOfCells*2 +1 for the amount of columns
		// (one for a space left of each a cell and + 1 for the right of the last cell)
		char[][] characters = new char[rows][columns*2+1];
		
		// looping through each cell of the maze and finding its corresponding text
		for(int row = 0; row < rows; row++)
		{
			for(int column = 1; column < columns*2+1; column = column+2)
			{
				/*
				 * only text for the south, east and west directions needs to be written
				 * the algorithm will write from left to right, top to bottom
				 *
				 * the west direction of each cell will be based on the previous's cell
				 * east direction, which would be repeated computation
				 */
				
				// NOTE: (column-1)/2 is the corresponding cell in the maze array
				
				// ------------- SOUTH -------------
				if(maze[row][(column-1)/2].getNeighbor(Cell.SOUTH))
				{
					characters[row][column] = ' ';
				}
				else
				{
					characters[row][column] = '_';
				}
				
				// ------------- EAST -------------
				if(maze[row][(column-1)/2].getNeighbor(Cell.EAST))
				{
					characters[row][column+1] = ' ';
				}
				else
				{
					characters[row][column+1] = '|';
				}
				
				// if this cell is on the path out, replace its character with a hashtag
				if(maze[row][(column-1)/2].isOnPath())
				{
					characters[row][column] = '#';
				}
			} // for
		} // for
		
		for(int i = 0; i < characters.length; i++)
		{
			characters[i][0] = '|';
		}
		
		// if a path has not been found, the last cell is put as an empty space
		// to show the user where the maze ends
		
		// if a path has been found, this will overwrite the # symbol which is unwanted
		if(!pathFound)
		{
			characters[rows-1][columns*2-1] = ' ';
		}
		
		// all rows of the maze have numOfCells*2+1 columns except for the first one
		// which has numOfCells*2 columns
		
		// i am unsure why this is
		char[] topRow = new char[columns*2];
		
		// looping through the top array and creating an underscore at each cell column
		for(int i = 0; i < topRow.length; i++)
		{
			if(i%2 == 1 && i != 1)
			{
				topRow[i] = '_';
			}
			else
			{
				topRow[i] = ' ';
			}
		} // for
		
		String maze = new String(topRow) + "\n";
		
		// adding all the character rows into a single string
		for(int i = 0; i < characters.length; i++)
		{
			maze += new String(characters[i]) + "\n";
		}
		
		return maze;
	}
	
	/*
	 * using a breadth-first algorithm, this method searches through the maze and attempts
	 * to find a path out of it
	 * 
	 * if a path is found, the "pathFound" boolean is set to true and each cell in the path out
	 * is set to be on the path
	 */
	public void findPath()
	{
		// the set will contain every location that has already been entered into the queue at some point
		// so that the same cell isn't computed multiple times
		Set<Location> visitedLocations = new Set<Location>();
		// the queue contains cells that are waiting to be computed
		Queue<Location> queue = new Queue<Location>();
		
		// this queue will be used to play the search animation at the end of the program
		// the animation needs to be in the same order as the algorithm
		Queue<Location> animationQueue = new Queue<Location>();
		
		// this Location object will contain the current Location being worked on
		Location currentLocation = new Location(0, 0);
		// this Location will contain possible next locations, always adjacent to the current location
		// in some direction
		Location nextLocation = null;
		
		// this location represents the final location of the maze
		Location finalLocation = new Location(rows-1, columns-1);
		
		// queueing the first location and adding it to the set
		queue.enqueue(currentLocation);
		visitedLocations.enter(currentLocation);

		// continue to search the maze until the queue is empty or a solution is found
		while(!queue.isEmpty())
		{
			// grabbing a location from the queue
			currentLocation = queue.dequeue();
			
			// adding the location to the animation queue
			animationQueue.enqueue(currentLocation);
			
			// checking is this location is in the same location as the final location
			if(currentLocation.equals(finalLocation))
			{
				// setting the final locations previous location to the current locations previous
				// that way I can loop back through the path
				finalLocation.setPrevious(currentLocation.previous());
				
				break;
			}
			
			// getting the cell from the maze array that is located in the current Location
			Cell cell = maze[currentLocation.getRow()][currentLocation.getColumn()];
			
			// checking if there is a path in any of the 4 directions
			// if there is a path, and it hasn't already been visited, it is added to the queue
			
			if(cell.getNeighbor(Cell.NORTH))
			{
				nextLocation = new Location(currentLocation.getRow()-1, currentLocation.getColumn(), currentLocation);
				
				if(visitedLocations.enter(nextLocation))
				{
					queue.enqueue(nextLocation);
				}
			}
			
			if(cell.getNeighbor(Cell.SOUTH))
			{
				nextLocation = new Location(currentLocation.getRow()+1, currentLocation.getColumn(), currentLocation);
				
				if(visitedLocations.enter(nextLocation))
				{
					queue.enqueue(nextLocation);
				}
			}
			
			if(cell.getNeighbor(Cell.EAST))
			{
				nextLocation = new Location(currentLocation.getRow(), currentLocation.getColumn()+1, currentLocation);
				
				if(visitedLocations.enter(nextLocation))
				{
					queue.enqueue(nextLocation);
				}
			}
			
			if(cell.getNeighbor(Cell.WEST))
			{
				nextLocation = new Location(currentLocation.getRow(), currentLocation.getColumn()-1, currentLocation);
				
				if(visitedLocations.enter(nextLocation))
				{
					queue.enqueue(nextLocation);
				}
			}
		}
		
		// creating a timeline to play an animation of the maze being solved
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
			addCircle(animationQueue.dequeue(), Color.RED);
		}));
		
		// running the animation for each location in the animation queue
		timeline.setCycleCount(animationQueue.size());
		timeline.play();
		
		// updating the display once the animation is finished
		timeline.setOnFinished(event -> {
			if(pathFound)
			{
				updateDisplay();
			}
		});
		
		// if "finalLocation"'s previous location is null, a solution was not found and the method ends here
		if(finalLocation.previous() == null)
		{
			return;
		}
		
		// if a path was found, the boolean is set to true and each cell in the 
		// path has their corresponding boolean set to true
		pathFound = true;
		
		currentLocation = finalLocation;
		
		// looping backwards through the path and setting each cell to be on the path
		while(currentLocation != null)
		{
			maze[currentLocation.getRow()][currentLocation.getColumn()].setOnPath();
			
			currentLocation = currentLocation.previous();
		} // while
		
	}
	
	/*
	 * returns true if a path has been found, false if not
	 */
	public boolean pathFound()
	{
		return pathFound;
	}
	
	public void loadMaze(String s) throws FileNotFoundException
	{
		loadMaze(new Scanner(new File(s)));
	}
	
	/*
	 * method used to initialize the maze based on text input from a file
	 */
	public void loadMaze(Scanner s)
	{
		this.rows = Integer.parseInt(s.next());
		this.columns = Integer.parseInt(s.next());
		
		maze = new Cell[rows][columns];
		
		// the character representations of the maze have double the amount of cells
		// plus one characters in each row
		
		/*
		 * Example
		 * 
		 * |_ _ _  |  _ _   _  |
		 *  X X X X X X X X X X
		 *  
		 *  The X's are under the cells, which there are 10 of whilst
		 *  there is a total of 10*2 + 1 (21) characters is this row of the maze
		 *  
		 *  each cell resides at an even number, or an odd number if its located in an array
		 */
		char[][] characters = new char[rows][columns*2 + 1];
		
		// skipping the empty line after the rows and columns
		s.nextLine();
		
		// skipping this line since the top part of the maze is always blocked
		s.nextLine();
		
		for(int row = 0; row < rows; row++)
		{
			characters[row] = s.nextLine().toCharArray();
		}
		
		// looping over every character in the maze and creating a cell for each array
		for(int row = 0; row < rows; row++)
		{
			// each odd number in the character array represents a cell
			// so the iteration will start at 1 and iterate by adding 2
			for(int column = 1; column < columns*2+1; column=column+2)
			{
				Cell cell = new Cell();
								
				// setting the values for each direction
				
				// ----------------NORTH----------------
				// if this cell is in the top row, the north direction is always blocked
				// otherwise, check if the characters above the cell is blocked (an underscore)
				if(row == 0)
				{
					cell.setNeighbor(Cell.NORTH, false);
				}
				else if(characters[row-1][column] == '_')
				{
					cell.setNeighbor(Cell.NORTH, false);
				}
				else
				{
					cell.setNeighbor(Cell.NORTH, true);
				}
				
				// ----------------SOUTH----------------
				
				// the last row is always blocked from moving down unless
				// it is the final cell in the maze which I will account for later
				if(row == rows-1)
				{
					cell.setNeighbor(Cell.SOUTH, false);
				}
				else if(characters[row][column] == '_')
				{
					cell.setNeighbor(Cell.SOUTH, false);
				}
				else
				{
					cell.setNeighbor(Cell.SOUTH, true);
				}
				
				// ----------------EAST----------------
				
				// the last cell in each row is always blocked from moving to the right
				if(column == (columns*2+1) - 2)
				{
					cell.setNeighbor(Cell.EAST, false);
				}
				else if(characters[row][column+1] == '|')
				{
					cell.setNeighbor(Cell.EAST, false);
				}
				else
				{
					cell.setNeighbor(Cell.EAST, true);
				}
				
				// ----------------WEST----------------
				
				// the first cell in each row is always blocked from moving left
				if(column == 1)
				{
					cell.setNeighbor(Cell.WEST, false);
				}
				else if(characters[row][column-1] == '|')
				{
					cell.setNeighbor(Cell.WEST, false);
				}
				else
				{
					cell.setNeighbor(Cell.WEST, true);
				}
				
				maze[row][(column-1)/2] = cell;
			} // for
		} // for
		
		updateDisplay();
	}
	
	/*
	 * clears the found path, if there is one
	 */
	public void clear()
	{
		// if a path has not been found, the cleared display will look the same
		// therefore, there is no reason to redraw its
		if(!pathFound)
		{
			return;
		}
		
		pathFound = false;
		
		for(int row = 0; row < maze.length; row++)
		{
			for(int column = 0; column < maze[row].length; column++)
			{
				maze[row][column].removeFromPath();
			}
		}
		
		// updating the display
		updateDisplay();
	}
	
	/*
	 * returns a String representation of the maze
	 */
	public String toString()
	{
		return this.asText();
	}
	
	/*
	 * clears the maze and redraws it
	 */
	private void updateDisplay()
	{	
		// clearing the current visualization of the maze
		getChildren().clear();
		
		double x = 0;
		double y = 0;
		
		Cell cell = null;
		
		
		// for loops to run for each cell in the maze
		for(int row = 0; row < maze.length; row++)
		{	
			for(int column = 0; column < maze[row].length; column++)
			{
				// for each cell in the maze, I only need to check and draw the left (west) and down (south) directions
				
				// drawing all 4 directions for each cell would cause lines to be drawn on top of each other, which visually would
				// look the same but creates unnecessary processing
				cell = maze[row][column];
				
				x = START_X + (column*CELL_WIDTH);
				y = START_Y + (row*CELL_HEIGHT);
				
				// adding a green circle to the cell if its on the path
				// if the maze is solved, this will show the user the path out of the maze
				if(cell.isOnPath())
				{
					addCircle(new Location(row, column), Color.GREEN);
				}
				
				if(!cell.getNeighbor(Cell.SOUTH) && !(cell.equals(maze[rows-1][columns-1])))
				{
					Line line = new Line();
					
					line.setStartX(x);
					line.setEndX(x + CELL_WIDTH);
					
					line.setStartY(y + CELL_HEIGHT);
					line.setEndY(y + CELL_HEIGHT);
					
					getChildren().add(line);
				}
				
				if(!cell.getNeighbor(Cell.WEST))
				{
					Line line = new Line();
					
					line.setStartX(x);
					line.setEndX(x);
					
					line.setStartY(y);
					line.setEndY(y + CELL_HEIGHT);
					
					getChildren().add(line);
				}
			}
		}
		
		Line topLine = new Line();
		
		topLine.setStartX(START_X + CELL_WIDTH);
		topLine.setEndX(START_X + CELL_WIDTH*columns);
		
		topLine.setStartY(START_Y);
		topLine.setEndY(START_Y);
		
		Line rightLine = new Line();
		
		rightLine.setStartX(START_X + CELL_WIDTH*columns);
		rightLine.setEndX(START_X + CELL_WIDTH*columns);
		
		rightLine.setStartY(START_Y);
		rightLine.setEndY(START_Y + CELL_HEIGHT*rows);
		
		getChildren().addAll(topLine, rightLine);
	}
	
	/*
	 * adds a circle to the center of the location with the given color
	 */
	private void addCircle(Location loc, Color color)
	{
		// placing a circle in the center of the cell
		// this, in the GUI, symbolizes that this cell has been visited
		Circle circle = new Circle();
		
		circle.setCenterX(START_X + CELL_WIDTH*(loc.getColumn() + 0.5));
		circle.setCenterY(START_Y + CELL_HEIGHT*(loc.getRow() + 0.5));
		
		circle.setRadius(RADIUS);
		
		circle.setFill(color);
		
		getChildren().add(circle);
	}
} // Maze.java
