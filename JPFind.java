//alias JP = `java JPFind | cd`
//class processes arguments to return a pwd from the hashTable
//JPFind should only directly use retrieveTable(), never storeTable()
public class JPFind{
    
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
    
    public String returnBest(String k){
	String key = truncate(k);
	JPConstructor io = new JPConstructor();
	HashTableChained hash = io.retrieveTable();
	PList paths = hash.find(key);
	if(paths != null && paths.length() != 0){
	    return (String) paths.front().item();
	}else{
	    return null;
	}
    }

    public String returnAlt(String k){
	String key = truncate(k);
	JPConstructor io = new JPConstructor();
	HashTableChained hash = io.retrieveTable();
	PList paths = hash.find(key);
	JPInsert reorder = new JPInsert();
	if(paths != null && paths.length() != 0){
	    reorder.insert((String) paths.queryType(), (String) paths.back().item());
	    return (String) paths.back().item();
	}else{
	    return null;
	}
    }

    public String printOptions(String k){
	String key = truncate(k);
	JPConstructor io = new JPConstructor();
	HashTableChained hash = io.retrieveTable();
	PList paths = hash.find(key);	
	if(paths != null && paths.length()!=0){
	    return paths.toString();
	}else{
	    return null;
	}
    }

    public static void main(String[] args){
	String key = args[0];
	String option="";
	JPFind finder = new JPFind();
	String returnPath="";
	if(args.length > 1){
	    option=args[1];
	}

	if(option.equals("alt")){
	    returnPath = finder.returnAlt(key);
	}else{
	    returnPath = finder.returnBest(key);
	}
	if(option.equals("print")){
	    returnPath = finder.printOptions(key);
	}
	System.out.println(returnPath);
	//return returnPath;
	
    }

}