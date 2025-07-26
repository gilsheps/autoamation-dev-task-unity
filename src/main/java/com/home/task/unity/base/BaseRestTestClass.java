/*
 * *
 *  * Created by Gil on 6/24/18 3:41 PM
 *
 */

package com.home.task.unity.base;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public abstract class BaseRestTestClass extends BaseTestClass {
    protected static Cookies cookie;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = prop.getProperty("baseUri");
        cookie = given().formParams("email", prop.getProperty("adminEmail"), "password", prop.getProperty("adminPassword"))
                .post("admin/login").then().extract().response().getDetailedCookies();
    }
}
