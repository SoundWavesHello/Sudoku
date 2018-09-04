/*
 * 		Author: Kevin Lane
 * 		Lab 4
 * 		Last Modified: 10/18/16
 * 
 * 		This class contains an interface that should
 * 		be implemented in a Stack class.
 * 
 * 		This interface contains methods to do the following:
 * 		
 * 		1) Add an element to a stack and push the rest of
 * 			the elements further back
 *
 * 		2) Pop the first item off of the stack and return it
 * 
 * 		3) Return the first item on the stack without 
 * 			changing it
 * 
 * 		4) Return the size of the stack
 * 
 * 		5) Clear the stack of all elements
 * 
 * 		6) Return a boolean describing if the stack is 
 * 			empty or not
 * 
 */

public interface StackInterface<E> {

	// adds an element to the end of the stack
	public void push(E element);
		
	// deletes the first element in the stack and returns it
	public E pop();
		
	// returns the first element of the stack
	public E peep();
		
	// returns the size of the stack
	public int size();
		
	// 
	public void clear();
		
		
	public boolean isEmpty();
}
