package com.restAssured.Pages;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;

public class Authorization {
    public static String bearerToken;
    public String baseUrl;

    public Authorization(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    public void bearerToken() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("user", "super_admin");
        requestBody.put("password", "Super1@trst");

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody.toJSONString())
                .post(baseUrl+ "/api/v1/users/login/admins");
        response.then().statusCode(200).log().all();

        bearerToken = response.jsonPath().getString("data.access_token");

    }

    /*

    public Object[][] readValues(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\lnv0179\\Downloads\\RequestBody.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        for (Row row : sheet ) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue + "\t");
            }
            System.out.println();
        }


    }

     */

}
