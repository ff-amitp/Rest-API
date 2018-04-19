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




public class FinalGoogleSearchAPIScript {

	
	       Properties prop = new Properties();
	
	       
	       @BeforeTest
	       	public void getPropertiesData() throws IOException
	       		{
	    	   		FileInputStream fis = new FileInputStream(System.getProperty ("user.dir") + "\\env.properties");
	    	   		prop.load(fis);
	       		}
		
	       	 // **************************************************************//         
	        // ***** GET Request : Get the details about Google Places ***** //
	       // **************************************************************//      
	       @Test
	       public void getGooglePlaceDetails()
	       		{
	    	   		RestAssured.baseURI = prop.getProperty("googleHOST"); // BASE URL
	    	   		given().
					param("location",prop.getProperty("location")).
					param("radius", prop.getProperty("radius")).
					param("key",prop.getProperty("googleKEY")).
					when().
					get(APIbasicsResources.getGooglePlacesDataJson()).
					then().
					assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
					body("results[0].name", equalTo("Sydney")).and().
					body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"));
	       		}
	
           
	
	       
	         // ***************************************************************************//         
	        // *** POST Request : Add a Google Place Search record using Post request *** //
	       // ***************************************************************************//   
	       @Test
	       public void postGooglePlaceDetails()
	       		{
	    	   		//Task-1 : Adding a Google Place
	    	   		RestAssured.baseURI = prop.getProperty("googleHOST");
	    	   		given().
					queryParam("key",prop.getProperty("googleKEY")).
					body(APIbasicsPayLoad.postGoogleDataFirstPlaceJson()).
					when().
				    post(APIbasicsResources.postGooglePlacesDataJson()).
				    then().
				    assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				    body("status",equalTo("OK"));  
	    	   	 
	       		}	
		             
		 
		                    
	         // **************************************************************************//         
	        // ***** POST Request : Add a Google Place Search and Delete the same ****** //
	       // **************************************************************************//  
			@Test
		    public void postAndDeleteGooglePlaceDetails()
		     	{
		     	   	//Task-1 : Adding a Google Place
		     		RestAssured.baseURI = prop.getProperty("googleHOST");
		     			      
		            Response rawPostResponse = 
					given().
					queryParam("key",prop.getProperty("googleKEY")).
					body(APIbasicsPayLoad.postGoogleDataSecondPlaceJson()).
					when().
					post(APIbasicsResources.postGooglePlacesDataJson()).
					then().
					assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
					body("status",equalTo("OK")).extract().response();  
						      
		    
		            //Task-2 : Grab the Place id from the response
		            JsonPath jsonPostResponse = APIbasicsReusableMethods.rawToJson(rawPostResponse);
		            String PlaceId = jsonPostResponse.get("place_id").toString();
		           
		            
		            // Task-3 : Delete the Place
		            given().
		            queryParam("key",prop.getProperty("googleKEY")).
		            body("{" + "\"place_id\": \"" + PlaceId + "\"" + "}"). 
		            when().
		            post(APIbasicsResources.deleteGooglePlacesDataJson()).
		            then().
		            assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		            body("status",equalTo("OK"));
		            
		           
	             
		     	}
			
}
