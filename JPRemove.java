//removes directory from hash in case it's been deleted
public class JPRemove{
    
    public void remove(String k){
	String key = truncate(k);
	JPConstructor io = new JPConstructor();
	HashTableChained hash = io.retrieveTable();
	hash.remove(key);
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
	String key = args[0];
	JPRemove remover = new JPRemove();
	remover.remove(key);
    }
    
}