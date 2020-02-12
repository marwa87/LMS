
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class DAO {

	private final static String PATH = "/src/FILES";  
	
	//a representaion of the csv in the java program
	private static File authorFile;  //
	private static File bookFile;
	private static File publisherFile;	
	
	//constructor, reads all files into the programs
	public DAO() {
		authorFile = new File(PATH + "authors.csv");
		bookFile = new File(PATH + "books.csv");
		publisherFile = new File(PATH + "publisher.csv");		
		
		readAll();		
	}		
	
	private static File getAuthorFile() { return authorFile; }
	private static File getBookFile() { return bookFile; } 
	private static File getPublisherFile() { return publisherFile; }
	
	
	private void readAll() {
		readAuthors();
		readPublishers();
		readBooks();
	}
	
	public void updateAuthors() {
		writeAuthors();
		readAuthors();
	}
	
	public void updateBooks() {
		writeBooks();
		readBooks();		
	}
	
	public void updatePublishers() {
		writePublishers();
		readPublishers();
	}
	
	//reads each line in the csv file, and files the list with each entry
	private void readAuthors() {		
		try {				
			if(getAuthorFile().exists()) {
				Scanner scan = new Scanner(getAuthorFile());
				LibraryManager.authorList.clear();
				
				while(scan.hasNextLine()) {//checks if theres is another line in file
					String line = scan.nextLine(); //reads that line and stores in variable
					String[] delimitedLine = line.split(","); // splits the line by a comma and stores in array
					
					//creates an author object from each line
					Author author = new Author(Integer.parseInt(delimitedLine[0]), delimitedLine[1]);
					
					LibraryManager.authorList.add(author);								
				}
				scan.close();			
			}							
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		
	}
	
	private void readPublishers() {
		try {			
			if(getPublisherFile().exists()) {
				Scanner scan = new Scanner(getPublisherFile());
				LibraryManager.publisherList.clear();
				
				while(scan.hasNextLine()) { 
					String line = scan.nextLine(); 
					String[] delimitedLine = line.split(",");
					
					Publisher publisher = new Publisher(Integer.parseInt(delimitedLine[0]), delimitedLine[1]);
					
					LibraryManager.publisherList.add(publisher);								
				}
				scan.close();			
			}					
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	private void readBooks() {
		try {						
			if(getBookFile().exists()) {
				Scanner scan = new Scanner(getBookFile());
				LibraryManager.bookList.clear();
				
				while(scan.hasNextLine()) {
					String line = scan.nextLine();
					String[] delimitedLine = line.split(",");
					
					int id = Integer.parseInt(delimitedLine[0]);
					String name = delimitedLine[1];					
					Author author = LibraryManager.getAuthorById(Integer.parseInt(delimitedLine[2]));
					Publisher publisher = LibraryManager.getPublisherById(Integer.parseInt(delimitedLine[3]));
					
					Book book = new Book(id, name, author, publisher);
					LibraryManager.bookList.add(book);
				}
				scan.close();				
			}					
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	// write will put new information to to the csv file
	private void writeAuthors() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(getAuthorFile())); //allows to write to files			
			for(Author auth : LibraryManager.authorList) {// goes through each author object in the list
				final String line = auth.getId() + "," + auth.getName(); //formats the string from tghe author objects
				writer.write(line);// puts the string into the csv file
				writer.newLine();// creates a newline so that the next author is on a new line				
			}
			writer.close();						
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();			
		}	
	}
	
	private void writePublishers() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(getPublisherFile()));
			for(Publisher pub : LibraryManager.publisherList) {
				final String line = pub.getId() + "," + pub.getName();
				writer.write(line);
				writer.newLine();
			}
			writer.close();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	private void writeBooks() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(getBookFile()));
			for(Book book : LibraryManager.bookList) {
				final String line = book.getId() + "," + book.getName() + "," + book.getAuthor().getId() + "," + book.getPublisher().getId();
				writer.write(line);
				writer.newLine();				
			}
			writer.close();
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}
	}
	
	
	
}