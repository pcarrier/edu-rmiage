package rmiage.framework.data;

public class SearchablesContainer<T extends Searchable> extends Container<Content> {

	/**
	 * Create a new instance of a SearchablesContainer.
	 */
	public SearchablesContainer() {
		super();
	}
	
	/**
	 * Search contents corresponding to the string searched.  
	 * @param searched The string to search
	 * @param strict if true, the search will try to find content corresponding exactly to the string searched (Searchable.matches)
	 * If false, the method contains will be called. 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SearchablesContainer<Searchable> find(String searched, boolean strict) {
		SearchablesContainer<Searchable> ret = new SearchablesContainer<Searchable>();
		Searchable s;
		SearchablesContainer<Searchable> subc;
		for(IContent c:this.Contents()){
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
