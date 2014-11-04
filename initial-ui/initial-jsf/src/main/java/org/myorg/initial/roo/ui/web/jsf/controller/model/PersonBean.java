package org.myorg.initial.roo.ui.web.jsf.controller.model;
import java.io.Serializable;
import org.myorg.initial.roo.core.domain.model.Person;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@Configurable
@RooSerializable
@RooJsfManagedBean(entity = Person.class, beanName = "personBean")
public class PersonBean implements Serializable {

	private static final long serialVersionUID = 1L;
}
