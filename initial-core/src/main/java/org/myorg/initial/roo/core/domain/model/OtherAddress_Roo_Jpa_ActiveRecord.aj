// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.domain.model;

import java.util.List;
import org.myorg.initial.roo.core.domain.model.OtherAddress;
import org.springframework.transaction.annotation.Transactional;

privileged aspect OtherAddress_Roo_Jpa_ActiveRecord {
    
    public static final List<String> OtherAddress.fieldNames4OrderClauseFilter = java.util.Arrays.asList("active");
    
    public static long OtherAddress.countOtherAddresses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM OtherAddress o", Long.class).getSingleResult();
    }
    
    public static List<OtherAddress> OtherAddress.findAllOtherAddresses() {
        return entityManager().createQuery("SELECT o FROM OtherAddress o", OtherAddress.class).getResultList();
    }
    
    public static List<OtherAddress> OtherAddress.findAllOtherAddresses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM OtherAddress o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, OtherAddress.class).getResultList();
    }
    
    public static OtherAddress OtherAddress.findOtherAddress(Long id) {
        if (id == null) return null;
        return entityManager().find(OtherAddress.class, id);
    }
    
    public static List<OtherAddress> OtherAddress.findOtherAddressEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM OtherAddress o", OtherAddress.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<OtherAddress> OtherAddress.findOtherAddressEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM OtherAddress o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, OtherAddress.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public OtherAddress OtherAddress.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        OtherAddress merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
