import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Ella Nicolson and Rachel Swoap
 *
 */
public class HuffmanTree {
	
	private static Queue<Node> tree;
	

	public HuffmanTree(Map<Short, Integer> m) {
		tree = new PriorityQueue<Node>();
		m.forEach((k, v) -> tree.add(new Node(v, k)));
		tree.add(new Node(1, (short) 256));
	}
	
	public void encode(BitInputStream in, BitOutputStream out) {
		
	}
	
	public void decode(BitInputStream in, BitOutputStream out){
		
	}
	
	public String toString() {
		return tree.toString();
	}
	
	public static void main(String[] args) {
		Map<Short, Integer> test = new HashMap<Short, Integer>();
		test.put((short) 'a', 3);
		test.put((short) ' ', 2);
		test.put((short) 'b', 2);
		test.put((short) 'z', 1);
		
		HuffmanTree ht = new HuffmanTree(test);
		
		System.out.println(ht.toString());
	}
}
