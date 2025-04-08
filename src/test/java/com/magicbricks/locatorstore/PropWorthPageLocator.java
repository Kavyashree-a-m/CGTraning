package com.magicbricks.locatorstore;

import org.openqa.selenium.By;

public class PropWorthPageLocator {

	public static final By clickestimatebtn = By.xpath("(//div[@class='propwrthw__estmip']//span)[2]");
	public static final By locality = By.xpath("//input[@placeholder='Enter Project/Locality']");
	public static final By localitydropdown = By.xpath("(//div[@class='auto-suggest__drop-down__item']//span)[1]");
	public static final By subLocality = By.cssSelector(".search-filter__super-area subloc");
	public static final By subLocalityDropdowm = By.xpath("//div[@class='search-filter__super-area subloc']/select");
	public static final By propertytype = By
			.xpath("// label[@class='filter__common__component__item__label propertytype propertytype_10002']");
	public static final By squareFt = By.cssSelector(".search-filter__super-area__input");
	public static final By floors = By.xpath("//div[@class='search-filter__super-area__sqft floor-field']/select");
	public static final By totalfloor = By.className("search-filter__totalfloor");
	public static final By interior = By
			.xpath("// label[@class='filter__common__component__item__label interiors interiors_19900']");
	public static final By spendmoney = By.className("search-filter__interioramnt__input");
	public static final By addDetails = By.xpath("//div[text()='Additional Details']");
	public static final By scrolladd = By
			.xpath("//div[@class='filter__common__component__title' and text()='Overlooking']");
	public static final By getEstimate = By.xpath("//div[text()='Get Estimate']");
	public static final By propworthestimation = By.xpath("//div[@class='estowner__priceblk__whitesec']/span[1]");
	public static final By errormsg = By.cssSelector(".search-filter__error-red");
	public static final By sublocalityoption = By.className("subloc__txt");
	public static final By flooroption = By.cssSelector(".search-filter__super-area__sqft.floor-field > div");

}
