package googleAPIfiles;

import java.io.IOException;


public class APIbasicsPayLoad {
	
		public static String postGoogleDataFirstPlaceJson()
			{
					String FirstPlaceBody = "{" +
												"\"location\": {" +
												"\"lat\": -33.8669710," +
												"\"lng\": 151.1958750" +
												"}," +
												"\"accuracy\": 50," +
												"\"name\": \"Google Shoes1\"," +
												"\"phone_number\": \"(02) 9374 4000\"," +
												"\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\"," +
												"\"types\": [\"shoe_store\"]," +
												"\"website\": \"http://www.google.com.au/\"," +
												"\"language\": \"en-AU\"" +
												"}";
		
					return FirstPlaceBody;
			}
	
		
		public static String postGoogleDataSecondPlaceJson()
			{
					String SecondPlaceBody = "{" +
												 "\"location\": {" +
												 "\"lat\": -33.8669710," +
												 "\"lng\": 151.1958750" +
												 "}," +
												 "\"accuracy\": 50," +
												 "\"name\": \"Google Shoes2\"," +
												 "\"phone_number\": \"(02) 9374 4000\"," +
												 "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\"," +
												 "\"types\": [\"shoe_store\"]," +
												 "\"website\": \"http://www.google.com.au/\"," +
												 "\"language\": \"en-AU\"" +
												 "}";
		
					return SecondPlaceBody;
			}

		
		public static String postGoogleDataFirstPlaceXML() throws IOException
			{
				return APIbasicsReusableMethods.GenerateStringFromResource("C:\\Users\\apandhe\\workspace\\RESTApiAutomationPOC\\Misc Files\\postGoogleDataFirstPlaceXML.xml");
			}

		
		public static String postGoogleDataSecondPlaceXML() throws IOException
			{
				return APIbasicsReusableMethods.GenerateStringFromResource("C:\\Users\\apandhe\\workspace\\RESTApiAutomationPOC\\Misc Files\\postGoogleDataSecondPlaceXML.xml");
			}
	

		public static String deleteGoogleDataXML() throws IOException
			{
				return APIbasicsReusableMethods.GenerateStringFromResource("C:\\Users\\apandhe\\workspace\\RESTApiAutomationPOC\\Misc Files\\deleteGoogleDataXML.xml");
			}
	}
