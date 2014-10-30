package org.myorg.initial.roo.ui.web.mvc.controller.security.json;
import org.myorg.initial.roo.core.domain.security.AuthRole;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = AuthRole.class)
@Controller
@RequestMapping("/authroles")
public class AuthRoleControllerJson {
}
