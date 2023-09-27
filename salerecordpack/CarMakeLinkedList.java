package salerecordpack;
/**CarMakeLinkedList
 * Custom linked list intended for SalesRecord data. This list sorts new sales inputs by date. An additional pointer links sales
 * of identical car models by date
 * 
 * @param <E>
 */
public class CarMakeLinkedList<E> extends SalesRecord {

	/** starting node of the linked list*/
	private Node<SalesRecord> head = null;
	/**keep track of the index of the last input node*/
	private int lastSaleIndex = 0;
	/**track linked list number of elements */
	private int size = 0;

	/**addFirst: Insert the new sales record at the head of the list and update associated pointers
	 * 
	 * @param newSale Sales record data to attach as a node to the list
	 */
	public void addFirst(SalesRecord newSale) {
		head = new Node<SalesRecord>(newSale, head, null);
		size++;
		
		Node<SalesRecord> nextModelNode = null;
		nextModelNode = modelNodeSearch(newSale.carModel(), -1, 1); //using -1 as the start index as a workaround for the how this method searches
		head.nextModelSale = nextModelNode; //set the newly input sales node nextModelSale pointer to the next newer sale of the same model, or null if none exists

	}

	//OLD ADDAFTER USING NODES INSTEAD OF INDEXES
	//	/**addAfter: Insert the new sales record after the specified node and update the references accordingly
	//	 * 
	//	 * @param newSale Sales record data to attach as a node to the list
	//	 * @param workingNode This node will be set to reference new node
	//	 * @param refModelUpdate If a later sale of the same model was found, we will update this pointer. Otherwise, it will pass as null
	//	 */
	//	private void addAfter( SalesRecord newSale, Node<SalesRecord> workingNode) {
	//		workingNode.nextSale = new Node<>(newSale, workingNode.nextSale, null);
	//		//workingNode.nextModelSale=refModelUpdate;
	//		size++;
	//	}
	//	
	/**addAfter: Insert the new sales record after the specified node and update the references accordingly
	 * 
	 * @param newSale Sales record data to attach as a node to the list
	 * @param workingNode This node will be set to reference new node
	 * @param refModelUpdate If a later sale of the same model was found, we will update this pointer. Otherwise, it will pass as null
	 */
	private void addAfter( SalesRecord newSale, int index) {
		this.getNode(index).nextSale = new Node<>(newSale, this.getNode(index).nextSale, null);
		//workingNode.nextModelSale=refModelUpdate;
		size++;
		//update nextModelSale pointers
		Node<SalesRecord> prevModelNode = null;
		Node<SalesRecord> nextModelNode = null;
		prevModelNode = modelNodeSearch(newSale.carModel(), index, -1);
		nextModelNode = modelNodeSearch(newSale.carModel(), index, 1);
		if(prevModelNode!=null)
		{prevModelNode.nextModelSale = this.getNode(index).nextSale;} //if it exists, set an older node of the same model to point to this newly-input sales node
		this.getNode(index).nextSale.nextModelSale = nextModelNode; //set the newly input sales node nextModelSale pointer to any newer sale of the same model, or null

	}

	/**modelNodeSearch
	 * Method to assist in updating the nextModelSale pointers in SalesRecords nodes.
	 * Search down to find the next older sale of the same model as the node added in method addAfter.
	 * Search up to find the next newer sale of the same model as the node added in method addAfter
	 * 
	 * @param model the model of the node added in method addAfter
	 * @param startSearch the index we are starting from
	 * @param upOrDown use -1 to search down n the linked list and 1 to search up in the linked list
	 * @return
	 */
	private Node<SalesRecord> modelNodeSearch(String model, int startSearch, int upOrDown) {
		int startSearchIndex = startSearch;
		if (upOrDown == -1) { //search down
			for(int i = startSearchIndex; i >= 0; i--) {
				if(model.equalsIgnoreCase(this.get(i).carModel())) {
					return this.getNode(i);
				}
			}
		} 
		else if (upOrDown == 1) { //search up
			for(int i = startSearchIndex+2; i < size; i++) { //new sale was added after startSearchIndex, so we don't care about that node or the node we just added
				if(model.equalsIgnoreCase(this.get(i).carModel())) {
					return this.getNode(i);
				}
			}
		}
		return null; //if no matching model was found as an older or a newer record, our nextModelSale will be null
	}

	/**getNode
	 * Using the specified index, run through the list until we hit that index number and then return that node as long as it isn't empty
	 * @param index Target list position
	 * @return node The SalesRecord node found at the target position
	 */
	private Node<SalesRecord> getNode(int index){
		Node<SalesRecord> node = head;
		for (int i= 0; i< index && node != null; i++) {
			node=node.nextSale;
		}
		return node;
	}

