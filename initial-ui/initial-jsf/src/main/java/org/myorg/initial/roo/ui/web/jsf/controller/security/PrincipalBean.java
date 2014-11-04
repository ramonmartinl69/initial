package org.myorg.initial.roo.ui.web.jsf.controller.security;
import java.io.Serializable;
import org.myorg.initial.roo.core.domain.security.Principal;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@Configurable
@RooSerializable
@RooJsfManagedBean(entity = Principal.class, beanName = "principalBean")
public class PrincipalBean implements Serializable {

	private static final long serialVersionUID = 1L;
}
