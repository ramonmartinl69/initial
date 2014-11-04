package org.myorg.initial.roo.ui.web.jsf.controller.model.converter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.myorg.initial.roo.core.domain.model.HomeAddress;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@Configurable
@FacesConverter("org.myorg.initial.roo.ui.web.jsf.controller.model.converter.HomeAddressConverter")
@RooJsfConverter(entity = HomeAddress.class)
public class HomeAddressConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return HomeAddress.findHomeAddress(id);
    }

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof HomeAddress ? ((HomeAddress) value).getId().toString() : "";
    }
}
