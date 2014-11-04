package org.myorg.initial.roo.core.service.security;

import java.util.List;
import org.myorg.initial.roo.core.domain.security.Principal;

public interface PrincipalService {

	public abstract long countAllPrincipals();


	public abstract void deletePrincipal(Principal principal);


	public abstract Principal findPrincipal(Long id);


	public abstract List<Principal> findAllPrincipals();


	public abstract List<Principal> findPrincipalEntries(int firstResult, int maxResults);


	public abstract void savePrincipal(Principal principal);


	public abstract Principal updatePrincipal(Principal principal);

}
