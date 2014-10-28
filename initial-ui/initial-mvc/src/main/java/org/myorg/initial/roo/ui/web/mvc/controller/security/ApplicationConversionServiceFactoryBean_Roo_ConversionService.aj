// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.ui.web.mvc.controller.security;

import org.myorg.initial.roo.core.domain.model.HomeAddress;
import org.myorg.initial.roo.core.domain.model.OtherAddress;
import org.myorg.initial.roo.core.domain.model.WorkAddress;
import org.myorg.initial.roo.core.domain.security.AuthRole;
import org.myorg.initial.roo.core.domain.security.Principal;
import org.myorg.initial.roo.ui.web.mvc.controller.security.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    public Converter<HomeAddress, String> ApplicationConversionServiceFactoryBean.getHomeAddressToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.myorg.initial.roo.core.domain.model.HomeAddress, java.lang.String>() {
            public String convert(HomeAddress homeAddress) {
                return new StringBuilder().append(homeAddress.getAddress()).append(' ').append(homeAddress.getPostalCode()).append(' ').append(homeAddress.getPopulation()).append(' ').append(homeAddress.getAddresNumber()).toString();
            }
        };
    }
    
    public Converter<Long, HomeAddress> ApplicationConversionServiceFactoryBean.getIdToHomeAddressConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, org.myorg.initial.roo.core.domain.model.HomeAddress>() {
            public org.myorg.initial.roo.core.domain.model.HomeAddress convert(java.lang.Long id) {
                return HomeAddress.findHomeAddress(id);
            }
        };
    }
    
    public Converter<String, HomeAddress> ApplicationConversionServiceFactoryBean.getStringToHomeAddressConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.myorg.initial.roo.core.domain.model.HomeAddress>() {
            public org.myorg.initial.roo.core.domain.model.HomeAddress convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), HomeAddress.class);
            }
        };
    }
    
    public Converter<OtherAddress, String> ApplicationConversionServiceFactoryBean.getOtherAddressToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.myorg.initial.roo.core.domain.model.OtherAddress, java.lang.String>() {
            public String convert(OtherAddress otherAddress) {
                return new StringBuilder().append(otherAddress.getAddress()).append(' ').append(otherAddress.getPostalCode()).append(' ').append(otherAddress.getPopulation()).append(' ').append(otherAddress.getAddresNumber()).toString();
            }
        };
    }
    
    public Converter<Long, OtherAddress> ApplicationConversionServiceFactoryBean.getIdToOtherAddressConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, org.myorg.initial.roo.core.domain.model.OtherAddress>() {
            public org.myorg.initial.roo.core.domain.model.OtherAddress convert(java.lang.Long id) {
                return OtherAddress.findOtherAddress(id);
            }
        };
    }
    
    public Converter<String, OtherAddress> ApplicationConversionServiceFactoryBean.getStringToOtherAddressConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.myorg.initial.roo.core.domain.model.OtherAddress>() {
            public org.myorg.initial.roo.core.domain.model.OtherAddress convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), OtherAddress.class);
            }
        };
    }
    
    public Converter<WorkAddress, String> ApplicationConversionServiceFactoryBean.getWorkAddressToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.myorg.initial.roo.core.domain.model.WorkAddress, java.lang.String>() {
            public String convert(WorkAddress workAddress) {
                return new StringBuilder().append(workAddress.getAddress()).append(' ').append(workAddress.getPostalCode()).append(' ').append(workAddress.getPopulation()).append(' ').append(workAddress.getAddresNumber()).toString();
            }
        };
    }
    
    public Converter<Long, WorkAddress> ApplicationConversionServiceFactoryBean.getIdToWorkAddressConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, org.myorg.initial.roo.core.domain.model.WorkAddress>() {
            public org.myorg.initial.roo.core.domain.model.WorkAddress convert(java.lang.Long id) {
                return WorkAddress.findWorkAddress(id);
            }
        };
    }
    
    public Converter<String, WorkAddress> ApplicationConversionServiceFactoryBean.getStringToWorkAddressConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.myorg.initial.roo.core.domain.model.WorkAddress>() {
            public org.myorg.initial.roo.core.domain.model.WorkAddress convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), WorkAddress.class);
            }
        };
    }
    
    public Converter<AuthRole, String> ApplicationConversionServiceFactoryBean.getAuthRoleToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.myorg.initial.roo.core.domain.security.AuthRole, java.lang.String>() {
            public String convert(AuthRole authRole) {
                return new StringBuilder().append(authRole.getRoleName()).toString();
            }
        };
    }
    
    public Converter<Long, AuthRole> ApplicationConversionServiceFactoryBean.getIdToAuthRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, org.myorg.initial.roo.core.domain.security.AuthRole>() {
            public org.myorg.initial.roo.core.domain.security.AuthRole convert(java.lang.Long id) {
                return AuthRole.findAuthRole(id);
            }
        };
    }
    
    public Converter<String, AuthRole> ApplicationConversionServiceFactoryBean.getStringToAuthRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.myorg.initial.roo.core.domain.security.AuthRole>() {
            public org.myorg.initial.roo.core.domain.security.AuthRole convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), AuthRole.class);
            }
        };
    }
    
    public Converter<Principal, String> ApplicationConversionServiceFactoryBean.getPrincipalToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.myorg.initial.roo.core.domain.security.Principal, java.lang.String>() {
            public String convert(Principal principal) {
                return new StringBuilder().append(principal.getUserName()).append(' ').append(principal.getPassword()).append(' ').append(principal.getActivationKey()).toString();
            }
        };
    }
    
    public Converter<Long, Principal> ApplicationConversionServiceFactoryBean.getIdToPrincipalConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, org.myorg.initial.roo.core.domain.security.Principal>() {
            public org.myorg.initial.roo.core.domain.security.Principal convert(java.lang.Long id) {
                return Principal.findPrincipal(id);
            }
        };
    }
    
    public Converter<String, Principal> ApplicationConversionServiceFactoryBean.getStringToPrincipalConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.myorg.initial.roo.core.domain.security.Principal>() {
            public org.myorg.initial.roo.core.domain.security.Principal convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Principal.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getHomeAddressToStringConverter());
        registry.addConverter(getIdToHomeAddressConverter());
        registry.addConverter(getStringToHomeAddressConverter());
        registry.addConverter(getOtherAddressToStringConverter());
        registry.addConverter(getIdToOtherAddressConverter());
        registry.addConverter(getStringToOtherAddressConverter());
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
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
