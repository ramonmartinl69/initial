package org.myorg.initial.roo.ui.web.jsf.controller.security;
import org.myorg.initial.roo.core.domain.security.Principal;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = Principal.class, beanName = "principalBean")
public class PrincipalBean {
}
