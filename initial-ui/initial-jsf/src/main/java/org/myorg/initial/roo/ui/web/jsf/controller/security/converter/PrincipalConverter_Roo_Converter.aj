// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.ui.web.jsf.controller.security.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.myorg.initial.roo.core.domain.security.Principal;
import org.myorg.initial.roo.ui.web.jsf.controller.security.converter.PrincipalConverter;

privileged aspect PrincipalConverter_Roo_Converter {
    
    declare parents: PrincipalConverter implements Converter;
    
    declare @type: PrincipalConverter: @FacesConverter("org.myorg.initial.roo.ui.web.jsf.controller.security.converter.PrincipalConverter");
    
    public Object PrincipalConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return Principal.findPrincipal(id);
    }
    
    public String PrincipalConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Principal ? ((Principal) value).getId().toString() : "";
    }
    
}
