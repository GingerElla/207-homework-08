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
		
		if (toEncode) {
			GrinEncoder.encode(inFile, outFile);
		} else {
			GrinDecoder.decode(inFile, outFile);
		}
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

}
