package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import org.testng.Assert;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("Routes");//load Routes.properties
		return routes;
		
	}

	public static Response createUser(User payLoad)
	{
		String postURL=getURL().getString("post_url");
		
		Response response=given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(payLoad)
		
		.when()
		.post(postURL);
		return response;
	}
	public static Response getUser(String userName)
	{
		String getURL=getURL().getString("get_url");
		Response response=given()
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		
		.when()
		.get(getURL);
		return response;
	}
	public static Response updateUser(String userName,User payLoad)
	{
		String putURL=getURL().getString("update_url");
		Response response=given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.pathParam("username", userName)
		.body(payLoad)
		
		.when()
		.put(putURL);
		return response;
		
	}
	public static Response deleteUser(String userName)
	{
		String deleteURL=getURL().getString("delete_url");
		Response response=given()
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		
		
		.when()
		.delete(deleteURL);
		return response;
	}
}
