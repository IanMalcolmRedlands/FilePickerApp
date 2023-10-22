package file_reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileAnalyzer {
	public static String calcStats(File file) {
		if (file==null) {
			return "File was not picked";
		}
		
		CustomLinkedList list = new CustomLinkedList();
		
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				if (scanner.hasNextDouble()) {
					list.add(scanner.nextDouble());
				}
				else {
					scanner.next();
				}
			}
			scanner.close();
			
			for (double num : list) {
				System.out.println(num);
			}
			
			if (list.isEmpty()) {
				return "No numbers found.";
			}
			
			double mean = calcMean(list);
			double standardDev = calcStandardDev(list, mean);
			
			return "Mean: "+mean+", Standard Deviation: "+standardDev;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static double calcMean(CustomLinkedList list) {
		double count = 0;
		double sum = 0;
		for (double num : list) {
			count += 1;
			sum+=num;
		}
		return sum/count;
	}
	
	private static double calcStandardDev(CustomLinkedList list, double mean) {
		double sum = 0;
		double count = 0;
		for (double num : list) {
			count += 1;
			double diff = num-mean;
			sum += diff*diff;
		}
		
		return Math.sqrt(sum/count);
	}
}
