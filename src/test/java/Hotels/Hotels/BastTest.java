package Hotels.Hotels;

import static io.restassured.RestAssured.given;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.restassured.http.ContentType;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class BastTest {

	@BeforeSuite
	public void checkApiAlive() {
		RestAssured.baseURI = "http://localhost:8090/example/v1/hotels";
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.requestSpecification = new RequestSpecBuilder()
		        .setContentType(ContentType.JSON)
		        .setAccept(ContentType.JSON)
		        .build();
		given().when().get("").then().statusCode(200);
		
	}

	@AfterSuite
	public void deleteAllHotels() {
		ApiActions apiActions = new ApiActions();
		apiActions.deleteAllHotels();
	}
}
