import java.io.FileInputStream;
import java.io.*;
import java.util.*;

public class Grin {
	
	private static Map<Short, Integer> m;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String command = args[0];
		String inFile = args[1];
		String outFile = args[2];
		boolean toEncode = isEncode(command);
		
		InputStream inF = new FileInputStream(inFile);
		makeFrequencyTable(inF);
		//System.out.println(m.toString());
		HuffmanTree ht = new HuffmanTree(m);
		System.out.println(ht.toString());
		inF.close();
		
		BitInputStream in = new BitInputStream(inFile);
		BitOutputStream out = new BitOutputStream(outFile);
		System.out.println(toEncode);
		if (toEncode) {
			ht.encode(in, out);
		} else {
			ht.decode(in, out);
		}
		in.close();
		out.close();
	}
	
	private static boolean isEncode (String opt) {
		if (opt.toLowerCase().equals("encode")) {
			return true;
		} else if (opt.toLowerCase().equals("decode")) {
			return false;
		} else {
			throw new IllegalArgumentException("Must provide"
					+ " \"encode\" or \"decode\".");
		}
	}
	
	private static void makeFrequencyTable (InputStream in) throws IOException {
		m = new HashMap<Short, Integer>();
		for (short c = 0; (c = (short) in.read()) != -1;) {
			if (m.containsKey(c)) {
				m.put(c, (m.get(c) + 1));
			} else {
				m.put(c, 1);
			}
		}
	}

}
