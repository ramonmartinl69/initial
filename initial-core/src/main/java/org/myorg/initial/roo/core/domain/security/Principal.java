package org.myorg.initial.roo.core.domain.security;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.myorg.initial.roo.core.domain.model.Person;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@Entity
@Table(name = "security_user")
@RooJavaBean
@RooToString
@RooSerializable
@RooEquals(excludeFields = { "id", "password", "repeatPassword", "enabled", "roles" })
@RooJpaEntity(identifierColumn = "id_user", table = "security_user", versionColumn = "OPT_LOCK")
//@RooJpaActiveRecord(identifierColumn = "id_user", table = "security_user", versionColumn = "OPT_LOCK", finders = { "findPrincipalsByRoles", "findPrincipalsByUserNameEquals", "findPrincipalsByUserNameLike", "findPrincipalsByUserNameIsNull", "findPrincipalsByUserNameIsNotNull", "findPrincipalsByEnabledNot", "findPrincipalsByActivationKeyEquals" })
public class Principal implements Serializable {

    /**
     */
    @NotNull
    @Size(min = 3, max = 50)
    @Pattern(regexp = ".+@.+\\.[a-z]+")
    private String userName;

    /**
     */
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    /**
     */
    @Size(min = 3, max = 100)
    private transient String repeatPassword;

    /**
     */
    private Boolean enabled;

    /**
     */
    @Size(min = 3, max = 100)
    private String activationKey;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AuthRole> roles = new HashSet<AuthRole>();

    /**
     */
    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person person;

	public String getUserName() {
        return this.userName;
    }

	public void setUserName(String userName) {
        this.userName = userName;
    }

	public String getPassword() {
        return this.password;
    }

	public void setPassword(String password) {
        this.password = password;
    }

	public Boolean getEnabled() {
        return this.enabled;
    }

	public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

	public String getActivationKey() {
        return this.activationKey;
    }

	public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

	public Set<AuthRole> getRoles() {
        return this.roles;
    }

	public void setRoles(Set<AuthRole> roles) {
        this.roles = roles;
    }

	public Person getPerson() {
        return this.person;
    }

	public void setPerson(Person person) {
        this.person = person;
    }

	private static final long serialVersionUID = 1L;

	public boolean equals(Object obj) {
        if (!(obj instanceof Principal)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Principal rhs = (Principal) obj;
        return new EqualsBuilder().append(activationKey, rhs.activationKey).append(person, rhs.person).append(userName, rhs.userName).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(activationKey).append(person).append(userName).toHashCode();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
