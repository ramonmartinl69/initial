package org.myorg.initial.roo.ui.web.jsf.controller.model.converter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.myorg.initial.roo.core.domain.model.OtherAddress;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@FacesConverter("org.myorg.initial.roo.ui.web.jsf.controller.model.converter.OtherAddressConverter")
@Configurable
@RooJsfConverter(entity = OtherAddress.class)
public class OtherAddressConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return OtherAddress.findOtherAddress(id);
    }

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof OtherAddress ? ((OtherAddress) value).getId().toString() : "";
    }
}
