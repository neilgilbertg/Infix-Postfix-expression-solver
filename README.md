# Infix-Postfix-expression-solver
An expression solver utilizing the Infix Postfix conversion. 
It employs the basic rules of converting an Infix expression to a Postfix one then solves the equation.

This implementation not only employ the <b>basic arithmetic operations (+, -, *, /, %)</b> but also allows you to use some common Java Math functions.

Current list if usable methods:
* .abs(val1)
* .max(val1, val2)
* .min(val1, val2)
* .round(val1)
* .sqrt(val1)
* .cbrt(val1)
* .pow(val1, val2)
* .signum(val1)
* .copysign(val1, val2)
* .nextafter(val1, val2)
* .nextdown(val1)
* .random()
* .rint(val1)
* .hypot(val1, val2)
* .ulp(val1)
* .getexponent(val1)
* .ieeeremainder(val1, val2)

Sample run:
>Enter your expression:<br/>
<b>1 + pow(2,2)</b><br/>
INFIX:1 + pow(2,2)<br/>
POSTFIX:[1, 2, 2, pow, +]<br/>
EXPRESSION RESULT:5.0<br/>