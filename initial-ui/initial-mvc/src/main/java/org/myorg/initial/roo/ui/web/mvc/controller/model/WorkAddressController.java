package org.myorg.initial.roo.ui.web.mvc.controller.model;
import org.myorg.initial.roo.core.domain.model.WorkAddress;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/workaddresses")
@Controller
@RooWebScaffold(path = "workaddresses", formBackingObject = WorkAddress.class)
public class WorkAddressController {
}
