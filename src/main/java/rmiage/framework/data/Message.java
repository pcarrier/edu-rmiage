package rmiage.framework.data;


public class Message extends Content implements ISearchable{

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
		return (this.corpus.toLowerCase().contains(searched.toLowerCase()) ||
				this.subject.toLowerCase().contains(searched.toLowerCase()));
	}

	public boolean matches(String searched) {
		return (this.corpus.toLowerCase().equals(searched.toLowerCase()) ||
				this.subject.toLowerCase().equals(searched.toLowerCase()));
	}
}
