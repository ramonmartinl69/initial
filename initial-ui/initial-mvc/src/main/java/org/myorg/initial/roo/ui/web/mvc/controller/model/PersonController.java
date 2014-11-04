package org.myorg.initial.roo.ui.web.mvc.controller.model;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.myorg.initial.roo.core.domain.model.GeneralAddress;
import org.myorg.initial.roo.core.domain.model.Person;
import org.myorg.initial.roo.core.domain.reference.CountryEnum;
import org.myorg.initial.roo.core.domain.reference.SemanticQuestionEnum;
import org.myorg.initial.roo.core.repository.AppQueryRepository;
import org.myorg.initial.roo.core.service.model.PersonService;
import org.myorg.initial.roo.core.service.security.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;
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

@RequestMapping("/persons")
@Controller
@RooWebScaffold(path = "persons", formBackingObject = Person.class)
@RooWebFinder
public class PersonController {
	
	@Autowired
	AppQueryRepository appQueryRepository;

	@RequestMapping(params = { "find=ByFirstNameLikeAndLastNameLike", "form" }, method = RequestMethod.GET)
    public String findPeopleByFirstNameLikeAndLastNameLikeForm(Model uiModel) {
        return "persons/findPeopleByFirstNameLikeAndLastNameLike";
    }

	@RequestMapping(params = "find=ByFirstNameLikeAndLastNameLike", method = RequestMethod.GET)
    public String findPeopleByFirstNameLikeAndLastNameLike(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("people", appQueryRepository.findPeopleByFirstNameLikeAndLastNameLike(firstName, lastName, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) appQueryRepository.countFindPeopleByFirstNameLikeAndLastNameLike(firstName, lastName) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("people", appQueryRepository.findPeopleByFirstNameLikeAndLastNameLike(firstName, lastName, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "persons/list";
    }

	@RequestMapping(params = { "find=ByNIFLike", "form" }, method = RequestMethod.GET)
    public String findPeopleByNIFLikeForm(Model uiModel) {
        return "persons/findPeopleByNIFLike";
    }

	@RequestMapping(params = "find=ByNIFLike", method = RequestMethod.GET)
    public String findPeopleByNIFLike(@RequestParam("nIF") String nIF, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("people", appQueryRepository.findPeopleByNIFLike(nIF, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) appQueryRepository.countFindPeopleByNIFLike(nIF) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("people", appQueryRepository.findPeopleByNIFLike(nIF, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "persons/list";
    }

	@Autowired
    PersonService personService;

	@Autowired
    PrincipalService principalService;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Person person, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, person);
            return "persons/create";
        }
        uiModel.asMap().clear();
        personService.savePerson(person);
        return "redirect:/persons/" + encodeUrlPathSegment(person.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Person());
        return "persons/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("person", personService.findPerson(id));
        uiModel.addAttribute("itemId", id);
        return "persons/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("people", personService.findPersonEntries(firstResult, sizeNo));
            float nrOfPages = (float) personService.countAllPeople() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("people", personService.findAllPeople());
        }
        addDateTimeFormatPatterns(uiModel);
        return "persons/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Person person, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, person);
            return "persons/update";
        }
        uiModel.asMap().clear();
        personService.updatePerson(person);
        return "redirect:/persons/" + encodeUrlPathSegment(person.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, personService.findPerson(id));
        return "persons/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Person person = personService.findPerson(id);
        personService.deletePerson(person);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/persons";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("person_birthdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, Person person) {
        uiModel.addAttribute("person", person);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("generaladdresses", GeneralAddress.findAllGeneralAddresses());
        uiModel.addAttribute("countryenums", Arrays.asList(CountryEnum.values()));
        uiModel.addAttribute("semanticquestionenums", Arrays.asList(SemanticQuestionEnum.values()));
        uiModel.addAttribute("principals", principalService.findAllPrincipals());
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
