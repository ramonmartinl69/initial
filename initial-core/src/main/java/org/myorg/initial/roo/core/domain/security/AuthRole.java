package org.myorg.initial.roo.core.domain.security;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.myorg.initial.roo.core.domain.reference.SecurityRoleEnum;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooSerializable
@RooEquals(excludeFields = { "id", "roleName" })
@RooJpaActiveRecord(identifierColumn = "id_role", table = "security_role", versionColumn = "OPT_LOCK", finders = { "findAuthRolesByAuthority", "findAuthRolesByRoleNameLike" })
@RooJson
public class AuthRole {

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
}
