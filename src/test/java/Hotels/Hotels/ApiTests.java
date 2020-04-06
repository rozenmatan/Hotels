package Hotels.Hotels;

import org.testng.annotations.Test;


public class ApiTests extends BastTest{



	@Test
	public void createHotel() {

		ApiActions apiActions = new ApiActions();
		apiActions.createHotel("Tel Aviv", "All included", "Sheraton", 4);

	}
	@Test(dependsOnMethods = "createHotel")
	public void changeHotelRating() {
		ApiActions apiActions = new ApiActions();
		apiActions.changeHotelRating("Sheraton",1);
	}

	@Test(dependsOnMethods = "deleteHotelByRating")
	public void printAllHotels() {
		
		ApiActions apiActions = new ApiActions();
		apiActions.printAllHotels();

	}

	@Test
	public void deleteHotelByRating() {
		
		ApiActions apiActions = new ApiActions();
		apiActions.deleteHotelByRating(5);
	}

	@Test(invocationCount = 94)
	public void createRandomHotel() {
		ApiActions apiActions = new ApiActions();
		apiActions.createRandomHotel();
	}




}
