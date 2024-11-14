/*
NAME: 	Josiah Johnson
PROJECT: Project 2
COURSE: CS 26000
INSTRUCTOR: Beomjin Kim
LAB TIME: TR 3:00PM
DUE DATE: 10/24/24

DESCRIPTION:
This class represents a generic node for a LinkedList of type D
*/
public class Node <D>
{
	// the data the node contains
	private D data;
	
	// the next node in the LinkedList
	private Node<D> next;
	
	/*
	 * Constructs a node with "data" that points to the sent node
	 */
	public Node(D data, Node<D> next)
	{
		this.data = data;
		this.next = next;
	}
	
	/*
	 * creates a node with data "data" that does not point to another node
	 */
	public Node(D data)
	{
		this.data = data;
		this.next = null;
	}
	
	/*
	 * returns the type D data this node contains
	 */
	public D getData()
	{
		return data;
	}
	
	/*
	 * sets the data of this node
	 */
	public void setData(D data)
	{
		this.data = data;
	}
	
	/*
	 * returns the next node in the LinkedList
	 */
	public Node<D> getNextNode()
	{
		return next;
	}
	
	/*
	 * sets the node that this node points too
	 */
	public void setNextNode(Node<D> next)
	{
		this.next = next;
	}

} // Node
