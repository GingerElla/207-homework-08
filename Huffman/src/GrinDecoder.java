import java.io.IOException;
import java.util.Map;

public class GrinDecoder {
	
	private static Map<Short, Integer> m;
	private static HuffmanTree ht;

	public static void decode(String infile, String outfile) throws IOException {
		BitInputStream in = new BitInputStream(infile);
		BitOutputStream out = new BitOutputStream(outfile);
		
		int header = in.readBits(32);
		if (header != 1846) { 
			throw new IllegalArgumentException("Not a .grin file"); 
		}
		
		int numCodes = in.readBits(32);
		makeTable(in, numCodes);
		
		ht = new HuffmanTree(m);
		ht.decode(in, out);
	}
	
	private static void makeTable(BitInputStream in, int n) {
		for (int i = 0; i < n; i++) {
			addEntry(in);
		}
	}
	
	private static void addEntry(BitInputStream in) {
		short key = (short) in.readBits(16);
		int val = in.readBits(32);
		m.put(key, val);
	}
}
