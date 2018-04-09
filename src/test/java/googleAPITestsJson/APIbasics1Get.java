package googleAPITestsJson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;


//Get a Place
public class APIbasics1Get {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
*/			
	@Test
	public void getTest(){
		// BASE URL
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		given().
				param("location","-33.8670522,151.1957362").
				param("radius", "500").
				param("key","AIzaSyDPiig3Sz7wEUIpVIi1elaOE5avaWhguEM").
	    when().
	           get("maps/api/place/nearbysearch/json").
	    then().
	          assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	          //body("results[0].geometry.location.lat", equalTo("-33.8688197"));
	          body("results[0].name", equalTo("Sydney")).and().body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"));
	}
				

}
