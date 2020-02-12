
public class Author {
	
	//private int id = 0;
	//private String name = "No Author";
	int id;
	String name;
	
	
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }		
	
	
	public Author() {}
	
	public Author(int id, String name) {
		this.id = id;
		this.name = name;
	}
	

}