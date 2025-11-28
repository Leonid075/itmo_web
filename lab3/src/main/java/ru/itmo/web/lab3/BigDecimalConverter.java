package ru.itmo.web.lab3;

import java.math.BigDecimal;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.faces.application.FacesMessage;

@FacesConverter("bigDecimalConverter")
public class BigDecimalConverter implements Converter<BigDecimal> {
    @Override
    public BigDecimal getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        if (value == null || value.trim().isEmpty())
            return null;

        try {
            return new BigDecimal(value);
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Некорректное значение",
                    "Y должен быть числом"));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, BigDecimal value) {
        if (value == null)
            return "";
        return value.toString();
    }
}
