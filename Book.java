
public class Book {
	
	//private int id = 0;
	//private String name = "No Book";
	
	int id;
	String name;
	private Author author;
	private Publisher publisher;
	
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public Author getAuthor() { return author; }
	public void setAuthor(Author author) { this.author = author; }
	
	public Publisher getPublisher() { return publisher; }
	public void setPublisher(Publisher publisher) { this.publisher = publisher; }		
	
	
	public Book() {}
	
	public Book(int id, String name, Author author, Publisher publisher) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.publisher = publisher;		
	}
	
	
	public void displayName() {
		System.out.println(this.getId() + ") " + this.getName() + ", Author: " 
				+ getAuthor().getName() + ", Publisher: " + getPublisher().getName());
		
		
		//UI.say(this.getId() + ") " + this.getName() + ", Author: " 
				//+ getAuthor().getName() + ", Publisher: " + getPublisher().getName());
	}
	
}