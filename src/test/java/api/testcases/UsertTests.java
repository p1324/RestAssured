package api.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
 
import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UsertTests {

	Faker faker;
	User userPayload;
	public static Logger logger;

	@BeforeClass
	public void generateTestData() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		logger = LogManager.getLogger("RestFramework");
	}

	@Test(priority = 1)
	public void testCreateUser() {
		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("Createuser executed");
	}

	@Test(priority = 2)
	public void testGetUserData() {
		Response response = UserEndpoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("Get User executed");
	}

	@Test(priority = 3)
	public void testUpdateUserData() {
		userPayload.setFirstName(faker.name().firstName());
		Response response = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("update user executed");
		// get data to verify update operation

		Response responseAfterUpdate = UserEndpoints.getUser(this.userPayload.getUsername());
		System.out.println("After Updating User ");
		responseAfterUpdate.then().log().all();
	}

	@Test(priority = 4)
	public void testDeleteUserData() {

		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("delete user executed");

	}

}
