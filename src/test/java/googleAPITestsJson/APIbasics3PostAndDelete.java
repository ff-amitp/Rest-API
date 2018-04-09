package googleAPITestsJson;

import googleAPIfiles.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APIbasics3PostAndDelete {

	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\apandhe\\workspace\\RestApiAutomation\\src\\googleAPIfiles\\env.properties");
		prop.load(fis);
	}
	
	// Add and Delete a Place
		@Test
		public void postAndDeleteTest()
		{
			
			//Task-1
			RestAssured.baseURI = prop.getProperty("googleHOST");
			
			/*String b = "{" +
					  "\"location\": {" +
					    "\"lat\": -33.8669710," +
					    "\"lng\": 151.1958750" +
					  "}," +
					  "\"accuracy\": 50," +
					  "\"name\": \"Google Shoes!\"," +
					  "\"phone_number\": \"(02) 9374 4000\"," +
					  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\"," +
					  "\"types\": [\"shoe_store\"]," +
					  "\"website\": \"http://www.google.com.au/\"," +
					 "\"language\": \"en-AU\"" +
					"}";*/
			
			Response res = given().
			       queryParam("key",prop.getProperty("googleKEY")).
			       body(APIbasicsPayLoad.postGoogleDataFirstPlaceJson()).
			       
		when().post(APIbasicsResources.postGooglePlacesDataJson()).
		then().
		assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK")).extract().response();  // equalTo : Hamcrest file / package need to be imported
			       
		System.out.println(res.asString());	
			       
		//Task-2 : Grab the Place id from the response
		JsonPath jp = new JsonPath(res.asString());
		System.out.println(jp.get("place_id").toString());
		
		
		// Task-3 " Delete the Place
		given().
		queryParam("key","AIzaSyDPiig3Sz7wEUIpVIi1elaOE5avaWhguEM").
		body("{"+
  "\"place_id\": \""+jp.get("place_id").toString()+ "\"" +
"}"). 
		when().
		post("https://maps.googleapis.com/maps/api/place/delete/json").
		then().
		assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));
		}
	}

