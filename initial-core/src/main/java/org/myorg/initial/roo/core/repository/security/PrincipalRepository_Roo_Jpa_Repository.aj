// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.repository.security;

import org.myorg.initial.roo.core.domain.security.Principal;
import org.myorg.initial.roo.core.repository.security.PrincipalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect PrincipalRepository_Roo_Jpa_Repository {
    
    declare parents: PrincipalRepository extends JpaRepository<Principal, Long>;
    
    declare parents: PrincipalRepository extends JpaSpecificationExecutor<Principal>;
    
    declare @type: PrincipalRepository: @Repository;
    
}