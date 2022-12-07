import java.util.*;
import java.io.*;
import javax.swing.*;

public class main{
	public static void main (String[] args) {
		Scanner in=new Scanner(System.in);
		String sethead="";
		String head;
		String children;
		String partner;
		int  famID;
		int generation;
		String relation;
		Scanner in1=new Scanner(System.in);
		File f=new File(("FamilyTree.txt"));
		ArrayList<Person> mylist=new ArrayList<>();
		ArrayList<Person> mylist1=new ArrayList<>();
		ObjectOutputStream oos = null; //write the file
		ObjectInputStream ois = null; //read the file or result
		FileOutputStream fos=null;
		FileInputStream fis=null;
		ListIterator li = null;
		try{
			if(f.isFile()){
				ois = new ObjectInputStream(new FileInputStream(f));
				mylist = (ArrayList<Person>)ois.readObject();
				ois.close();
			}
		}
		catch(IOException e){
			System.out.println(e);	
		}
		catch(ClassNotFoundException ex){
			System.out.println(ex);
		}String choice;
		do{choice=JOptionPane.showInputDialog(null,"1. Add/Create Family Tree\n"+
												"2. Display Family Tree.\n"+
												"3. Search Member of the Family Tree.\n" +
												"4. Display Specific Family Tree.\n"+
												"0. Exit Program.");
			switch (choice){
				case "1":
					try{
						if(!f.exists()){ //Creating a new file
							f.createNewFile();
						}else{
							famID=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Family ID"));
							head=JOptionPane.showInputDialog(null,"Enter Head of the Family");
							partner=JOptionPane.showInputDialog(null,"Enter wife or Husband");
							generation=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Family Generation Level."));
							int no=Integer.parseInt(JOptionPane.showInputDialog(null,"Number of Kids."));
							for(int i=0;i<no;i++){
								children=JOptionPane.showInputDialog(null,"Name");
								relation=JOptionPane.showInputDialog(null,"Relation");
								mylist.add(new Person(famID,head, generation, partner,  children, relation));
							}
							fos= new FileOutputStream(f);
							oos=new ObjectOutputStream(fos);
							oos.writeObject(mylist);
							oos.close();
							fos.close();//Writes it to the database once you input the information of the family tree
							JOptionPane.showMessageDialog(null,"Data Appended Succesfully.");
						}
					}catch(IOException e){
						System.out.println(e);
					}break;
				case "2": //Display the information of the family tree
					try{
						fis=new FileInputStream(f);
						ois=new ObjectInputStream(fis);
						ArrayList<Person> p =(ArrayList<Person>)ois.readObject();
						Collections.sort(p, new Comparator<Person>(){//Sort family ID
							public int compare(Person p1, Person p2){
								return (p1.getGeneration()-p2.getGeneration()) + (p1.getFamID()-p2.getFamID());
							}	
						});
						for(Person person:p){
							for(int i=0;i<=p.size();i++){
								if(person.getFamID()==i){
									JOptionPane.showMessageDialog(null,"Family ID: "+ person.getFamID()+"\n"+
																"Generation Level: " + person.getGeneration()+ "\n" +
																"Head of the family: " + person.getHead()+ "\n" +
																"Partner:" +person.getPartner()+ "\n" +
																person.getChildren()+ " - " + person.getRelation());continue;
								}continue;
							}
						}
					}catch(IOException e){
						System.out.println(e);
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
							String	searchName=JOptionPane.showInputDialog(null,"Enter Name to Search");
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
									found=true;	}
							}if(!found){
								JOptionPane.showMessageDialog(null,"Record not found");	}
						}else{
							JOptionPane.showMessageDialog(null,"File Not Exist");
						}
					}catch(IOException e){
						System.out.println(e);
					}catch(ClassNotFoundException ex){
						System.out.println(ex);}
					break;
				case "4":
					try{
						if(f.isFile()){
						fis=new FileInputStream(f);
						ois=new ObjectInputStream(fis);
						ArrayList<Person> p =(ArrayList<Person>)ois.readObject();
						ois.close();
						boolean found = false;
						int ID=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Family ID of the family tree you want to search to Search"));
						Collections.sort(p, new Comparator<Person>(){//Sort family ID
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
								found=true;	}
						}if(!found){
							JOptionPane.showMessageDialog(null,"Record Not Found");}
						}else{
							JOptionPane.showMessageDialog(null,"File Not Exist");	}
					}catch(IOException e){
						System.out.println(e);
					}catch(ClassNotFoundException ex){
						System.out.println(ex);}
					break;
		
				default:
					JOptionPane.showMessageDialog(null,"Invalid Input");
				case "0":
					JOptionPane.showMessageDialog(null,"Family Tree Appended Succesfully");
		}
	}while(!choice.equals("0"));}
}