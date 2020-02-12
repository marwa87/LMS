
import java.util.ArrayList;
import java.util.List;


public class LibraryManager {

	public static List<Author> authorList = new ArrayList<Author>(); // to create a static list of authors
	public static List<Book> bookList = new ArrayList<Book>();
	public static List<Publisher> publisherList = new ArrayList<Publisher>();
	
	public static final String AUTHOR = "Author"; // declare variables of string 
	public static final String BOOK = "Book";
	public static final String PUBLISHER = "Publisher";
	
	private DAO fDAO; 
	
	// construct
	public LibraryManager() {
		fDAO = new DAO();
		
	}		
	
	
	
	
	public void showAuthorList() {
		for(Author auth : authorList) { // for each author object in the author list
			UI.say(auth.getId() + ") " + auth.getName());
		}
	}
	
	public void showPublisherList() {
		for(Publisher pub : publisherList) {
			UI.say(pub.getId() + ") " + pub.getName());
		}
	}
	
	public void showBookList() {
		for(Book book : bookList) {			
			book.displayName();
		}
	}
	
	
	
	
	private int getMaxId(String type) {   // will go to 6 and get after the max which is 7
		int max = 0;
		switch(type){  // to check wich list or ID to get the max up 
			case AUTHOR:
				for(Author auth : authorList) { // goes through each author object in the list 
					if(auth.getId() > max) { // checks if the current author id is greater than the max
						max = auth.getId();// max would be assigned the highest number 
					}
				}
			break;
			case BOOK:
				for(Book book : bookList) {
					if(book.getId() > max) {
						max = book.getId();
					}
				}
			break;
			case PUBLISHER:
				for(Publisher pub : publisherList) {
					if(pub.getId() > max) {
						max = pub.getId();
					}
				}
			break;
		}
		return max;
	}
	
	
	
	
	public static Author getAuthorById(int id) {
		for(Author auth : authorList) { //goes through each author object in the list
			if(auth.getId() == id) { //checks if the id input is equal to the id of the author object
				return auth; //returns the author object that has the id of the inputted id
			}
		}
		return new Author(0,"No Author"); // if no author with input id was found, return blank author
	}
	
	public static Publisher getPublisherById(int id) {
		for(Publisher pub : publisherList) {
			if(pub.getId() == id) {
				return pub;
			}
		}
		return new Publisher(0, "No Publisher");
	}
	
	public static Book getBookById(int id) {
		for(Book book : bookList) {
			if(book.getId() == id) {
				return book;
			}
		}
		return new Book(0, "No Book", new Author(), new Publisher());
	}	

	
	
	
	public void displayBookByName() {
		UI.say("Please enter the name of the book"); //ask the name of the book
		String name = UI.readLine();// get the book name from user
		Book theBook = new Book(); //create a blank book object
		
		for(Book book : bookList) { //goes through each book in list
			if(book.getName().equalsIgnoreCase(name)) { // check if input is equal to book name, ignoring any capital letters
				theBook = book; // if book is found assign that book to theBook object
				break;
			}
		}
		
		if(theBook.getId() == 0) { // if id is 0 the book wasnt found
			UI.say("No book by the name " + name + " was found");
		}
		else {
			theBook.displayName();
		}		
	}
	
	
	
	
	
	public Author createAuthor() {
		UI.say("Please enter the name of the author");	
		String name = UI.readLine();
		Author author = new Author(getMaxId(AUTHOR)+ 1, name);
		authorList.add(author);			
		
		fDAO.updateAuthors();
		
		return author;
	}
	
	public Publisher createPublisher() {
		UI.say("Please enter the name of the publisher");
		String name = UI.readLine();
		
		Publisher pub = new Publisher(getMaxId(PUBLISHER) + 1, name);
		publisherList.add(pub);		
		
		fDAO.updatePublishers();
		
		return pub;
	}
	
	public Book createBook() {
		UI.say("Please enter the name of the book");
		String name = UI.readLine();
		
		Author auth = newOrOldAuthor();
		Publisher pub = newOrOldPublisher();
		
		Book book = new Book(getMaxId(BOOK) + 1, name, auth, pub);
		bookList.add(book);
		
		fDAO.updateBooks();
		
		return book;
	}	
	
	
	
	
	public void updateBook() {
		boolean flag = true;
		while(flag) {
			UI.say("Which book do you want to update?"); //ask which book to update
			showBookList();		// show all available books
			
			int choice = UI.readInt();// gets the users choice
			
			Book book = getBookById(choice);
			if(book.getId() == 0) {
				UI.say("Not a valid option");
				continue;
			}
			UI.say("Which do you want to change? \n<1>Book's name \n<2>Author of book \n<3>Publisher of book");
			choice = UI.readInt();
				
			if(choice == 1) {
				UI.say("What is the new name of the book?");
				String name = UI.readLine();
				book.setName(name);
				flag = false;
				fDAO.updateBooks();
			}
			else if(choice == 2) {
				Author auth = promptGetAuthor();
				book.setAuthor(auth);
				flag = false;
				fDAO.updateBooks();
			}
			else if(choice == 3) {
				Publisher pub = promptGetPublisher();
				book.setPublisher(pub);
				flag = false;
				fDAO.updateBooks();
			}
			else {
				UI.badInput();
				flag = true;
			}
		}
	}
	
	
	
	
	private Author promptGetAuthor() {
		boolean flag = true;
		Author auth = new Author();
		while(flag) {
			UI.say("Which author do you want to use?");
			showAuthorList();		
			int choice = UI.readInt();
			auth = getAuthorById(choice);
			if(auth.getId() == 0) {
				UI.badInput();
				flag = true;
				continue;
			}
			else {
				flag = false;
			}					
		}
		return auth;		
	}
	
