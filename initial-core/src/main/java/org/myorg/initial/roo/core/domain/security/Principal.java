package org.myorg.initial.roo.core.domain.security;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import org.springframework.roo.addon.equals.RooEquals;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.myorg.initial.roo.core.domain.model.Person;

@RooJavaBean
@RooToString
@RooSerializable
@RooEquals(excludeFields = { "id", "password", "repeatPassword", "enabled", "roles" })
@RooJpaActiveRecord(identifierColumn = "id_user", table = "security_user", versionColumn = "OPT_LOCK", finders = { "findPrincipalsByUserNameEquals", "findPrincipalsByUserNameLike", "findPrincipalsByUserNameIsNull", "findPrincipalsByUserNameIsNotNull", "findPrincipalsByEnabledNot", "findPrincipalsByActivationKeyEquals" })
public class Principal {

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
}
