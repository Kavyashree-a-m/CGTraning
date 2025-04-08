package com.magicbricks.locatorstore;

import org.openqa.selenium.By;

public class HomePageLocator {

	public static final By buybutton = By.id("tabBUY");
	public static final By mainBuybutton = By.xpath("//a[@id='buyheading']");
	public static final By finddefaultlocation = By.className("mb-search__tag-t");
	public static final By removedefalutlocation = By.className("mb-search__tag-close");
	public static final By enterlocationfield = By.id("keyword");
	public static final By searchbutton = By.className("mb-search__btn");
	public static final By loginPopUp = By.className("onmodal__cross");
	public static final By defaultproperty = By.id("buy_proertyTypeDefault");
	public static final By flat = By.cssSelector("input[title='Flat'] + label");
	public static final By budget = By.cssSelector("#rent_budget_lbl");
	public static final By myfrofile = By.cssSelector("a[href='#']");
	public static final By logOut = By.linkText("Sign Out");
	public static final By minVal = By.id("budgetMin");
	public static final By maxVal = By.id("budgetMax");
	public static final By clickProp = By.linkText("PropWorth");
	public static final By clickPremium = By.linkText("Premium Homes");
	public static final By getsearchvalue = By.name("homeSearchTxt");

}
