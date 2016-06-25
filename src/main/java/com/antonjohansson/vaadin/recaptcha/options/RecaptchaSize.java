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
 * Defines various sizes of a reCAPTCHA.
 */
public enum RecaptchaSize
{
    /** The regular size of a reCAPTCHA. */
    NORMAL("normal"),

    /** A more compact size of a reCAPTCHA. */
    COMPACT("compact");

    private final String value;

    RecaptchaSize(String value)
    {
        this.value = value;
    }

    /**
     * Gets the size value used in the JavaScript API.
     *
     * @return Returns the size value.
     */
    public String getValue()
    {
        return value;
    }
}
