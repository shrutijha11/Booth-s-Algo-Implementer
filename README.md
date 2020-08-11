# Booth-s-Algo-Implementer

Booth’s algorithm gives a procedure for multiplying binary integers in signed 2’s
complement representation in an efficient way.
The implementation of Booth’s algorithm in the java code is done according to the
following procedure:
● The procedure for Booth’s algorithm is explained below:
○ Let M be the n-bit multiplicand.
○ Let Q be the n-bit multiplier.
○ Let Q1 be the last bit of Q.
○ Consider a 1-bit register Qo and initialise it to 0.
○ Consider a n-bit register A and initialize it to 0.
○ n: the step count = n-bit size of A
● The conditions are as follows:
○ If Q1Qo are the same, that is 00 or 11, then perform arithmetic right shift
by 1-bit. Reduce step count by 1.
○ If Q1Qo is 01, then perform
A=A+M,
and then perform arithmetic right shift by 1-bit. Reduce step count by 1.
○ If Q1Qo is 10, then perform
A=A-M,
and then perform arithmetic right shift by 1-bit. Reduce step count by 1.
● The procedure is continued till step count does not reduce to 0.
● When step count becomes equal to 0, then the final product of the 2 integers is
obtained. The product is 2n-bit, and is stored in AQ.
