// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import org.myorg.initial.roo.core.domain.model.Person;

privileged aspect Person_Roo_Jpa_Entity {
    
    declare @type: Person: @Entity;
    
    declare @type: Person: @Table(name = "person");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long Person.id;
    
    @Version
    @Column(name = "OPT_LOCK")
    private Integer Person.version;
    
    public Long Person.getId() {
        return this.id;
    }
    
    public void Person.setId(Long id) {
        this.id = id;
    }
    
    public Integer Person.getVersion() {
        return this.version;
    }
    
    public void Person.setVersion(Integer version) {
        this.version = version;
    }
    
}
