/*
NAME: 	Josiah Johnson
PROJECT: Project 2
COURSE: CS 26000
INSTRUCTOR: Beomjin Kim
LAB TIME: TR 3:00PM
DUE DATE: 10/24/24

DESCRIPTION:
This class is a generic LinkedList
*/
public class LinkedList<D> implements Cloneable
{
	// the head of the LinkedList
	protected Node<D> head;
	// the cursor pointing to the current node of the LinkedList
	protected Node<D> cursor;
	
	/*
	 * No argument constructor, initialized head and cursor to null
	 */
	public LinkedList()
	{
		head = null;
		cursor = null;
	}
	
	/*
	 * Constructor with a starting node
	 * 
	 * both head and cursor will point to this node
	 */
	public LinkedList(Node<D> head)
	{
		this.head = head;
		cursor = head;
	}
	
	/*
	 * Constructor with D data type
	 * 
	 * Created a new list with one node containing the sent data
	 */
	public LinkedList(D data)
	{
		head = new Node<D>(data);
		
		cursor = head;
	}
	
	/*
	 * Inserts the sent data after the current node
	 * 
	 * if the list is empty, the node is inserted as the head
	 */
	public void insertAfter(D data)
	{
		Node<D> next = new Node<D>(data);
		
		// if the list is empty
		if(head == null)
		{
			head = next;
		}
		else if(cursor.getNextNode() == null)
		{
			cursor.setNextNode(next);
		}
		else
		{
			next.setNextNode(cursor.getNextNode());
			
			cursor.setNextNode(next);
		}
		
		cursor = next;
	}
	
	/*
	 * insers the sent data at the end of the list
	 */
	public void insertAtEnd(D data)
	{	
		goToEnd();
		
		insertAfter(data);
	}
	
	/*
	 * removes the currently selected node from the list
	 */
	public void remove()
	{
		// if the list is empty
		if(head == null)
		{
			return;
		}
		
		if(cursor == head)
		{
			// if there is only one node in the list
			if(atEndOfList())
			{
				head = null;
				cursor = null;
				
				return;
			}
			
			head = head.getNextNode();
			cursor = head;
			
			return;
		}
		
		prior();
		
		cursor.setNextNode(cursor.getNextNode().getNextNode());
		
		if(cursor.getNextNode() == null)
		{
			cursor = head;
		}
		else
		{
			cursor = cursor.getNextNode();
		}
	}
	
	/*
	 * returns the D data from the current node
	 */
	public D retrieve()
	{
		if(head != null)
		{
			return cursor.getData();
		}
		
		return null;
	}
	
	/*
	 * Replaces the data of the current node
	 */
	public void replace(D data)
	{
		if(cursor == null)
		{
			return;
		}
		
		cursor.setData(data);
	}
	
	public void clear()
	{
		head = null;
		cursor = null;
	}
	
	/*
	 * Changes the cursor to point to the beginning of the list
	 */
	public void goToBeginning()
	{
		cursor = head;
	}
	
	/*
	 * Changes the cursor to point to the next node
	 */
	public void next()
	{
		if(cursor.getNextNode() != null)
		{
			cursor = cursor.getNextNode();
		}
	}
	
	/*
	 * returns the number of items in the LinkedList
	 */
	public int size()
	{
		if(head == null)
		{
			return 0;
		}
		
		cursor = head;
		
		int counter = 0;
		
		while(cursor != null)
		{
			counter++;
			
			cursor = cursor.getNextNode();
		}
		
		return counter;
		
	}
	
	/*
	 * Changes the cursor to point to the previous node
	 */
	public void prior()
	{
		if(head == cursor || head == null)
		{
			return;
		}
		
		Node<D> count = head;
		
		while(count.getNextNode() != cursor)
		{
			count = count.getNextNode();
		}
		
		cursor = count;
	}
	
	/*
	 * Moves the cursor to the end of the list
	 */
	public void goToEnd()
	{
		if(head == null)
		{
			return;
		}
		
		while(cursor.getNextNode() != null)
		{
			cursor = cursor.getNextNode();
		}
	}
	
	/*
	 * returns true if the current node is the last node in the list,
	 * false if not
	 */
	public boolean atEndOfList()
	{
		if(head == null)
		{
			return true;
		}
		
		return cursor.getNextNode() == null;
	}
	
	public void merge(LinkedList<D> list)
	{
		if(list.head == null)
		{
			return;
		}
		
		if(head == null)
		{
			head = list.head;
			
			return;
		}
		
		goToEnd();
		
		cursor.setNextNode(list.head);
	}
	
	/*
	 * searches for the D target in the list. If it is found, the cursor
	 * is set to point to the node containing the data and the method returns true. If the data
	 * is not found, the cursor doesn't change and the method returns false
	 */
	public boolean search(D target)
	{
		if(head == null)
		{
			return false;
		}
		
		Node<D> temp = head;
		
		while(temp != null)
		{
			if(temp.getData().equals(target))
			{
				cursor = temp;
				
				return true;
			}
			
			temp = temp.getNextNode();
		}
		
		return false;
	}
	
	/*
	 * returns true if the list is empty, false if it is not
	 */
	public boolean isEmpty()
	{
		return head == null;
	}
	
	/*
	 * this is a shallow copy clone
	 * it creates a new linked list with separate nodes but the actual data (of type D) 
	 * in the nodes is a shallow copy
	 */
	public LinkedList<D> clone()
	{
		LinkedList<D> newList = new LinkedList<D>();
		
		if(head == null)
		{
			return newList;
		}
		
		Node<D> iterator = head;
		
		while(iterator != null)
		{
			newList.insertAfter(iterator.getData());
			
			iterator = iterator.getNextNode();
		}
		
		return newList;
	}
	
	/*
	 * returns true if the sent list contains the same data in the same order as this list
	 */
	public boolean equals(LinkedList<D> list)
	{
		list.goToBeginning();
		goToBeginning();
		
		if(head == null)
		{
			if(list.isEmpty())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		while(true)
		{
			if(cursor == null || list.cursor == null)
			{
				return cursor == list.cursor;
			}
			
			if(!cursor.getData().equals(list.cursor.getData()))
			{
				return false;
			}
			cursor = cursor.getNextNode();
			list.cursor = list.cursor.getNextNode();
		}
		
	}
	
	/*
	 * returns a string of all nodes contained in the LinkedList
	 */
	public String toString()
	{
		String str = "";
		
		Node<D> temp = head;
		
		while(temp != null)
		{
			str += temp.getData() + "\n";
			
			temp = temp.getNextNode();
		}
		
		return str;
	}
} // LinkedList
