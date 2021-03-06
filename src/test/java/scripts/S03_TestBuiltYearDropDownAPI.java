package scripts;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.notNullValue;
import java.util.Map;
import genericLib.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

class S03_TestBuiltYearDropDownAPI extends BaseClass {
	
	@Test(dataProvider = "dp", dataProviderClass = dataProvider.Dp.class)
	public void BuiltDateDropDown_BuiltDateWithPositiveData(Map<String, String> mdata) throws Exception {

		// Assigning value to variables
		int statusCode = Integer.parseInt(mdata.get("statusCode"));
		
		// Logging info to Extent Report
		logInfo("TestCase #" + mdata.get("TC_ID"));
		logInfo("Path = " + mdata.get("path"));
		logInfo("ManufacturerID = " + mdata.get("manufacturerId"));
		logInfo("Main Type = " + mdata.get("mainType"));
		logInfo("Locale = " + mdata.get("locale"));

		// API Request
		ValidatableResponse response = RestAssured
			.given().basePath(mdata.get("path"))
				.queryParam("manufacturer", mdata.get("manufacturerId"))
				.queryParam("main-type", mdata.get("mainType"))
				.queryParam("locale", mdata.get("locale"))
				.queryParam("wa_key", mdata.get("wa_key"))
				.when().get().then();

		//Logging the Response
		logInfo("Response is - " + response.extract().asString());

		//Assert Status Code
		response.statusCode(statusCode);		
		logPass("Status Code Verified Successfully");

		//Assert Content Type is JSON
		response.contentType(ContentType.JSON);
		logPass("Content Type Verified Successfully");

		//Assert wkda is not null
		response.body("wkda", notNullValue());
		logPass("WKDA (NotNullValue) Verified Successfully");
		
		//Assert list of Years for given ManufacturerID and Main Type
		response.body("wkda", hasToString(mdata.get("wkdaList")));
		logPass("List of Years for given ManufacturerID and Main Type Tested Successfully");

	}

	@Test(dataProvider = "dp", dataProviderClass = dataProvider.Dp.class)
	public void BuiltDateDropDown_BuiltDateWithNegativeData(Map<String, String> mdata) throws Exception {

	// Assigning value to variables
	int statusCode = Integer.parseInt(mdata.get("statusCode"));
			
	// Logging info to Extent Report
	logInfo("TestCase #" + mdata.get("TC_ID"));
	logInfo("Path = " + mdata.get("path"));
	logInfo("ManufacturerID = " + mdata.get("manufacturerId"));
	logInfo("Main Type = " + mdata.get("mainType"));
	logInfo("Locale = " + mdata.get("locale"));

	// API Request
	ValidatableResponse response = RestAssured
		.given().basePath(mdata.get("path"))
			.queryParam("manufacturer", mdata.get("manufacturerId"))
			.queryParam("main-type", mdata.get("mainType"))
			.queryParam("locale", mdata.get("locale"))
			.queryParam("wa_key", mdata.get("wa_key"))
			.when().get().then();

		//Logging the Response
		logInfo("Response is - " + response.extract().asString());

		//Assert Status Code
		response.statusCode(statusCode);		
		logPass("Status Code Verified Successfully");

	}

}