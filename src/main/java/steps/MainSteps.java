package steps;

import common.Application;
import common.Assertion;
import common.ScreenShot;
import cucumber.api.Result;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.runtime.ScenarioImpl;
import flow.LoginFlow;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.json.simple.parser.ParseException;
import pages.MainPage;
import testrail.APIException;
import testrail.APIIntegration;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class MainSteps {
    public static Integer scenarioId;
    public static String scenarioTag;
    public static String scenarioStatus;
    public static Long start;
    public static String duration;

    @Before()
    public void beforeSteps() {
        Assertion.error = "";
        Assertion.message = "";
        start = System.currentTimeMillis();
    }

    @After()
    public void afterSteps(Scenario scenario) throws IOException, APIException {
//        LoginFlow.analyzeLogs();
        Long range = System.currentTimeMillis() - start;
        System.out.println("RANGE " + range);
        duration = String.format("%d" + "m " + "%d" + "s",
                TimeUnit.MILLISECONDS.toMinutes(range),
                TimeUnit.MILLISECONDS.toSeconds(range) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(range)));
        System.out.println("DURATION" + duration);

        try {
            if (!scenario.getSourceTagNames().isEmpty()) {
                String comment = String.valueOf(getErrorComment(scenario));
                scenarioId = Integer.parseInt(String.valueOf(scenario.getSourceTagNames()).replaceAll("[^0-9]", ""));
                scenarioTag = String.valueOf(scenario.getSourceTagNames());
                scenarioStatus = String.valueOf(scenario.getStatus());
                APIIntegration.updateCaseResultsForSpecificTestrun(
                        "BASE_URL: " + System.getenv("bamboo_BASE_URL") + "\n" +
                                "RUN_ID: " + System.getenv("bamboo_TESTRAIL_RUN_ID") + "\n"
                                + "Error message: " + comment);
            }
        } catch (NumberFormatException | ParseException e) {
            Application.log("Scenario ID is missing");
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        Application.log("This will run after the Scenario Сценарий завершился(лог)");
        Application.log(scenario.getName());
        if (scenario.isFailed()) {
            ScreenShot.makeScreenShot("ScreenShot - 'Test Failed'");
            //Browser.getDriver().quit();
        }
        MainPage.switchToDefaultFrame();
        //LoginFlow.logout();
    }


    public static Throwable getErrorComment(Scenario scenario) {

        Field field = FieldUtils.getField(((ScenarioImpl) scenario).getClass(), "stepResults", true);
        field.setAccessible(true);
        try {
            ArrayList<Result> results = (ArrayList<Result>) field.get(scenario);
            System.out.println("Стенд " + System.getenv("bamboo_BASE_URL"));
            return results.stream().map(Result::getError).filter(Objects::nonNull).findFirst().orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to parse results!");
        }
    }
}