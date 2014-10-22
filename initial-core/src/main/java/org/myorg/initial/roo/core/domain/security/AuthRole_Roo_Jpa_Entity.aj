// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.domain.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import org.myorg.initial.roo.core.domain.security.AuthRole;

privileged aspect AuthRole_Roo_Jpa_Entity {
    
    declare @type: AuthRole: @Entity;
    
    declare @type: AuthRole: @Table(name = "security_role");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role")
    private Long AuthRole.id;
    
    @Version
    @Column(name = "OPT_LOCK")
    private Integer AuthRole.version;
    
    public Long AuthRole.getId() {
        return this.id;
    }
    
    public void AuthRole.setId(Long id) {
        this.id = id;
    }
    
    public Integer AuthRole.getVersion() {
        return this.version;
    }
    
    public void AuthRole.setVersion(Integer version) {
        this.version = version;
    }
    
}
