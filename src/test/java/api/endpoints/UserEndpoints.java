package api.endpoints;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

	public static Response createUser(User payLoad)
	{
		Response response=given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(payLoad)
		
		.when()
		.post(Routes.post_url);
		return response;
	}
	public static Response getUser(String userName)
	{
		Response response=given()
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		
		.when()
		.get(Routes.get_url);
		return response;
	}
	public static Response updateUser(String userName,User payLoad)
	{
		Response response=given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.body(payLoad)
		
		.when()
		.put(Routes.put_url);
		return response;
		
	}
	public static Response deleteUser(String userName)
	{
		Response response=given()
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		
		
		.when()
		.delete(Routes.del_url);
		return response;
	}
}
