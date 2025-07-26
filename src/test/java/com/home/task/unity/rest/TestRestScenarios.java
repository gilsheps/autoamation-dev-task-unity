package com.home.task.unity.rest;

import com.home.task.unity.base.BaseRestTestClass;
import com.home.task.unity.utils.Enums;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestRestScenarios extends BaseRestTestClass {
    static int publisherId, postId;

    @Test
    public void testCreatePublisher() {
        TestRestScenarios.publisherId = given().cookies(cookie).formParams("name", prop.getProperty("name"),
                        "email", prop.getProperty("email"))
                .when().post(prop.getProperty("newPublisher"))
                .then().statusCode(200)
                .body("redirectUrl", equalTo("/admin/resources/Publisher"))
                .body("notice.message", equalTo("successfullyCreated")).body("notice.type", equalTo("success"))
                .body("record.params.id", isA(Number.class)).body("record.params.email", is(prop.getProperty("email")))
                .body("record.params.name", is(prop.getProperty("name"))).body("record.title", equalTo(prop.getProperty("email")))
                .statusCode(200)
                .extract().path("record.id");
    }

    @Test(dependsOnMethods = {"testCreatePublisher"})
    public void testCreatePost() {
        TestRestScenarios.postId = given().cookies(cookie)
                .formParams("title", prop.getProperty("name"),
                        "published", "true", "publisher", publisherId,
                        "status", Enums.PostStatus.ACTIVE.toString(), "content", null, "someJson.0", null)
                .when().post(prop.getProperty("newPost"))
                .then().statusCode(200).extract().path("record.params.id");

    }

    @Test(dependsOnMethods = {"testCreatePost"})
    public void testEditPost() {
        Map<String, Object> params = given().cookies(cookie).pathParam("id", postId)
                .get("admin/api/resources/Post/records/{id}/edit").then().extract().path("record.params");
        params.put("status", Enums.PostStatus.REMOVED.toString());
        printToLog(params.toString());
        given()
                .cookies(cookie).formParams(params)
                .pathParam("id", postId)
                .post("admin/api/resources/Post/records/{id}/edit")
                .then().statusCode(200);
    }

    //    @Test
    @Test(dependsOnMethods = {"testEditPost"})
    public void testPostChange() {
        given().cookies(cookie).pathParam("id", postId).get("admin/api/resources/Post/records/{id}/show").then()
                .body("record.params.id", equalTo(postId))
                .body("record.params.status", equalTo(Enums.PostStatus.REMOVED.toString()))
                .body("record.populated.publisher.params.id", equalTo(publisherId))
                .body("record.populated.publisher.params.email", equalTo(prop.getProperty("email")))
                .body("record.populated.publisher.params.name", equalTo(prop.getProperty("name")))
                .statusCode(200);
    }

}
