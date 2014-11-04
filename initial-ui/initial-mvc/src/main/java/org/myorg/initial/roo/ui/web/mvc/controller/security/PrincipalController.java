package org.myorg.initial.roo.ui.web.mvc.controller.security;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.myorg.initial.roo.core.domain.security.AuthRole;
import org.myorg.initial.roo.core.domain.security.Principal;
import org.myorg.initial.roo.core.repository.AppQueryRepository;
import org.myorg.initial.roo.core.service.model.PersonService;
import org.myorg.initial.roo.core.service.security.PrincipalService;
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

@RequestMapping("/principals")
@Controller
@RooWebScaffold(path = "principals", formBackingObject = Principal.class)
@RooWebFinder
public class PrincipalController {
	
	@Autowired
	AppQueryRepository appQueryRepository;

	@RequestMapping(params = { "find=ByActivationKeyEquals", "form" }, method = RequestMethod.GET)
    public String findPrincipalsByActivationKeyEqualsForm(Model uiModel) {
        return "principals/findPrincipalsByActivationKeyEquals";
    }

	@RequestMapping(params = "find=ByActivationKeyEquals", method = RequestMethod.GET)
    public String findPrincipalsByActivationKeyEquals(@RequestParam("activationKey") String activationKey, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("principals", appQueryRepository.findPrincipalsByActivationKeyEquals(activationKey, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) appQueryRepository.countFindPrincipalsByActivationKeyEquals(activationKey) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("principals", appQueryRepository.findPrincipalsByActivationKeyEquals(activationKey, sortFieldName, sortOrder).getResultList());
        }
        return "principals/list";
    }

	@RequestMapping(params = { "find=ByEnabledNot", "form" }, method = RequestMethod.GET)
    public String findPrincipalsByEnabledNotForm(Model uiModel) {
        return "principals/findPrincipalsByEnabledNot";
    }

	@RequestMapping(params = "find=ByEnabledNot", method = RequestMethod.GET)
    public String findPrincipalsByEnabledNot(@RequestParam(value = "enabled", required = false) Boolean enabled, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("principals", appQueryRepository.findPrincipalsByEnabledNot(enabled == null ? Boolean.FALSE : enabled, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) appQueryRepository.countFindPrincipalsByEnabledNot(enabled == null ? Boolean.FALSE : enabled) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("principals", appQueryRepository.findPrincipalsByEnabledNot(enabled == null ? Boolean.FALSE : enabled, sortFieldName, sortOrder).getResultList());
        }
        return "principals/list";
    }

	@RequestMapping(params = { "find=ByRoles", "form" }, method = RequestMethod.GET)
    public String findPrincipalsByRolesForm(Model uiModel) {
        uiModel.addAttribute("authroles", AuthRole.findAllAuthRoles());
        return "principals/findPrincipalsByRoles";
    }

	@RequestMapping(params = "find=ByRoles", method = RequestMethod.GET)
    public String findPrincipalsByRoles(@RequestParam("roles") Set<AuthRole> roles, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("principals", appQueryRepository.findPrincipalsByRoles(roles, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) appQueryRepository.countFindPrincipalsByRoles(roles) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("principals", appQueryRepository.findPrincipalsByRoles(roles, sortFieldName, sortOrder).getResultList());
        }
        return "principals/list";
    }

	@RequestMapping(params = { "find=ByUserNameEquals", "form" }, method = RequestMethod.GET)
    public String findPrincipalsByUserNameEqualsForm(Model uiModel) {
        return "principals/findPrincipalsByUserNameEquals";
    }

	@RequestMapping(params = "find=ByUserNameEquals", method = RequestMethod.GET)
    public String findPrincipalsByUserNameEquals(@RequestParam("userName") String userName, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("principals", appQueryRepository.findPrincipalsByUserNameEquals(userName, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) appQueryRepository.countFindPrincipalsByUserNameEquals(userName) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("principals", appQueryRepository.findPrincipalsByUserNameEquals(userName, sortFieldName, sortOrder).getResultList());
        }
        return "principals/list";
    }

	@RequestMapping(params = { "find=ByUserNameIsNotNull", "form" }, method = RequestMethod.GET)
    public String findPrincipalsByUserNameIsNotNullForm() {
        return "principals/findPrincipalsByUserNameIsNotNull";
    }

	@RequestMapping(params = "find=ByUserNameIsNotNull", method = RequestMethod.GET)
    public String findPrincipalsByUserNameIsNotNull(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("principals", appQueryRepository.findPrincipalsByUserNameIsNotNull(sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) appQueryRepository.countFindPrincipalsByUserNameIsNotNull() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("principals", appQueryRepository.findPrincipalsByUserNameIsNotNull(sortFieldName, sortOrder).getResultList());
        }
        return "principals/list";
    }

	@RequestMapping(params = { "find=ByUserNameIsNull", "form" }, method = RequestMethod.GET)
    public String findPrincipalsByUserNameIsNullForm() {
        return "principals/findPrincipalsByUserNameIsNull";
    }

	@RequestMapping(params = "find=ByUserNameIsNull", method = RequestMethod.GET)
    public String findPrincipalsByUserNameIsNull(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("principals", appQueryRepository.findPrincipalsByUserNameIsNull(sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) appQueryRepository.countFindPrincipalsByUserNameIsNull() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("principals", appQueryRepository.findPrincipalsByUserNameIsNull(sortFieldName, sortOrder).getResultList());
        }
        return "principals/list";
    }

	@RequestMapping(params = { "find=ByUserNameLike", "form" }, method = RequestMethod.GET)
    public String findPrincipalsByUserNameLikeForm(Model uiModel) {
        return "principals/findPrincipalsByUserNameLike";
    }

	@RequestMapping(params = "find=ByUserNameLike", method = RequestMethod.GET)
    public String findPrincipalsByUserNameLike(@RequestParam("userName") String userName, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("principals", appQueryRepository.findPrincipalsByUserNameLike(userName, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) appQueryRepository.countFindPrincipalsByUserNameLike(userName) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("principals", appQueryRepository.findPrincipalsByUserNameLike(userName, sortFieldName, sortOrder).getResultList());
        }
        return "principals/list";
    }

	@Autowired
    PrincipalService principalService;

	@Autowired
    PersonService personService;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Principal principal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, principal);
            return "principals/create";
        }
        uiModel.asMap().clear();
        principalService.savePrincipal(principal);
        return "redirect:/principals/" + encodeUrlPathSegment(principal.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Principal());
        return "principals/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("principal", principalService.findPrincipal(id));
        uiModel.addAttribute("itemId", id);
        return "principals/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("principals", principalService.findPrincipalEntries(firstResult, sizeNo));
            float nrOfPages = (float) principalService.countAllPrincipals() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("principals", principalService.findAllPrincipals());
        }
        return "principals/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Principal principal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, principal);
            return "principals/update";
        }
        uiModel.asMap().clear();
        principalService.updatePrincipal(principal);
        return "redirect:/principals/" + encodeUrlPathSegment(principal.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, principalService.findPrincipal(id));
        return "principals/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Principal principal = principalService.findPrincipal(id);
        principalService.deletePrincipal(principal);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/principals";
    }

	void populateEditForm(Model uiModel, Principal principal) {
        uiModel.addAttribute("principal", principal);
        uiModel.addAttribute("people", personService.findAllPeople());
        uiModel.addAttribute("authroles", AuthRole.findAllAuthRoles());
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
