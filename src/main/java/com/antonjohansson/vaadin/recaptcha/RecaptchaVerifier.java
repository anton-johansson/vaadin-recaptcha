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

import static java.net.URLEncoder.encode;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Verifies reCAPTCHA responses.
 */
class RecaptchaVerifier
{
    private final String secretKey;
    private final String response;
    private final String verifyURL;

    /**
     * Constructs a new {@link RecaptchaVerifier}.
     *
     * @param secretKey The secret key to use when verifying.
     * @param response The received response to verify.
     * @param verifyURL The URL to use when verifying.
     */
    RecaptchaVerifier(String secretKey, String response, String verifyURL)
    {
        this.secretKey = secretKey;
        this.response = response;
        this.verifyURL = verifyURL;
    }

    /**
     * Gets whether or not this request is verified.
     *
     * @return Returns {@code true} if this request is verified.
     */
    boolean isVerified()
    {
        String json = getJSON();
        return json.contains("\"success\": true");
    }

    private String getJSON()
    {
        HttpURLConnection connection = null;
        try
        {
            String parameters = new StringBuilder()
                    .append("secret=")
                    .append(encode(secretKey, "UTF-8"))
                    .append("&response=")
                    .append(encode(response, "UTF-8"))
                    .toString();

            URL url = new URL(verifyURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", Integer.toString(parameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            DataOutputStream output = new DataOutputStream(connection.getOutputStream());
            output.writeBytes(parameters);
            output.close();

            InputStream input = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder httpResponse = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                httpResponse.append(line);
                httpResponse.append('\r');
            }
            reader.close();
            return httpResponse.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "{}";
        }
        finally
        {
            if (connection != null)
            {
                connection.disconnect();
            }
        }
    }
}
