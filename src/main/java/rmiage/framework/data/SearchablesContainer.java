package rmiage.framework.data;

public class SearchablesContainer<T extends Searchable> extends Container<Content> {

	public SearchablesContainer() {
		super();
	}
	
	public SearchablesContainer<Searchable> find(String searched, boolean strict) {
		SearchablesContainer<Searchable> ret = new SearchablesContainer<Searchable>();
		Searchable s;
		SearchablesContainer<Searchable> subc;
		for(IContent c:this.Contents()){
			//System.out.println(c);
			s=(Searchable) c;
			if((!strict && s.contains(searched)) || (strict && s.matches(searched))){
				ret.addContent(c);
			}
			
			try{
				subc=(SearchablesContainer<Searchable>)c;
				SearchablesContainer<Searchable> tmp = subc.find(searched, strict);
				for(IContent res: tmp.Contents()){
					ret.addContent(res);
				}
			}catch (Exception e){
			}
			
		}
		return ret;
	}
}
