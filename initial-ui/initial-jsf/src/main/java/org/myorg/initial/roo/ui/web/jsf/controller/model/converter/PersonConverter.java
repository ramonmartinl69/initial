package org.myorg.initial.roo.ui.web.jsf.controller.model.converter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.myorg.initial.roo.core.domain.model.Person;
import org.myorg.initial.roo.core.service.model.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@FacesConverter("org.myorg.initial.roo.ui.web.jsf.controller.model.converter.PersonConverter")
@Configurable
@RooJsfConverter(entity = Person.class)
public class PersonConverter implements Converter {

	@Autowired
    PersonService personService;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Long id = Long.parseLong(value);
        return personService.findPerson(id);
    }

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Person ? ((Person) value).getId().toString() : "";
    }
}
