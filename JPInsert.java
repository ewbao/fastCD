//alias cd = JPInsert.sh
//function JPInsert(){
// argument = $1;
// cd $1;
// path = `pwd`
// java JPInsert $1 path
//}
//class processes arguments to insert key,val pair into HashTable
//must make sure we don't insert bad values
public class JPInsert{

    public void insert(String k, String v){
	//replace hash with appropriate (saved) hash table
	String key = truncate(k);
	String value = v;
	JPConstructor io = new JPConstructor();
	HashTableChained hash = io.retrieveTable();
	hash.insert(key, value);
	io.storeTable(hash);
    }
    
    public String truncate(String x){
	String k = x;
	for(int i = k.length()-1; i >= 0; i--){
	    char letter = k.charAt(i);
	    //if the last letter is / , cut it out
	    if((letter=='/' || letter == ' ') && i==k.length()-1){
		k = k.substring(0, i);
		i = k.length();
		continue;
	    }
	    if( letter=='/' && i!=k.length()-1){
		k = k.substring(i+1);
		return k;
	    }
	}
	return k;
    }
    

    public static void main(String[] args){
	if(args.length != 2){
	    System.out.println("wrong number of arguments");
	}
	String key = args[0];
	String val = args[1];
	JPInsert adder = new JPInsert();
	adder.insert(key, val);
	
	/*
	String one = "/usr/local/visit/1";
	String two = "/usr/local/visit/2";
	String three = "/3";
	JPInsert test1 = new JPInsert();
	JPInsert test2 = new JPInsert();
	JPFind find1 = new JPFind();
	JPConstructor testCons = new JPConstructor();
	testCons.clearTable();
	test1.insert(one, "/usr/1/1");
	test1.insert(one, "/usr/1/2");
	test1.insert(one, "/usr/1/3");
	test2.insert(one, "/usr/1/1");
	test2.insert(two, "/usr/2/3");
	test2.insert(three, "/3");
	//System.out.println("test1 :" + test1.truncate(one));
	//System.out.println("test2 :" + test2.truncate(two));
	System.out.println(find1.printOptions(one));
	String return1 = find1.returnBest(one);
	System.out.println("here is the return from the hash : " + return1);
	System.out.println(find1.printOptions(one));
	*/
	
    }

}