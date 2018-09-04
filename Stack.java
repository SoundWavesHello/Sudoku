/*
 * 		Author: Kevin Lane
 * 		Lab 4
 * 		Last Modified: 10/18/16
 * 
 * 		This class contains the following:
 * 
 * 		1) A constructor to initialize a Stack 
 * 			class object when given no parameters
 * 
 * 		2) A constructor to initialize a Stack 
 * 			class object when given the length 
 * 			of the Stack
 * 
 * 		3) A method to add a new item to the Stack
 * 			and push other items further down on
 * 			the Stack
 * 
 * 		4) A method to pop the first item off of
 * 			the Stack and return it
 * 
 * 		5) A method to return the first item of
 * 			the Stack without changing the Stack
 * 
 * 		6) A method to return the size of the Stack
 * 
 * 		7) A method to clear the Stack of all items
 * 
 * 		8) A method to determine if the Stack is 
 * 			empty or not
 * 
 */


import java.util.ArrayList;

public class Stack<E> implements StackInterface<E> {
	
	private ArrayList<E> stack;
	
	
	public Stack(){
		stack = new ArrayList<E>();
	}
	
	
	public Stack(int numMoves){
		stack = new ArrayList<E>(numMoves);
	}
	
	
	public void push(E guess) {
		stack.add(guess);

	}

	public E pop() {
		
		if (isEmpty()){
			return null;
		}
		
		E firstMove = stack.get(stack.size() - 1);
		stack.remove(stack.size() - 1);
		
		return firstMove;
	}

	
	public E peep() {
		
		return stack.get(stack.size() - 1);
	}

	
	public int size() {
		
		return stack.size();
	}

	public void clear() {
		stack.clear();
	}

	
	public boolean isEmpty() {
		
		return stack.size() == 0;
	}

}
