package Hotels.Hotels;



public class Hotel {

	private String city;
	private String description;
	private String name;
	private int rating;

	public Hotel() {
		
	}


	public Hotel(String city, String description, String name, int rating) {
		super();
		this.city = city;
		this.description = description;
		this.name = name;
		this.rating = rating;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
