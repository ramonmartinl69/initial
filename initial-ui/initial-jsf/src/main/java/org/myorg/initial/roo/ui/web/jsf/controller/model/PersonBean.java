package org.myorg.initial.roo.ui.web.jsf.controller.model;
import org.myorg.initial.roo.core.domain.model.Person;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Person.class, beanName = "personBean")
public class PersonBean {
}
