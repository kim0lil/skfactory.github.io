package ex;

public class Todo {
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public Todo() { }
	
	public Todo(int id, String contents) {
		this.id = id;
		this.contents = contents;
	}

	private int id;
	private String contents;
}
