package org.myorg.initial.roo.ui.web.mvc.controller.model;
import org.myorg.initial.roo.core.domain.model.Person;
import org.myorg.initial.roo.core.repository.AppQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String findPeopleByNIFLike(@RequestParam("NIF") String nIF, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
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
}
