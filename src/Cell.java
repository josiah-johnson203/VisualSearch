/*
NAME: 	Josiah Johnson
PROJECT: Project 3
COURSE: CS 26000
INSTRUCTOR: Beomjin Kim
LAB TIME: TR 3:00PM
DUE DATE: 11/14/24

DESCRIPTION:
This class represents a cell in a maze. The cell contains a boolean array which describes
which directions you can move from in the maze
*/
public class Cell 
{
	// constant ints that specify directions
	public static final int NORTH = 0;
	public static final int SOUTH = 1;
	public static final int EAST = 2;
	public static final int WEST = 3;
	
	// the array of booleans
	private boolean[] neighbors;
	// specifies whether this cell is on the path out of the maze
	private boolean onPath;
	
	/*
	 * default constructor
	 * initialiazes the neighbors array to be all false
	 */
	public Cell()
	{
		neighbors = new boolean[4];
		
		for(int x = 0; x < neighbors.length; x++)
		{
			neighbors[x] = false;
		}
		
		onPath = false;
	}
	
	/*
	 * sets the direction to true or false
	 */
	public void setNeighbor(int direction, boolean value)
	{
		neighbors[direction] = value;
	}
	
	/*
	 * returns true if the cell in this direction can be travlled to, false if not
	 */
	public boolean getNeighbor(int direction)
	{
		return neighbors[direction];
	}
	
	/*
	 * adds this cell to the solution path
	 */
	public void setOnPath()
	{
		onPath = true;
	}
	
	/*
	 * removes this cell from the path
	 */
	public void removeFromPath()
	{
		onPath = false;
	}
	
	/*
	 * returns true if this cell is located on the path out of the maze
	 */
	public boolean isOnPath()
	{
		return onPath;
	}
} // Cell.java
