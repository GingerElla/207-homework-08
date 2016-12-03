import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Ella Nicolson and Rachel Swoap
 *
 */
public class HuffmanTree {
	
	private static PriorityQueue<Node> tree;
	private static Map<Short, int[]> huffCodes;
	
	public HuffmanTree(Map<Short, Integer> m) {
		// Make a tree of nodes
		tree = new PriorityQueue<Node>();
		Map<Short, Node> temp = new HashMap<Short, Node>();
		m.forEach((k, v) -> { 
			tree.add(new Node(v, k));
			temp.put(k, new Node (v, k));});

		tree.add(new Node(1, (short) 256));
		condenseList(tree);
		
		// Create a map of the Huffman Codes
		huffCodes = new HashMap<Short, int[]>();
		m.forEach((k, v) -> getCode());
		
	}
	
	public void condenseList(PriorityQueue<Node> ls) {
		while (ls.size() >= 2) {
			Node temp = condenseNodes(ls.poll(), ls.poll());
			ls.add(temp);
		}
	}
	
	public Node condenseNodes(Node fore, Node aft) {
		return new Node((fore.freq + aft.freq), fore, aft);
	}
	
	public void getCode() {
		getCodeH(tree.peek(), new int[1]);
	}
	
	private void getCodeH(Node root, int[] ret) {
		if (root.datum != null) {
			huffCodes.put(root.datum, Arrays.copyOf(ret, ret.length - 1));
		}
		
		if (root.left != null) {
			ret[ret.length - 1] = 0;
			int[] temp = Arrays.copyOf(ret, ret.length + 1);
			getCodeH(root.left, temp);
		} 
		
		if (root.right != null) {
			ret[ret.length - 1] = 1;
			int[] temp = Arrays.copyOf(ret, ret.length + 1);
			getCodeH(root.right, temp);
		}
	}
	
	public void encode(BitInputStream in, BitOutputStream out) {
		List<int[]> bits = new ArrayList<int[]>();
		for (int c = 0; (c = in.readBits(8)) != -1;) {
			short temp = (short) c;
			bits.add(huffCodes.get(temp));
		}
		
		for (int[] c : bits) {
			for (int i = 0; i < c.length; i++) {
				out.writeBit(c[i]);
			}
		}
		
		int[] eof = huffCodes.get((short) 256);
		for (int i = 0; i < eof.length; i++) {
			out.writeBit(eof[i]);
		}
	}
	
	public void decode(BitInputStream in, BitOutputStream out) {
		List<Integer> ret = new ArrayList<Integer>();
		Node root = tree.peek();
		for (int bit = 0; (bit = in.readBit()) != -1;) {
			if (bit == 0) {
				root = root.left;
				if (root.datum != null) {
					if (root.datum == (short) 256) {
						break;
					} else {
						ret.add((int) root.datum);
						root = tree.peek();
					}
				}
			} else {
				root = root.right;
				if (root.datum != null) {
					if (root.datum == (short) 256) {
						break;
					} else {
						ret.add((int) root.datum);
						root = tree.peek();
					}
				}
			}
		}
		
		for (int c : ret) {
			out.writeBits(c, 8);
		}
	}
	
	public String toString() {
		return tree.toString();
	}
	
//	public static void main(String[] args) throws IOException {
//		Map<Short, Integer> test = new HashMap<Short, Integer>();
//		test.put((short) 'a', 3);
//		test.put((short) ' ', 2);
//		test.put((short) 'b', 2);
//		test.put((short) 'z', 1);
//		
//		HuffmanTree ht = new HuffmanTree(test);
//		
//		System.out.println(ht.toString());
//		
//		ht.huffCodes.forEach((k, v) -> System.out.println((char) k.intValue() +
//				" " + Arrays.toString(v) + "\n"));
//		
//		BitInputStream in = new BitInputStream("simpleTests/originalText.txt");
//		BitOutputStream out = new BitOutputStream("simpleTests/encodedText.txt");
//		ht.encode(in, out);
//		in.close();
//		out.close();
//		
//		BitInputStream in2 = new BitInputStream("simpleTests/encodedText.txt");
//		BitOutputStream out2 = new BitOutputStream("simpleTests/decodedText.txt");
//		ht.decode(in2, out2);
//	}
}
