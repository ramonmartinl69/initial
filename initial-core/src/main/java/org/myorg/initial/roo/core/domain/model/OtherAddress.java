package org.myorg.initial.roo.core.domain.model;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;import org.springframework.transaction.annotation.Transactional;

@Entity
@Configurablepublic class OtherAddress extends GeneralAddress {

    /**
     */
    private Boolean active;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("active");

	public static long countOtherAddresses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM OtherAddress o", Long.class).getSingleResult();
    }

	public static List<OtherAddress> findAllOtherAddresses() {
        return entityManager().createQuery("SELECT o FROM OtherAddress o", OtherAddress.class).getResultList();
    }

	public static List<OtherAddress> findAllOtherAddresses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM OtherAddress o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, OtherAddress.class).getResultList();
    }

	public static OtherAddress findOtherAddress(Long id) {
        if (id == null) return null;
        return entityManager().find(OtherAddress.class, id);
    }

	public static List<OtherAddress> findOtherAddressEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM OtherAddress o", OtherAddress.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<OtherAddress> findOtherAddressEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
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
    public OtherAddress merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        OtherAddress merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static OtherAddress fromJsonToOtherAddress(String json) {
        return new JSONDeserializer<OtherAddress>()
        .use(null, OtherAddress.class).deserialize(json);
    }

	public static String toJsonArray(Collection<OtherAddress> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<OtherAddress> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<OtherAddress> fromJsonArrayToOtherAddresses(String json) {
        return new JSONDeserializer<List<OtherAddress>>()
        .use("values", OtherAddress.class).deserialize(json);
    }

	public Boolean getActive() {
        return this.active;
    }

	public void setActive(Boolean active) {
        this.active = active;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
