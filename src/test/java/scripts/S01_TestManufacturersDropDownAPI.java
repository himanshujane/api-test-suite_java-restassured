package scripts;

import org.testng.annotations.Test;
import java.util.Map;
import genericLib.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.*;

class S01_TestManufacturersDropDownAPI extends BaseClass {

	@Test(dataProvider = "dp", dataProviderClass = dataProvider.Dp.class)
	public void CarMakeDropDown_ManufacturersWithPositiveData(Map<String, String> mdata) throws Exception {

		// Logging info to Extent Report
		logInfo("TestCase #" + mdata.get("TC_ID"));
		logInfo("Path = " + mdata.get("path"));
		logInfo("Locale = " + mdata.get("locale"));

		// Assigning value to variables
		int statusCode = Integer.parseInt(mdata.get("statusCode"));

		// API Request
		ValidatableResponse response = RestAssured
				.given()
				.basePath(mdata.get("path"))
				.queryParam("locale", mdata.get("locale"))
				.queryParam("wa_key", mdata.get("wa_key"))
				.when().get().then();

		// Logging the Response
		logInfo("Response is - " + response.extract().asString());

		// Assert Status Code
		response.statusCode(statusCode);
		logPass("Status Code Verified Successfully");

		// Assert Content Type is JSON
		response.contentType(ContentType.JSON);
		logPass("Content Type Verified Successfully");

		// Assert wkda is not null
		response.body("wkda", notNullValue());
		logPass("WKDA (NotNullValue) Verified Successfully");

		// Assert wkda Key and values
		response.body(mdata.get("wkdaKey"), equalTo(mdata.get("wkdaValue")));
		logPass("WKDA for given Key & value Tested Successfully");

	}

	@Test(dataProvider = "dp", dataProviderClass = dataProvider.Dp.class)
	public void CarMakeDropDown_ManufacturersWithNegativeData(Map<String, String> mdata) throws Exception {

		// Logging info to Extent Report
		logInfo("TestCase #" + mdata.get("TC_ID"));
		logInfo("Path = " + mdata.get("path"));
		logInfo("Locale = " + mdata.get("locale"));

		// Assigning value to variables
		int statusCode = Integer.parseInt(mdata.get("statusCode"));

		// API Request
		ValidatableResponse response = RestAssured
				.given()
				.basePath(mdata.get("path"))
				.queryParam("locale", mdata.get("locale"))
				.queryParam("wa_key", mdata.get("wa_key"))
				.when().get().then();

		// Logging the Response
		logInfo("Response is - " + response.extract().asString());

		// Assert Status Code
		response.statusCode(statusCode);
		logPass("Status Code Verified Successfully");

	}
}