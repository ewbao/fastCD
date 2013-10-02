import java.util.Random;
import java.io.Serializable;


public class HashTableChained implements Serializable{


   
    protected int buckets;
    protected int entries;
    protected int alpha;
    protected int beta;
    protected PList[] dict;
    protected final static int PRIME = 9999991;
    private static final long serialVersionUID = 3L;



    public HashTableChained(int sizeEstimate) {
	Random random = new Random();
	buckets = nextPrime( (int) (sizeEstimate*1.5) );
	alpha = random.nextInt(PRIME-2)+2;
	beta = random.nextInt(PRIME);   
	entries = 0;
	dict = new PList[buckets];
    }



    public HashTableChained() {

	this(101);
    }

 
    int compFunction(int code) {
	return ( ( Math.abs( (alpha*code) + beta ) % PRIME ) % buckets);
    }

  

    public int size() {
	return entries;
    }

  
    public boolean isEmpty() {

	return entries==0;
    }

   

    public PList insert(Object key, Object value) {    
	//value is a path to the directory
	//key is the name of the directory
	int index = compFunction(key.hashCode());
	PList chainedList=null;
	PList subList=null;
	boolean makeNewSublist = true;
	if(dict[index]==null){
	    //add new PList
	    dict[index] = new PList();
	    
	}

	//check to see if sublist with the same key exists. 
	//if it exists, just add the value to that typelist
	chainedList=dict[index];
	PListNode reference = chainedList.front();
	while(reference.isValidNode()){
	    subList = (PList) reference.item();
	    reference = reference.next();
	    if(key.equals(subList.queryType())){
		subList.insert(value);
		makeNewSublist = false;
		break;
	    }
	}
	//if the typelist does not exist,
	// make a new sublist and add it to the chainedList
	if(makeNewSublist){
	    subList = new PList(key);
	    subList.insert(value);
	    chainedList.insert(subList);
	    entries++;
	}
		
	return subList;
	
    }

 
    public PList find(Object key) {

	int index = compFunction(key.hashCode());
	PList entry = dict[index];
	
	if (entry==null){
	    return null;
	}
	else{
	    try{
		PListNode cur = entry.front();
		while (cur.isValidNode()){
		    PList subList = (PList) cur.item();
		    if (subList.queryType().equals(key) ){
			return subList;
		    }
		    cur = cur.next();
		}
	    }
	    catch (Exception e){
		System.err.println(e);
	    }
	    return null;
	}
    }

  

    public PList remove(Object key) {

	int index = compFunction(key.hashCode());
	PList entry = dict[index];
	if (entry==null){
	    return null;
	}
	else{
	    try{
		PListNode cur = entry.front();
		while (cur.isValidNode()){
		    PList subList = (PList) cur.item();
		    if ( subList.queryType().equals(key) ){
			entry.remove(cur);
			entries--;
			return subList;
		    }
		    cur = cur.next();
		}
	    }
	    catch (Exception e){
		System.out.println("happens in remove");
		System.err.println(e);
	    }
	    return null;
	}
    
    }

 
    public void makeEmpty() {

	dict = new PList[buckets];
	entries=0;    
    }
  
    //nextPrime() returns the first prime number greater than or equal to n
  
    protected int nextPrime(int n){
	for (int i = n;;i++){
	    if ( isPrime(i) ){
		return i;
	    }
	}
    }
  
    //isPrime() returns true if n is prime, false if otherwise
  
    protected boolean isPrime(int n){
	if ( n < 2){
	    return false;
	}
	else if (n < 4){
	    return true;
	}
	else{
	    for (int i = 2; i < Math.pow(n,.5)+1; i++){
		if ( n % i ==0 ){
		    return false;
		}
	    }
	    return true;
	}
    }
  
    public String collisions(){
	String s = "Collision Map \n==============\n";
	int col=0;
	int len;
	for (int i=0; i < dict.length; i++){
	    if ( ! (dict[i]==null)){
		len = dict[i].length();
		s+= "Bucket " + i + " contains " + len + " entries \n";
		if (len >1){
		    col += len-1;
		}
	    }
	}
	s+= "Total collisions: " + Integer.toString(col);
	s+= "\nExpected collisions: " + Double.toString(expectedCollisions(entries));
	return s;
    }
      
    public double expectedCollisions(int n){
	return n - buckets + buckets*Math.pow((1- 1.0/buckets),n);
    }

    
    
  
    

}
