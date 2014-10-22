package org.myorg.initial.roo.ui.web.mvc.controller.security;
import org.myorg.initial.roo.core.domain.security.Principal;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RequestMapping("/principals")
@Controller
@RooWebScaffold(path = "principals", formBackingObject = Principal.class)
@RooWebFinder
public class PrincipalController {
}
