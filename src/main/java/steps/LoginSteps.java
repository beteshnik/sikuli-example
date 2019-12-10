package steps;

import common.Application;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import flow.LoginFlow;
import io.qameta.allure.Step;
import pages.MainPage;
import parsing.Parser;

import java.io.IOException;

public class LoginSteps {


    //авторизация с системными данными
    @Step("User logs in by major")
    @When("User logs in by major")
    public void login() {
        LoginFlow.openLoginPage();
        String login = Application.getProperty("adminLogin");
        String password = Application.getProperty("adminPassword");
        LoginFlow.login(login, password);
    }


}
