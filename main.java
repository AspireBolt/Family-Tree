import java.util.*;
import java.io.*;
import javax.swing.*;

public class main{
	public static void main (String[] args) {
		Scanner in=new Scanner(System.in);
		String sethead="";
		String head;
		String children="";
		String partner;
		int  famID;
		int generation;
		String relation="";
		Scanner in1=new Scanner(System.in);
		File f=new File(("testFamTree111.txt"));
		ArrayList<Person> mylist=new ArrayList<>();
		ArrayList<Person> mylist1=new ArrayList<>();
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		FileOutputStream fos=null;
		FileInputStream fis=null;
		ListIterator li = null;
		try{
			if(f.isFile()){
				ois = new ObjectInputStream(new FileInputStream(f));
				mylist = (ArrayList<Person>)ois.readObject();
				ois.close();
			}
		}catch(IOException e){
			System.out.println(e);
		}catch(ClassNotFoundException ex){
			System.out.println(ex);
		}String choice;
		do{
			choice=JOptionPane.showInputDialog(null,"1. Add/Create Family Tree\n"+
													"2. Display Entire Family Tree.\n"+
													"3. Search Specific Member in the Family Tree.\n" +
													"4. Display Specific Family Tree ID.\n"+
													"5. Display Specific Family Tree Generation.\n"+
													"0. Exit Program.");
		switch (choice){
			case "1":
				try{
					if(!f.exists()){
						f.createNewFile();
					}else{ 	
						famID=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Family ID"));	
						head=JOptionPane.showInputDialog(null,"Enter Head of the Family");
						partner=JOptionPane.showInputDialog(null,"Enter wife or Husband");
						generation=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Family Generation Level."));
						int no=Integer.parseInt(JOptionPane.showInputDialog(null,"Number of Kids."));	
						if(no==0){
							children="This family had no children";
							relation="Cannot be specified";
							no=1;
							mylist.add(new Person(famID,head, generation, partner,  children, relation));
						}else{
							for(int i=0;i<no;i++){
								children=JOptionPane.showInputDialog(null,"Name");
								relation=JOptionPane.showInputDialog(null,"Relation");
								mylist.add(new Person(famID,head, generation, partner,  children, relation));
							}
						}fos= new FileOutputStream(f);
	 					oos=new ObjectOutputStream(fos);
						oos.writeObject(mylist);
						oos.close();
						fos.close();
						JOptionPane.showMessageDialog(null,"Data Appended Succesfully.");
		
					}
				}catch(IOException e){
					System.out.println(e);
				}break;
			case "2":
				try{
					fis=new FileInputStream(f);
					ois=new ObjectInputStream(fis);
					ArrayList<Person> p =(ArrayList<Person>)ois.readObject();
					Collections.sort(p, new Comparator<Person>(){
						public int compare(Person p1, Person p2){
							return p1.getGeneration()-p2.getGeneration();
						}	
					});
					Collections.sort(p, new Comparator<Person>(){
						public int compare(Person p1, Person p2){
							return p1.getFamID()-p2.getFamID();
						}	
					});for(Person person:p){
						for(int i=0;i<=p.size();i++){
							if(person.getFamID()==i){
								JOptionPane.showMessageDialog(null,"Family ID: "+ person.getFamID()+"\n"+
																"Generation Level: " + person.getGeneration()+ "\n" +
																"Head of the family: " + person.getHead()+ "\n" +
																"Partner:" +person.getPartner()+ "\n" +
																person.getChildren()+ " - " + person.getRelation());
	
							}continue;
						}
					}
				}catch(IOException e){
					JOptionPane.showMessageDialog(null,"File Not Found");
				}catch(ClassNotFoundException ex){
					System.out.println(ex);
				}break;
			case "3":
				try{
					if(f.isFile()){
						fis=new FileInputStream(f);
						ois=new ObjectInputStream(fis);
						ArrayList<Person> p =(ArrayList<Person>)ois.readObject();//Reads the specific object given
						ois.close();
						boolean found = false;
						String	searchName=JOptionPane.showInputDialog(null,"Enter any Family Tree Member's Name to Search");
						String searchNameLower=searchName.toLowerCase();
						for(Person person:p){
							String toLower=	person.getHead().toLowerCase();
							String toLower1=person.getPartner().toLowerCase();
							String toLower2=person.getChildren().toLowerCase();
							if(searchNameLower.equals(toLower)||searchNameLower.equals(toLower1)||searchNameLower.equals(toLower2)){
								JOptionPane.showMessageDialog(null,"Family ID: "+ person.getFamID()+"\n"+
																	"Generation Level: " + person.getGeneration()+ "\n" +
																	"Head of the family: " + person.getHead()+ "\n" +
																	"Partner:" +person.getPartner()+ "\n" +
																	person.getChildren()+ " - " + person.getRelation());
								found=true;	
							}
						}if(!found){
							JOptionPane.showMessageDialog(null,"Record not found");	
						}
					}else{
						JOptionPane.showMessageDialog(null,"File Not Exist");
					}
				}catch(IOException e){
					JOptionPane.showMessageDialog(null,"File Not Found");
				}catch(ClassNotFoundException ex){
					JOptionPane.showMessageDialog(null,"File Not Found");
				}break;
			case "4":
				try{
					if(f.isFile()){
					fis=new FileInputStream(f);
					ois=new ObjectInputStream(fis);
					ArrayList<Person> p =(ArrayList<Person>)ois.readObject();
					ois.close();
					boolean found = false;
					int ID=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter specific Family Tree ID to search"));
					Collections.sort(p, new Comparator<Person>(){
						public int compare(Person p1, Person p2){
							return (p1.getGeneration()-p2.getGeneration()) + (p1.getFamID()-p2.getFamID());
						}
					});
					for(Person person:p){
						if(ID==person.getFamID()){
							JOptionPane.showMessageDialog(null,"Family ID: "+ person.getFamID()+"\n"+
																"Generation Level: " + person.getGeneration()+ "\n" +
																"Head of the family: " + person.getHead()+ "\n" +
																"Partner:" +person.getPartner()+ "\n" +
																person.getChildren()+ " - " + person.getRelation());
							found=true;
						}
					}if(!found){
						JOptionPane.showMessageDialog(null,"Record Not Found");}
					}else{
						JOptionPane.showMessageDialog(null,"File Not Exist");	}
				}catch(IOException e){
					JOptionPane.showMessageDialog(null,"File Not Found");
				}catch(ClassNotFoundException ex){
					System.out.println(ex);
				}break;
			case "5":
				try{
					if(f.isFile()){
					fis=new FileInputStream(f);
					ois=new ObjectInputStream(fis);
					ArrayList<Person> p =(ArrayList<Person>)ois.readObject();
					ois.close();
					boolean found = false;
					int ID=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter specific Family Tree ID to search Generation Level"));
					generation=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter specific Generation level of the family tree"));
					Collections.sort(p, new Comparator<Person>(){
						public int compare(Person p1, Person p2){
							return (p1.getGeneration()-p2.getGeneration()) + (p1.getFamID()-p2.getFamID());
						}	
					});
					for(Person person:p){
						if(ID==person.getFamID()&&generation==person.getGeneration()){
							JOptionPane.showMessageDialog(null,"Family ID: "+ person.getFamID()+"\n"+
																"Generation Level: " + person.getGeneration()+ "\n" +
																"Head of the family: " + person.getHead()+ "\n" +
																"Partner:" +person.getPartner()+ "\n" +
																person.getChildren()+ " - " + person.getRelation());
							found=true;
						}
					}if(!found){
						JOptionPane.showMessageDialog(null,"Record Not Found");}
					}else{
						JOptionPane.showMessageDialog(null,"File Not Exist");	}
				}catch(IOException e){
					JOptionPane.showMessageDialog(null,"File Not Found");
				}catch(ClassNotFoundException ex){
					System.out.println(ex);}
				break;
			case "0":
				JOptionPane.showMessageDialog(null,"Family Tree Appended Succesfully");
				break;
			default:
				JOptionPane.showMessageDialog(null,"Invalid Input");
		}
	}while(!choice.equals("0"));}
}