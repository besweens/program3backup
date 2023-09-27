package salerecordpack;
import java.util.ArrayList;
import java.util.LinkedList;

/*
CLASS SaleData handles file data to create new SalesRecord objects.
Methods here allow users to find average sale prices, the highest commission,
how many cars of a certain make are sold, and how many people with more money than
sense are selling their cars after 2 years or less.

@author: Ben Sweeney
@version: 20230911
 */
import java.util.Scanner;

public class SaleData{

	/**
	 * stoData
	 * Receive the file input and the array to find and store the SalesRecord field values
	 * @param storageArray SalesRecord array to be filled with SalesRecord objects
	 * @param input Input stream
	 * @param count Number of records found
	 * @return storageArray Return the array full of beautiful new SalesRecord objects
	 * 
	 * TODO: can probably just use the existing array length instead of passing the count parameter
	 */

	public static SalesRecord[] stoData(SalesRecord[] storageArray, Scanner input, int count) {

		for (int i=0; i<count; i++) {
			storageArray[i] = new SalesRecord(input.next(),input.next(),input.next(),
					input.next(),input.next(),input.nextInt(),input.nextDouble(),input.nextDouble(),input.nextDouble());
		}
		return storageArray;
	}


	/**loadList:
	 *  
	 * @param input
	 * @param carMake
	 * @return
	 */
	public static CarMakeLinkedList<SalesRecord> loadList (Scanner input, String carMake) {
		CarMakeLinkedList<SalesRecord> list = new CarMakeLinkedList<>();
		SalesRecord tempRecordIn;
		while(input.hasNext()) {
			tempRecordIn = new SalesRecord(input.next(),input.next(),input.next(),
					input.next(),input.next(),input.nextInt(),input.nextDouble(),input.nextDouble(),input.nextDouble());
			if(tempRecordIn.carMake().contains(carMake)) {
				list.add(tempRecordIn);
			}
		}
		return list;
	}
}


