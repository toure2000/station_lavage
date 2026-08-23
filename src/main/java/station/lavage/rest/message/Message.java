package station.lavage.rest.message;

public class Message {
	private String text;
	private String text2;
	private String text3;

	
	
	public String getText2() {
		return text2;
	}
	public void setText2(String text2) {
		this.text2 = text2;
	}
	public String getText3() {
		return text3;
	}
	public void setText3(String text3) {
		this.text3 = text3;
	}
	public Message(String text) {
		// TODO Auto-generated constructor stub
		this.text=text;
	}
	public Message() {}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
