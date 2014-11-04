package org.myorg.initial.roo.ui.web.jsf.controller.security.converter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.myorg.initial.roo.core.domain.security.Principal;
import org.myorg.initial.roo.core.service.security.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@Configurable
@FacesConverter("org.myorg.initial.roo.ui.web.jsf.controller.security.converter.PrincipalConverter")
@RooJsfConverter(entity = Principal.class)
public class PrincipalConverter implements Converter {

	@Autowired
    PrincipalService principalService;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return principalService.findPrincipal(id);
    }

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Principal ? ((Principal) value).getId().toString() : "";
    }
}
