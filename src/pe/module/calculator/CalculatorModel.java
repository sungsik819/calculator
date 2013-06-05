package pe.module.calculator;

import java.util.Stack;

class CalculatorModel {
	
	private boolean isOperator(char value) {
		
		if(value == '+' || value == '-' 
				|| value == '*' || value == '/') {
			return true;
		}
		
		return false;	
	}
	
	private boolean isNumber(char value) {
		if(value >= '0' && value <= '9') {
			return true;
		}
		
		return false;		
	}
	
	int devide(int a, int b) {
		return a / b;
	}
	
	int mult(int a, int b) {
		return a * b;
	}
	
	int minus(int a, int b) {
		return a - b;
	}
	double minus(int a, double b) {
		return a - b;
	}
	
	int plus(int a, int b) {
		return a + b;	
	}
	
	double plus(double a, double b) {
		return a + b;
	}
	
	private int result(char value, int first, int second) {
		
		int result = 0;
		
		if(value == '+') {
			result = plus(first, second);
		}
		else if(value == '-') {
			
			result = minus(first, second);
		}
		
		else if(value == '*') {
			result = mult(first, second);
		}
		
		else if(value == '/') {
			result = devide(first, second);
		}
		
		return result;
	}
	
	String postfixIter(Stack<Character> OperatorStack, int index, String postfix, String value) {		
		
		if(index == value.length()) {
			if(OperatorStack.isEmpty() == false) {
				if(Character.valueOf(postfix.charAt(postfix.length() - 1)).equals(' ') == false) {
					postfix += " ";
				} 
				
				postfix += OperatorStack.pop().toString();
			}
			
			return postfix;
		}
		
		if(isOperator(value.charAt(index))) {
			postfix += " ";
			if(OperatorStack.isEmpty() == false) {
				postfix += OperatorStack.pop().toString() + " ";				
			}
			OperatorStack.push(value.charAt(index));			
		} 
		else if(isNumber(value.charAt(index))){
			postfix += value.charAt(index);
		}
		
		return postfixIter(OperatorStack, index + 1, postfix, value);		
	}
	int calculate(String postfixed) {	
		
		int result = 0;
		Stack<Character> NumberStack = new Stack<Character>();
		
		for(int i = 0; i < postfixed.length(); i++) {
			if(isNumber(postfixed.charAt(i))) {
				NumberStack.push(postfixed.charAt(i));
			}
			else if(isOperator(postfixed.charAt(i))) {
				
				int second = Integer.valueOf(NumberStack.pop().toString());
				int first = Integer.valueOf(NumberStack.pop().toString());
				
				result = result(postfixed.charAt(i), first, second);				
			}
		}
		
		return result;
	}
	
	String changePostfix(String value) {
		Stack<Character> OperatorStack = new Stack<Character>();
				
		return postfixIter(OperatorStack, 0, "", value);
	}
}
