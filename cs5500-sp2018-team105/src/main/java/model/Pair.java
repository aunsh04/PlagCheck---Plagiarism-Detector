package model;


/**
 * POJO for Pair
 * @author Aunsh
 *
 */
public class Pair<K,V> 
{
	
	public Pair(K val1, V val2) 
	{
		this.startIndex = val1;
		this.endIndex = val2;
	}

	private K startIndex;
	private V endIndex;
	
	
    /**
     * Returns the object that is the key of the pair
     * 
     * @return startIndex the object that is the key of the pair
     */
	public K getStartIndex() {
		return startIndex;
	}
	
	
    /**
     * Sets the object that is the key of the pair
     * 
     * @param startIndex the object that is the key of the pair
     */
	public void setStartIndex(K startIndex) {
		this.startIndex = startIndex;
	}
	
    /**
     * Returns the object that is the value of the pair
     * 
     * @return endIndex the object that is the value of the pair
     */
	public V getEndIndex() {
		return endIndex;
	}
	
    /**
     * Sets the object that is the value of the pair
     * 
     * @param endIndex the object that is the value of the pair
     */
	public void setEndIndex(V endIndex) {
		this.endIndex = endIndex;
	}


	public Pair() {
		super();
	}
	
	
}