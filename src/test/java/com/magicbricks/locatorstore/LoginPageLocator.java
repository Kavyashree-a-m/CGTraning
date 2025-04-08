package com.magicbricks.locatorstore;

import org.openqa.selenium.By;

public class LoginPageLocator {

	public static final By buyer = By.className("filter__component__item__label");
	public static final By mobile = By.id("emailOrMobile");
	public static final By capctha = By.id("captchaCodeSignIn");
	public static final By captchaerror = By.id("commentCaptchaErrSignIn");
	public static final By nextbutton = By.id("btnStep1");
	public static final By continuebtn = By.cssSelector("button[onclick='verifyOtp()']");
	public static final By errormsg = By.id("step1Error");
	public static final By name = By.id("regName");
	public static final By email = By.id("regEmailid");
	public static final By phone = By.id("regMobile");
	public static final By checkBox = By.name("chk-TNC");
	public static final By capcthaProp = By.id("captchaCodeRegistration");
	public static final By signUp = By.cssSelector("button[onclick='validateRegistration();']");
	
}
