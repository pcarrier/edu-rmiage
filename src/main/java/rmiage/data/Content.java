/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiage.data;

import java.util.ArrayList;

/**
 *
 * @author jc
 * Tis interface describe an element for our Graphe.
 */
public class Content extends Object implements IContent{	
    private ArrayList<IContainer<IContent>> parents;
    
    public Content(){
        super();
        this.parents = new ArrayList<IContainer<IContent>>();
    }
    /* (non-Javadoc)
	 * @see rmiage.framework.data.IContent#addParent(rmiage.framework.data.Content)
	 */
    public void  addParent(IContainer<IContent> parent){
    	if(parent != null){
    		if (!this.parents.contains(parent)){
    			this.parents.add(parent);
    		}
    	}
    }
    
    /* (non-Javadoc)
	 * @see rmiage.framework.data.IContent#getParents()
	 */
    public ArrayList<IContainer<IContent>> getParents()
    {
    	return this.parents;
    }
   
    /* (non-Javadoc)
	 * @see rmiage.framework.data.IContent#dropParent(rmiage.framework.data.IContent)
	 */
    @SuppressWarnings("unchecked")
	public void dropParent(IContainer<IContent> t){
    	ArrayList<IContainer<IContent>> tmp = (ArrayList<IContainer<IContent>>)this.parents.clone();
    	for(IContainer<IContent> ct:tmp){
    		if (this.parents.contains(ct)){
    			this.parents.remove(ct);
    		}
    	}
    }
    /* (non-Javadoc)
	 * @see rmiage.framework.data.IContent#delete()
	 */
    public void delete(){
    	for(IContainer<IContent> ct:this.parents){
    		this.dropParent(ct);
    	}
    }
    /* (non-Javadoc)
	 * @see rmiage.framework.data.IContent#getUi()
	 */
	public void getUi() {
		// TODO Auto-generated method stub
		
	}

}
