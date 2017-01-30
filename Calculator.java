/*
 * Calculator Library:
 * This class computes arithmetic 
 * expressions at once considering order 
 * of operations using stack
 * 
 * Author: Archana Injineri
 */
package src.com.sparkcental.calc;

import java.util.Stack;

public class Calculator{
		
	//Evaluates the Postfix Expression
	public int evalExpression(String postfixExpr){
        char[] postExprCharArr = postfixExpr.toCharArray();
        Stack<Integer> operandStack = new Stack<Integer>();
        for (char ch : postExprCharArr) {
                if (isOperand(ch)) {
                	//Converts char to int
                	operandStack.push(ch - '0'); 
                } else if (isOperator(ch)) {
                		//pop top two elements & perform operations
                        int operand1 = operandStack.pop();
                        int operand2 = operandStack.pop();
                        int result;
                        switch (ch) {
                        case '*':
                                result = operand2 * operand1;
                                operandStack.push(result);
                                break;
                        case '/':
                                result = operand2 / operand1;
                                operandStack.push(result);
                                break;
                        case '+':
                                result = operand2 + operand1;
                                operandStack.push(result);
                                break;
                        case '-':
                                result = operand2 - operand1;
                                operandStack.push(result);
                                break;
                        }
                }
        }
        //return last element of stack which is final result
        return operandStack.pop();
	}

	//Convert Infix to Postfix expression
	public  String convertToPostfix(String infixExpression){
		char[] infExprCharArr = infixExpression.toCharArray();
        Stack<Character> operatorStack = new Stack<Character>();
        StringBuilder postExprStrBldr = new StringBuilder(infixExpression.length());

        for (char ch : infExprCharArr) {
                if (isOperator(ch)) {
                		//pop elements from stack till stack is empty
                        while (!operatorStack.isEmpty()) {                        	
                        	//check operator precedence
                                if (isHigherPrecedence(operatorStack.peek(), ch)) {
                                	postExprStrBldr.append(operatorStack.pop());
                                } else {
                                        break;
                                }
                        }
                        operatorStack.push(ch);
                }  else if (isOperand(ch)) {
                	postExprStrBldr.append(ch);
                }
        }
        // pop remaining elements of stack
        while (!operatorStack.empty()) {
        	postExprStrBldr.append(operatorStack.pop());
        }
        System.out.println("Postfix Expression is : "+ postExprStrBldr.toString());
         return postExprStrBldr.toString();
	}
	
	public  boolean isOperator(char ch){
		if("+-*/".indexOf(ch) != -1){
			return true;
		}	
		return false;
	}
	
	public  boolean isOperand(char c){
		if("0123456789".indexOf(c) != -1){
			return true;
		}
		return false;
	}
	
	//Get Precedence of Operators
	private  int getPrecedence(char operator) {
          int precision = 0;
          if (operator == '-' || operator == '+') {
        	  precision = 1;
          } else if (operator == '*' || operator == '/') {
        	  precision = 2;
          }
          return precision;
  }

	// compare the precedence of input operator to the stack top
	private  boolean isHigherPrecedence(char inputOp, char stackTopOp) {
         return getPrecedence(inputOp) >= getPrecedence(stackTopOp);
	}
	
}
