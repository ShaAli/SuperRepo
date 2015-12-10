// Team Ye Ali - Joel Ye, Shanjeed Ali
//APCS1 pd10
//HW44 - This or That or Fourteen Other Things
//2015 - 12 - 08

public class Hexadecimal {

    private final static String HEXDIGITS = "0123456789ABCDEF";
    private int _decNum;
    private String _hexNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _hexNum to "0"
      =====================================*/
    public Hexadecimal() { 
	_hexNum = "0";
	_decNum = 0;
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _hexNum to equiv in base-16
      =====================================*/
    public Hexadecimal( int n ) {
	_decNum = n;
	_hexNum = decToHex(n);   
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative hex number
      post: sets _hexNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal( String s ) {
	_decNum = hexToDec(s);
	_hexNum = s;   
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of base-16 characters representing value of this Object
      =====================================*/
    public String toString() { 
	return _hexNum;   
    }


    /*=====================================
      String decToHex(int) -- converts base-10 input to base-16
      pre:  n >= 0
      post: returns String of base-16 characters
      eg  decToHex(0) -> "0"
      decToHex(1) -> "1"
      decToHex(17) -> "11"
      decToHex(23) -> "17"
      decToHex(43) -> "2B"
      =====================================*/
    //adds the 
    public static String decToHex( int n ) {
	String s = "";
	while (n > 0){
	    s = HEXDIGITS.substring(n % 16, n % 16 + 1) + s;
		n /= 16;
	}//adds the base-16 character at the n mod 1 index to s, then does the same with n/16
	return s;
    }


    /*=====================================
      String decToHexR(int) -- converts base-10 input to hexary, recursively
      pre:  n >= 0
      post: returns String of base-16 characters
      eg  decToHex(0) -> "0"
      decToHex(1) -> "1"
      decToHex(17) -> "11"
      decToHex(23) -> "17"
      decToHex(43) -> "2B"  
      =====================================*/
    public static String decToHexR( int n ) {
	if (n == 0)
		return "0";
	return HEXDIGITS.substring(n % 16, n % 16 + 1) + decToHexR( n / 16 );
    }


    /*=====================================
      String hexToDec(String) -- converts base-10 input to hex
      pre:  s represents non-negative hex number
      post: returns decimal equivalent as int
      eg  
      hexToDec("0") -> 0
      hexToDec("1") -> 1
      hexToDec("11") -> 17
      hexToDec("17") -> 23
      hexToDec("2B") -> 43
      =====================================*/
    public static int hexToDec( String s ) {
	int n = 0;
	while (s.length() > 0){
	    n += HEXDIGITS.indexOf(s.substring(0,1));
		n *= 16;
		s = s.substring(1);
	}//gets the value of the first digit in s, multiplies by 16 and adds the value of the 2nd digit and repeats
	return n;
    }


    /*=====================================
      String hexToDecR(String) -- converts base-10 input to hexary, recursively
      pre:  s represents non-negative hexary number
      post: returns decimal equivalent as int
      eg  
      hexToDec("0") -> 0
      hexToDec("1") -> 1
      hexToDec("11") -> 17
      hexToDec("17") -> 23
      hexToDec("2B") -> 43
      =====================================*/
    public static int hexToDecR( String s ) {
	if (s.length() <= 1)
	    return HEXDIGITS.indexOf(s);
	return HEXDIGITS.indexOf(s.substring(s.length()-1)) + 16 * hexToDecR(s.substring(0,s.length()-1));//takes the value of the last digit in s and multiplies it by 16*hexToDecR(s minus the last digit)
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal hexary values
      =============================================*/
    public boolean equals( Object other ) { 
	return this == other || (other instanceof Hexadecimal && _decNum == ((Hexadecimal)other)._decNum);   
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Hexadecimal objects is greater
      pre:  other is instance of class Hexadecimal
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
		ClassCastException e = new ClassCastException("Can't cast " + other.getClass() + " to " + this.getClass());
	if (other instanceof Hexadecimal)
	    return _decNum - ((Hexadecimal)other)._decNum;
	else 
	    throw e;
    }


    //main method for testing
    public static void main( String[] args ) {
	System.out.println();
	System.out.println( "Testing ..." );

	Hexadecimal b1 = new Hexadecimal(17);
	Hexadecimal b2 = new Hexadecimal(23);
	Hexadecimal b3 = b1;
	Hexadecimal b4 = new Hexadecimal(43);

	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       

	System.out.println("0 in hex: "+decToHex(0));//0
	System.out.println("1 in hex: "+decToHex(1));//1
	System.out.println("17 in hex: "+decToHex(17));//11
	System.out.println("23 in hex: "+decToHex(23));//17
	System.out.println("43 in hex: "+decToHex(43));//2B
	System.out.println("617 in hex: "+decToHex(617));//269
	System.out.println("256 in hex: "+decToHex(256));//100
	System.out.println("4096 in hex: "+decToHex(4096));//1000
	System.out.println("1001 in hex: "+decToHex(1001));//3E9
	System.out.println("400000 in hex: "+decToHex(400000));//61A80
	System.out.println("1000 in hex: "+decToHex(1000));//3E8
		
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
	System.out.println( b1.compareTo("hello")); //throws the expected error
    }//end main()

} //end class
