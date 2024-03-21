package api.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.GitEndPoints;
import api.payloads.GitPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GitCRUDTests {
	
	 Faker faker;
	 GitPojo gitPayload;
	// private static String Name=null;
	 public static String description = "This is your first API repo!";
	 public static String homepage="https://github.com";
	 public static String owner="DeepsriDubey";
	
	public Logger logger;
	
	@BeforeClass
	public  void setup()
	{
		faker=new Faker();
		gitPayload= new GitPojo();
		
		gitPayload.setId(faker.idNumber().hashCode());
		gitPayload.setName(faker.name().firstName());
		gitPayload.setDescription(faker.letterify(description));
		gitPayload.setHomepage(homepage);
		gitPayload.setPrivate(faker.bool().equals(true));
		gitPayload.setOwner(owner);
		
		
		
		//logs
		 logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=0)
	public void test_GetAllRepo()
	{
		logger.info("**********Displaying All Repository Information**********");
		Response response=GitEndPoints.getAllRepo();
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("Verified status code is 200");
		AssertJUnit.assertEquals(response.contentType(),ContentType.JSON + "; charset=utf-8");
		logger.info("Verified content type is application/json; charset=utf-8");
		System.out.println("Total repositories:" + response.body().jsonPath().get("size()"));
		System.out.println("Name of public repositories:" + response.body().jsonPath().getList("findAll{it->it.visibility=='public'}.full_name"));
		logger.info("**********All Repository  Information is displayed**********");
	}
	
	@Test(priority=1)
	public void test_createRepo()
	{
		logger.info("**********Creating new repository**********");
		
		Response response=GitEndPoints.createRepo(gitPayload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 201);
		logger.info("**********Status code is verified as 201**********");
		
		AssertJUnit.assertEquals(response.getBody().jsonPath().get("name"), gitPayload.getName());
		logger.info("**********Name is verified **********");
		
		AssertJUnit.assertEquals(response.getBody().jsonPath().get("owner.login"), gitPayload.getOwner());
		logger.info("**********Login is verified **********");
		
		AssertJUnit.assertEquals(response.getBody().jsonPath().get("owner.type"), "User");
		logger.info("**********Type is verified **********");
		
		logger.info("**********Repository is created**********");
		
		
		//System.out.println("Name of first repo:" + response.body().jsonPath().get("name[0]"));
	
		 //Name =response.body().jsonPath().get("name[0]");
		
	
	}
	
	
	@Test(priority=2)
	public void test_singleRepo()
	{
		logger.info("**********Displaying Repo Information**********");
		Response response=GitEndPoints.getSingleRepo(this.gitPayload.getOwner(),this.gitPayload.getName());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("**********Repo Information is displayed**********");
	}
	
	
	@Test(priority=3)
	public void test_createRepowithExistingName()
	{
		logger.info("**********Creating new repository with existing name**********");
		
		Response response=GitEndPoints.createRepo(gitPayload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 422);
		logger.info("**********Status code is verified as 422**********");
		
		AssertJUnit.assertEquals(response.body().jsonPath().get("errors[0].message"), "name already exists on this account");
		logger.info("**********Error msg is verified as : name already exists on this account**********");
		
		logger.info("**********Repository is not created as name already exists**********");
	
	}
	
	@Test(dependsOnMethods="test_createRepo",priority=7)
	public void test_UpdateRepoName()
	{
		//Update data using payload
		logger.info("**********Updating Repo Name **********");
		//gitPayload.setName(faker.name().firstName());
		gitPayload.setName(faker.name().firstName()+"1");
		gitPayload.setDescription("Test");
		gitPayload.setPrivate(false);



//Response response= GitEndPoints.updateRepo(this.gitPayload.getOwner(),this.gitPayload.getName(),gitPayload);
		Response response= GitEndPoints.updateRepo(this.gitPayload.getOwner(),"Hello-World",gitPayload);	
response.then().log().all();
AssertJUnit.assertEquals(response.getStatusCode(), 200);

logger.info("**********Repo Information is updated**********");
	

//checking repo after update
	//Response responseAfterUpdate=GitEndPoints.getSingleRepo(this.gitPayload.getOwner(),this.gitPayload.getName());
	//AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	
	//logger.info("**********Repo Information is verified after update**********");
	
	}
	
	
	@Test(priority=4)
	public void test_DeleteRepoByName()
	{
		logger.info("**********Deleting User Information**********");
	Response response=GitEndPoints.deleteRepo(this.gitPayload.getOwner(),this.gitPayload.getName());
	AssertJUnit.assertEquals(response.getStatusCode(), 204);
	logger.info("*********Status code is verified as 204");
	
	AssertJUnit.assertEquals(response.getBody().equals(false),false);
	logger.info("*********Body is verified as null");
	
	logger.info("**********User Information is deleted**********");	
	}
		
	@Test(priority=5)
	public void test_DeleteRepoByNonexistingName()
	{
		logger.info("**********Deleting User Information**********");
	Response response=GitEndPoints.deleteRepo(this.gitPayload.getOwner(),this.gitPayload.getName());
	AssertJUnit.assertEquals(response.getStatusCode(), 404);
	logger.info("*********Status code is verified as 404");
	
	AssertJUnit.assertEquals(response.getBody().jsonPath().get("message"), "Not Found");
	logger.info("*********Message is verified as :Not found");
	
	logger.info("**********User Information is deleted**********");	
	}
	
	@Test(priority=6)
	public void test_singleRepowithNonExistingRepoName()
	{
		logger.info("**********Displaying Repo Information**********");
		Response response=GitEndPoints.getSingleRepo(this.gitPayload.getOwner(),this.gitPayload.getName());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 404);
		logger.info("**********Repo Information is displayed**********");
	}
	
	

}
