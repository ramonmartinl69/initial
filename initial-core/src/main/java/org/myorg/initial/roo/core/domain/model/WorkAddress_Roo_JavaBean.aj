// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.domain.model;

import org.myorg.initial.roo.core.domain.model.WorkAddress;

privileged aspect WorkAddress_Roo_JavaBean {
    
    public String WorkAddress.getEnterpriseName() {
        return this.enterpriseName;
    }
    
    public void WorkAddress.setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
    
    public String WorkAddress.getPostalAddress() {
        return this.postalAddress;
    }
    
    public void WorkAddress.setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }
    
}
