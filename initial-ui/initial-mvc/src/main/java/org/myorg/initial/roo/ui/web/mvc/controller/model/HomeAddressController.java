package org.myorg.initial.roo.ui.web.mvc.controller.model;
import org.myorg.initial.roo.core.domain.model.HomeAddress;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RequestMapping("/homeaddresses")
@Controller
@RooWebScaffold(path = "homeaddresses", formBackingObject = HomeAddress.class)
@RooWebFinder
public class HomeAddressController {
}
