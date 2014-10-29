package org.myorg.initial.roo.core.domain.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Enumerated;

import org.myorg.initial.roo.core.domain.reference.CountryEnum;

import javax.validation.constraints.Pattern;
import javax.persistence.Lob;

import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;
import org.myorg.initial.roo.core.domain.reference.SemanticQuestionEnum;

import javax.persistence.OneToOne;

import org.myorg.initial.roo.core.domain.security.Principal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.roo.addon.equals.RooEquals;

@RooJavaBean
@RooToString
@RooSerializable
@RooEquals(excludeFields = { "id", "firstName", "lastName", "lastName2", "birthDate", "nationality", "photoFile", "phone", "question", "response", "alternateQuestion", "alternateResponse", "actKey", "principal", "addresses" })
//@RooJpaActiveRecord(identifierColumn = "id_user", table = "person", versionColumn = "OPT_LOCK", finders = { "findPeopleByNIFLike", "findPeopleByFirstNameLikeAndLastNameLike" })
@RooJpaEntity(identifierColumn = "id_user", table = "person", versionColumn = "OPT_LOCK")
public class Person {

    /**
     */
    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;

    /**
     */
    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;

    /**
     */
    @NotNull
    @Size(min = 1, max = 50)
    private String lastName2;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date birthDate;

    /**
     */
    @Enumerated
    private CountryEnum nationality;

    /**
     */
    @NotNull
    @Size(min = 9, max = 15)
    @Pattern(regexp = "([X-Z]{1}\\d{7}[A-Z]{1})|(\\d{8}[A-Z]{1})")
    private String nIF;

    /**
     */
    @RooUploadedFile(contentType = "image/jpeg")
    @Lob
    private byte[] photoFile;

    /**
     */
    @NotNull
    @Size(min = 3, max = 15)
    @Pattern(regexp = ".*\\d.*")
    private String phone;

    /**
     */
    @Enumerated
    private SemanticQuestionEnum question;

    /**
     */
    @Size(min = 3, max = 100)
    private String response;

    /**
     */
    @Size(min = 3, max = 100)
    private String alternateQuestion;

    /**
     */
    @Size(min = 3, max = 100)
    private String alternateResponse;

    /**
     */
    @OneToOne
    private Principal principal;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<GeneralAddress> addresses = new ArrayList<GeneralAddress>();
}
