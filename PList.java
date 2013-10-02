//PList is modified DList
//package dict;
import java.io.Serializable;

public class PList implements Serializable{

    protected int size = 0;
    protected PListNode head;
    protected Object type = null;
    private static final long serialVersionUID = 2L;
   
    public PList(){
	this.head = new PListNode(null, null, null);
	this.head.next = head;
	this.head.prev = head;
	this.type = "";
	this.size = 0;	
    }

    public PList(Object myType){
	this.head = new PListNode(null, null, null);
	this.head.next = head;
	this.head.prev = head;
	this.type = myType;
	this.size = 0;
    }
    
    public void insert(Object x){
	PListNode duplicate = exists(x);
	if(duplicate != null){
	    remove(duplicate);
	}
	    PListNode temp = this.head.next;
	    PListNode object = new PListNode(x, head, temp);
	    this.head.next = object;
	    temp.prev = object;
	    this.size++;
    }
    
    public void remove(PListNode x){
	PListNode xNext = x.next;
	PListNode xPrev = x.prev;
	x.next = null;
	x.prev = null;
	xNext.prev = xPrev;
	xPrev.next = xNext;
	this.size--;
    }

    public PListNode exists(Object x){
	PListNode reference = this.head.next;
	while(reference != this.head){
	    if(!reference.item.equals(x)){
		reference = reference.next;
	    }else{
		return reference;
	    }
	}
	return null;
    }

    public Object queryType(){
	return this.type;
    }
    
    public int length(){
	return this.size;
    }


    public PListNode front(){
	return this.head.next;
    }

    public PListNode back(){
	return this.head.prev;
    }

    public PListNode head(){
	return this.head.next;
    }

    public String toString() {
	String result = "[  ";
	PListNode current = this.head.next;
	while (current != this.head) {
	    result = result + current.item + "  ";
	    current = current.next;
	}
	return result + "]";
    }
    
    /*
    public static void main(String[] argv) {
	PList l = new PList("thisType");
	l.insert("1");
       
	l.insert("2");
	l.insert("3");
	System.out.println("l  a list of 3 elements: " + l.toString());

	l.insert("2");
	System.out.println(l.toString());
	System.out.println(l.queryType());
	System.out.println(l.front().item());
	
	PList emptyList= new PList();
	if(emptyList.head == null){
	    System.out.println("what");
	}
	if(emptyList.front() != null){
	    System.out.println("what1");
		    
	}
	
    }
    */
    

}