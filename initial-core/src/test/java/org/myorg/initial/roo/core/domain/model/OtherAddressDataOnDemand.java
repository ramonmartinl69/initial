package org.myorg.initial.roo.core.domain.model;
import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = OtherAddress.class)
public class OtherAddressDataOnDemand {

	public void setPostalCode(OtherAddress obj, int index) {
        String postalCode = "123" + index;
        if (postalCode.length() > 10) {
            postalCode = postalCode.substring(0, 10);
        }
        obj.setPostalCode(postalCode);
    }
}
