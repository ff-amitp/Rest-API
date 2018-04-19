package twitterAPITestsJson;

import twitterAPIfiles.APIbasicsResourcesTwitter;
import twitterAPIfiles.APIbasicsReusableMethodsTwitter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import static io.restassured.RestAssured.*;


public class FinalTwitterAPIscript {

			Properties prop = new Properties();
			
			@BeforeTest
	       	public void getPropertiesData() throws IOException
	       		{
	    	   		FileInputStream fis = new FileInputStream(System.getProperty ("user.dir") + "\\env.properties");
	    	   		prop.load(fis);
	       		}
			
			// ScribeJava API and ScribeJava Core are the 2 more additional jars that need to Outh token cases
			
			
			 // **************************************************************//         
	        // ***** GET Request : Get the details about latest Tweet ****** //
	       // **************************************************************// 
	        @Test
			public void getTweet()
	       		 {
	    	   			RestAssured.baseURI = prop.getProperty("twitterHOST"); // BASE URL
	    	   		   
	    	   			// Task-1 : Fetching the latestTweet
	    	   			Response getTweetRawResponse = given().auth().oauth(prop.getProperty("twitterConsumerKey"), prop.getProperty("twitterConsumerSecret"), prop.getProperty("twitterAccessToken"), prop.getProperty("twitterAccessTokenSecret")).
	    	   			queryParam("count", "1").
	    	   			when().
	    	   			get(APIbasicsResourcesTwitter.getTweet()).
	    	   			then().
	    	   			assertThat().
	    	   			statusCode(200).and().contentType(ContentType.JSON).and().extract().response();
	    	   			
	    	   			JsonPath getTweetFinalResponse = APIbasicsReusableMethodsTwitter.rawToJson(getTweetRawResponse);
	    	   			String tweetText = getTweetFinalResponse.getString("text").toString();
	    	   			Assert.assertEquals(getTweetFinalResponse.getString("text"), tweetText);
	    	   			Assert.assertEquals(getTweetFinalResponse.getString("user.name"), "[OnlineAPI]");			
	    	   			
	       		}
	       
			
			
	        
	          // ***************************************************************************//         
	         // ********************** POST Request : Create a Tweet ********************* //
	        // ***************************************************************************//   
			/*@Test
			public void postTweet()
				 {
						RestAssured.baseURI = prop.getProperty("twitterHOST");
				
						//Task-1 : Adding a Tweet
						Response postTweetRawResponse = given().
						auth().oauth(prop.getProperty("twitterConsumerKey"), prop.getProperty("twitterConsumerSecret"), prop.getProperty("twitterAccessToken"), prop.getProperty("twitterAccessTokenSecret")).
	    	   			queryParam("status", "My Tweet through Automation").
	    	   			when().
	    	   			post(APIbasicsResourcesTwitter.postTweet()).
	    	   			then().
	    	   			assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();
				
						
						// Task-2 : Validating the response for Post Tweet
						JsonPath postTweetJsonResponse = APIbasicsReusableMethodsTwitter.rawToJson(postTweetRawResponse);
						Assert.assertEquals(postTweetJsonResponse.getString("text"), "My Tweet through Automation");
						Assert.assertEquals(postTweetJsonResponse.getString("user.name"), "OnlineAPI");
				 }*/


			
			
			  // **************************************************************************//         
	         // ********* POST Request : Add a Tweet and Delete the same **************** //
	        // **************************************************************************//  
			@Test
			public void postAndDeleteTweet()
				 {
						RestAssured.baseURI = prop.getProperty("twitterHOST");
				
						//Task-1 : Adding a Tweet
						Response postTweetRawResponse = given().
						auth().oauth(prop.getProperty("twitterConsumerKey"), prop.getProperty("twitterConsumerSecret"), prop.getProperty("twitterAccessToken"), prop.getProperty("twitterAccessTokenSecret")).
	    	   			queryParam("status", "My Tweet through Automation 12345").
	    	   			when().
	    	   			post(APIbasicsResourcesTwitter.postTweet()).
	    	   			then().
	    	   			assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();
				
				
						// Task-2 : Validating the response for Post Tweet
						JsonPath postTweetJsonResponse = APIbasicsReusableMethodsTwitter.rawToJson(postTweetRawResponse);
						Assert.assertEquals(postTweetJsonResponse.getString("text"), "My Tweet through Automation 12345");
						Assert.assertEquals(postTweetJsonResponse.getString("user.name"), "OnlineAPI");
	   			
	   			
						// Task-3 : Fetching the Tweet Id from Post response 
						String tweetId = postTweetJsonResponse.getString("id").toString();
	   			
	   			
						// Task-4 : Deleting the same Tweet which is created in Task-1 above
						Response deleteTweetRawResponse = given().
						auth().oauth(prop.getProperty("twitterConsumerKey"), prop.getProperty("twitterConsumerSecret"), prop.getProperty("twitterAccessToken"), prop.getProperty("twitterAccessTokenSecret")).
	    	   			when().
	    	   			post("/destroy/" + tweetId + ".json").
	    	   			then().
	    	   			assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();
	   			
						
						// Task-5 : Validating the response for Delete Tweet
						JsonPath deleteTweetJsonResponse = APIbasicsReusableMethodsTwitter.rawToJson(deleteTweetRawResponse);
						Assert.assertEquals(deleteTweetJsonResponse.getString("text"), "My Tweet through Automation 12345");
						Assert.assertEquals(deleteTweetJsonResponse.getString("user.name"), "OnlineAPI");
						Assert.assertEquals(deleteTweetJsonResponse.getString("id"), tweetId);
	   			
				 }
}
