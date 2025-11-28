package ru.itmo.web.lab3;

import java.math.BigDecimal;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("yValidator")
public class YValidator implements Validator<BigDecimal> {
    @Override
    public void validate(FacesContext context, UIComponent component, BigDecimal value) throws ValidatorException {
        if (value == null)
            return;

        if (value.compareTo(new BigDecimal(-3)) < 0 || value.compareTo(new BigDecimal(3)) > 0) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Вне диапазона",
                    "Y должен входить в [-3, 3]"));
        }
    }
}
