package org.myorg.initial.roo.core.repository.security;
import org.myorg.initial.roo.core.domain.security.Principal;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Principal.class)
public interface PrincipalRepository {
}
