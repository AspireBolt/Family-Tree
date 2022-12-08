import java.util.*;
import java.io.*;
public class Person implements Serializable{
private	String head;
private String children;
private String partner;
private int  famID;
private int generation;
private String relation;

public Person(int famID, String head,int generation, String partner, String children,String relation){


	this.famID=famID;
	this.generation=generation;
	this.head=head;
	this.partner=partner;
	this.children=children;
	this.relation=relation;

}
public String getPartner(){
	return partner;
}

public String getHead(){
	return head;
}

public String getChildren(){
	return children;
}
public String getRelation(){
	return relation;
}
public int getFamID(){
	return famID;
}
public int getGeneration(){
	return generation;
}
}