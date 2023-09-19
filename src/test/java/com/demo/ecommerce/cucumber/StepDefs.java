package com.demo.ecommerce.cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs extends SpringIntegrationTest {
    @When("^dado la fecha y hora \"([^\"]*)\", código de producto \"([^\"]*)\" y la cadena del grupo \"([^\"]*)\"$")
    public void testCase1(String date, String productId, String brandId) {
        executeGet(date, productId, brandId);
    }

    @Then("^el código HTTP de respuesta es \"([^\"]*)\"$")
    public void responseCode(String httpStatus) {
        int statusCode = Integer.parseInt(httpStatus);
        lastResponse.expectStatus().isEqualTo(statusCode);
    }
}
