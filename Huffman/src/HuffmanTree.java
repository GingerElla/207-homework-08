import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Ella Nicolson and Rachel Swoap
 *
 */
public class HuffmanTree {
	
	PriorityQueue<Node> tree;
	

	public HuffmanTree(Map<Short, Integer> m) {
		tree = new PriorityQueue<Node>();
		tree.add(new Node(m.get(32), (short) 32));
		//m.forEach((k, v) -> tree.add(new Node(v, k)));
	}
	
	void encode(BitInputStream in, BitOutputStream out) {
		
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
		test.put((short) 256, 1);
		
		HuffmanTree ht = new HuffmanTree(test);
		
		System.out.println(ht);
	}
}
