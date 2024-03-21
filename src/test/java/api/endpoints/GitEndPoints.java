package api.endpoints;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.*;
import api.payloads.GitPojo;

public class GitEndPoints {
	
	/*Get User URL=https://api.github.com/repos/{OWNER}/{REPO}
		 * Path parameters
			owner( string,Required)
			The account owner of the repository. The name is not case sensitive.
			repo( string, Required)
			The name of the repository without the .git extension. The name is not case sensitive*/
	
	public static Response getAllRepo()
	{	
		
		Response response=
				given()
				.auth().oauth2("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")
				.when()
				.get(Routes.getAllRepo);
		
		return response;
				
	}
	
	public static Response createRepo(GitPojo payload)
	{	
		
		Response response=
				given()
				.contentType(ContentType.JSON)
				.auth().oauth2("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")
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(Routes.createRepo);
		
		return response;
				
	}
	
	public static Response updateRepo(String ownerName,String repoName, GitPojo payload)
	{
		Response response=
				given()
				.auth().oauth2("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")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("ownername", ownerName)
				.pathParam("reponame", repoName)
				.body(payload)
				.when()
				.patch(Routes.updateRepo);
		
		return response;
				
	}
	
	public static Response getSingleRepo(String ownerName,String repoName)
	{
		Response response=
				given()
				.auth().oauth2("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")
				.pathParam("ownername", ownerName)
				.pathParam("reponame", repoName)
				.when()
				.get(Routes.getSingleRepo);
		
		return response;
				
	}
	
	public static Response deleteRepo(String ownerName,String repoName)
	{
		Response response=
				given()
				.auth().oauth2("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")
				.pathParam("ownername", ownerName)
				.pathParam("reponame", repoName)
				.when()
				.delete(Routes.deleteRepo);
		
		return response;
				
	}
	
	
	

}
