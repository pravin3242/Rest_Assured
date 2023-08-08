package com.restAssured.test;

import com.beust.ah.A;
import com.restAssured.Pages.ApiMethods;
import com.restAssured.Pages.Authorization;
import com.restAssured.util.TestConfigreader;
import org.testng.annotations.Test;

import java.io.IOException;

public class ApiResponse  {

    String baseUrl = TestConfigreader.getBaseUrl();


    @Test(priority = 1)
    public void authorization(){
        Authorization authorization = new Authorization(baseUrl);
        authorization.bearerToken();
    }

    @Test(priority = 2)
    public void post() {
        Authorization authorization = new Authorization(baseUrl);
        authorization.bearerToken();
        for(int i=0;i<2;i++){
            ApiMethods api = new ApiMethods(baseUrl);
            api.post();
        }
    }
  /*  @Test(priority = 3)
    public void get(){
        ApiMethods api = new ApiMethods(baseUrl);
        api.get();
    }
@Test (priority = 4)
    public void put(){
        ApiMethods api = new ApiMethods(baseUrl);
        api.put();
        api.get();
    }

   */

    @Test
    public void apiResponse() {
        ApiMethods methodClass = new ApiMethods(baseUrl);
        methodClass.bearerToken();
        methodClass.post();
        methodClass.get();
        methodClass.put();
        methodClass.get();
        methodClass.delete();
    }

}
