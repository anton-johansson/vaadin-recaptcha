/**
 * Copyright (c) Anton Johansson <antoon.johansson@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.antonjohansson.vaadin.recaptcha;

import com.antonjohansson.vaadin.recaptcha.shared.RecaptchaState;
import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
 * A Google reCAPTCHA v2 component.
 */
@JavaScript({"https://www.google.com/recaptcha/api.js", "recaptcha-connector.js"})
public class Recaptcha extends AbstractJavaScriptComponent
{
    private static final String DEFAULT_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    private String secretKey = "";
    private String response = "";
    private String verifyURL = DEFAULT_VERIFY_URL;

    /**
     * Constructs a new {@link Recaptcha}.
     */
    public Recaptcha()
    {
        addFunction("setResponse", arguments ->
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
