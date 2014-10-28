package org.myorg.initial.roo.ui.web.mvc.controller.model;
import org.myorg.initial.roo.core.domain.model.OtherAddress;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/otheraddresses")
@Controller
@RooWebScaffold(path = "otheraddresses", formBackingObject = OtherAddress.class)
public class OtherAddressController {
}