	/** Get the sales data at index
	@param index The position of the data to return
	@return The data at index
	@throws IndexOutOfBoundsException if index is out of range
	 */
	public SalesRecord get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<SalesRecord> node = getNode(index);
		return node.sale;
	}


	/**add
	 * The big method to take in a new SalesRecord and determine how to insert it into this linked list.
	 * When the list is empty, send the new sale node to addFirst.
	 * 
	 * When the list isn't empty, we are forcing a sort-by-date. Find out whether the new sale date is older or more recent than the last stored node.
	 * If the new date is older, we will search downward to find if any stored sales are older. If we find an older sales node, store the new sale after that one.
	 * If we don't find an older sales node, addFirst.
	 * 
	 * If the new date is more recent than the last stored sale node, search to the end of the list to see if any sales node date is more recent than
	 * the new sale date. If there is a more recent node date, add our new sale at the index position just before the more recent node date. If there
	 * isn't a more recent date, add the new sale to the end of the list.
	 * 
	 * @param inSale New sale to be stored in the list
	 */
	public void add(SalesRecord inSale) {
		System.out.println("lastSaleIndex start " + lastSaleIndex);

		//if list is empty, start the first node and the last input sale is index 0
		if (size == 0) { 
			addFirst(inSale);
			lastSaleIndex = 0;
		} 
		else  //if list is not empty, compare the new entry sale date to the last stored sale date
		{	
			//NEW ENTRY SALE DATE IS OLDER THAN THE LAST STORED ENTRY
			if(inSale.saleDate().compareTo(this.get(lastSaleIndex).saleDate()) < 0) { //if the new entry sale date is older than the last stored entry
				for(int i = lastSaleIndex; i >= 0; i--) { //search down until we find a node with a date as old or older than the new entry
					if((inSale.saleDate().compareTo(this.get(i).saleDate()) >= 0)) {
						lastSaleIndex = i; //we will set the last-stored index as this as-old or older sale node
						break;
					}
					lastSaleIndex = -1; //otherwise, we will mark that the new entry is the oldest
				}
			}
			else if(inSale.saleDate().compareTo(this.get(lastSaleIndex).saleDate()) > 0) { //if the new entry sale date is more recent than the last-stored node
				for(int i = lastSaleIndex; i < size; i++) {//search up until we find a newer sale
					if((inSale.saleDate().compareTo(this.get(i).saleDate()) <= 0)) {
						lastSaleIndex = i-1; //set the last-stored to the entry right before the newer sale
						break;
					}
					lastSaleIndex = size-1;//otherwise, this is the newest sale and we will add it ot the end
				}
			}
			if(lastSaleIndex == -1){
				System.out.println("ADDING " + inSale.saleDate() + " first");
				addFirst(inSale);
				lastSaleIndex=0;
			} 
			else
			{
				System.out.println("ADDING " + inSale.saleDate() + " after " + this.get(lastSaleIndex).saleDate());
				addAfter(inSale, lastSaleIndex);
			}			
		}
	}
	
	/**size
	 * Getter for the current size of this linkedlist
	 * 
	 * @return size - The size of this linked list at this time
	 */
	public int size() {
		return size;
	}

	/**printRange
	 * Printer to print a range of nodes from the specified start to the specified end. Used for testing.
	 * 
	 * @param fromIndex
	 * @param toIndex
	 */
	public void printRange(int fromIndex, int toIndex) {
		if (fromIndex < 0 || fromIndex > size-1 || fromIndex > toIndex)
			throw new IndexOutOfBoundsException("Your fromIndex is ILLEGAL");
		if (toIndex < 0 || toIndex > size-1 || toIndex < fromIndex)
			throw new IndexOutOfBoundsException("Your toIndex is ILLEGAL. FIX IT IF YOU WANT TO SURVIVE running this print method");
		System.out.println("");
		for (int i = fromIndex; i <= toIndex; i++) {
			System.out.println(this.get(i).toString());
		}
	}

	/**printModelLinks
	 * 
	 * Printer to test the validity of our nextModelSale references
	 * @param model String input for the model we want to find and print the subsequent nextModelSale nodes
	 */
	public void printModelLinks(String model) {
		for(int i = 0; i < size; i++) {
			if(model.equalsIgnoreCase(this.get(i).carModel())) {
				Node<SalesRecord> nodeTemp = this.getNode(i);
				Node<SalesRecord> nextLink = nodeTemp.nextModelSale;

				System.out.println("Node found at index " + i);
				if(indexOf(nodeTemp.nextModelSale)!=-1)
					System.out.println( this.get(i).toStringDateModelMake() + " points to " + this.get(indexOf(nodeTemp.nextModelSale)).toStringDateModelMake());
				else
					System.out.println( this.get(i).toStringDateModelMake() + " has nothing to point to");
			}
		}
		System.out.println("");
	}

	/**indexOf
	 * Find the first instance of the desired node and return the iterator value as the index value
	 * @param nodeFind Node we want to find in this list
	 * @return Return the iterator as the index or, if not found, return a -1 to indicate failure
	 * 
	 * TODO: change the return or method behavior to be less annoying to deal with when a node is not found
	 */
	private int indexOf(Node<SalesRecord> nodeFind) {
		Node<SalesRecord> node = head;
		int indexFound = -1;
		for (int i= 0; i< size && node != null; i++) { //as long as our list isn't empty, start the search
			if(node.equals(nodeFind))
				return i;
			node=node.nextSale;
		}
		return -1;
	}
	
	
	
	
	
	/** The building block for the SalesLinkedList 
	 * @author Sweeney
	 * @version 20230926*/
	
	private static class Node<SalesRecord>{
		/** sale data to be stored */
		private SalesRecord sale;
		/** reference for the next vehicle sold */
		private Node<SalesRecord> nextSale;
		/** reference for the next vehicle sold of the same model*/
		private Node<SalesRecord> nextModelSale;

		/**
		 * Constructor for a node without data to point to	
		 * @param item stored data
		 */
		private Node(SalesRecord newSale) {
			sale = newSale;
			nextSale = null;
			nextModelSale = null;
		}

		/**
		 * Constructor for a node without data to point to	
		 * @param item stored data
		 */
		private Node(SalesRecord newSale, Node<SalesRecord> nextSaleRef) {
			sale = newSale;
			nextSale = nextSaleRef;
			nextModelSale = null;
		}

		/**
		 * Constructor for a node with data to point to
		 * @param item stored data
		 * @param nextNode next piece of stored data to point to
		 */
		private Node(SalesRecord newSale, Node<SalesRecord> nextSaleRef, Node<SalesRecord> nextModelRef) {
			sale = newSale;
			nextSale = nextSaleRef;
			nextModelSale = nextModelRef;
		}

	}
}
