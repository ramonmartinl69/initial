package org.myorg.initial.roo.core.domain.model;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.myorg.initial.roo.core.domain.reference.AddressTypeEnum;
import org.myorg.initial.roo.core.domain.reference.AddresLocationTypeEnum;
import javax.validation.constraints.Size;
import org.myorg.initial.roo.core.domain.reference.CountryEnum;
import org.myorg.initial.roo.core.domain.reference.ProvinceEnum;
import javax.validation.constraints.Pattern;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.Version;

@Entity
@Table(name = "address")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Configurable
@RooJavaBean
@RooToString
@RooSerializable
@RooEquals(excludeFields = { "addressType", "locationType", "address", "country", "province", "postalCode", "population", "addresNumber" })
@RooJpaActiveRecord(identifierColumn = "id_address", inheritanceType = "SINGLE_TABLE", table = "address", versionColumn = "OPT_LOCK", finders = { "findGeneralAddressesByPerson", "findGeneralAddressesByAddressLike" })
public abstract class GeneralAddress implements Serializable  {

    /**
     */
    @NotNull
    @Enumerated
    private AddressTypeEnum addressType;

    /**
     */
    @NotNull
    @Enumerated
    private AddresLocationTypeEnum locationType;

    /**
     */
    @NotNull
    @Size(min = 3, max = 250)
    private String address;

    /**
     */
    @Enumerated
    private CountryEnum country;

    /**
     */
    @NotNull
    @Enumerated
    private ProvinceEnum province;

    /**
     */
    @NotNull
    @Size(min = 3, max = 10)
    @Pattern(regexp = ".*\\d.*")
    private String postalCode;

    /**
     */
    @NotNull
    @Size(min = 3, max = 250)
    private String population;

    /**
     */
    @NotNull
    @Size(min = 1, max = 250)
    private String addresNumber;

    /**
     */
    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person person;

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("addressType", "locationType", "address", "country", "province", "postalCode", "population", "addresNumber", "person");

	public static final EntityManager entityManager() {
        EntityManager em = new GeneralAddress() {
        }.entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countGeneralAddresses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM GeneralAddress o", Long.class).getSingleResult();
    }

	public static List<GeneralAddress> findAllGeneralAddresses() {
        return entityManager().createQuery("SELECT o FROM GeneralAddress o", GeneralAddress.class).getResultList();
    }

	public static List<GeneralAddress> findAllGeneralAddresses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM GeneralAddress o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, GeneralAddress.class).getResultList();
    }

	public static GeneralAddress findGeneralAddress(Long id) {
        if (id == null) return null;
        return entityManager().find(GeneralAddress.class, id);
    }

	public static List<GeneralAddress> findGeneralAddressEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM GeneralAddress o", GeneralAddress.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<GeneralAddress> findGeneralAddressEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
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
            GeneralAddress attached = GeneralAddress.findGeneralAddress(this.id);
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
    public GeneralAddress merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        GeneralAddress merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static Long countFindGeneralAddressesByAddressLike(String address) {
        if (address == null || address.length() == 0) throw new IllegalArgumentException("The address argument is required");
        EntityManager em = GeneralAddress.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM GeneralAddress AS o WHERE LOWER(o.address) LIKE LOWER(:address)", Long.class);
        q.setParameter("address", address);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindGeneralAddressesByPerson(Person person) {
        if (person == null) throw new IllegalArgumentException("The person argument is required");
        EntityManager em = GeneralAddress.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM GeneralAddress AS o WHERE o.person = :person", Long.class);
        q.setParameter("person", person);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<GeneralAddress> findGeneralAddressesByAddressLike(String address) {
        if (address == null || address.length() == 0) throw new IllegalArgumentException("The address argument is required");
        EntityManager em = GeneralAddress.entityManager();
        TypedQuery<GeneralAddress> q = em.createQuery("SELECT o FROM GeneralAddress AS o WHERE LOWER(o.address) LIKE LOWER(:address)", GeneralAddress.class);
        q.setParameter("address", address);
        return q;
    }

	public static TypedQuery<GeneralAddress> findGeneralAddressesByAddressLike(String address, String sortFieldName, String sortOrder) {
        if (address == null || address.length() == 0) throw new IllegalArgumentException("The address argument is required");
        EntityManager em = GeneralAddress.entityManager();
        String jpaQuery = "SELECT o FROM GeneralAddress AS o WHERE LOWER(o.address) LIKE LOWER(:address)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<GeneralAddress> q = em.createQuery(jpaQuery, GeneralAddress.class);
        q.setParameter("address", address);
        return q;
    }

	public static TypedQuery<GeneralAddress> findGeneralAddressesByPerson(Person person) {
        if (person == null) throw new IllegalArgumentException("The person argument is required");
        EntityManager em = GeneralAddress.entityManager();
        TypedQuery<GeneralAddress> q = em.createQuery("SELECT o FROM GeneralAddress AS o WHERE o.person = :person", GeneralAddress.class);
        q.setParameter("person", person);
        return q;
    }

	public static TypedQuery<GeneralAddress> findGeneralAddressesByPerson(Person person, String sortFieldName, String sortOrder) {
        if (person == null) throw new IllegalArgumentException("The person argument is required");
        EntityManager em = GeneralAddress.entityManager();
        String jpaQuery = "SELECT o FROM GeneralAddress AS o WHERE o.person = :person";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<GeneralAddress> q = em.createQuery(jpaQuery, GeneralAddress.class);
        q.setParameter("person", person);
        return q;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_address")
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

	public AddressTypeEnum getAddressType() {
        return this.addressType;
    }

	public void setAddressType(AddressTypeEnum addressType) {
        this.addressType = addressType;
    }

	public AddresLocationTypeEnum getLocationType() {
        return this.locationType;
    }

	public void setLocationType(AddresLocationTypeEnum locationType) {
        this.locationType = locationType;
    }

	public String getAddress() {
        return this.address;
    }

	public void setAddress(String address) {
        this.address = address;
    }

	public CountryEnum getCountry() {
        return this.country;
    }

	public void setCountry(CountryEnum country) {
        this.country = country;
    }

	public ProvinceEnum getProvince() {
        return this.province;
    }

	public void setProvince(ProvinceEnum province) {
        this.province = province;
    }

	public String getPostalCode() {
        return this.postalCode;
    }

	public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

	public String getPopulation() {
        return this.population;
    }

	public void setPopulation(String population) {
        this.population = population;
    }

	public String getAddresNumber() {
        return this.addresNumber;
    }

	public void setAddresNumber(String addresNumber) {
        this.addresNumber = addresNumber;
    }

	public Person getPerson() {
        return this.person;
    }

	public void setPerson(Person person) {
        this.person = person;
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof GeneralAddress)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        GeneralAddress rhs = (GeneralAddress) obj;
        return new EqualsBuilder().append(id, rhs.id).append(person, rhs.person).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(id).append(person).toHashCode();
    }

	private static final long serialVersionUID = 1L;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
