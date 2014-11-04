package org.myorg.initial.roo.core.domain.security;
import org.springframework.beans.factory.annotation.Configurable;import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.myorg.initial.roo.core.domain.reference.SecurityRoleEnum;
import javax.validation.constraints.Size;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(name = "security_role")public class AuthRole implements Serializable {

    /**
     */
    @NotNull
    @Enumerated
    private SecurityRoleEnum authority;

    /**
     */
    @NotNull
    @Size(min = 3, max = 100)
    private String roleName;

	public boolean equals(Object obj) {
        if (!(obj instanceof AuthRole)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        AuthRole rhs = (AuthRole) obj;
        return new EqualsBuilder().append(authority, rhs.authority).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(authority).toHashCode();
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("authority", "roleName");

	public static final EntityManager entityManager() {
        EntityManager em = new AuthRole().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countAuthRoles() {
        return entityManager().createQuery("SELECT COUNT(o) FROM AuthRole o", Long.class).getSingleResult();
    }

	public static List<AuthRole> findAllAuthRoles() {
        return entityManager().createQuery("SELECT o FROM AuthRole o", AuthRole.class).getResultList();
    }

	public static List<AuthRole> findAllAuthRoles(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM AuthRole o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, AuthRole.class).getResultList();
    }

	public static AuthRole findAuthRole(Long id) {
        if (id == null) return null;
        return entityManager().find(AuthRole.class, id);
    }

	public static List<AuthRole> findAuthRoleEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM AuthRole o", AuthRole.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<AuthRole> findAuthRoleEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM AuthRole o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, AuthRole.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            AuthRole attached = AuthRole.findAuthRole(this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public AuthRole merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        AuthRole merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static Long countFindAuthRolesByAuthority(SecurityRoleEnum authority) {
        if (authority == null) throw new IllegalArgumentException("The authority argument is required");
        EntityManager em = AuthRole.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM AuthRole AS o WHERE o.authority = :authority", Long.class);
        q.setParameter("authority", authority);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindAuthRolesByRoleNameLike(String roleName) {
        if (roleName == null || roleName.length() == 0) throw new IllegalArgumentException("The roleName argument is required");
        roleName = roleName.replace('*', '%');
        if (roleName.charAt(0) != '%') {
            roleName = "%" + roleName;
        }
        if (roleName.charAt(roleName.length() - 1) != '%') {
            roleName = roleName + "%";
        }
        EntityManager em = AuthRole.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM AuthRole AS o WHERE LOWER(o.roleName) LIKE LOWER(:roleName)", Long.class);
        q.setParameter("roleName", roleName);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<AuthRole> findAuthRolesByAuthority(SecurityRoleEnum authority) {
        if (authority == null) throw new IllegalArgumentException("The authority argument is required");
        EntityManager em = AuthRole.entityManager();
        TypedQuery<AuthRole> q = em.createQuery("SELECT o FROM AuthRole AS o WHERE o.authority = :authority", AuthRole.class);
        q.setParameter("authority", authority);
        return q;
    }

	public static TypedQuery<AuthRole> findAuthRolesByAuthority(SecurityRoleEnum authority, String sortFieldName, String sortOrder) {
        if (authority == null) throw new IllegalArgumentException("The authority argument is required");
        EntityManager em = AuthRole.entityManager();
        String jpaQuery = "SELECT o FROM AuthRole AS o WHERE o.authority = :authority";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<AuthRole> q = em.createQuery(jpaQuery, AuthRole.class);
        q.setParameter("authority", authority);
        return q;
    }

	public static TypedQuery<AuthRole> findAuthRolesByRoleNameLike(String roleName) {
        if (roleName == null || roleName.length() == 0) throw new IllegalArgumentException("The roleName argument is required");
        roleName = roleName.replace('*', '%');
        if (roleName.charAt(0) != '%') {
            roleName = "%" + roleName;
        }
        if (roleName.charAt(roleName.length() - 1) != '%') {
            roleName = roleName + "%";
        }
        EntityManager em = AuthRole.entityManager();
        TypedQuery<AuthRole> q = em.createQuery("SELECT o FROM AuthRole AS o WHERE LOWER(o.roleName) LIKE LOWER(:roleName)", AuthRole.class);
        q.setParameter("roleName", roleName);
        return q;
    }

	public static TypedQuery<AuthRole> findAuthRolesByRoleNameLike(String roleName, String sortFieldName, String sortOrder) {
        if (roleName == null || roleName.length() == 0) throw new IllegalArgumentException("The roleName argument is required");
        roleName = roleName.replace('*', '%');
        if (roleName.charAt(0) != '%') {
            roleName = "%" + roleName;
        }
        if (roleName.charAt(roleName.length() - 1) != '%') {
            roleName = roleName + "%";
        }
        EntityManager em = AuthRole.entityManager();
        String jpaQuery = "SELECT o FROM AuthRole AS o WHERE LOWER(o.roleName) LIKE LOWER(:roleName)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<AuthRole> q = em.createQuery(jpaQuery, AuthRole.class);
        q.setParameter("roleName", roleName);
        return q;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role")
    private Long id;

	@Version
    @Column(name = "OPT_LOCK")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static AuthRole fromJsonToAuthRole(String json) {
        return new JSONDeserializer<AuthRole>()
        .use(null, AuthRole.class).deserialize(json);
    }

	public static String toJsonArray(Collection<AuthRole> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<AuthRole> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<AuthRole> fromJsonArrayToAuthRoles(String json) {
        return new JSONDeserializer<List<AuthRole>>()
        .use("values", AuthRole.class).deserialize(json);
    }

	private static final long serialVersionUID = 1L;

	public SecurityRoleEnum getAuthority() {
        return this.authority;
    }

	public void setAuthority(SecurityRoleEnum authority) {
        this.authority = authority;
    }

	public String getRoleName() {
        return this.roleName;
    }

	public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
