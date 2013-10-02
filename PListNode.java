//PList is a doubly linked list modified to suit our purposes 
//package dict;
import java.io.Serializable;

public class PListNode implements Serializable{

    protected PListNode prev;
    protected PListNode next;
    protected Object item;
    private static final long serialVersionUID = 5L;
    

    public PListNode(){
	item = null;
	prev = null;
	next = null;
    }

    public PListNode(Object entry, PListNode previous, PListNode nxt){
	item = entry;
	prev = previous;
	next = nxt;
    }
    
    public PListNode(Object entry){
	this(entry, null, null);
    }
    
    public PListNode prev(){
	return prev;
    }

    public PListNode next(){
	return next;
    }
    
    public Object item(){
	return item;
    }

    public boolean isValidNode(){
	return item != null;
    }

}