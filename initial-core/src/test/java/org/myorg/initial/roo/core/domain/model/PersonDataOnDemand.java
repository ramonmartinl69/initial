package org.myorg.initial.roo.core.domain.model;
import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = Person.class)
public class PersonDataOnDemand {

	public void setNIF(Person obj, int index) {
        String nIF = "0858559" + String.valueOf(index).charAt(0) + "A";
        if (nIF.length() > 15) {
            nIF = nIF.substring(0, 15);
        }
        obj.setNIF(nIF);
    }

	public void setPhone(Person obj, int index) {
        String phone = "123" + index;
        if (phone.length() > 15) {
            phone = phone.substring(0, 15);
        }
        obj.setPhone(phone);
    }
}
