/*
NAME: 	Josiah Johnson
PROJECT: Project 3
COURSE: CS 26000
INSTRUCTOR: Beomjin Kim
LAB TIME: TR 3:00PM
DUE DATE: 11/14/24

DESCRIPTION:
This class is a generic Queue that uses an internal generic LinkedList
*/
public class Queue<E> 
{
	// LinkedList that contains all items in the queue
	private LinkedList<E> list;
	
	/*
	 * default constructor, initializes an empty LinkedList
	 */
	public Queue()
	{
		list = new LinkedList<E>();
	}
	
	/*
	 * adds an element to the queue
	 */
	public void enqueue(E data)
	{	
		// added elements are placed at the back of the List
		list.insertAtEnd(data);
	}
	
	/*
	 * removes the first element from the queue and returns its value
	 */
	public E dequeue()
	{
		// return null is the list is empty
		if(list.isEmpty())
		{
			return null;
		}
		
		// the front of the list is always the first to be dequeued
		list.goToBeginning();

		E data = list.retrieve();
		
		// removing the element
		list.remove();
		
		return data;
	}
	
	/*
	 * returns the value of the first element in the queue without removing it
	 */
	public E peek()
	{
		// return null if the queue is empty
		if(list.isEmpty())
		{
			return null;
		}
		
		list.goToBeginning();
		return list.retrieve();
	}
	
	/*
	 * returns the number of items currently in the queue
	 */
	public int size()
	{
		return list.size();
	}
	
	/*
	 * returns true if the queue is empty, false if not
	 */
	public boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	/*
	 * returns true if the sent has the same elements as this queue
	 */
	public boolean equals(Queue<E> queue)
	{
		return list.equals(queue.list);
	}
	
	/*
	 * returns a shallow copy of this queue
	 */
	public Queue<E> clone()
	{
		Queue<E> queue = new Queue<E>();
		
		queue.list = list.clone();
		
		return queue;
	}
	
	/*
	 * returns a String representation of this Queue, which contains
	 * a list of the String representation of the generic E
	 */
	public String toString()
	{
		return list.toString();
	}
} // Queue.java
