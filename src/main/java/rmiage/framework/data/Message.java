package rmiage.framework.data;


public class Message extends Content implements Searchable{

	protected String subject;
	protected String corpus;
	protected UserBasic author;
	protected Container<UserBasic> destinataires;
	
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

	public boolean contains(String searched) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean matches(String searched) {
		// TODO Auto-generated method stub
		return false;
	}
}
