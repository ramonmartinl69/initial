// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.domain.security;

import org.myorg.initial.roo.core.domain.reference.SecurityRoleEnum;
import org.myorg.initial.roo.core.domain.security.AuthRole;

privileged aspect AuthRole_Roo_JavaBean {
    
    public SecurityRoleEnum AuthRole.getAuthority() {
        return this.authority;
    }
    
    public void AuthRole.setAuthority(SecurityRoleEnum authority) {
        this.authority = authority;
    }
    
    public String AuthRole.getRoleName() {
        return this.roleName;
    }
    
    public void AuthRole.setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
}
