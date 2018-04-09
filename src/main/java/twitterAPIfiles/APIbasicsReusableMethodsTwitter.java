package twitterAPIfiles;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class APIbasicsReusableMethodsTwitter {
	
			public static XmlPath rawToXML(Response r)
				{
					String respon=r.asString();
					XmlPath x=new XmlPath(respon);
					return x;
				}
	
			
			public static JsonPath rawToJson(Response r)
				{ 
					String respon=r.asString();
					JsonPath x=new JsonPath(respon);
					return x;
				}
	
			
			public static String GenerateStringFromResource(String path) throws IOException
				{
					return new String(Files.readAllBytes(Paths.get(path)));
				}
	
	
	}
