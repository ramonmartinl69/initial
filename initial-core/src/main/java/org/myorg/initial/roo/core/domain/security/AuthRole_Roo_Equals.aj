// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.domain.security;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.myorg.initial.roo.core.domain.security.AuthRole;

privileged aspect AuthRole_Roo_Equals {
    
    public boolean AuthRole.equals(Object obj) {
        if (!(obj instanceof AuthRole)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        AuthRole rhs = (AuthRole) obj;
        return new EqualsBuilder().append(authority, rhs.authority).isEquals();
    }
    
    public int AuthRole.hashCode() {
        return new HashCodeBuilder().append(authority).toHashCode();
    }
    
}
