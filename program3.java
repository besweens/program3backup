/*
	COS 285 - Assignment 1

	Program1:
	Process .CSV records of car sales for data storage, recall, and analysis

	@author Ben Sweeney
	@version 20230920
 */

import java.io.*;
import java.util.*;
import java.util.Scanner;
import  salerecordpack.*;

public class program3 {
	public static void main(String[] args) throws IOException {


		ArrayList<String> argArray = new ArrayList<>();

		String argMake = "";
		String argModel = "";
		String fileName = "";

		for (String s: args) {
			argArray.add(s);	
		}

		if(!(argArray.get(0).isEmpty()))
			fileName = argArray.get(0);
		if(argArray.size() >= 2 &&!(argArray.get(1).isEmpty()))
			argMake = argArray.get(1);
		if(argArray.size() == 3 && !(argArray.get(2).isEmpty()))
			argModel = argArray.get(2);


		FileInputStream fileInStream = new FileInputStream(fileName); // File input stream
		Scanner readRecord = new Scanner(fileInStream);

		long start;
		long stop;
		long timeElapsed;
		
		CarMakeLinkedList<SalesRecord> salesLink = new CarMakeLinkedList<>();

		start = System.currentTimeMillis();  
		readRecord.nextLine(); //skip the header line
		readRecord.useDelimiter("[,\r\n]+");	        
		salesLink = SaleData.loadList(readRecord, argMake);
		stop = System.currentTimeMillis();
		timeElapsed = stop - start;
		System.out.println(timeElapsed + "ms to read the records with car make " + argMake);
		System.out.println(salesLink.size() + " records detected for car make " + argMake);
		salesLink.printRange(0, 6);
		salesLink.printModelLinks("Altima");
		String test1 = "2022-08-01";
		String test2 = "2023-03-15";
		System.out.println(test1.compareTo(test2));
	//	System.out.println(salesLink.get(0).saleDate().compareTo(salesLink.get(3).saleDate()));
		
//		CarMakeLinkedList<SalesRecord> testList = new CarMakeLinkedList<>();
//		SalesRecord testRecord = new SalesRecord();
//		SalesRecord testRecord2 = new SalesRecord();
//		SalesRecord testRecord3 = new SalesRecord();
//
//		testRecord.setSaleDate("8/1/2022");
//		testRecord2.setSaleDate("7/2/2022");
//		testRecord3.setSaleDate("7/1/2022");
//
//		testList.add(testRecord);
//		testList.add(testRecord2);
//		testList.add(testRecord3);
//		testList.printRange(0, 2);
//		
//		CarMakeLinkedList<SalesRecord> testList2 = new CarMakeLinkedList<>();
//		testList2 = SaleData.loadList(readRecord, argMake);
//		testList2.printRange(0, 2);
		
//        Scanner sc = new Scanner(System.in);
//        String userModel = "";
//        System.out.print("Enter the car model: ");
       // userModel = sc.nextLine();
        //System.out.println("You said: " + userModel);

	}

//	public static return CarMakeLinkedList<SalesRecord>loadList(Scanner )
//}
}
