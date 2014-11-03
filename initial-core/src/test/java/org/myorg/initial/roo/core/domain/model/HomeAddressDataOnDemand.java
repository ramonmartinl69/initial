package org.myorg.initial.roo.core.domain.model;
import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = HomeAddress.class)
public class HomeAddressDataOnDemand {

	public void setPostalCode(HomeAddress obj, int index) {
        String postalCode = "123" + index;
        if (postalCode.length() > 10) {
            postalCode = postalCode.substring(0, 10);
        }
        obj.setPostalCode(postalCode);
    }
}
