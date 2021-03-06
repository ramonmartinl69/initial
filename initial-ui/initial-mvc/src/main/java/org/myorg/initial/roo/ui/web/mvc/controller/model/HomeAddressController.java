package org.myorg.initial.roo.ui.web.mvc.controller.model;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.myorg.initial.roo.core.domain.model.HomeAddress;
import org.myorg.initial.roo.core.domain.reference.AddresLocationTypeEnum;
import org.myorg.initial.roo.core.domain.reference.AddressTypeEnum;
import org.myorg.initial.roo.core.domain.reference.CountryEnum;
import org.myorg.initial.roo.core.domain.reference.ProvinceEnum;
import org.myorg.initial.roo.core.service.model.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RequestMapping("/homeaddresses")
@Controller
@RooWebScaffold(path = "homeaddresses", formBackingObject = HomeAddress.class)
@RooWebFinder
public class HomeAddressController {

	@Autowired
    PersonService personService;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid HomeAddress homeAddress, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, homeAddress);
            return "homeaddresses/create";
        }
        uiModel.asMap().clear();
        homeAddress.persist();
        return "redirect:/homeaddresses/" + encodeUrlPathSegment(homeAddress.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new HomeAddress());
        return "homeaddresses/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("homeaddress", HomeAddress.findHomeAddress(id));
        uiModel.addAttribute("itemId", id);
        return "homeaddresses/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("homeaddresses", HomeAddress.findHomeAddressEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) HomeAddress.countHomeAddresses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("homeaddresses", HomeAddress.findAllHomeAddresses(sortFieldName, sortOrder));
        }
        return "homeaddresses/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid HomeAddress homeAddress, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, homeAddress);
            return "homeaddresses/update";
        }
        uiModel.asMap().clear();
        homeAddress.merge();
        return "redirect:/homeaddresses/" + encodeUrlPathSegment(homeAddress.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, HomeAddress.findHomeAddress(id));
        return "homeaddresses/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        HomeAddress homeAddress = HomeAddress.findHomeAddress(id);
        homeAddress.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/homeaddresses";
    }

	void populateEditForm(Model uiModel, HomeAddress homeAddress) {
        uiModel.addAttribute("homeAddress", homeAddress);
        uiModel.addAttribute("people", personService.findAllPeople());
        uiModel.addAttribute("addreslocationtypeenums", Arrays.asList(AddresLocationTypeEnum.values()));
        uiModel.addAttribute("addresstypeenums", Arrays.asList(AddressTypeEnum.values()));
        uiModel.addAttribute("countryenums", Arrays.asList(CountryEnum.values()));
        uiModel.addAttribute("provinceenums", Arrays.asList(ProvinceEnum.values()));
    }

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
}
