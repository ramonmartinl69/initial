package org.myorg.initial.roo.core.repository.security;
import org.myorg.initial.roo.core.domain.security.Principal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PrincipalRepository extends JpaSpecificationExecutor<Principal>, JpaRepository<Principal, Long> {
}
