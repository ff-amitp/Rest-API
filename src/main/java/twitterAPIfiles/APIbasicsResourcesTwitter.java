package twitterAPIfiles;

public class APIbasicsResourcesTwitter {

		public static String getTweet()
    		{
        		String resourceURL = "/home_timeline.json";
        		return resourceURL;
    		}	
	
		
		public static String postTweet()
    		{
        		String resourceURL = "/update.json";
        		return resourceURL;
    		}

		
		public static String deleteTweet()
			{
				String resourceURL = "/destroy/240854986559455234.json";
				return resourceURL;
			}

}
