var activeVaadinRecaptcha = {};

window.com_antonjohansson_vaadin_recaptcha_Recaptcha = function()
{
    var connector = this;
    var element = connector.getElement();
    activeVaadinRecaptcha = connector;

    this.onStateChange = function()
    {
        var siteKey = connector.getState().siteKey;
        element.innerHTML = '<div class="g-recaptcha" data-sitekey="' + siteKey + '" data-callback="vaadinRecaptchaCallback"></div>';
    };
};

function vaadinRecaptchaCallback(response)
{
    activeVaadinRecaptcha.setRecaptchaResponse(response);
}
