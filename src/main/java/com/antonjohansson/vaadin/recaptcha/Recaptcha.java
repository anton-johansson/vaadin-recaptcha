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

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.antonjohansson.vaadin.recaptcha.listeners.CheckPassedListener;
import com.antonjohansson.vaadin.recaptcha.options.RecaptchaSize;
import com.antonjohansson.vaadin.recaptcha.shared.RecaptchaState;
import com.vaadin.annotations.JavaScript;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
 * A Google reCAPTCHA v2 component.
 */
@JavaScript("recaptcha-connector.js")
public class Recaptcha extends AbstractJavaScriptComponent
{
    private static final String DEFAULT_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    private final Collection<CheckPassedListener> checkPassedListeners = new ArrayList<>();
    private String secretKey;
    private String verifyURL;
    private Optional<Boolean> verified = Optional.empty();

    /**
     * Constructs a new, empty {@link Recaptcha} with the default verify URL.
     */
    public Recaptcha()
    {
        this("", "");
    }

    /**
     * Constructs a new {@link Recaptcha} with the given site key and secret key
     * using the default verify URL.
     *
     * @param siteKey The public key to use.
     * @param secretKey The secret key to use.
     */
    public Recaptcha(String siteKey, String secretKey)
    {
        this(siteKey, secretKey, DEFAULT_VERIFY_URL);
    }

    /**
     * Constructs a new {@link Recaptcha} with the given site key, secret key
     * and verify URL.
     *
     * @param siteKey The public key to use.
     * @param secretKey The secret key to use.
     * @param verifyURL The URL to use when verifying the reCAPTCHA.
     */
    public Recaptcha(String siteKey, String secretKey, String verifyURL)
    {
        setSiteKey(siteKey);
        setSecretKey(secretKey);
        setVerifyURL(verifyURL);
        addFunction("setResponse", arguments ->
        {
            checkResponse(arguments.asString());
        });
    }

    /**
     * Sets the public key.
     *
     * @param siteKey The public key to use.
     */
    public void setSiteKey(String siteKey)
    {
        getState().siteKey = siteKey;
    }

    /**
     * Sets the secret key.
     *
     * @param secretKey The secret key to use.
     */
    public void setSecretKey(String secretKey)
    {
        this.secretKey = secretKey;
    }

    /**
     * Sets the URL to use when verifying the reCAPTCHA.
     *
     * @param verifyURL The URL to use when verifying the reCAPTCHA.
     */
    public void setVerifyURL(String verifyURL)
    {
        this.verifyURL = verifyURL;
    }

    /**
     * Sets the size of the reCAPTCHA.
     * <p>
     * If not excplicitly set, it will fall back to <a href=
     * "https://developers.google.com/recaptcha/docs/display#render_param">
     * Googles default</a>.
     * </p>
     *
     * @param size The size to use.
     */
    public void setSize(RecaptchaSize size)
    {
        requireNonNull(size, "size cannot be null");
        getState().size = size.getValue();
    }

    /**
     * Adds a check passed listener.
     *
     * @param listener The listener to add.
     */
    public void addCheckPassedListener(CheckPassedListener listener)
    {
        this.checkPassedListeners.add(listener);
    }

    /**
     * Removes a check passed listener.
     *
     * @param listener The listener to remove.
     */
    public void removeCheckPassedListener(CheckPassedListener listener)
    {
        this.checkPassedListeners.remove(listener);
    }

    @Override
    protected RecaptchaState getState()
    {
        return (RecaptchaState) super.getState();
    }

    private void checkResponse(String response)
    {
        if (verified.isPresent())
        {
            throw new IllegalStateException("The response is already checked");
        }

        String remoteAddress = VaadinService.getCurrentRequest().getRemoteAddr();
        RecaptchaVerifier verifier = new RecaptchaVerifier(secretKey, response, verifyURL, remoteAddress);
        boolean verified = verifier.isVerified();
        this.verified = Optional.of(verified);

        if (verified)
        {
            checkPassedListeners.forEach(CheckPassedListener::onCheckPassed);
        }
    }

    /**
     * Resets the reCAPTCHA.
     */
    public void reset()
    {
        verified = Optional.empty();
        callFunction("reset");
    }

    /**
     * Gets whether or not this request is verified.
     *
     * @return Returns {@code true} if this request is verified.
     */
    public boolean isVerified()
    {
        return verified.orElse(false);
    }
}
