package rmiage.data;

public class SearchablesContainer<T extends ISearchable> extends Container<Content> {

	/**
	 * Create a new instance of a SearchablesContainer.
	 */
	public SearchablesContainer() {
		super();
	}
	
	/**
	 * Search contents corresponding to the string searched.  
	 * @param searched The string to search
	 * @param strict if true, the search will try to find content corresponding exactly to the string searched (ISearchable.matches)
	 * If false, the method contains will be called. 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SearchablesContainer<ISearchable> find(String searched, boolean strict) {
		SearchablesContainer<ISearchable> ret = new SearchablesContainer<ISearchable>();
		ISearchable s;
		SearchablesContainer<ISearchable> subc;
		for(IContent c:this.Contents()){
			s=(ISearchable) c;
			if((!strict && s.contains(searched)) || (strict && s.matches(searched))){
				ret.addContent(c);
			}
			
			try{
				subc=(SearchablesContainer<ISearchable>)c;
				SearchablesContainer<ISearchable> tmp = subc.find(searched, strict);
				for(IContent res: tmp.Contents()){
					ret.addContent(res);
				}
			}catch (Exception e){
			}
			
		}
		return ret;
	}
}
