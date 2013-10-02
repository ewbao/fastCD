import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//JPConstructor serves as out input/output class that handles data flow
//between our data structures
//JPConstructor creates a centralized hash table. It will only work 
//on one copy the whole time no matter how many times we instantiate it

public class JPConstructor implements Serializable{

    private static final long serialVersionUID = 6L;
    private File file=new File("/home/user/cdProject/hashCode/dict/JPhashtable.data");

    //constructor handles creating the hashTable if it does not exist already
    public JPConstructor(){
	try{
	    if(!file.exists()){
		file.getParentFile().mkdirs();
		file.createNewFile();
		storeTable(new HashTableChained());
	    
	    }
	}catch(Exception e){
	    System.out.println(e);
	}
    }

    //return the hash table that's in our data. If no data, return new hashtable
    //and store it
    public HashTableChained retrieveTable(){
	try{
	    if(file.exists()){
		FileInputStream diskInput = new FileInputStream(file);
		ObjectInputStream objInput = new ObjectInputStream(diskInput);
		Object obj = objInput.readObject();
		if( obj instanceof HashTableChained){
		    HashTableChained hash = (HashTableChained) obj;
		    return hash;
		}else{
		    HashTableChained hash = new HashTableChained();
		    storeTable(hash);
		    return hash;
		}
	    }else{
		HashTableChained hash = new HashTableChained();
		storeTable(hash);
		return hash;
	    }
	}catch(Exception e){
	    return null;
	}
	
    }
    

    //after working on table, put it back
    public void storeTable(HashTableChained table){
	
	try{
	    if(!file.exists()){
		System.out.println("file does not exist at storeTable");
	    }
	    HashTableChained hash = table;	    
	    FileOutputStream diskOutput = new FileOutputStream(file);
	    ObjectOutputStream objOutput = new ObjectOutputStream(diskOutput);
	    objOutput.writeObject(hash);
	}catch(Exception e){
	    System.out.println("got exception at storeTable");
	}
    }

   
    //create new hash, clear the other one.
    public void clearTable(){
	try{
	   
	    HashTableChained hash = new HashTableChained();	
	    FileOutputStream diskOutput = new FileOutputStream(file);
	    ObjectOutputStream objOutput = new ObjectOutputStream(diskOutput);
	    objOutput.writeObject(hash);
	}catch(Exception e){
	    System.out.println("got exception at clearTable");
	}
    }

    

}