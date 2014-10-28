package org.myorg.initial.roo.ui.web.jsf.controller.security;
import org.myorg.initial.roo.core.domain.security.AuthRole;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@RooSerializable
@RooJsfManagedBean(entity = AuthRole.class, beanName = "authRoleBean")
public class AuthRoleBean {
}
