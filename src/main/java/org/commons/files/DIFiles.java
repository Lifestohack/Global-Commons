package org.commons.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DIFiles {

	public static List<String> getEveryLinesFromFile(String path) {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public static List<String> getEveryLinesFromFile(String path, Charset encoding) {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(path), encoding);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public static String getTextFromFile(String path) {
		String content = null;
		try {
			content = readFile(path, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}

	public static String getTextFromFile(String path, Charset encoding) {
		String content = null;
		if (encoding == null) {
			content = getTextFromFile(path);
		} else {
			try {
				content = readFile(path, encoding);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	public static boolean isValidFile(String path) {
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			return true;
		} else {
			return false;
		}
	}

	public static void saveasTextFile(String text, String path) throws FileNotFoundException {
		try (PrintStream out = new PrintStream(new FileOutputStream(path))) {
			out.print(text);
		}
	}

	public static void saveasTextFile(String text, String path, String encoding)
			throws FileNotFoundException, UnsupportedEncodingException {
		try (PrintStream out = new PrintStream(path, encoding)) {
			out.print(text);
		}

	}

	private static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
