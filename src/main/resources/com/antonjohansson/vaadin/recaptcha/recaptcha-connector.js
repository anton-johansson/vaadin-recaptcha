/*
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

window.com_antonjohansson_vaadin_recaptcha_Recaptcha = function()
{
    var connector = this;
    var widgetId = -1;

    var addOption = function(parameters, option)
    {
        if (connector.getState()[option])
        {
            parameters[option] = connector.getState()[option];
        }
    }

    this.onStateChange = function()
    {
        var parameters =
        {
            'sitekey': connector.getState().siteKey,
            'callback': this.responseCallback,
            'expired-callback': this.expiredCallback
        }

        addOption(parameters, 'size');
        addOption(parameters, 'theme');
        addOption(parameters, 'type');
        addOption(parameters, 'tabindex');

        widgetId = grecaptcha.render(connector.getElement(), parameters);
    };

    this.responseCallback = function(response)
    {
        connector.setResponse(response);
    }

    this.expiredCallback = function()
    {
        connector.expired();
    }

    this.reset = function()
    {
        grecaptcha.reset(widgetId);
    }
};
