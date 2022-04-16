package neilgilbertg.ca;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Stack;

public class InPostSolver {


    /**
     *  This method takes parses an equation and calculates it according to the Prefix and Postfix method
     *  @param equation String Equation that will be parsed
     *  @return double Returns the result of the calculation.
     */
    public static double calculateEquation(String equation)
    {
        //INFIX TO POSTFIX notation
        String eq = equation.replace(" ", "").toLowerCase();

        ArrayList<String> postfix = new ArrayList<String>();
        Stack symbols = new Stack();

        String valToParse = "";
        String functionToParse = "";
        for(int i = 0; i<eq.length(); i++)
        {
            //System.out.println(eq.charAt(i)+"-> "+valToParse+"-> "+postfix+" : "+symbols);
            switch(eq.charAt(i))
            {
                case '(':
                    symbols.push(eq.charAt(i));
                    if(functionToParse.length()>0){ symbols.add(functionToParse);functionToParse=""; }
                    break;
                case ')':
                    if(valToParse.length()>0){ postfix.add(valToParse);valToParse=""; }
                    while(symbols.peek().toString().charAt(0)!='(')
                    {
                        postfix.add(symbols.pop().toString());
                    }
                    symbols.pop();

                    if(functionToParse.length()>0){ postfix.add(functionToParse);functionToParse=""; }
                    break;
                case ',':
                    if(valToParse.length()>0){ postfix.add(valToParse);valToParse=""; }
                    while(symbols.peek().toString().charAt(0)!='(' &&
                            !(Character.isAlphabetic(symbols.peek().toString().charAt(0))))
                    {
                        postfix.add(symbols.pop().toString());
                    }
                    break;
                default:
                    if(isOperator(eq.charAt(i)))
                    {
                        if(valToParse.length()>0){ postfix.add(valToParse);valToParse=""; }
                        symbols.push(eq.charAt(i));
                        functionToParse="";
                    }

                    if(Character.isDigit(eq.charAt(i)) || eq.charAt(i)=='.') {
                        valToParse+=eq.charAt(i);
                    } else if(Character.isAlphabetic(eq.charAt(i))) {
                        functionToParse+=eq.charAt(i);
                    }
                    break;
            }
            if(i+1 >= eq.length()){
                while(symbols.size() > 0)
                {
                    if(valToParse.length()>0){ postfix.add(valToParse);valToParse=""; }

                    if((char)symbols.peek()!='(')
                        postfix.add(symbols.pop().toString());
                    else
                        symbols.pop();
                }
            }
        }
        System.out.println("INFIX:"+equation);
        System.out.println("POSTFIX:"+postfix);

        //Evaluate postfix expression
        double operand1 = 0.0;
        double operand2 = 0.0;
        Stack operandBank = new Stack();
        for(int i = 0; i<postfix.size(); i++)
        {
            if(!Character.isDigit(postfix.get(i).charAt(0))) {
                switch(postfix.get(i).toString())
                {
                    //Math operations
                    case "+":
                        operand2 = Double.parseDouble(operandBank.pop().toString());
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(operand1 + operand2);
                        break;
                    case "-":
                        operand2 = Double.parseDouble(operandBank.pop().toString());
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(operand1 - operand2);
                        break;
                    case "*":
                        operand2 = Double.parseDouble(operandBank.pop().toString());
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(operand1 * operand2);
                        break;
                    case "/":
                        operand2 = Double.parseDouble(operandBank.pop().toString());
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(operand1 / operand2);
                        break;
                    case "%":
                        operand2 = Double.parseDouble(operandBank.pop().toString());
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(operand1 % operand2);
                        break;

                    //Math methods ( Based on https://www.javatpoint.com/java-math )
                    case "abs":
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.abs(operand1));
                        break;
                    case "max":
                        operand2 = Double.parseDouble(operandBank.pop().toString());
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.max(operand1, operand2));
                        break;
                    case "min":
                        operand2 = Double.parseDouble(operandBank.pop().toString());
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.min(operand1, operand2));
                        break;
                    case "round":
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.round(operand1));
                        break;
                    case "sqrt":
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.sqrt(operand1));
                        break;
                    case "cbrt":
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.cbrt(operand1));
                        break;
                    case "pow":
                        operand2 = Double.parseDouble(operandBank.pop().toString());
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.pow(operand1, operand2));
                        break;
                    case "signum":
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.signum(operand1));
                        break;
                    case "copysign":
                        operand2 = Double.parseDouble(operandBank.pop().toString());
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.copySign(operand1, operand2));
                        break;
                    case "nextafter":
                        operand2 = Double.parseDouble(operandBank.pop().toString());
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.nextAfter(operand1, operand2));
                        break;
                    case "nextdown":
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.nextDown(operand1));
                        break;
                    case "random":
                        operandBank.push(Math.random());
                        break;
                    case "rint":
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.rint(operand1));
                        break;
                    case "hypot":
                        operand2 = Double.parseDouble(operandBank.pop().toString());
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.hypot(operand1, operand2));
                        break;
                    case "ulp":
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.ulp(operand1));
                        break;
                    case "getexponent":
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.getExponent(operand1));
                        break;
                    case "ieeeremainder":
                        operand2 = Double.parseDouble(operandBank.pop().toString());
                        operand1 = Double.parseDouble(operandBank.pop().toString());
                        operandBank.push(Math.IEEEremainder(operand1, operand2));
                        break;
                }
                operand1 = 0.0;
                operand2 = 0.0;
            } else {
                operandBank.push(Double.parseDouble(postfix.get(i)));
            }
        }

        return Double.parseDouble(operandBank.pop().toString());
    }

    /**
     *  This method checks if passed parameter is an operator
     *  @param valToParse char that will be checked
     *  @return boolean Returns <b>true</b> if passed parameter is an operator, <b>false</b> if not
     */
    private static boolean isOperator(char valToParse)
    {
        return valToParse=='+' ||
                valToParse=='-' ||
                valToParse=='*' ||
                valToParse=='/' ||
                valToParse=='%';
    }

    /**
     *  This method checks if formula is valid
     *  @param formula String Formula that will be checked
     *  @return boolean Returns <b>true</b> if formula is valid, <b>false</b> if not
     */
    public static boolean verifyFormula(String formula)
    {
        if(formula.length() <= 0)
            return false;
        Stack group = new Stack();
        Stack varName = new Stack();

        for(int i = 0; i < formula.length(); i++)
        {
            switch(formula.charAt(i))
            {
                case '(':
                    group.push(formula.charAt(i));
                    break;
                case ')':
                    if(group.size()<=0)
                        return false;
                    if((char)group.peek()=='(') {
                        group.pop();
                    } else {
                        return false;
                    }
                    break;

                case '[':
                    varName.push(formula.charAt(i));
                    break;
                case ']':
                    if(varName.size()<=0)
                        return false;
                    if((char)varName.peek()=='[') {
                        varName.pop();
                    } else {
                        return false;
                    }
                    break;
                default:
                    if(varName.size()==0)
                    {
                        if(Character.isAlphabetic(formula.charAt(i)))
                            return false;
                    }
            }
        }

        return true;
    }
}
