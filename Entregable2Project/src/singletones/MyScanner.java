package singletones;

import java.util.Scanner;

public class MyScanner {
	
	private static Scanner scan = new Scanner(System.in);
	
	private MyScanner() {
		
	}
	
	public static Scanner getScan() {
		return scan;
	}
	
	public static void cerrarScan() {
		scan.close();
	}
	
}
