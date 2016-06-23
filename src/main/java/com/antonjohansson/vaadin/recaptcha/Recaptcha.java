package com.antonjohansson.vaadin.recaptcha;

import com.antonjohansson.vaadin.recaptcha.shared.RecaptchaState;
import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
 * A Google reCAPTCHA v2 component.
 */
@JavaScript({"recaptcha-connector.js", "https://www.google.com/recaptcha/api.js"})
public class Recaptcha extends AbstractJavaScriptComponent
{
    private static final String DEFAULT_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    private String secretKey;
    private String response = "";
    private String verifyURL = DEFAULT_VERIFY_URL;

    /**
     * Constructs a new {@link Recaptcha}.
     */
    public Recaptcha()
    {
        addFunction("setRecaptchaResponse", arguments ->
        {
            response = arguments.asString();
        });
    }

    public void setSiteKey(String siteKey)
    {
        getState().siteKey = siteKey;
    }

    public void setSecretKey(String secretKey)
    {
        this.secretKey = secretKey;
    }

    public void setVerifyURL(String verifyURL)
    {
        this.verifyURL = verifyURL;
    }

    @Override
    protected RecaptchaState getState()
    {
        return (RecaptchaState) super.getState();
    }

    /**
     * Gets whether or not this request is verified.
     *
     * @return Returns {@code true} if this request is verified.
     */
    public boolean isVerified()
    {
        RecaptchaVerifier verifier = new RecaptchaVerifier(secretKey, response, verifyURL);
        return verifier.isVerified();
    }
}
