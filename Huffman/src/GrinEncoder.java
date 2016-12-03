import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GrinEncoder {
	/**
	 * creates a mapping from 8-bit sequences to number-of-occurrences of those
	 * in file
	 * 
	 * @param file,
	 *            a given file
	 * @return m, a map containing 8-bit sequences and their
	 *         number-of-occurrences
	 * @throws IOException,
	 *             if input file does not exist
	 */
	private static Map<Short, Integer> createFrequencyMap(String file) throws IOException {
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

	/**
	 * encodes infile and writes the output to outfile
	 * 
	 * @param infile,
	 *            a given file of data
	 * @param outfile,
	 *            an encoded version of infile
	 * @throws IOException,
	 *             if input file does not exist
	 */
	public static void encode(String infile, String outfile) throws IOException {
		Map<Short, Integer> m = createFrequencyMap(infile);

		BitInputStream in = new BitInputStream(infile);
		BitOutputStream out = new BitOutputStream(outfile);

		out.writeBits(1846, 32);
		out.writeBits(m.size(), 32);

		m.forEach((k, v) -> {
			out.writeBits((int) k, 16);
			out.writeBits(v, 32);
		});

		HuffmanTree ht = new HuffmanTree(m);
		ht.encode(in, out);

		in.close();
		out.close();
	}
}
