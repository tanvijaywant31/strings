//import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;


/**
 * 
 * BB: 10
 * 
 * Complexity O(n)
 */
public  class HashOfString {
	
    private final char value[];
	
    /* lazy init */
    private int hash; // default 0/
    
	public HashOfString (String str) {
		value = str.toCharArray();
	}
	
	public int hashCode() {
		int h = hash; // copy for thread safety
		if (h != 0 || value.length == 0) return h;
		
		for (char ch : value) {
			h = 31 * h + ch;
		}
		
		hash = h; // save the change.
		return h;
	}
	
	public static void main(String[] args) {
		HashOfString h1 = new HashOfString("ameya");
		assertEquals("ameya".hashCode(), h1.hashCode());
	}
}
