/*
 * Calculator Library
 * Author: Archana Injineri
 */
package sparkcental;

import java.util.Scanner;
import java.util.Stack;

public class Calculator{

	private static Scanner scanner;

	public static void main(String[] args){		
		Calculator calcObj = new Calculator();
		scanner = new Scanner(System.in);
		System.out.println("Enter the Infix Expression here ");
		String expression = scanner.next();
		
		//Covert Infix to postfix Expression
		String postfixExpression = convertToPostfix(expression);

		System.out.println("Expression "+expression+" is evaluated to " + calcObj.evalExpression(postfixExpression));
	}
	
	//Evaluates the Postfix Expression
	public int evalExpression(String postfixExpr){
        char[] chars = postfixExpr.toCharArray();
        Stack<Integer> stack = new Stack<Integer>();
        for (char c : chars) {
                if (isOperand(c)) {
                        stack.push(c - '0'); 
                } else if (isOperator(c)) {
                        int op1 = stack.pop();
                        int op2 = stack.pop();
                        int result;
                        switch (c) {
                        case '*':
                                result = op1 * op2;
                                stack.push(result);
                                break;
                        case '/':
                                result = op2 / op1;
                                stack.push(result);
                                break;
                        case '+':
                                result = op1 + op2;
                                stack.push(result);
                                break;
                        case '-':
                                result = op2 - op1;
                                stack.push(result);
                                break;
                        }
                }
        }
        return stack.pop();
	}

	//Convert to Postfix expression
	public static String convertToPostfix(String infixExpression){
		char[] chars = infixExpression.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        StringBuilder out = new StringBuilder(infixExpression.length());

        for (char c : chars) {
                if (isOperator(c)) {
                        while (!stack.isEmpty()) {
                                if (checkGraterOrEqual(stack.peek(), c)) {
                                        out.append(stack.pop());
                                } else {
                                        break;
                                }
                        }
                        stack.push(c);
                }  else if (isOperand(c)) {
                        out.append(c);
                }
        }
        while (!stack.empty()) {
                out.append(stack.pop());
        }
        System.out.println("Postfix Expression is : "+ out.toString());
         return out.toString();
	}
	
	public static boolean isOperator(char c){
		if("+-*/".indexOf(c) != -1){
			return true;
		}	
		return false;
	}
	
	public static boolean isOperand(char c){
		if("0123456789".indexOf(c) != -1){
			return true;
		}
		return false;
	}
	
	//Get Precedence of Operators
	private static int getPrecedence(char operator) {
          int precision = 0;
          if (operator == '-' || operator == '+') {
        	  precision = 1;
          } else if (operator == '*' || operator == '/') {
        	  precision = 2;
          }
          return precision;
  }

	private static boolean checkGraterOrEqual(char op1, char op2) {
         return getPrecedence(op1) >= getPrecedence(op2);
	}
	
}
