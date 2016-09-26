package HelperClasses;

public enum StatusValues {
	FAILED_API_ACCESS_ATTEMPT("Failed Api access attempt"),
	FAILED_TO_RETRIEVE_DATA("Failed to retrieve data"),
	DATA_RECEIVED("Data successfully received"),
	FAILED_ATTEMPT_TO_RETRIEVE_DATA("Failed to retrive data"),
	NO_DATA_RECEIVED_FROM_API("Failed, No data received from api");
	
	
	 StatusValues(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