	private Publisher promptGetPublisher() {
		boolean flag = true;
		Publisher pub = new Publisher();
		
		while(flag) {
			UI.say("Which publisher do you want to use?");
			showPublisherList();			
			int choice = UI.readInt();
			pub = getPublisherById(choice);
			if(pub.getId() == 0) {
				UI.badInput();
				flag = true;
				continue;
			}
			else {
				flag = false;					
			}					
		}
		return pub;		
	}
	
	
	
	
	
	public void updateAuthor() {
		boolean flag = true;
		
		while(flag) {
			UI.say("Which Author do you want to edit?");
			showAuthorList();	
			
			int choice = UI.readInt();
			Author auth = getAuthorById(choice);
			
			if(auth.getId() == 0) {
				UI.badInput();
				flag = true;
				continue;
			}
			else {
				UI.say("What is the new name of the Author?");
				String name = UI.readLine();
				auth.setName(name);			
				
				fDAO.updateAuthors();
				flag = false;
			}						
		}		
	}
	
	public void updatePublisher() {
		boolean flag = true;
		
		while(flag) {
			UI.say("Which Publisher do you want to edit?");
			showPublisherList();			
		
			int choice = UI.readInt();
			Publisher pub = getPublisherById(choice);
			if(pub.getId() == 0) {
				UI.badInput();
				continue;
			}
			else {
				UI.say("What is the new name of the Publisher?");
				String name = UI.readLine();
				pub.setName(name);			
				
				fDAO.updatePublishers();
				flag = false;
			}				
		}		
	}
	
	
	
	
	public void deleteAuthor() {
		boolean flag = true;
		while(flag) {
			UI.say("Which author do you want to delete?");
			showAuthorList();
			
			int choice = UI.readInt();
			Author auth = getAuthorById(choice);
			
			if(auth.getId() == 0) {
				UI.badInput();
				continue;
			}
			else {
				authorList.remove(auth);
				for(Book book : bookList) {
					if(book.getAuthor().getId() == choice) {
						book.setAuthor(new Author());
					}
				}
				fDAO.updateAuthors();
				fDAO.updateBooks();
				flag = false;	
			}		
		}
	}
	
	public void deletePublisher() {
		boolean flag = true;
		while(flag) {
			UI.say("Which publisher do you want to delete?");
			showPublisherList();			
			
			int choice = UI.readInt();
			Publisher pub = getPublisherById(choice);
			if(pub.getId() == 0) {
				UI.badInput();
				continue;
			}
			else {
				publisherList.remove(pub);
				for(Book book : bookList) {
					if(book.getPublisher().getId() == choice) {
						book.setPublisher(new Publisher());
					}
				}
				fDAO.updatePublishers();
				fDAO.updateBooks();
				flag = false;	
			}	
		}	
	}
	
	public void deleteBook() {
		boolean flag = true;
		while(flag) {
			UI.say("Which book do you want to delete?");
			showBookList();			
			
			int choice = UI.readInt();
			Book book = getBookById(choice);
			if(book.getId() == 0) {
				UI.badInput();
				continue;
			}
			else {
				bookList.remove(book);
				fDAO.updateBooks();			
				flag = false;	
			}			
		}			
	}
	
	
	
	
	
	
	private Publisher newOrOldPublisher() {
		boolean flag = true;
		Publisher pub = new Publisher();
		
		while(flag) {
			UI.say("Would you like to \n<1>Create new Publisher \n<2>Select from an existing one");		
			int choice = UI.readInt();				
			
			if(choice == 1) {
				flag = false;
				pub = createPublisher();				
			}
			else if(choice == 2) {
				flag = false;
				showPublisherList();
				int numChoice = UI.readInt();
				pub = getPublisherById(numChoice);
				
				if(pub.getId() == 0) {
					UI.badInput();
					continue;
				}
				else {
					flag = false;					
				}				
			}		
			else {
				UI.say("Not a valid choice");
				flag = true;
			}					
			
		}	
		return pub;
	}
	
	
	private Author newOrOldAuthor() {
		boolean flag = true;
		Author auth = new Author();
		while(flag) {
			UI.say("Would you like to \n<1>Create new Author \n<2>Select from an existing one");		
		
			int choiceNum = UI.readInt();
			
			if(choiceNum == 1) {
				flag = false;
				auth = createAuthor();				
			}
			else if(choiceNum == 2) {
				showAuthorList();				
				int numChoice = UI.readInt();
				auth = getAuthorById(numChoice);
				
				if(auth.getId() == 0) {
					UI.badInput();
					continue;
				}
				else {
					flag = false;
				}					
			}	
			else {
				UI.badInput();
				continue;
			}		
		}
		return auth;
	}
	
	
}
