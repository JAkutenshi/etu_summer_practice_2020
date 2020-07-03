import java.util.Scanner;


class program {
	static boolean readOperand(String str, char cur, int index){
		   
		   boolean b  = false;
		   if (cur == 't') b = true;
		   else if (cur == 'f') b = false;
		   else {System.out.println("Incorrect"); System.exit(0);}
		
			return b;
	   }
	   
	   static boolean execOperation(char sign, boolean op1, boolean op2){
		   if (sign == 'V')
			   return op1 || op2;
		   else if(sign == '^')
			   return op1 && op2;
		   else {
			   System.out.println("Incorrect");
			   System.exit(0);
			}return false;
		}
		
		static int readLogicalExpr(Stack st, String str, int i){
			
			char sign = ' ';
			boolean leftop, rightop, flag = false;
			boolean invert = false;
			while (i < str.length()){
				
				System.out.println(str.charAt(i));
				if (str.charAt(i) == '('){
					i++;
					i = readLogicalExpr(st, str, i);
					i++;
				}
				else if (str.charAt(i)== '!'){
					invert = true;
					i++;
					continue;
				}
				else if (str.charAt(i) == ' '){
					i++;
					continue;
				}
				
				else if (str.charAt(i) == ')'){
					if (!invert){
						rightop = st.readTop();
						st.deleteElement();
						if (!st.isEmpty()) {
							leftop = st.readTop();
							st.deleteElement();
							st.addElement(execOperation(sign, leftop, rightop));
						}
						else {
							st.addElement(rightop);
						}
					}
					else{
						boolean a = st.readTop();
						st.deleteElement();
						st.addElement(!a);
						
					}
					return i;
				}
				else if (str.charAt(i) == 'V' || str.charAt(i) == '^') {sign = str.charAt(i);i++;}
				
				else {
					if (!flag){
						leftop = readOperand(str, str.charAt(i), i);
						if (leftop) i+=4; else i+=5;
						st.addElement(leftop);
						flag = true;
						continue;
					}
					else{
						rightop = readOperand(str, str.charAt(i), i);
						if (rightop) i+=4; else i+=5;
						st.addElement(rightop);
					}
				}
			}return i;
		
		}
		
	   public static void main(String args[]) {
		   
		  Scanner in = new Scanner(System.in);
		  String str = in.nextLine();
		  in.close();
		 
	      Stack st = new Stack(10);
		  
		  readLogicalExpr(st, str, 0);
	     // System.out.println("stack: " + st);
	      st.addElement(false);
	      st.addElement(true);
	      st.addElement(false);
	      st.deleteElement();
	      st.deleteElement();
	      st.deleteElement();
	      if (st.isEmpty()){
	         System.out.println("empty stack");
	      }
		  System.out.println("solution: " + st.readTop());
	   }
}
