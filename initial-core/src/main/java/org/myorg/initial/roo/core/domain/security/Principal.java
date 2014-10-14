package org.myorg.initial.roo.core.domain.security;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.myorg.initial.roo.core.domain.model.Person;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooSerializable
@RooEquals(excludeFields = { "id", "password", "repeatPassword", "enabled", "roles" })
@RooJpaEntity(identifierColumn = "id_user", table = "security_user", versionColumn = "OPT_LOCK")
//@RooJpaActiveRecord(identifierColumn = "id_user", table = "security_user", versionColumn = "OPT_LOCK", finders = { "findPrincipalsByRoles", "findPrincipalsByUserNameEquals", "findPrincipalsByUserNameLike", "findPrincipalsByUserNameIsNull", "findPrincipalsByUserNameIsNotNull", "findPrincipalsByEnabledNot", "findPrincipalsByActivationKeyEquals" })
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
