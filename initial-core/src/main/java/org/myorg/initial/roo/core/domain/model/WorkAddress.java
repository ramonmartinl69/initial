package org.myorg.initial.roo.core.domain.model;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Configurable
public class WorkAddress extends GeneralAddress {

    /**
     */
    @NotNull
    @Size(min = 3, max = 250)
    private String enterpriseName;

    /**
     */
    @NotNull
    @Size(min = 3, max = 250)
    private String postalAddress;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("enterpriseName", "postalAddress");

	public static long countWorkAddresses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM WorkAddress o", Long.class).getSingleResult();
    }

	public static List<WorkAddress> findAllWorkAddresses() {
        return entityManager().createQuery("SELECT o FROM WorkAddress o", WorkAddress.class).getResultList();
    }

	public static List<WorkAddress> findAllWorkAddresses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM WorkAddress o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, WorkAddress.class).getResultList();
    }

	public static WorkAddress findWorkAddress(Long id) {
        if (id == null) return null;
        return entityManager().find(WorkAddress.class, id);
    }

	public static List<WorkAddress> findWorkAddressEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM WorkAddress o", WorkAddress.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<WorkAddress> findWorkAddressEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
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
    public WorkAddress merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        WorkAddress merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String getEnterpriseName() {
        return this.enterpriseName;
    }

	public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

	public String getPostalAddress() {
        return this.postalAddress;
    }

	public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }
}
