package org.myorg.initial.roo.core.domain.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class WorkAddress extends GeneralAddress {

    /**
     */
    @NotNull
    @Size(min = 3, max = 250)
    private String enterpriseName;

    /**
     */
    @NotNull
    @Size(min = 3, max = 250)
    private String postalAddress;
}
