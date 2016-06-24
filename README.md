# Google reCAPTCHA 2 Component for Vaadin

Provides a simple Google reCAPTCHA v2 component for Vaadin. See https://developers.google.com/recaptcha/ for more information.


## Usage

1. Generate your reCAPTCHA keys on http://www.google.com/recaptcha/intro/index.html
2. Include the reCAPTCHA JavaScript in your UI:
    ```java
    @JavaScript("https://www.google.com/recaptcha/api.js")
    public class CustomUI extends UI
    {
       ...
    }
    ```
3. Add the ```Recaptcha```-component to your UI:
    ```java
    String siteKey = "...";
    String secretKey = "...";
    Recaptcha captcha = new Recaptcha(siteKey, secretKey);
    layout.addComponent(captcha);
    ```
4. Validate the user input:
    ```java
    if (!captcha.isValid())
    {
        ...
    }
    ```

## License

Apache License © [Anton Johansson](https://github.com/anton-johansson)
