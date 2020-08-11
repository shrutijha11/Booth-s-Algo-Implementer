import java.util.*;

class shruti_2019274_Project2 {

	
	public static void add(int [] acc, int[] md) //function to add 2 integers stored in binary forms in arrays
	{
		int carry=0; //carry bit
		int temp;
		
		for(int i=acc.length-1; i>=0;i--)
		{
			temp=acc[i];
			acc[i]=temp^md[i]^carry; //final bit to be stored in accumulator
			
			if((carry==1 && temp==1) || (carry==1 && md[i]==1) || (temp==1 && md[i]==1)) //deciding value of carry bit
				carry=1;
			
			else
				carry=0;
				
		}
			
	}


	
	public static void complement_1(int [] arr) //function to calculate 1's complement
	{
		for(int j=0; j<arr.length; j++)
		{
			arr[j]=1-arr[j];  
		}
		
	}


	
	public static void complement_2(int [] arr, int n) //function to calculate 2's complement of input integers
	{
		complement_1(arr);
		
		int [] plusone= new int[n];
		plusone[n-1]=1;

		for(int c=0;c<n-1;c++)
			{
				plusone[c]=0;
			}
		
		add(arr,plusone); //adding 1 to 1's complement
		
	}


	
	public static void fr_complement2(int [] arr, int n) //function to calculate 2's complement of final product
	{	
		complement_1(arr);
		
		int [] plusone= new int[2*n];
		plusone[2*n-1]=1;

		for(int c=0;c<2*n-1;c++)
			{
				plusone[c]=0;
			}

		add(arr,plusone); //adding 1 to 1's complement
	}
	
	
	
	
	public static int[] dectoBin(int dec, int n) //function to convert integer from decimal to binary form
	{
		boolean check=false;
		if(dec<0) //checking if integer is -ve or +ve
			check=true; 
		
		int [] bin= new int[n];
		int index=n-1;
		
		if(dec<0)
			dec=dec*-1;
		
		while(dec!=0) 
		{
			bin[index]=dec%2; //calculating binary form
			dec=dec/2;
			index=index-1;
			
		}
		
		if(check==true) 
		{
			complement_2(bin,n); //if integer is -ve, calculate 2's complement of binary form
		}
		
		return bin;
	}
	


	public static long bintoDec(int [] arrb, int n) //function to convert integer from binary to decimal form
	{	
		long dec=0;
		boolean check=false;
		if(arrb[0]==1)
			check=true; //if msb is 1, then it is binary of -ve integer
		
		if(check==false) //integer is +ve
		{	
			long base_f=1;
			for(int k=arrb.length-1; k>=0;k--)
			{
				dec=dec+(arrb[k]*base_f); //multiply each bit by place value
				base_f=base_f*2; 
			}
			return dec;
		}
		
		else //integer is -ve
		{
			fr_complement2(arrb,n); //first find 2's complement & then convert to decimal
			long base_t=1;
			for(int i=arrb.length-1; i>=0;i--)
			{
				dec=dec+(arrb[i]*base_t);
				base_t=base_t*2;
			}
			return dec*-1;
		}
		
	}

	
	
	public static void rshift(int[] acc, int [] mr, int [] Qo, int n) //function to right shift accumulator, multiplier and Qo contents
	{
		Qo[0]=mr[n-1];

		int temp=acc[n-1];

		for(int j=n-1;j>0;j--)
		{	
			acc[j]=acc[j-1];
			mr[j]=mr[j-1];
		}
		mr[0]=temp;
		
		
	}
	
	

