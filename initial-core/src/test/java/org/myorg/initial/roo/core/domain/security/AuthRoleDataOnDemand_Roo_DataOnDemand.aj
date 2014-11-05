// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.domain.security;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.myorg.initial.roo.core.domain.reference.SecurityRoleEnum;
import org.myorg.initial.roo.core.domain.security.AuthRole;
import org.myorg.initial.roo.core.domain.security.AuthRoleDataOnDemand;
import org.springframework.stereotype.Component;

privileged aspect AuthRoleDataOnDemand_Roo_DataOnDemand {
    
    declare @type: AuthRoleDataOnDemand: @Component;
    
    private Random AuthRoleDataOnDemand.rnd = new SecureRandom();
    
    private List<AuthRole> AuthRoleDataOnDemand.data;
    
    public AuthRole AuthRoleDataOnDemand.getNewTransientAuthRole(int index) {
        AuthRole obj = new AuthRole();
        setAuthority(obj, index);
        setRoleName(obj, index);
        return obj;
    }
    
    public void AuthRoleDataOnDemand.setAuthority(AuthRole obj, int index) {
        SecurityRoleEnum authority = SecurityRoleEnum.class.getEnumConstants()[0];
        obj.setAuthority(authority);
    }
    
    public void AuthRoleDataOnDemand.setRoleName(AuthRole obj, int index) {
        String roleName = "roleName_" + index;
        if (roleName.length() > 100) {
            roleName = roleName.substring(0, 100);
        }
        obj.setRoleName(roleName);
    }
    
    public AuthRole AuthRoleDataOnDemand.getSpecificAuthRole(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        AuthRole obj = data.get(index);
        Long id = obj.getId();
        return AuthRole.findAuthRole(id);
    }
    
    public AuthRole AuthRoleDataOnDemand.getRandomAuthRole() {
        init();
        AuthRole obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return AuthRole.findAuthRole(id);
    }
    
    public boolean AuthRoleDataOnDemand.modifyAuthRole(AuthRole obj) {
        return false;
    }
    
    public void AuthRoleDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = AuthRole.findAuthRoleEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'AuthRole' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<AuthRole>();
        for (int i = 0; i < 10; i++) {
            AuthRole obj = getNewTransientAuthRole(i);
            try {
                obj.persist();
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}