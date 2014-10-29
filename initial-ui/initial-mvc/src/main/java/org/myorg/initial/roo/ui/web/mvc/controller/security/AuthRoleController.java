package org.myorg.initial.roo.ui.web.mvc.controller.security;
import org.myorg.initial.roo.core.domain.security.AuthRole;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RequestMapping("/roles")
@Controller
@RooWebScaffold(path = "roles", formBackingObject = AuthRole.class)
@RooWebFinder
public class AuthRoleController {
}