	public static void display(int[] n, int[] A, int[] Q, int[] Qo) //function to display stepcount,accumulator & multiplier contents,Qo at each step
	{	
		System.out.print(n[0]);
		System.out.print("   ");
		for(int i=0;i<A.length; i++)
			System.out.print(A[i]);
		System.out.print("   ");
		for(int j=0; j<Q.length;j++)
			System.out.print(Q[j]);
		System.out.print("   ");
		System.out.print(Qo[0]);
		System.out.print("   ");
			
	}
	


	
	public static void booth_algo(int [] md, int [] mr, int[] md_neg, int n) //function to implement booth's algorithm
	{
		//array md is the multiplicand, array mr is the multiplier, array md_neg is the -ve of multiplicand and int n is the stepcount
		
		int [] A= new int [n]; //accumulator initialised to zero
		int [] Q= mr; //array Q is the multiplier
		int [] sc= {n}; //sc is the step count 
		int Qo[]= {0}; //Qo is bit taken according to booth's algorithm

		System.out.println("n"+"--> "+"Acc"+"--> "+"Mtpr"+"--> "+"Qo"+"--> "+"Action");
		System.out.println("");
		display(sc,A,Q,Qo);
		System.out.print("INITIALISATION"); //printing initial values 
		System.out.println("");
		
		while(sc[0]!=0) //while step counts>0
		{
			int Q1=Q[n-1]; //Q1 is the last bit of multiplier taken according to booth's algorithm
			
			if((Q1==0 && Qo[0]==0) || (Q1==1 && Qo[0]==1)) //First condition when Q1Qo are 11 or 00
				{
					rshift(A,Q,Qo,n); //shift the accumulator,multiplier and Qo right
					sc[0]=sc[0]-1; //step count reduces by 1
					display(sc,A,Q,Qo); //display values
					System.out.print("Arithmetic Shift Right");
					System.out.println("");
				}
			
			else if(Q1==1 && Qo[0]==0) //Second condition when Q1Qo is 10
			{
				add(A,md_neg); //subtract multiplicand from accumulator
				display(sc,A,Q,Qo); 
				System.out.print("Subtraction: A=A-M");
				System.out.println("");

				rshift(A,Q,Qo,n); 
				sc[0]=sc[0]-1; 
				display(sc,A,Q,Qo);
				System.out.print("Arithmetic Shift Right");
				System.out.println("");
			}
			
			else if(Q1==0 && Qo[0]==1) //Third condition when Q1Qo is 01
			{
				add(A,md); //add multiplicand to accumulator
				display(sc,A,Q,Qo);
				System.out.print("Addition: A=A+M");
				System.out.println("");

				rshift(A,Q,Qo,n);
				sc[0]=sc[0]-1;
				display(sc,A,Q,Qo);
				System.out.print("Arithmetic Shift Right");
				System.out.println("");
			}
			
				
		}

		int [] arr = new int[2*n]; //16-bit final product AQ in binary
		for(int a=0; a<n; a++)
		{
			arr[a]=A[a];
			arr[a+n]=Q[a];
		}
		
		System.out.println("");
		System.out.println("THE RESULT OF MULTIPLICATION IS: " );
		System.out.print("In binary: ");
		for(int a=0; a<2*n; a++)
		{	
			System.out.print(arr[a]);
			
		}

		long result= bintoDec(arr,n); //converting product from binary to decimal
		
		System.out.println("");
		System.out.println("In decimal: "+result); //priniting product
	}
	
	

	public static void main(String[] args) {

		try{
		Scanner in =new Scanner(System.in);
		System.out.println("Enter the two integers to be multiplied: ");
		int n1= in.nextInt();
		int n2= in.nextInt();
		in.close();

		int n1_f=n1;
		int n2_f=n2;

		if(n1==-2147483648 || n2==-2147483648)
		{
			System.out.println("ERROR: Integers entered are larger than integers allowed by java(-2^31+1 to 2^31-1). Please reenter and try again.");
			System.exit(0);
		}

		int n=0;
		if(n1<0)
			n1_f=n1*-1;
		if(n2<0)
			n2_f=n2*-1;
		
		String s1=Integer.toBinaryString(n1_f);
		
		String s2=Integer.toBinaryString(n2_f);
		
		if(s1.length()>s2.length())
			n=s1.length()+1;
		else
			n=s2.length()+1;
		
		System.out.println("");
		System.out.println("Implementing Booth's algorithm for "+n1+"*"+n2+" :");
		System.out.println("");

		int [] md= dectoBin(n1,n); //converting input integers from decimal to binary form
		int [] md_neg= dectoBin(-n1,n);
		int [] mr= dectoBin(n2,n);
		
		booth_algo(md,mr,md_neg,n); //calling booth's algorithm

	}
	catch(InputMismatchException e) //to check if integers entered are in the range of -2^31+1 to 2^31-1(java stores int in 32 bits in 2's complement form)
	{
		System.out.println("ERROR: Integers entered are larger than integers allowed by java(-2^31+1 to 2^31-1). Please reenter and try again.");
		System.exit(0);
	}
}

}
