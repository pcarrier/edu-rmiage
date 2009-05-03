package rmiage.data;

import rmiage.server.storage.Content;

public class bidonSearchablesContainer<T extends bidonISearchable> extends bidonContainer<Content> {

	/**
	 * Create a new instance of a bidonSearchablesContainer.
	 */
	public bidonSearchablesContainer() {
		super();
	}
	
	/**
	 * Search contents corresponding to the string searched.  
	 * @param searched The string to search
	 * @param strict if true, the search will try to find content corresponding exactly to the string searched (bidonISearchable.matches)
	 * If false, the method contains will be called. 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public bidonSearchablesContainer<bidonISearchable> find(String searched, boolean strict) {
		bidonSearchablesContainer<bidonISearchable> ret = new bidonSearchablesContainer<bidonISearchable>();
		bidonISearchable s;
		bidonSearchablesContainer<bidonISearchable> subc;
		for(Content c:this.Contents()){
			s=(bidonISearchable) c;
			if((!strict && s.contains(searched)) || (strict && s.matches(searched))){
				ret.addContent(c);
			}
			
			try{
				subc=(bidonSearchablesContainer<bidonISearchable>)c;
				bidonSearchablesContainer<bidonISearchable> tmp = subc.find(searched, strict);
				for(Content res: tmp.Contents()){
					ret.addContent(res);
				}
			}catch (Exception e){
			}
			
		}
		return ret;
	}
}
