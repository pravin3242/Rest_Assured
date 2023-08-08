package com.restAssured.Pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.Date;

public class ApiMethods extends  Authorization{
    public static int getUniqueData(){

        return (int) new Date().getTime();
    }

    String uniqueStr = "" + getUniqueData();
    public static String userid;

    public ApiMethods(String baseUrl) {
        super(baseUrl);
    }

    public void post(){

        JSONObject requestBody = new JSONObject();

        requestBody.put("firstName","NewUser");
        requestBody.put("lastName","Testing");
        requestBody.put("countryCode","+91");
        requestBody.put("phoneNumber",uniqueStr.substring(uniqueStr.length()-10));
        requestBody.put("email",uniqueStr + "user@gmail.com");
        requestBody.put("dob","2000/01/01");
        requestBody.put("timeZone","UTC");

        Response request = RestAssured.given()
                .header("Authorization", "Bearer" + bearerToken)
                .contentType("application/json")
                .body(requestBody.toJSONString())
                .post(baseUrl+"/api/v1/users");
        request.then().statusCode(201).log().all();

        userid = request.jsonPath().getString("data.id");
        Assert.assertNotNull(userid);

    }

    public void get() {

        Response request = RestAssured.given()
                .header("Authorization", "Bearer" + bearerToken)
                .contentType("application/json")
                // .get(baseUrl + "/api/v1/users/b8d6c872-0213-4683-9257-e56b02957890");
                .get(baseUrl + "/api/v1/users/" + userid);
        request.then().statusCode(200).log().all();

        String id =  request.jsonPath().getString("data.id");

        Assert.assertEquals(id,userid);

    }

    public void put() {

        JSONObject requestBody = new JSONObject();

        requestBody.put("firstName", "FirstName");
        requestBody.put("lastName", "LastName");
        requestBody.put("phoneNumber",uniqueStr.substring(uniqueStr.length()-10));
        requestBody.put("countryCode", "+91");
        requestBody.put("email",uniqueStr + "user@gmail.com");
        requestBody.put("dob", "1998/03/08");

        Response request = RestAssured.given()
                .header("Authorization", "Bearer" + bearerToken)
                .contentType("application/json")
                .body(requestBody.toJSONString())
                .put(baseUrl + "/api/v1/users/"+ userid);
        request.then().statusCode(200).log().all();
        String id =  request.jsonPath().getString("data.id");
        Assert.assertEquals(id,userid);

    }
    public void delete(){

        Response response = RestAssured.given()
                .header("Authorization","Bearer"+bearerToken)
                .contentType("application/json")
                .delete(baseUrl + "/api/v1/users/" + userid);
        response.then().statusCode(200).log().all();
    }

}
