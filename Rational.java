// Shanjeed Ali
// APCS1 pd10
// HW45 -- Come Together
// 2015-12-10

public class Rational implements Comparable {
   
    private int numer;
    private int denom;

    // Default Setting
    public Rational() {
	numer = 0;
	denom = 1;
    }

    public Rational(int n, int d) {
	this();
	// denominator cannot be zero
	if (d == 0) {
	    System.out.println("Invalid. Setting to 0/1");
	}
	else {
	    numer = n;
	    denom = d;
	}
    }

    public String toString() {
	return numer + "/" + denom;
    }

    // returns the most precise float version of the rational
    public double floatValue() {
	return ((double) numer) / denom; 
    }
    // multiplies numerators and denominators
    public void multiply(Rational factor) {
	numer *= factor.numer;
	denom *= factor.denom;
    }

    //multiplies fraction by reciprocal
    public void divide(Rational factor) {
	denom *= factor.numer;
	numer *= factor.denom;
    }
    
    // changes denom to product of denoms, adds numerators after scaling
    public void add(Rational factor) {
	int temp = factor.numer * denom;
	numer *= factor.denom;
	denom *= factor.denom;
	numer += temp;
    }

    // does same as above, except subtracting numerators
    public void subtract(Rational factor) {
	int temp = factor.numer * denom;
	numer *= factor.denom;
	denom *= factor.denom;
	numer -= temp;
    }
    
    /* Euclid's algorithm, subtracts smaller of the two numbers until 
    they equal each other, at which that is the gcd of the original
    two values */
    public int gcd(){

	int tempN = numer;
	int tempD = denom;
	if (tempN == 0){
	    return tempD;
	}
	if (tempD == 0){
	    return tempN;
	}
	while (tempN != tempD){
	    if (tempN > tempD){
		tempN -= tempD;
	    }
	    else{
		tempD -= tempN;
	    }
	}
	return tempN;

    }

    // divides numer and denom by gcd
    public void reduce() {
	int gcd = gcd();
	numer /= gcd;
	denom /= gcd;
    }


    public static int gcd(int a, int b){
	if (a > b) {return gcd(b, a);} // Make the integers (smaller,larger)
	if (b % a == 0) { // If larger/smaller is 0
	    return a;     // Return smaller
	}
	return gcd(b % a, a); // Recursion: Euclidean Algorithm on (remainder, smaller)
    }
    
    //determines equality or inequality using cross-multipication

    public int compareTo( Object other ) {
	ClassCastException e = new ClassCastException("Can't cast " + other.getClass() + " to Comparable");
	NullPointerException n = new NullPointerException("Input is null");
	if (other==null){
	    throw n;
	}
	if (other instanceof Comparable){
	    if (other instanceof Rational){
		Rational r=(Rational)other; //typecasts the object as an instance of Rational
		int here = numer * r.denom;
		int there = denom * r.numer;
		if (here == there) {return 0;}
		else if (there > here) {return -1;}
		else return 1;
	    }
	    else if(other instanceof Binary){
		return (int)floatValue() - ((Binary)other).getDec();
	    }
	    else /*if(other instanceof Hexadecimal)*/{
		return (int)floatValue() - ((Hexadecimal)other).getDec();
	    }
	}
	else {
	    throw e;
	}
    }
    
    public boolean equals(Object val){
	if(val instanceof Rational){
	    Rational ra=(Rational)val;
	    return (0 == compareTo(ra));
	}
	else {return false;}
    }
    
    public static void main(String[] args){
	Rational cowboy = new Rational();
	Rational ranger = new Rational(3,5);
	Rational matador = new Rational(6,7);
	Rational superhero = new Rational(324,378);
	System.out.println(matador.equals(superhero));
	System.out.println(matador.equals(cowboy));
	System.out.println(ranger.equals(matador));
	System.out.println("WHAT".equals(matador));
	System.out.println("34".equals(matador));
    }
	
}
