package utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

public class ResponceSpec {
    public static final ResponseSpecification OK = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static final ResponseSpecification CREATE = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();

    public static final ResponseSpecification NOT_FOUND = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .expectBody(Matchers.is("{}"))
            .build();
}