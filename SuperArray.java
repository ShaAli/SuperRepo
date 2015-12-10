// Shanjeed Ali
// APCS1 pd10
// HW45 -- Come Together
// 2015-12-10

/*****************************
 * class SuperArray --  A wrapper class for an array. 
 * Maintains functionality:
 *  access value at index
 *  overwrite value at index
 *  report number of meaningful items
 * Adds functionality to std Java array:
 *  resizability
 *  ability to print meaningfully
 *  add item (at end)
 *  insert item
 *  remove item (while maintaining "left-justification")
 *****************************/

public class SuperArray{
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

		
    //~~~~~METHODS~~~~~
    //default constructor – initializes 10-item array
    public SuperArray() { 
	_data = new Comparable[10];
	_lastPos = -1; //flag to indicate no lastpos yet
	_size = 0;	
    }

		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() 
    { 
	String foo = "[";
	for( int i = 0; i < _size; i++ ) {
	    foo += _data[i] + ",";
	}
	//shave off trailing comma
	if ( foo.length() > 1 )
	    foo = foo.substring( 0, foo.length()-1 );
	foo += "]";
	return foo;
    }

		
    //double capacity of this SuperArray
    private void expand() 
    { 
	Comparable[] temp = new Comparable[ _data.length * 2 ];
	for( int i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }

		
    //accessor -- return value at specified index
    public Comparable get( int index ) { return _data[index]; }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) 
    { 
 	Comparable temp = _data[index];
	_data[index] = newVal;
	return temp;
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( Comparable newVal ) {
	if (_size == _data.length) {
	    expand();
	}
	set(_size, newVal ); 
	_size += 1;
	_lastPos += 1;
    }

    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, Comparable newVal ) {
	if (_size == _data.length) {
	    expand();
	}
	for (int ctr = _size; ctr > index; ctr--) {
	    _data[ctr] = _data[ctr-1];
	}
	_data[index] = newVal;
	_size += 1;
	_lastPos += 1;
    }

    //removes the item at index
    //shifts elements left to fill in newly-emptied slot
    public void remove( int index ) { 
	for (int ctr = index; ctr < _lastPos; ctr++) {
	    _data[ctr] = _data[ctr+1];
	}
	_size -= 1;
	_lastPos -= 1;
	}

    //return number of meaningful items in _data
    public int size() { 
	return _size;
    }

    //searches the array of Comparables for the input and returns the index of its first occurrence
    public int linSearch(Comparable c){
	if (_lastPos>-1){
	    for(int ctr=0; ctr<=_lastPos; ctr++){
		if (_data[ctr].equals(c)){
		    return ctr;
		}
	    }
	}
	return -1;	
    }

    public boolean isSorted(){
	boolean boo=true;
	for(int ctr=1;ctr<=_lastPos; ctr++){
	    if ((_data[ctr].compareTo(_data[ctr-1]))<0){
		boo=false;
	    }
	}
	return boo;
    }

    //main method for testing
    public static void main( String[] args ) 
    {
	SuperArray curtis = new SuperArray();

	SuperArray lee = new SuperArray();
	System.out.println("Original lee:\n" + lee);

	System.out.println("\nRational numbers added to lee:\n");
	Rational r1= new Rational(15,3);
	Rational r2= new Rational(7,13);
	Rational r3= new Rational (100,20);
	lee.add(r1);
	lee.add(r2);
	lee.add(r3);
	System.out.println(lee);

	System.out.println("\nBinary numbers added to lee:\n");
	Binary b1= new Binary(5);
	Binary b2= new Binary(63);
	Binary b3= new Binary(100);
	lee.add(b1);
	lee.add(b2);
	lee.add(b3);
	System.out.println(lee);

	System.out.println("\nHexadecimal numbers added to lee:\n");
	Hexadecimal h1= new Hexadecimal(5);
	Hexadecimal h2= new Hexadecimal(63);
	Hexadecimal h3= new Hexadecimal(100);
	lee.add(h1);
	lee.add(h2);
	lee.add(h3);
	System.out.println(lee);

	System.out.println("\nTesting add at index and remove...");
	lee.add(0,b3);
	System.out.println("add at index:  " + lee);
	lee.remove(0);
	System.out.println("remove:  " + lee);

	System.out.println("\nTesting compareTo methods...");
	System.out.println("r1 and r2: "+r1.compareTo(r2));//pos
	System.out.println("r1 and r3: "+r1.compareTo(r3));//0
	System.out.println("r1 and b1: "+r1.compareTo(b1));//0
	System.out.println("b1 and r1: "+b1.compareTo(r1));//0
	System.out.println("b1 and r2: "+b1.compareTo(r2));//pos
	System.out.println("b1 and b2: "+b1.compareTo(b2));//neg
	System.out.println("h1 and r1: "+h1.compareTo(r1));//0
	System.out.println("h1 and r3: "+h1.compareTo(r3));//0
	System.out.println("h2 and r2: "+h2.compareTo(r2));//pos

	System.out.println("\nTesting linSearch...");
	Binary b4= new Binary(63);
	Hexadecimal h4= new Hexadecimal(63);
	System.out.println(lee.linSearch(r3));
	System.out.println(lee.linSearch(b4));
	System.out.println(lee.linSearch(h4));

	System.out.println("\nTesting isSorted...");
	System.out.println(lee.isSorted());
	Binary a=new Binary(23);
	Hexadecimal b= new Hexadecimal(29);
	Rational c= new Rational(1000,30);
	SuperArray mayfield = new SuperArray();
	mayfield.add(a);
	mayfield.add(b);
	mayfield.add(c);
	System.out.println(mayfield.isSorted());

	System.out.println(a.compareTo(null));
   
	/*	happy.add(33);
	happy.add(34);
	happy.add(0,118);*/
	//System.out.println(happy);
	/*	happy.remove(0);
	System.out.println("Int at index0 of happy has been removed:\n"+ happy);
	System.out.println("happy has "+happy.size()+" meaningful ints.");
	System.out.println("Testing get...\n"+ happy.get(0));
	System.out.println("Testing set...\n"+ happy.set(4,17));

	System.out.println("Printing empty SuperArray curtis...");
	System.out.println(curtis);

	for( int i = 0; i < curtis._data.length; i++ ) {
	    curtis.set(i,i*2);
	    curtis._size++; //necessary bc no add() method yet
	    }*/
	
	/*SuperArray mayfield = new SuperArray();
	System.out.println("Printing empty SuperArray mayfield...");
	System.out.println(mayfield);

	  mayfield.add(5);
	  mayfield.add(4);
	  mayfield.add(3);
	  mayfield.add(2);
	  mayfield.add(1);

	  System.out.println("Printing populated SuperArray mayfield...");
	  System.out.println(mayfield);

	  mayfield.remove(3);
	  System.out.println("Printing SuperArray mayfield post-remove...");
	  System.out.println(mayfield);
	  mayfield.remove(3);
	  System.out.println("Printing SuperArray mayfield post-remove...");
	  System.out.println(mayfield);

	  mayfield.add(3,99);
	  System.out.println("Printing SuperArray mayfield post-insert...");
	  System.out.println(mayfield);
	  mayfield.add(2,88);
	  System.out.println("Printing SuperArray mayfield post-insert...");
	  System.out.println(mayfield);
	  mayfield.add(1,77);
	  System.out.println("Printing SuperArray mayfield post-insert...");
	  System.out.println(mayfield);*/

    }//end main
		
}//end class
