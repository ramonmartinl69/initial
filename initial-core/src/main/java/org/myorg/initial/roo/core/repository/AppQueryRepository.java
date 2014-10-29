package org.myorg.initial.roo.core.repository;

import java.util.Set;

import javax.persistence.TypedQuery;

import org.myorg.initial.roo.core.domain.model.Person;
import org.myorg.initial.roo.core.domain.security.AuthRole;
import org.myorg.initial.roo.core.domain.security.Principal;

public interface AppQueryRepository {
	
	public Long countFindPrincipalsByActivationKeyEquals(String activationKey);
    
    public Long countFindPrincipalsByEnabledNot(Boolean enabled);
    
    public Long countFindPrincipalsByRoles(Set<AuthRole> roles);
    
    public Long countFindPrincipalsByUserNameEquals(String userName);
    
    public Long countFindPrincipalsByUserNameIsNotNull();
    
    public Long countFindPrincipalsByUserNameIsNull();
    
    public Long countFindPrincipalsByUserNameLike(String userName);
    
    public TypedQuery<Principal> findPrincipalsByActivationKeyEquals(String activationKey);
    
    public TypedQuery<Principal> findPrincipalsByActivationKeyEquals(String activationKey, String sortFieldName, String sortOrder);
    
    public TypedQuery<Principal> findPrincipalsByEnabledNot(Boolean enabled);
    
    public TypedQuery<Principal> findPrincipalsByEnabledNot(Boolean enabled, String sortFieldName, String sortOrder);
    
    public TypedQuery<Principal> findPrincipalsByRoles(Set<AuthRole> roles);
    
    public TypedQuery<Principal> findPrincipalsByRoles(Set<AuthRole> roles, String sortFieldName, String sortOrder);
    
    public TypedQuery<Principal> findPrincipalsByUserNameEquals(String userName);
    
    public TypedQuery<Principal> findPrincipalsByUserNameEquals(String userName, String sortFieldName, String sortOrder);
    
    public TypedQuery<Principal> findPrincipalsByUserNameIsNotNull();
    
    public TypedQuery<Principal> findPrincipalsByUserNameIsNotNull(String sortFieldName, String sortOrder);
    
    public TypedQuery<Principal> findPrincipalsByUserNameIsNull();
    
    public TypedQuery<Principal> findPrincipalsByUserNameIsNull(String sortFieldName, String sortOrder);
    
    public TypedQuery<Principal> findPrincipalsByUserNameLike(String userName);
    
    public TypedQuery<Principal> findPrincipalsByUserNameLike(String userName, String sortFieldName, String sortOrder);
    
    public Long countFindPeopleByFirstNameLikeAndLastNameLike(String firstName, String lastName);
    
    public Long countFindPeopleByNIFLike(String nIF);
    
    public TypedQuery<Person> findPeopleByFirstNameLikeAndLastNameLike(String firstName, String lastName);
    
    public TypedQuery<Person> findPeopleByFirstNameLikeAndLastNameLike(String firstName, String lastName, String sortFieldName, String sortOrder);
    
    public TypedQuery<Person> findPeopleByNIFLike(String nIF);
    
    public TypedQuery<Person> findPeopleByNIFLike(String nIF, String sortFieldName, String sortOrder);
}
