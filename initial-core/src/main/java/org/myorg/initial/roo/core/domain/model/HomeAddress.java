package org.myorg.initial.roo.core.domain.model;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entitypublic class HomeAddress extends GeneralAddress {

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static long countHomeAddresses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM HomeAddress o", Long.class).getSingleResult();
    }

	public static List<HomeAddress> findAllHomeAddresses() {
        return entityManager().createQuery("SELECT o FROM HomeAddress o", HomeAddress.class).getResultList();
    }

	public static List<HomeAddress> findAllHomeAddresses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM HomeAddress o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, HomeAddress.class).getResultList();
    }

	public static HomeAddress findHomeAddress(Long id) {
        if (id == null) return null;
        return entityManager().find(HomeAddress.class, id);
    }

	public static List<HomeAddress> findHomeAddressEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM HomeAddress o", HomeAddress.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<HomeAddress> findHomeAddressEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM HomeAddress o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, HomeAddress.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public HomeAddress merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        HomeAddress merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static HomeAddress fromJsonToHomeAddress(String json) {
        return new JSONDeserializer<HomeAddress>()
        .use(null, HomeAddress.class).deserialize(json);
    }

	public static String toJsonArray(Collection<HomeAddress> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<HomeAddress> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<HomeAddress> fromJsonArrayToHomeAddresses(String json) {
        return new JSONDeserializer<List<HomeAddress>>()
        .use("values", HomeAddress.class).deserialize(json);
    }
}
