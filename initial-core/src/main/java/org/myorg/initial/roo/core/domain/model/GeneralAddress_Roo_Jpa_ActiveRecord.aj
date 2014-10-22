// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.domain.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.myorg.initial.roo.core.domain.model.GeneralAddress;
import org.springframework.transaction.annotation.Transactional;

privileged aspect GeneralAddress_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager GeneralAddress.entityManager;
    
    public static final List<String> GeneralAddress.fieldNames4OrderClauseFilter = java.util.Arrays.asList("addressType", "locationType", "address", "country", "province", "postalCode", "population", "addresNumber", "person");
    
    public static final EntityManager GeneralAddress.entityManager() {
        EntityManager em = new GeneralAddress() {
        }.entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long GeneralAddress.countGeneralAddresses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM GeneralAddress o", Long.class).getSingleResult();
    }
    
    public static List<GeneralAddress> GeneralAddress.findAllGeneralAddresses() {
        return entityManager().createQuery("SELECT o FROM GeneralAddress o", GeneralAddress.class).getResultList();
    }
    
    public static List<GeneralAddress> GeneralAddress.findAllGeneralAddresses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM GeneralAddress o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, GeneralAddress.class).getResultList();
    }
    
    public static GeneralAddress GeneralAddress.findGeneralAddress(Long id) {
        if (id == null) return null;
        return entityManager().find(GeneralAddress.class, id);
    }
    
    public static List<GeneralAddress> GeneralAddress.findGeneralAddressEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM GeneralAddress o", GeneralAddress.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<GeneralAddress> GeneralAddress.findGeneralAddressEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM GeneralAddress o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, GeneralAddress.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void GeneralAddress.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void GeneralAddress.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            GeneralAddress attached = GeneralAddress.findGeneralAddress(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void GeneralAddress.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void GeneralAddress.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public GeneralAddress GeneralAddress.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        GeneralAddress merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
