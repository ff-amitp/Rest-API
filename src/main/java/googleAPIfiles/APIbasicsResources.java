package googleAPIfiles;

public class APIbasicsResources {

		public static String getGooglePlacesDataJson()
    		{
        		String resourceURL = "/maps/api/place/nearbysearch/json";
        		return resourceURL;
    		}	
	
		
		public static String postGooglePlacesDataJson()
    		{
        		String resourceURL = "/maps/api/place/add/json";
        		return resourceURL;
    		}

		
		public static String deleteGooglePlacesDataJson()
			{
				String resourceURL = "/maps/api/place/delete/json";
				return resourceURL;
			}

		
		public static String getGooglePlacesDataXML()
			{
    			String resourceURL = "/maps/api/place/nearbysearch/xml";
    			return resourceURL;
			}	

		
		public static String postGooglePlacesDataXML()
			{
    			String resourceURL = "/maps/api/place/add/xml";
    			return resourceURL;
			}

		
		public static String deleteGooglePlacesDataXML()
			{
				String resourceURL = "/maps/api/place/delete/xml";
				return resourceURL;
			}
}
