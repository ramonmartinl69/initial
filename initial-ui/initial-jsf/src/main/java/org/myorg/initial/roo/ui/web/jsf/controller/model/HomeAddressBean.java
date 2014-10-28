package org.myorg.initial.roo.ui.web.jsf.controller.model;
import org.myorg.initial.roo.core.domain.model.HomeAddress;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = HomeAddress.class, beanName = "homeAddressBean")
public class HomeAddressBean {
}
