package rmiage.framework.data;


public class Message extends Content {

	protected String subject;
	protected String corpus;
	protected UserBasic author;
	
	public Message() {
		super();
	}
	
	public Message(String subject, String corpus){
		this();
		this.subject=subject;
		this.corpus=corpus;
	}
	
	public String getSubject(){
		return this.subject;
	}
	
	public String getCorpus(){
		return this.corpus;
	}
}
