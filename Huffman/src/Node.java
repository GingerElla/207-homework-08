

/**
 * @author Ella Nicolson and Rachel Swoap
 *
 */
public class Node {

	public int freq;
	public Short datum;
	public Node left;
	public Node right;
	
	public Node (int freq, Short datum, Node left, Node right) {
		this.freq = freq;
		this.datum = datum;
		this.left = left;
		this.right = right;	
	}
	
	
	public Node (int freq, Node left, Node right) {
		new Node (freq, null, left, right);
	}
	
	public Node (int freq, Short datum) {
		new Node (freq, datum, null, null);	
	}
	
	public String toString() {
		return "" + "Frequency: " + freq + "; Datum: " + datum;
	}
}
