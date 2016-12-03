import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GrinDecoder {

	private static Map<Short, Integer> m;
	private static HuffmanTree ht;

	/**
	 * decodes the infile and returns output to outfile
	 * 
	 * @param infile,
	 *            an encoded .grin file
	 * @param outfile,
	 *            a decoded .grin file
	 * @throws IOException,
	 *             if input file is not a .grin file
	 */
	public static void decode(String infile, String outfile) throws IOException {
		BitInputStream in = new BitInputStream(infile);
		BitOutputStream out = new BitOutputStream(outfile);

		int header = in.readBits(32);
		if (header != 1846) {
			throw new IllegalArgumentException("Not a .grin file");
		}

		int numCodes = in.readBits(32);
		m = makeTable(in, numCodes);

		ht = new HuffmanTree(m);
		ht.decode(in, out);

		in.close();
		out.close();
	}

	/**
	 * creates frequency table using mapping
	 * 
	 * @param in,
	 *            an input stream of data
	 * @param n,
	 *            the number of codes
	 * @return m, a frequency table
	 */
	private static Map<Short, Integer> makeTable(BitInputStream in, int n) {
		Map<Short, Integer> m = new HashMap<Short, Integer>();
		for (int i = 0; i < n; i++) {
			addEntry(in, m);
		}
		return m;
	}

	/**
	 * adds data into a map
	 * 
	 * @param in,
	 *            a stream of data
	 * @param m,
	 *            a map containing data
	 */
	private static void addEntry(BitInputStream in, Map<Short, Integer> m) {
		short key = (short) in.readBits(16);
		int val = in.readBits(32);
		m.put(key, val);
	}
}
