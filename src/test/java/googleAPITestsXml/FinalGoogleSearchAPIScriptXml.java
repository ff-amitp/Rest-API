package googleAPITestsXml;

import googleAPIfiles.*;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;




public class FinalGoogleSearchAPIScriptXml {

	
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
	    	   		
	    	   		Response rawXMLGetResponse = given().
					param("location",prop.getProperty("location")).
					param("radius", prop.getProperty("radius")).
					param("key",prop.getProperty("googleKEY")).
					when().
					get(APIbasicsResources.getGooglePlacesDataXML()).
					then().
					assertThat().statusCode(200).and().contentType(ContentType.XML).and().extract().response();
	    	   		
	    	   		XmlPath finalXMLGetResponse = APIbasicsReusableMethods.rawToXML(rawXMLGetResponse);
	    	   		Assert.assertEquals(finalXMLGetResponse.get("PlaceSearchResponse.status"), "OK");
	    	   		
	    	   		Assert.assertEquals(finalXMLGetResponse.get("PlaceSearchResponse.result[0].name"), "Sydney");
	    	   		Assert.assertEquals(finalXMLGetResponse.get("PlaceSearchResponse.result[0].type[2]"), "political");
	    	   		Assert.assertEquals(finalXMLGetResponse.get("PlaceSearchResponse.result[0].place_id"), "ChIJP3Sa8ziYEmsRUKgyFmh9AQM");
	    	   		
	    	   		Assert.assertEquals(finalXMLGetResponse.get("PlaceSearchResponse.result[1].name"), "The Star");
	    	   		Assert.assertEquals(finalXMLGetResponse.get("PlaceSearchResponse.result[1].type[2]"), "establishment");
	    	   		Assert.assertEquals(finalXMLGetResponse.get("PlaceSearchResponse.result[1].place_id"), "ChIJq6qq6jauEmsR46KYci7M5Jc");
				
	       		}
	
	
	
	       
	         // ***************************************************************************//         
	        // *** POST Request : Add a Google Place Search record using Post request *** //
	       // ***************************************************************************//  
	       @Test
	       public void postGooglePlaceDetails() throws IOException
	       		{
	    	   		//Task-1 : Adding a Google Place
	    	   		RestAssured.baseURI = prop.getProperty("googleHOST");
	    	   		
	    	   		Response rawXMLGetResponse = given().
					queryParam("key",prop.getProperty("googleKEY")).
					body(APIbasicsPayLoad.postGoogleDataFirstPlaceXML()).
					when().
				    post(APIbasicsResources.postGooglePlacesDataXML()).
				    then().
				    assertThat().statusCode(200).and().contentType(ContentType.XML).and().extract().response();
				      
	    	   		XmlPath finalXMLGetResponse = APIbasicsReusableMethods.rawToXML(rawXMLGetResponse);
	    	   		
	    	   		Assert.assertEquals(finalXMLGetResponse.get("PlaceAddResponse.status"), "OK");
	    	   		
	       		}	
		             
		
		             
		                    
		             
	         // **************************************************************************//         
	        //  POST and Delete Request : Add a Google Place Search and Delete the same  //
	       // **************************************************************************//  
			@Test
		    public void postAndDeleteGooglePlaceDetails() throws IOException
				 {
		            //Task-1 : Adding a Google Place
		     		RestAssured.baseURI = prop.getProperty("googleHOST");
		     			      
		            Response post = given().
					queryParam("key",prop.getProperty("googleKEY")).
					body(APIbasicsPayLoad.postGoogleDataSecondPlaceXML()).
					when().
					post(APIbasicsResources.postGooglePlacesDataXML()).
					then().
					assertThat().statusCode(200).and().contentType(ContentType.XML).and().extract().response();  
						      
		    
		            //Task-2 : Grab the Place id from the response
		            XmlPath postResponse = APIbasicsReusableMethods.rawToXML(post);
		            String PlaceId = postResponse.get("root.place_id").toString();   // Investigate and learn this more
		        
		            
		            // Task-3 : Place a Place id extracted to a Text file
		            File newTextFile = new File("D:/thetextfile.txt");
		            FileWriter fw = new FileWriter(newTextFile);
	                fw.write(PlaceId + "\n");
	                fw.close();

	                
	                // Task-4 : Replace the Place Id fetched from Response in XML File
		            String deleteGoogleDataXMLString = APIbasicsPayLoad.deleteGoogleDataXML().toString();    
		            String bodyInputDeletePlace = deleteGoogleDataXMLString.replace("placeID", PlaceId);
		            System.out.println(bodyInputDeletePlace);
		            
		            
		            // Task-5 : Delete the Google Place by giving Input of above created place
		            Response rawDelete = given().
		            queryParam("key",prop.getProperty("googleKEY")).
		            body(bodyInputDeletePlace). 
		            when().
		            post(APIbasicsResources.deleteGooglePlacesDataXML()).
		            then().
		            assertThat().statusCode(200).and().contentType(ContentType.XML).and().extract().response();
		            String deleteResponseString = rawDelete.asString();
		            System.out.println(deleteResponseString);
		            
		            
		            // Task-6 : Grab the details from Delete Response and validate.
		            XmlPath deleteResponse = APIbasicsReusableMethods.rawToXML(rawDelete);
		            Assert.assertEquals(deleteResponse.get("PlaceDeleteResponse.status"), "OK");
		            
				 }
}
