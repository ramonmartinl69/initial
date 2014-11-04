package org.myorg.initial.roo.core.domain.model;import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Enumerated;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.myorg.initial.roo.core.domain.reference.CountryEnum;
import javax.validation.constraints.Pattern;
import javax.persistence.Lob;
import org.myorg.initial.roo.core.domain.reference.SemanticQuestionEnum;
import javax.persistence.OneToOne;
import org.myorg.initial.roo.core.domain.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
@Entity
@Table(name = "person")public class Person implements Serializable {

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
    @Lob
    private byte[] photoFile;

    /**
     */
    @NotNull
    @Size(min = 3, max = 15)
    @Pattern(regexp = "^[0-9]*$*")
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

	public String getFirstName() {
        return this.firstName;
    }

	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	public String getLastName() {
        return this.lastName;
    }

	public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public String getLastName2() {
        return this.lastName2;
    }

	public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

	public Date getBirthDate() {
        return this.birthDate;
    }

	public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

	public CountryEnum getNationality() {
        return this.nationality;
    }

	public void setNationality(CountryEnum nationality) {
        this.nationality = nationality;
    }

	public String getNIF() {
        return this.nIF;
    }

	public void setNIF(String nIF) {
        this.nIF = nIF;
    }

	public byte[] getPhotoFile() {
        return this.photoFile;
    }

	public void setPhotoFile(byte[] photoFile) {
        this.photoFile = photoFile;
    }

	public String getPhone() {
        return this.phone;
    }

	public void setPhone(String phone) {
        this.phone = phone;
    }

	public SemanticQuestionEnum getQuestion() {
        return this.question;
    }

	public void setQuestion(SemanticQuestionEnum question) {
        this.question = question;
    }

	public String getResponse() {
        return this.response;
    }

	public void setResponse(String response) {
        this.response = response;
    }

	public String getAlternateQuestion() {
        return this.alternateQuestion;
    }

	public void setAlternateQuestion(String alternateQuestion) {
        this.alternateQuestion = alternateQuestion;
    }

	public String getAlternateResponse() {
        return this.alternateResponse;
    }

	public void setAlternateResponse(String alternateResponse) {
        this.alternateResponse = alternateResponse;
    }

	public Principal getPrincipal() {
        return this.principal;
    }

	public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

	public List<GeneralAddress> getAddresses() {
        return this.addresses;
    }

	public void setAddresses(List<GeneralAddress> addresses) {
        this.addresses = addresses;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long id;

	@Version
    @Column(name = "OPT_LOCK")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	private static final long serialVersionUID = 1L;

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static Person fromJsonToPerson(String json) {
        return new JSONDeserializer<Person>()
        .use(null, Person.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Person> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Person> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Person> fromJsonArrayToPeople(String json) {
        return new JSONDeserializer<List<Person>>()
        .use("values", Person.class).deserialize(json);
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Person rhs = (Person) obj;
        return new EqualsBuilder().append(nIF, rhs.nIF).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(nIF).toHashCode();
    }
}
