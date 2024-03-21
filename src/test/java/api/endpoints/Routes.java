package api.endpoints;

/*
 * swagger URL= https://petstore.swagger.io/v2/swagger.json
 * 
 * Base URL= https://api.github.com/
	Header
	"Authorization: Bearer <YOUR-TOKEN>"

 * 
 * Create User URL=https://petstore.swagger.io/v2/user
 * Get User URL=https://api.github.com/repos/{OWNER}/{REPO}
 * Path parameters
	owner( string,Required)
	The account owner of the repository. The name is not case sensitive.
	repo( string, Required)
	The name of the repository without the .git extension. The name is not case sensitive
 * 
 * Update User URL=https://petstore.swagger.io/v2/user/{username}
 * Delete User URL=https://petstore.swagger.io/v2/user/{username}
 * 
 * 
 * 
 */

public class Routes {

public static String base_URL="https://api.github.com/";
	
	//GitHub Module


	//public static String getUser_URL= base_URL+"repos/DeepsriDubey/{REPO}";
	


public static String getAllRepo=base_URL+"user/repos";
public static String createRepo=base_URL+"user/repos";
public static String getSingleRepo=base_URL+"repos/{ownername}/{reponame}";
public static String updateRepo=base_URL+"repos/{ownername}/{reponame}";

public static String deleteRepo=base_URL+"repos/{ownername}/{reponame}";
	
}
