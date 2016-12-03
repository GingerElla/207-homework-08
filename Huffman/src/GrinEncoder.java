import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GrinEncoder {
	
	private static Map<Short, Integer> createFrequencyMap(String file) 
			throws IOException {
		BitInputStream in = new BitInputStream(file);
		Map<Short, Integer> m = new HashMap<Short, Integer>();
		for (short c = 0; (c = (short) in.readBits(8)) != -1;) {
			if (m.containsKey(c)) {
				m.put(c, (m.get(c) + 1));
			} else {
				m.put(c, 1);
			}
		}
		
		return m;
	}
	
	public void encode(String infile, String outfile) throws IOException {
		Map<Short, Integer> m = createFrequencyMap(infile);
		
		BitInputStream in = new BitInputStream(infile);
		BitOutputStream out = new BitOutputStream(outfile);
		
		out.writeBits(1846, 32);
		out.writeBits(m.size(), 32);
		
		m.forEach((k, v) -> { out.writeBits((int) k, 16);
							  out.writeBits(v, 32); });
		
		HuffmanTree ht = new HuffmanTree(m);
		ht.encode(in, out);
	}
}
