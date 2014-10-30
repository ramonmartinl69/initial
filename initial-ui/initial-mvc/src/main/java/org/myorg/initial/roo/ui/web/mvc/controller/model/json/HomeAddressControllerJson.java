package org.myorg.initial.roo.ui.web.mvc.controller.model.json;
import org.myorg.initial.roo.core.domain.model.HomeAddress;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = HomeAddress.class)
@Controller
@RequestMapping("/homeaddresses")
public class HomeAddressControllerJson {
}
