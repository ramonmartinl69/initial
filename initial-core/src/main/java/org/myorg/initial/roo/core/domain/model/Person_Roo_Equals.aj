// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.myorg.initial.roo.core.domain.model.Person;

privileged aspect Person_Roo_Equals {
    
    public boolean Person.equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Person rhs = (Person) obj;
        return new EqualsBuilder().append(nIF, rhs.nIF).isEquals();
    }
    
    public int Person.hashCode() {
        return new HashCodeBuilder().append(nIF).toHashCode();
    }
    
}
