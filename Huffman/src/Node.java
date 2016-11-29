

/**
 * @author Ella Nicolson and Rachel Swoap
 *
 */
public class Node implements Comparable<Node> {

	public int freq;
	public short datum;
	public Node left;
	public Node right;
	
	public Node (int freq, Short datum, Node left, Node right) {
		this.freq = freq;
		this.datum = datum;
		this.left = left;
		this.right = right;	
	}
	
	
	public Node (int freq, Node left, Node right) {
		this(freq, null, left, right);
	}
	
	public Node (int freq, short datum) {
		this(freq, datum, null, null);	
	}
	
	public String toString() {
		return "" + "Frequency: " + freq + "; Datum: " + datum;
	}

	@Override
	public int compareTo(Node obj) {
		if (this.freq < obj.freq) {
			return -1;
		} else if (this.freq > obj.freq) {
			return 1;
		} else {
			return 0;
		}
	}
}
