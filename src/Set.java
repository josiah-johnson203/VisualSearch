/*
NAME: 	Josiah Johnson
PROJECT: Project 3
COURSE: CS 26000
INSTRUCTOR: Beomjin Kim
LAB TIME: TR 3:00PM
DUE DATE: 11/14/24

DESCRIPTION:
This class is a generic Set that uses a partially filled array
*/
public class Set<E> 
{
	// the default size of the array
	private final int INITIAL_SIZE = 15;
	
	// the array that contains the data in the set
	private E[] data;
	// the number of items in the set
	private int numOfItems;
	
	/*
	 * default constructor, initializes the data array with the default size
	 */
	public Set()
	{
		data = (E[]) new Object[INITIAL_SIZE];
	}
	
	/*
	 * initializes the data array with a custom initial size
	 */
	public Set(int initialSize)
	{
		data = (E[]) new Object[initialSize];
	}
	
	/*
	 * enters an item into the set, returns true if the item was successfully entered
	 * returns false if the item was null or is already in the set
	 */
	public boolean enter(E item)
	{
		// if the data array is full, double its capacity
		if(numOfItems == data.length)
		{
			ensureCapacity(numOfItems*2);
		}
		
		// return false if the item is null
		if(item == null)
		{
			return false;
		}
		
		// if the element is already in the array, return false
		if(isElement(item))
		{
			return false;
		} 
		
		// add the item to the set and increase numOfItems
		data[numOfItems] = item;
		numOfItems++;
		
		return true;
	}
	
	/*
	 * removes an element from the Set
	 */
	public void remove(E item)
	{	
		if(item == null) 
		{
			return;
		}
		
		// looping through the Set to find the element
		for(int i = 0; i<numOfItems; i++)
		{
			// if the item is found, it is removed from the Set
			if(data[i].equals(item))
			{
				if(numOfItems == 1)
				{
					data[i] = null;
				}
				else
				{
					data[i] = data[numOfItems-1];
				}
				
				numOfItems--;
				return;
			} // if
		} // for
	}
	
	/*
	 * searches the Set for an element, returns true if it found
	 * otherwise it returns false
	 */
	public boolean isElement(E item)
	{
		if(item == null)
		{
			return false;
		}
		
		// loop to search through the entire set
		for(int i = 0; i < numOfItems; i++)
		{
			if(item.equals(data[i]))
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * returns true if the Set is empty, false if not
	 */
	public boolean isEmpty()
	{
		return numOfItems == 0;
	}
	
	
	/*
	 * ensures that the array can fit "size" items
	 */
	private void ensureCapacity(int size)
	{
		// if the array can already fit "size" items, return
		if(data.length >= size)
		{
			return;
		}
		
		// creating a new array with the requested size
		E[] newArray = (E[]) new Object[size];
		
		// copying the contents of the current array into the new array
		System.arraycopy(data, 0, newArray, 0, numOfItems);
		
		// setting data to be the new array
		data = newArray;
	}
	
	/*
	 * returns a list of String representation of all elements in the Set
	 */
	public String toString()
	{
		String str = "";
		
		for(int i = 0; i < numOfItems; i++)
		{
			str += data[i].toString() + "\n";
		}
		
		return str;
	}
} // Set.java
