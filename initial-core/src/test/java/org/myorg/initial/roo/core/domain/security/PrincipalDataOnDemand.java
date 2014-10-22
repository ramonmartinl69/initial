package org.myorg.initial.roo.core.domain.security;
import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = Principal.class)
public class PrincipalDataOnDemand {

	public void setUserName(Principal obj, int index) {
        String userName = "userName" + index + "@myorg.org";
        if (userName.length() > 50) {
            userName = userName.substring(0, 50);
        }
        obj.setUserName(userName);
    }
}
