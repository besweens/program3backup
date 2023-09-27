package salerecordpack;
/*
CLASS SalesRecord handles vehicle sales data for input, storage, and recall.

@author: Ben Sweeney
@version: 20230911
 */

import java.util.Comparator;

public class SalesRecord{

	private String saleDate;
	private int carYear;
	private double salePrice;
	private double commRate;
	private double commEarned;
	private String seller;
	private String custName;
	private String carMake;
	private String carModel;
	private int saleYear;

	/**
	 * SalesRecord
	 * Constructor for the SalesRecord object when all data fields are passed
	 * @param saleDate
	 * @param seller
	 * @param custName
	 * @param carMake
	 * @param carModel
	 * @param carYear
	 * @param salePrice
	 * @param commRate
	 * @param commEarned

	 * */
	public SalesRecord(String saleDate, String seller, String custName, String carMake, String carModel, int carYear,
			double salePrice, double commRate, double commEarned) {
		this.saleDate = saleDate;
		this.carYear = carYear;
		this.salePrice = salePrice;
		this.commRate = commRate;
		this.commEarned = commEarned;
		this.seller = seller;
		this.custName = custName;
		this.carMake = carMake;
		this.carModel = carModel;

		String dateCode[] = this.saleDate.split("-");
		this.saleYear = Integer.parseInt(dateCode[0]);
	}
	
	/*
* Constructor for the SalesRecord object when no data fields are passed
	 */
	public SalesRecord() {
		this.saleDate = "not found";
		this.carYear = 0;
		this.salePrice = 0.0;
		this.commRate = 0.0;
		this.commEarned = 0.0;
		this.seller = "not found";
		this.custName = "not found";
		this.carMake = "not found";
		this.carModel = "not found";
		this.saleYear = 1900;
	}

	
	/**
	 *GETTER for saleDate
	 * @return saleDate string
	 */
	public String saleDate() {
		return saleDate;
	}

	/**
	 *SETTER for saleDate
	 * @return
	 */
	public SalesRecord setSaleDate(String saleDate) {
		this.saleDate = saleDate;
		return this;
	}

	/**
	 *GETTER for carYear
	 * @return carYear value
	 */
	public int carYear() {
		return carYear;
	}

	/**
	 *SETTER for carYear
	 * @return
	 */
	public SalesRecord setCarYear(int carYear) {
		this.carYear = carYear;
		return this;
	}

	/**
	 *GETTER for salePrice
	 * @return salePrice value
	 */
	public double salePrice() {
		return salePrice;
	}

	/**
	 *SETTER for salePrice
	 * @return
	 */
	public SalesRecord setSalePrice(double salePrice) {
		this.salePrice = salePrice;
		return this;
	}
	/**
	 *GETTER for commRate
	 * @return commRate value
	 */
	public double commRate() {
		return commRate;
	}

	/**
	 *SETTER for commRate
	 * @return
	 */
	public SalesRecord setCommRate(double commRate) {
		this.commRate = commRate;
		return this;
	}
	/**
	 *GETTER for commEarned
	 * @return commEarned value
	 */
	public double commEarned() {
		return commEarned;
	}

	/**
	 *SETTER for commEarned
	 * @return
	 */
	public SalesRecord setCommEarned(double commEarned) {
		this.commEarned = commEarned;
		return this;
	}
	/**
	 *GETTER for seller
	 * @return seller value
	 */
	public String seller() {
		return seller;
	}

	/**
	 *SETTER for seller
	 * @return
	 */
	public SalesRecord setSeller(String seller) {
		this.seller = seller;
		return this;
	}
	/**
	 *GETTER for custName
	 * @return custName value
	 */
	public String custName() {
		return custName;
	}

	/**
	 *SETTER for custName
	 * @return
	 */
	public SalesRecord setCustName(String custName) {
		this.custName = custName;
		return this;
	}
	/**
	 *GETTER for carMake
	 * @return carMake value
	 */
	public String carMake() {
		return carMake;
	}

	/**
	 *SETTER for carMake
	 * @return
	 */
	public SalesRecord setCarMake(String carMake) {
		this.carMake = carMake;
		return this;
	}
	/**
	 *GETTER for carModel
	 * @return carModel value
	 */
	public String carModel() {
		return carModel;
	}
	
	/**
	 *SETTER for carModel
	 * @return
	 */
	public SalesRecord setCarModel(String carModel) {
		this.carModel = carModel;
		return this;
	}
	
	/**
	 *GETTER for saleYear
	 * @return saleYear value
	 */
	public int getSaleYear() {
		return saleYear;
	}


	@Override
	/*
	 * Custom (generated) output for SalesRecord objects
	 * @return A string of the labeled fields for SalesRecord objects
	 */
	public String toString() {
		return "| Date='" + saleDate + '\'' +
				"|Car Year=" + carYear +
				"|Price= $" + salePrice +
				"|Comm. Rate=" + commRate +
				"|Comm. Earned=" + commEarned +
				"|Seller Name='" + seller + '\'' +
				"|Cust. Name='" + custName + '\'' +
				"|Make='" + carMake + '\'' +
				"|Model='" + carModel + '\'' +
				'|';
	}
	
	/*
	 * Custom (generated) output for SalesRecord objects
	 * @return A string of the labeled fields for SalesRecord objects
	 */
	public String toStringDateModelMake() {
		return "| Date='" + saleDate + '\'' +
				"|Make='" + carMake + '\'' +
				"|Model='" + carModel + '\'' +
				'|';
	}

}

