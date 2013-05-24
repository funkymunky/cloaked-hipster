package net.hello.world.webutils.page;

/**
 * Created with IntelliJ IDEA.
 * User: Ayesha
 * Date: 10/04/13
 * Time: 6:25 PM
 * To change this template use File | Settings | File Templates.
 */
/*
 * Copyright (c) 2003 - 2009 MoneySwitch Limited.
 * 125 York St, Sydney NSW 2000.
 * All rights reserved.
 */
//package net.moneyswitch.web.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;


public abstract class Page {

    private FluentWait<WebDriver> wait;

    protected final WebDriver driver;
    private final String expectedBodyId;

    protected Page(WebDriver driver, String expectedBodyId) {
        this.driver = driver;
        this.expectedBodyId = expectedBodyId;
        PageFactory.initElements(driver, this);
    }

    protected Page(WebDriver driver, String expectedBodyId, String expectedTitle) {
        this(driver, expectedBodyId);
    }

    private String getTitle() {
        return getDriver().getTitle();
    }

    public WebDriver getDriver() {
        return driver;
    }

}











