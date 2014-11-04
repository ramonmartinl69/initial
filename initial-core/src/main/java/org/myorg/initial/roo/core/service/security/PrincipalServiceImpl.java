package org.myorg.initial.roo.core.service.security;

import java.util.List;
import org.myorg.initial.roo.core.domain.security.Principal;
import org.myorg.initial.roo.core.repository.security.PrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrincipalServiceImpl implements PrincipalService {

	@Autowired
    PrincipalRepository principalRepository;

	public long countAllPrincipals() {
        return principalRepository.count();
    }

	public void deletePrincipal(Principal principal) {
        principalRepository.delete(principal);
    }

	public Principal findPrincipal(Long id) {
        return principalRepository.findOne(id);
    }

	public List<Principal> findAllPrincipals() {
        return principalRepository.findAll();
    }

	public List<Principal> findPrincipalEntries(int firstResult, int maxResults) {
        return principalRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void savePrincipal(Principal principal) {
        principalRepository.save(principal);
    }

	public Principal updatePrincipal(Principal principal) {
        return principalRepository.save(principal);
    }
}
