package com.formation.bean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.net.URI;
import java.net.URISyntaxException;

@FacesValidator("com.formation.bean.UrlValidator")
public class UrlValidator implements Validator {


    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        StringBuilder url = new StringBuilder();
        String urlValue = o.toString();

        if(!urlValue.startsWith("http://", 0)) {
            url.append("http://");
        }
        url.append(urlValue);

        try {
            new URI(url.toString());
        } catch (URISyntaxException e) {
            FacesMessage msg =
                    new FacesMessage("URL validation failed","Invalid URL format");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
