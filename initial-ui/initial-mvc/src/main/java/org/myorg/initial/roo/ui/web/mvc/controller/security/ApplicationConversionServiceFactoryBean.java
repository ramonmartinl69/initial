package org.myorg.initial.roo.ui.web.mvc.controller.security;

import org.myorg.initial.roo.core.domain.model.HomeAddress;
import org.myorg.initial.roo.core.domain.model.OtherAddress;
import org.myorg.initial.roo.core.domain.model.Person;
import org.myorg.initial.roo.core.domain.model.WorkAddress;
import org.myorg.initial.roo.core.domain.security.AuthRole;
import org.myorg.initial.roo.core.domain.security.Principal;
import org.myorg.initial.roo.core.service.model.PersonService;
import org.myorg.initial.roo.core.service.security.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

@Configurable
/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

	@Autowired
    PersonService personService;

	@Autowired
    PrincipalService principalService;

	public Converter<HomeAddress, String> getHomeAddressToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.myorg.initial.roo.core.domain.model.HomeAddress, java.lang.String>() {
            public String convert(HomeAddress homeAddress) {
                return new StringBuilder().append(homeAddress.getAddress()).append(' ').append(homeAddress.getPostalCode()).append(' ').append(homeAddress.getPopulation()).append(' ').append(homeAddress.getAddresNumber()).toString();
            }
        };
    }

	public Converter<Long, HomeAddress> getIdToHomeAddressConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, org.myorg.initial.roo.core.domain.model.HomeAddress>() {
            public org.myorg.initial.roo.core.domain.model.HomeAddress convert(java.lang.Long id) {
                return HomeAddress.findHomeAddress(id);
            }
        };
    }

	public Converter<String, HomeAddress> getStringToHomeAddressConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.myorg.initial.roo.core.domain.model.HomeAddress>() {
            public org.myorg.initial.roo.core.domain.model.HomeAddress convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), HomeAddress.class);
            }
        };
    }

	public Converter<OtherAddress, String> getOtherAddressToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.myorg.initial.roo.core.domain.model.OtherAddress, java.lang.String>() {
            public String convert(OtherAddress otherAddress) {
                return new StringBuilder().append(otherAddress.getAddress()).append(' ').append(otherAddress.getPostalCode()).append(' ').append(otherAddress.getPopulation()).append(' ').append(otherAddress.getAddresNumber()).toString();
            }
        };
    }

	public Converter<Long, OtherAddress> getIdToOtherAddressConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, org.myorg.initial.roo.core.domain.model.OtherAddress>() {
            public org.myorg.initial.roo.core.domain.model.OtherAddress convert(java.lang.Long id) {
                return OtherAddress.findOtherAddress(id);
            }
        };
    }

	public Converter<String, OtherAddress> getStringToOtherAddressConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.myorg.initial.roo.core.domain.model.OtherAddress>() {
            public org.myorg.initial.roo.core.domain.model.OtherAddress convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), OtherAddress.class);
            }
        };
    }

	public Converter<Person, String> getPersonToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.myorg.initial.roo.core.domain.model.Person, java.lang.String>() {
            public String convert(Person person) {
                return new StringBuilder().append(person.getFirstName()).append(' ').append(person.getLastName()).append(' ').append(person.getLastName2()).append(' ').append(person.getBirthDate()).toString();
            }
        };
    }

	public Converter<Long, Person> getIdToPersonConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, org.myorg.initial.roo.core.domain.model.Person>() {
            public org.myorg.initial.roo.core.domain.model.Person convert(java.lang.Long id) {
                return personService.findPerson(id);
            }
        };
    }

	public Converter<String, Person> getStringToPersonConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.myorg.initial.roo.core.domain.model.Person>() {
            public org.myorg.initial.roo.core.domain.model.Person convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Person.class);
            }
        };
    }

	public Converter<WorkAddress, String> getWorkAddressToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.myorg.initial.roo.core.domain.model.WorkAddress, java.lang.String>() {
            public String convert(WorkAddress workAddress) {
                return new StringBuilder().append(workAddress.getAddress()).append(' ').append(workAddress.getPostalCode()).append(' ').append(workAddress.getPopulation()).append(' ').append(workAddress.getAddresNumber()).toString();
            }
        };
    }

	public Converter<Long, WorkAddress> getIdToWorkAddressConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, org.myorg.initial.roo.core.domain.model.WorkAddress>() {
            public org.myorg.initial.roo.core.domain.model.WorkAddress convert(java.lang.Long id) {
                return WorkAddress.findWorkAddress(id);
            }
        };
    }

	public Converter<String, WorkAddress> getStringToWorkAddressConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.myorg.initial.roo.core.domain.model.WorkAddress>() {
            public org.myorg.initial.roo.core.domain.model.WorkAddress convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), WorkAddress.class);
            }
        };
    }

	public Converter<AuthRole, String> getAuthRoleToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.myorg.initial.roo.core.domain.security.AuthRole, java.lang.String>() {
            public String convert(AuthRole authRole) {
                return new StringBuilder().append(authRole.getRoleName()).toString();
            }
        };
    }

	public Converter<Long, AuthRole> getIdToAuthRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, org.myorg.initial.roo.core.domain.security.AuthRole>() {
            public org.myorg.initial.roo.core.domain.security.AuthRole convert(java.lang.Long id) {
                return AuthRole.findAuthRole(id);
            }
        };
    }

	public Converter<String, AuthRole> getStringToAuthRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.myorg.initial.roo.core.domain.security.AuthRole>() {
            public org.myorg.initial.roo.core.domain.security.AuthRole convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), AuthRole.class);
            }
        };
    }

	public Converter<Principal, String> getPrincipalToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.myorg.initial.roo.core.domain.security.Principal, java.lang.String>() {
            public String convert(Principal principal) {
                return new StringBuilder().append(principal.getUserName()).append(' ').append(principal.getPassword()).append(' ').append(principal.getActivationKey()).toString();
            }
        };
    }

	public Converter<Long, Principal> getIdToPrincipalConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, org.myorg.initial.roo.core.domain.security.Principal>() {
            public org.myorg.initial.roo.core.domain.security.Principal convert(java.lang.Long id) {
                return principalService.findPrincipal(id);
            }
        };
    }

	public Converter<String, Principal> getStringToPrincipalConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.myorg.initial.roo.core.domain.security.Principal>() {
            public org.myorg.initial.roo.core.domain.security.Principal convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Principal.class);
            }
        };
    }

	public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getHomeAddressToStringConverter());
        registry.addConverter(getIdToHomeAddressConverter());
        registry.addConverter(getStringToHomeAddressConverter());
        registry.addConverter(getOtherAddressToStringConverter());
        registry.addConverter(getIdToOtherAddressConverter());
        registry.addConverter(getStringToOtherAddressConverter());
        registry.addConverter(getPersonToStringConverter());
        registry.addConverter(getIdToPersonConverter());
        registry.addConverter(getStringToPersonConverter());
        registry.addConverter(getWorkAddressToStringConverter());
        registry.addConverter(getIdToWorkAddressConverter());
        registry.addConverter(getStringToWorkAddressConverter());
        registry.addConverter(getAuthRoleToStringConverter());
        registry.addConverter(getIdToAuthRoleConverter());
        registry.addConverter(getStringToAuthRoleConverter());
        registry.addConverter(getPrincipalToStringConverter());
        registry.addConverter(getIdToPrincipalConverter());
        registry.addConverter(getStringToPrincipalConverter());
    }

	public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
}
