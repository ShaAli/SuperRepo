// Shanjeed Ali
// APCS1 pd10
// HW45 -- Come Together
// 2015-12-10

//skeleton file for class Binary

public class Binary implements Comparable{

    private int _decNum;
    private String _binNum;
    

    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
        _decNum=0;
	_binNum="0";
    }
    

    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
	_decNum=n;
	_binNum=decToBin(n);
    }
    

    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
        _binNum=s;
	_decNum=binToDec(s);
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
        return _binNum;  
    }


    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int n ) {
	String ret="";
        while(n>0){
	    ret=n%2+ret; //adds n mod 2 to ret 
	    n=n/2;//prepares n to produce the next binary digit
	}
	return ret;
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
      =====================================*/
    public static String decToBinR( int n ) {
	while(n>0){
	    return ""+decToBinR(n/2)+n%2;
	    // strings together the numerals (n/2) mod 2 and n mod 2 until n=0
	}
	return "";
    }


    /*=====================================
      String binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec( String s ) {
	int ret=0;
        for(int c=0;c<s.length();c++){
	    ret=ret+(Integer.parseInt(s.substring(c,c+1))*(int)Math.pow(2,c));
	}
	//moves through s from left to right, multiply the binary digit with a power of 2
	return ret;
    }


    /*=====================================
      String binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int binToDecR( String s ) {
	while (s.length()>0){
	    return (Integer.parseInt(s.substring(0,1))*(int)Math.pow(2,(s.length()-1))) + binToDecR(s.substring(1,s.length()));
	}
	//runs binToDecR each time wth the leftmost numeral removed (it is removed after it is used to generate a value)
	return +0;
    }


    //accessor
    public int getDec(){
	return _decNum;
    }

    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) {
	if (other instanceof Binary){
	    Binary binother = (Binary)other;
	    return((this==binother) || (this.compareTo(other))==0);
	}
	else {return false;}
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/

    public int compareTo( Object other ) {
	ClassCastException e = new ClassCastException("Can't cast " + other.getClass() + " to Comparable");
	NullPointerException n = new NullPointerException("Input is null");
	if (other==null){
	    throw n;
	}
	if (other instanceof Comparable){
	    if (other instanceof Rational){
		return _decNum - (int)((Rational)other).floatValue();
	    }
	    else if(other instanceof Binary){
		return _decNum - ((Binary)other)._decNum;
	    }
	    else /*if(other instanceof Hexadecimal)*/{
		return _decNum - ((Hexadecimal)other).getDec();
	    }
	}
	else {
	    throw e;
	}
    }


    //main method for testing
    public static void main( String[] args ) {


	System.out.println();
	System.out.println( "Testing ..." );

	Binary b1 = new Binary(5);
	Binary b2 = new Binary(5);
	Binary b3 = b1;
	Binary b4 = new Binary(7);

	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos

	System.out.println("\nRecursive methods...");
	System.out.println(decToBinR(14));
	System.out.println(binToDecR("0"));
	System.out.println(binToDecR("1"));
	System.out.println(binToDecR("10"));
	System.out.println(binToDecR("11"));
	System.out.println(binToDecR("1110"));

	System.out.println("test");
	System.out.println(decToBinR(32));
	System.out.println(decToBin(32));
			
		/*=========================================
	  =========================================*/
    }//end main()

} //end class
