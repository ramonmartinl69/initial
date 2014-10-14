// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.domain.model;

import java.util.List;
import org.myorg.initial.roo.core.domain.model.WorkAddress;
import org.springframework.transaction.annotation.Transactional;

privileged aspect WorkAddress_Roo_Jpa_ActiveRecord {
    
    public static final List<String> WorkAddress.fieldNames4OrderClauseFilter = java.util.Arrays.asList("enterpriseName", "postalAddress");
    
    public static long WorkAddress.countWorkAddresses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM WorkAddress o", Long.class).getSingleResult();
    }
    
    public static List<WorkAddress> WorkAddress.findAllWorkAddresses() {
        return entityManager().createQuery("SELECT o FROM WorkAddress o", WorkAddress.class).getResultList();
    }
    
    public static List<WorkAddress> WorkAddress.findAllWorkAddresses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM WorkAddress o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, WorkAddress.class).getResultList();
    }
    
    public static WorkAddress WorkAddress.findWorkAddress(Long id) {
        if (id == null) return null;
        return entityManager().find(WorkAddress.class, id);
    }
    
    public static List<WorkAddress> WorkAddress.findWorkAddressEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM WorkAddress o", WorkAddress.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<WorkAddress> WorkAddress.findWorkAddressEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM WorkAddress o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, WorkAddress.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public WorkAddress WorkAddress.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        WorkAddress merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
