/*
 * Test the Calculator Implementation:
 * 
 * Author: Archana Injineri
 */

package src.com.sparkcental.calc;

import java.util.Scanner;

public class CalculatorMain {
	
	public static void main(String[] args) {

		Calculator calcObj = new Calculator();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Infix Expression here ");
		String inputExpr = scanner.next();
		
		//Covert Infix to postfix Expression
		String postfixExpression = calcObj.convertToPostfix(inputExpr);

		//Display the output of expression
		System.out.println("Expression "+inputExpr+" is evaluated to " + 
							calcObj.evalExpression(postfixExpression));
		scanner.close();
	}

}
