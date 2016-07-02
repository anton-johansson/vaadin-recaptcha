# Google reCAPTCHA 2 Component for Vaadin

[![Build Status](https://img.shields.io/travis/anton-johansson/vaadin-recaptcha/master.svg)](https://travis-ci.org/anton-johansson/vaadin-recaptcha)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.anton-johansson/vaadin-recaptcha/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.anton-johansson/vaadin-recaptcha)
[![Javadoc](https://javadoc-emblem.rhcloud.com/doc/com.anton-johansson/vaadin-recaptcha/badge.svg)](http://www.javadoc.io/doc/com.anton-johansson/vaadin-recaptcha)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg?maxAge=2592000)](https://raw.githubusercontent.com/anton-johansson/vaadin-recaptcha/master/LICENSE)

Provides a simple Google reCAPTCHA v2 component for Vaadin. See https://developers.google.com/recaptcha/ for more information.

## Usage

1. Generate your reCAPTCHA keys on http://www.google.com/recaptcha/intro/index.html

2. Include the artifact in your POM:
	```xml
	<dependency>
		<groupId>com.anton-johansson<groupId>
		<artifactId>vaadin-recaptcha</artifactId>
		<version>2.0.0</version>
	</dependency>
	```


3. Include the reCAPTCHA JavaScript in your UI:
	```java
	@JavaScript("https://www.google.com/recaptcha/api.js")
	public class CustomUI extends UI
	{
	    ...
	}
	```
    
4. Add the ```Recaptcha```-component to your UI:
	```java
	String siteKey = "...";
	String secretKey = "...";
	Recaptcha captcha = new Recaptcha(siteKey, secretKey);
	layout.addComponent(captcha);
	```
    
5. Optionally set extra properties on your component:
	```java
	captcha.setType(RecaptchaType.AUDIO);
	captcha.setTheme(RecaptchaTheme.DARK);
	captcha.setSize(RecaptchaSize.COMPACT);
	```
    
6. Validate the user input:
	```java
	if (!captcha.isVerified())
	{
	    ...
	}
    ```

## License

Apache License © [Anton Johansson](https://github.com/anton-johansson)
