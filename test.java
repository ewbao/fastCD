public class test{

    public static void main(String[] args){
	HashTableChained d = new HashTableChained();
	JPConstructor table = new JPConstructor();
	table.storeTable(d);
	System.out.println("Dictionary created... current size: " + d.size());
	System.out.println("Number of Buckets: " + d.buckets);
	d.insert("one", "1234");
	d.insert("one", 12345);
	
	d.insert("one", 1);
	d.insert("one", 12345);

	d.insert( "two", "2" );
	d.insert("two", "2");

	table.storeTable(d);
	JPConstructor newTable = new JPConstructor();
	d = newTable.retrieveTable();
	d.insert("three", "2");
	d.insert( "three", "3" );
	//d.remove("two");
	
	System.out.println ("Integers \"one\" , \"two\" , and \"three\" inserted...");
	if(d.find("one")!=null){
	System.out.println ("Looking up \"one\" : " + d.find("one").toString());
	}
	if(d.find("two")!=null){
	System.out.println ("Looking up \"two\" : " + d.find("two").toString());
	}
	System.out.println ("Looking up \"three\" : " + d.find("three").toString());
	System.out.println("Current size: " + d.size());
	System.out.println("Looking up \"four\" : " + d.find("four"));
	System.out.println(d.collisions());
	System.out.println("Removing all entries...");
	//d.remove("one");
	d.remove("two");
	d.remove("three");
	System.out.println("Current size: " + d.size() + "... Dictionary is empty -> " + d.isEmpty() );
	newTable.storeTable(d);
	

    }
}