package neilgilbertg.ca;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter your expression:");
        String expression = s.nextLine();

        System.out.println("EXPRESSION RESULT:" + InPostSolver.calculateEquation(expression));
    }
}
