package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class testing {
	static data d;
	static IO g;

	public static void main(String[] args) {
		g = new IO();
	}

	static String getInput() {
		String s = "";
		try {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			s = bufferRead.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
}
