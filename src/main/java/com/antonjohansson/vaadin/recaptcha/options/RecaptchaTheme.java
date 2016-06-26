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
package com.antonjohansson.vaadin.recaptcha.options;

/**
 * Defines various themes of a reCAPTCHA.
 */
public enum RecaptchaTheme
{
    /** A darker theme of a reCAPTCHA. */
    DARK("dark"),

    /** A lighter theme of a reCAPTCHA. */
    LIGHT("light");

    private final String value;

    RecaptchaTheme(String value)
    {
        this.value = value;
    }

    /**
     * Gets the {@code theme} value used in the JavaScript API.
     *
     * @return Returns the {@code theme} value.
     */
    public String getValue()
    {
        return value;
    }
}
