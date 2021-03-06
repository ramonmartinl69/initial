package org.myorg.initial.roo.ui.web.mvc.controller.security.json;
import java.util.List;
import org.myorg.initial.roo.core.domain.reference.SecurityRoleEnum;
import org.myorg.initial.roo.core.domain.security.AuthRole;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

@RooWebJson(jsonObject = AuthRole.class)
@Controller
@RequestMapping("/authroles")
public class AuthRoleControllerJson {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        AuthRole authRole = AuthRole.findAuthRole(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (authRole == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(authRole.toJson(), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<AuthRole> result = AuthRole.findAllAuthRoles();
        return new ResponseEntity<String>(AuthRole.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        AuthRole authRole = AuthRole.fromJsonToAuthRole(json);
        authRole.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+authRole.getId().toString()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (AuthRole authRole: AuthRole.fromJsonArrayToAuthRoles(json)) {
            authRole.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        AuthRole authRole = AuthRole.fromJsonToAuthRole(json);
        authRole.setId(id);
        if (authRole.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        AuthRole authRole = AuthRole.findAuthRole(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (authRole == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        authRole.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByAuthority", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindAuthRolesByAuthority(@RequestParam("authority") SecurityRoleEnum authority) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(AuthRole.toJsonArray(AuthRole.findAuthRolesByAuthority(authority).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByRoleNameLike", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindAuthRolesByRoleNameLike(@RequestParam("roleName") String roleName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(AuthRole.toJsonArray(AuthRole.findAuthRolesByRoleNameLike(roleName).getResultList()), headers, HttpStatus.OK);
    }
}
