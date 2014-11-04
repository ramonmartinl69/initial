package org.myorg.initial.roo.ui.web.mvc.controller.security;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.myorg.initial.roo.core.domain.reference.SecurityRoleEnum;
import org.myorg.initial.roo.core.domain.security.AuthRole;
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

@RequestMapping("/roles")
@Controller
@RooWebScaffold(path = "roles", formBackingObject = AuthRole.class)
@RooWebFinder
public class AuthRoleController {

	@RequestMapping(params = { "find=ByAuthority", "form" }, method = RequestMethod.GET)
    public String findAuthRolesByAuthorityForm(Model uiModel) {
        uiModel.addAttribute("securityroleenums", java.util.Arrays.asList(SecurityRoleEnum.class.getEnumConstants()));
        return "roles/findAuthRolesByAuthority";
    }

	@RequestMapping(params = "find=ByAuthority", method = RequestMethod.GET)
    public String findAuthRolesByAuthority(@RequestParam("authority") SecurityRoleEnum authority, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("authroles", AuthRole.findAuthRolesByAuthority(authority, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) AuthRole.countFindAuthRolesByAuthority(authority) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("authroles", AuthRole.findAuthRolesByAuthority(authority, sortFieldName, sortOrder).getResultList());
        }
        return "roles/list";
    }

	@RequestMapping(params = { "find=ByRoleNameLike", "form" }, method = RequestMethod.GET)
    public String findAuthRolesByRoleNameLikeForm(Model uiModel) {
        return "roles/findAuthRolesByRoleNameLike";
    }

	@RequestMapping(params = "find=ByRoleNameLike", method = RequestMethod.GET)
    public String findAuthRolesByRoleNameLike(@RequestParam("roleName") String roleName, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("authroles", AuthRole.findAuthRolesByRoleNameLike(roleName, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) AuthRole.countFindAuthRolesByRoleNameLike(roleName) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("authroles", AuthRole.findAuthRolesByRoleNameLike(roleName, sortFieldName, sortOrder).getResultList());
        }
        return "roles/list";
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid AuthRole authRole, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, authRole);
            return "roles/create";
        }
        uiModel.asMap().clear();
        authRole.persist();
        return "redirect:/roles/" + encodeUrlPathSegment(authRole.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new AuthRole());
        return "roles/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("authrole", AuthRole.findAuthRole(id));
        uiModel.addAttribute("itemId", id);
        return "roles/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("authroles", AuthRole.findAuthRoleEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) AuthRole.countAuthRoles() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("authroles", AuthRole.findAllAuthRoles(sortFieldName, sortOrder));
        }
        return "roles/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid AuthRole authRole, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, authRole);
            return "roles/update";
        }
        uiModel.asMap().clear();
        authRole.merge();
        return "redirect:/roles/" + encodeUrlPathSegment(authRole.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, AuthRole.findAuthRole(id));
        return "roles/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        AuthRole authRole = AuthRole.findAuthRole(id);
        authRole.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/roles";
    }

	void populateEditForm(Model uiModel, AuthRole authRole) {
        uiModel.addAttribute("authRole", authRole);
        uiModel.addAttribute("securityroleenums", Arrays.asList(SecurityRoleEnum.values()));
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
