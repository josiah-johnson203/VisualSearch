/*
NAME: 	Josiah Johnson
PROJECT: Project 3
COURSE: CS 26000
INSTRUCTOR: Beomjin Kim
LAB TIME: TR 3:00PM
DUE DATE: 11/14/24

DESCRIPTION:
This class represents a location within the maze, including coordinates for its position and a link
to the previous location
*/
public class Location 
{
	// coordinates
	private int row;
	private int column;
	
	// the Location object this location was visited from
	private Location previous;
	
	/*
	 * default constructor
	 */
	public Location()
	{
		row = -1;
		column = -1;
		
		previous = null;
	}
	
	/*
	 * constructor that initializes the coordinates of the Location
	 */
	public Location(int row, int column)
	{
		this.row = row;
		this.column = column;
		
		previous = null;
	}
	
	/*
	 * constructor that initializes the coordinates and previous Location
	 */
	public Location(int row, int column, Location previous)
	{
		this.row = row;
		this.column = column;
		this.previous = previous;
	}
	
	/*
	 * returns which row this Location is in
	 */
	public int getRow()
	{
		return row;
	}
	
	/*
	 * returns which column this Location is in
	 */
	public int getColumn()
	{
		return column;
	}
	
	/*
	 * returns the previous Location
	 */
	public Location previous()
	{
		return previous;
	}
	
	/*
	 * sets the previous Locationn
	 */
	public void setPrevious(Location previous)
	{
		this.previous = previous;
	}
	
	/*
	 * returns true if the sent Object is a Location object with matching coordinates, false
	 * if not
	 */
	public boolean equals(Object object)
	{
		if(!(object instanceof Location))
		{
			return false;
		}
		
		Location location = (Location) object; 
		
		return this.row == location.row && this.column == location.column;
	}
	
	/*
	 * returns a String representation of this object
	 */
	public String toString()
	{
		return "Row: " + row + "\tColumn: " + column;
	}
} // Location.java
