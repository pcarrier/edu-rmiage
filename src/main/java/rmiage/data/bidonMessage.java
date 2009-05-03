package rmiage.data;


public class bidonMessage extends bidonContent implements bidonISearchable{

	protected String subject;
	protected String corpus;
	protected bidonUserBasic author;
	protected bidonContainer<bidonUserBasic> destinataires;
	
	public bidonMessage() {
		super();
	}
	
	public bidonMessage(String subject, String corpus){
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
