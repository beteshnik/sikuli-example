package steps;

import common.Application;
import cucumber.api.java.en.When;
import flow.MapFlow;
import io.qameta.allure.Step;

import java.io.IOException;
import org.sikuli.script.FindFailed;

public class MapSteps {


    @Step("User checks full map visibility")
    @When("User checks full map visibility")
    public void checkFullMapVisibility() {
        MapFlow.checkFullMapVisibility();
    }

    @Step("User checks map fragment <fragment> visibility")
    @When("User checks map fragment (.*) visibility")
    public void checkFragmentMapVisibility(String fragmentPath) throws IOException {
        MapFlow.checkFragmentMapVisibility(fragmentPath);
    }

    @Step("User clicks by map fragment <mapFragment>")
    @When("User clicks by map fragment (.*)")
    public void clickMapFragment(String fragmentPath) throws FindFailed, IOException {
        MapFlow.clickMapFragment(fragmentPath);
    }
}
