package Hotels.Hotels;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

public class ApiActions {
	/**
	 * @TurnOnTheApi mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"

	 */
	private Faker faker;
	
	public ApiActions() {
		faker = new Faker();
	}
	public void createHotel(String city, String description, String name, int rating) {

		Hotel hotel = new Hotel(city, description, name, rating);
		given().body(hotel).when().post("").then().statusCode(201);
	}
	public void printAllHotels() {
		
		Response response = given().when().get("").then().extract().response();
		ArrayList<String> hotelsName = new ArrayList<String>(response.path("content.name"));
		ArrayList<String> hotelsCity = new ArrayList<String>(response.path("content.city"));
		ArrayList<String> hotelsDescription = new ArrayList<String>(response.path("content.description"));
		ArrayList<Integer> hotelsRating = new ArrayList<Integer>(response.path("content.rating"));
		System.out.println();
		for (int i = 0; i < hotelsName.size(); i++) {
			System.out.println("Hotel name: " + hotelsName.get(i));
			System.out.println("Hotel description: " + hotelsDescription.get(i));
			System.out.println("Hotel location: " + hotelsCity.get(i));
			System.out.println("Hotel rating: " + hotelsRating.get(i));
			System.out.println();
		}
	}
	
	public void deleteHotelByRating(int rating) {
		
		Response response = given().when().get("").then().extract().response();
		ArrayList<Integer> hotelsID = new ArrayList<Integer>(
				response.path("content.findAll{it.rating == "+rating+"}.id"));
		for (Integer ID : hotelsID) {
			given().pathParam("hotelID", ID).when().delete("/{hotelID}").then()
					.statusCode(204);
		}
	}
	
	public void createRandomHotel() {
		
		try {
			createHotel(faker.address().city(), faker.lorem().paragraph(), faker.company().name(),
					faker.number().randomDigit());
		}catch (AssertionError e) {
			createHotel(faker.address().city(), faker.lorem().paragraph(), faker.company().name(),
					faker.number().randomDigit());
		}


	}
	public void changeHotelRating(String hotelName,int newRating) {
		
		Response response = given().when().get("").then().extract().response();
	    int ID = response.path("content.find { it.name == '"+hotelName+"' }.id");
	    System.out.println("+++++"+ID);
	    String description = response.path("content.find{it.id == "+ID+"}.description");
	    String city = response.path("content.find{it.id == "+ID+"}.city");
	    String body = "{\r\n\"id\": "+ID+",\r\n\"name\": \""+hotelName+"\",\r\n\"description\": \""+description+"\",\r\n\"city\": \""+city+"\",\r\n\"rating\": "+newRating+"\r\n}";
	    given().pathParam("hotelID", ID).body(body).when().put("/{hotelID}").then().statusCode(204);;
	}
	
	public void deleteAllHotels() {
		
		Response response = given().when().get("").then().extract().response();
		ArrayList<Integer> hotelsID = new ArrayList<Integer>(response.path("content.id"));
		for (Integer ID : hotelsID) {
			given().pathParam("hotelID", ID).when().delete("/{hotelID}").then()
					.statusCode(204);
		}
	}
}
