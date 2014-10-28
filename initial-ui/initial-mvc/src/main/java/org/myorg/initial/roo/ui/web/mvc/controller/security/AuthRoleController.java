package org.myorg.initial.roo.ui.web.mvc.controller.security;
import org.myorg.initial.roo.core.domain.security.AuthRole;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/roles")
@Controller
@RooWebScaffold(path = "roles", formBackingObject = AuthRole.class)
public class AuthRoleController {
}
