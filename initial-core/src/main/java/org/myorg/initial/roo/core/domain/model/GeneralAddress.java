package org.myorg.initial.roo.core.domain.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.myorg.initial.roo.core.domain.reference.AddressTypeEnum;
import org.myorg.initial.roo.core.domain.reference.AddresLocationTypeEnum;
import javax.validation.constraints.Size;
import org.myorg.initial.roo.core.domain.reference.CountryEnum;
import org.myorg.initial.roo.core.domain.reference.ProvinceEnum;
import javax.validation.constraints.Pattern;
import org.springframework.roo.addon.equals.RooEquals;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(identifierColumn = "id_address", inheritanceType = "SINGLE_TABLE", table = "address", versionColumn = "OPT_LOCK")
@RooSerializable
@RooEquals(excludeFields = { "addressType", "locationType", "address", "country", "province", "postalCode", "population", "addresNumber" })
public abstract class GeneralAddress {

    /**
     */
    @NotNull
    @Enumerated
    private AddressTypeEnum addressType;

    /**
     */
    @NotNull
    @Enumerated
    private AddresLocationTypeEnum locationType;

    /**
     */
    @NotNull
    @Size(min = 3, max = 250)
    private String address;

    /**
     */
    @Enumerated
    private CountryEnum country;

    /**
     */
    @NotNull
    @Enumerated
    private ProvinceEnum province;

    /**
     */
    @NotNull
    @Size(min = 3, max = 10)
    @Pattern(regexp = ".*\\d.*")
    private String postalCode;

    /**
     */
    @NotNull
    @Size(min = 3, max = 250)
    private String population;

    /**
     */
    @NotNull
    @Size(min = 1, max = 250)
    private String addresNumber;

    /**
     */
    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person person;
}
