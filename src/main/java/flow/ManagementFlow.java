package flow;

import common.Browser;
import org.openqa.selenium.support.PageFactory;
import pages.ListPage;
import pages.FormPage;

import parsing.Parser;

import java.io.IOException;

public class ManagementFlow {


    private static ListPage listPage = PageFactory.initElements(
            Browser.getDriver(), ListPage.class
    );

    private static FormPage formPage = PageFactory.initElements(
            Browser.getDriver(), FormPage.class
    );


}