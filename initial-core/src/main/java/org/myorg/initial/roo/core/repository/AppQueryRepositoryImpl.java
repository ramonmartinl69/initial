package org.myorg.initial.roo.core.repository;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.myorg.initial.roo.core.domain.model.Person;
import org.myorg.initial.roo.core.domain.security.AuthRole;
import org.myorg.initial.roo.core.domain.security.Principal;
import org.springframework.stereotype.Repository;

@Repository
public class AppQueryRepositoryImpl implements AppQueryRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public static final List<String> PrincipalFieldNames4OrderClauseFilter = java.util.Arrays.asList("userName", "password", "repeatPassword", "enabled", "activationKey", "roles", "person");
    public static final List<String> PersonFieldNames4OrderClauseFilter = java.util.Arrays.asList("firstName", "lastName", "lastName2", "birthDate", "nationality", "nIF", "photoFile", "phone", "question", "response", "alternateQuestion", "alternateResponse", "principal", "addresses");

	
	public Long countFindPrincipalsByActivationKeyEquals(String activationKey) {
        if (activationKey == null || activationKey.length() == 0) throw new IllegalArgumentException("The activationKey argument is required");
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Principal AS o WHERE o.activationKey = :activationKey", Long.class);
        q.setParameter("activationKey", activationKey);
        return ((Long) q.getSingleResult());
    }
    
    public Long countFindPrincipalsByEnabledNot(Boolean enabled) {
        if (enabled == null) throw new IllegalArgumentException("The enabled argument is required");
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Principal AS o WHERE o.enabled IS NOT :enabled", Long.class);
        q.setParameter("enabled", enabled);
        return ((Long) q.getSingleResult());
    }
    
    public Long countFindPrincipalsByRoles(Set<AuthRole> roles) {
        if (roles == null) throw new IllegalArgumentException("The roles argument is required");
        StringBuilder queryBuilder = new StringBuilder("SELECT COUNT(o) FROM Principal AS o WHERE");
        for (int i = 0; i < roles.size(); i++) {
            if (i > 0) queryBuilder.append(" AND");
            queryBuilder.append(" :roles_item").append(i).append(" MEMBER OF o.roles");
        }
        TypedQuery q = em.createQuery(queryBuilder.toString(), Long.class);
        int rolesIndex = 0;
        for (AuthRole _authrole: roles) {
            q.setParameter("roles_item" + rolesIndex++, _authrole);
        }
        return ((Long) q.getSingleResult());
    }
    
    public Long countFindPrincipalsByUserNameEquals(String userName) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Principal AS o WHERE o.userName = :userName", Long.class);
        q.setParameter("userName", userName);
        return ((Long) q.getSingleResult());
    }
    
    public Long countFindPrincipalsByUserNameIsNotNull() {
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Principal AS o WHERE o.userName IS NOT NULL", Long.class);
        return ((Long) q.getSingleResult());
    }
    
    public Long countFindPrincipalsByUserNameIsNull() {
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Principal AS o WHERE o.userName IS NULL", Long.class);
        return ((Long) q.getSingleResult());
    }
    
    public Long countFindPrincipalsByUserNameLike(String userName) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        userName = userName.replace('*', '%');
        if (userName.charAt(0) != '%') {
            userName = "%" + userName;
        }
        if (userName.charAt(userName.length() - 1) != '%') {
            userName = userName + "%";
        }
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Principal AS o WHERE LOWER(o.userName) LIKE LOWER(:userName)", Long.class);
        q.setParameter("userName", userName);
        return ((Long) q.getSingleResult());
    }
    
    public TypedQuery<Principal> findPrincipalsByActivationKeyEquals(String activationKey) {
        if (activationKey == null || activationKey.length() == 0) throw new IllegalArgumentException("The activationKey argument is required");
        TypedQuery<Principal> q = em.createQuery("SELECT o FROM Principal AS o WHERE o.activationKey = :activationKey", Principal.class);
        q.setParameter("activationKey", activationKey);
        return q;
    }
    
    public TypedQuery<Principal> findPrincipalsByActivationKeyEquals(String activationKey, String sortFieldName, String sortOrder) {
        if (activationKey == null || activationKey.length() == 0) throw new IllegalArgumentException("The activationKey argument is required");
        String jpaQuery = "SELECT o FROM Principal AS o WHERE o.activationKey = :activationKey";
        if (PrincipalFieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Principal> q = em.createQuery(jpaQuery, Principal.class);
        q.setParameter("activationKey", activationKey);
        return q;
    }
    
    public TypedQuery<Principal> findPrincipalsByEnabledNot(Boolean enabled) {
        if (enabled == null) throw new IllegalArgumentException("The enabled argument is required");
        TypedQuery<Principal> q = em.createQuery("SELECT o FROM Principal AS o WHERE o.enabled IS NOT :enabled", Principal.class);
        q.setParameter("enabled", enabled);
        return q;
    }
    
    public TypedQuery<Principal> findPrincipalsByEnabledNot(Boolean enabled, String sortFieldName, String sortOrder) {
        if (enabled == null) throw new IllegalArgumentException("The enabled argument is required");
        String jpaQuery = "SELECT o FROM Principal AS o WHERE o.enabled IS NOT :enabled";
        if (PrincipalFieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Principal> q = em.createQuery(jpaQuery, Principal.class);
        q.setParameter("enabled", enabled);
        return q;
    }
    
    public TypedQuery<Principal> findPrincipalsByRoles(Set<AuthRole> roles) {
        if (roles == null) throw new IllegalArgumentException("The roles argument is required");
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM Principal AS o WHERE");
        for (int i = 0; i < roles.size(); i++) {
            if (i > 0) queryBuilder.append(" AND");
            queryBuilder.append(" :roles_item").append(i).append(" MEMBER OF o.roles");
        }
        TypedQuery<Principal> q = em.createQuery(queryBuilder.toString(), Principal.class);
        int rolesIndex = 0;
        for (AuthRole _authrole: roles) {
            q.setParameter("roles_item" + rolesIndex++, _authrole);
        }
        return q;
    }
    
    public TypedQuery<Principal> findPrincipalsByRoles(Set<AuthRole> roles, String sortFieldName, String sortOrder) {
        if (roles == null) throw new IllegalArgumentException("The roles argument is required");
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM Principal AS o WHERE");
        for (int i = 0; i < roles.size(); i++) {
            if (i > 0) queryBuilder.append(" AND");
            queryBuilder.append(" :roles_item").append(i).append(" MEMBER OF o.roles");
        }
        if (PrincipalFieldNames4OrderClauseFilter.contains(sortFieldName)) {
        	queryBuilder.append(" ORDER BY " + sortFieldName);
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                queryBuilder.append(" " + sortOrder);
            }
        }
        TypedQuery<Principal> q = em.createQuery(queryBuilder.toString(), Principal.class);
        int rolesIndex = 0;
        for (AuthRole _authrole: roles) {
            q.setParameter("roles_item" + rolesIndex++, _authrole);
        }
        return q;
    }
    
    public TypedQuery<Principal> findPrincipalsByUserNameEquals(String userName) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        TypedQuery<Principal> q = em.createQuery("SELECT o FROM Principal AS o WHERE o.userName = :userName", Principal.class);
        q.setParameter("userName", userName);
        return q;
    }
    
    public TypedQuery<Principal> findPrincipalsByUserNameEquals(String userName, String sortFieldName, String sortOrder) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        String jpaQuery = "SELECT o FROM Principal AS o WHERE o.userName = :userName";
        if (PrincipalFieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Principal> q = em.createQuery(jpaQuery, Principal.class);
        q.setParameter("userName", userName);
        return q;
    }
    
    public TypedQuery<Principal> findPrincipalsByUserNameIsNotNull() {
        TypedQuery<Principal> q = em.createQuery("SELECT o FROM Principal AS o WHERE o.userName IS NOT NULL", Principal.class);
        return q;
    }
    
    public TypedQuery<Principal> findPrincipalsByUserNameIsNotNull(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Principal AS o WHERE o.userName IS NOT NULL";
        if (PrincipalFieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Principal> q = em.createQuery(jpaQuery, Principal.class);
        return q;
    }
    
    public TypedQuery<Principal> findPrincipalsByUserNameIsNull() {
        TypedQuery<Principal> q = em.createQuery("SELECT o FROM Principal AS o WHERE o.userName IS NULL", Principal.class);
        return q;
    }
    
    public TypedQuery<Principal> findPrincipalsByUserNameIsNull(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Principal AS o WHERE o.userName IS NULL";
        if (PrincipalFieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Principal> q = em.createQuery(jpaQuery, Principal.class);
        return q;
    }
    
    public TypedQuery<Principal> findPrincipalsByUserNameLike(String userName) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        userName = userName.replace('*', '%');
        if (userName.charAt(0) != '%') {
            userName = "%" + userName;
        }
        if (userName.charAt(userName.length() - 1) != '%') {
            userName = userName + "%";
        }
        TypedQuery<Principal> q = em.createQuery("SELECT o FROM Principal AS o WHERE LOWER(o.userName) LIKE LOWER(:userName)", Principal.class);
        q.setParameter("userName", userName);
        return q;
    }
    
    public TypedQuery<Principal> findPrincipalsByUserNameLike(String userName, String sortFieldName, String sortOrder) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        userName = userName.replace('*', '%');
        if (userName.charAt(0) != '%') {
            userName = "%" + userName;
        }
        if (userName.charAt(userName.length() - 1) != '%') {
            userName = userName + "%";
        }
        String jpaQuery = "SELECT o FROM Principal AS o WHERE LOWER(o.userName) LIKE LOWER(:userName)";
        if (PrincipalFieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Principal> q = em.createQuery(jpaQuery, Principal.class);
        q.setParameter("userName", userName);
        return q;
    }
    
    public Long countFindPeopleByFirstNameLikeAndLastNameLike(String firstName, String lastName) {
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        firstName = firstName.replace('*', '%');
        if (firstName.charAt(0) != '%') {
            firstName = "%" + firstName;
        }
        if (firstName.charAt(firstName.length() - 1) != '%') {
            firstName = firstName + "%";
        }
        if (lastName == null || lastName.length() == 0) throw new IllegalArgumentException("The lastName argument is required");
        lastName = lastName.replace('*', '%');
        if (lastName.charAt(0) != '%') {
            lastName = "%" + lastName;
        }
        if (lastName.charAt(lastName.length() - 1) != '%') {
            lastName = lastName + "%";
        }
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Person AS o WHERE LOWER(o.firstName) LIKE LOWER(:firstName)  AND LOWER(o.lastName) LIKE LOWER(:lastName)", Long.class);
        q.setParameter("firstName", firstName);
        q.setParameter("lastName", lastName);
        return ((Long) q.getSingleResult());
    }
    
    public Long countFindPeopleByNIFLike(String nIF) {
        if (nIF == null || nIF.length() == 0) throw new IllegalArgumentException("The nIF argument is required");
        nIF = nIF.replace('*', '%');
        if (nIF.charAt(0) != '%') {
            nIF = "%" + nIF;
        }
        if (nIF.charAt(nIF.length() - 1) != '%') {
            nIF = nIF + "%";
        }
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Person AS o WHERE LOWER(o.nIF) LIKE LOWER(:nIF)", Long.class);
        q.setParameter("nIF", nIF);
        return ((Long) q.getSingleResult());
    }
    
    public TypedQuery<Person> findPeopleByFirstNameLikeAndLastNameLike(String firstName, String lastName) {
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        firstName = firstName.replace('*', '%');
        if (firstName.charAt(0) != '%') {
            firstName = "%" + firstName;
        }
        if (firstName.charAt(firstName.length() - 1) != '%') {
            firstName = firstName + "%";
        }
        if (lastName == null || lastName.length() == 0) throw new IllegalArgumentException("The lastName argument is required");
        lastName = lastName.replace('*', '%');
        if (lastName.charAt(0) != '%') {
            lastName = "%" + lastName;
        }
        if (lastName.charAt(lastName.length() - 1) != '%') {
            lastName = lastName + "%";
        }
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE LOWER(o.firstName) LIKE LOWER(:firstName)  AND LOWER(o.lastName) LIKE LOWER(:lastName)", Person.class);
        q.setParameter("firstName", firstName);
        q.setParameter("lastName", lastName);
        return q;
    }
    
    public TypedQuery<Person> findPeopleByFirstNameLikeAndLastNameLike(String firstName, String lastName, String sortFieldName, String sortOrder) {
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        firstName = firstName.replace('*', '%');
        if (firstName.charAt(0) != '%') {
            firstName = "%" + firstName;
        }
        if (firstName.charAt(firstName.length() - 1) != '%') {
            firstName = firstName + "%";
        }
        if (lastName == null || lastName.length() == 0) throw new IllegalArgumentException("The lastName argument is required");
        lastName = lastName.replace('*', '%');
        if (lastName.charAt(0) != '%') {
            lastName = "%" + lastName;
        }
        if (lastName.charAt(lastName.length() - 1) != '%') {
            lastName = lastName + "%";
        }
        String jpaQuery = "SELECT o FROM Person AS o WHERE LOWER(o.firstName) LIKE LOWER(:firstName)  AND LOWER(o.lastName) LIKE LOWER(:lastName)";
        if (PersonFieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Person> q = em.createQuery(jpaQuery, Person.class);
        q.setParameter("firstName", firstName);
        q.setParameter("lastName", lastName);
        return q;
    }
    
    public TypedQuery<Person> findPeopleByNIFLike(String nIF) {
        if (nIF == null || nIF.length() == 0) throw new IllegalArgumentException("The nIF argument is required");
        nIF = nIF.replace('*', '%');
        if (nIF.charAt(0) != '%') {
            nIF = "%" + nIF;
        }
        if (nIF.charAt(nIF.length() - 1) != '%') {
            nIF = nIF + "%";
        }
        TypedQuery<Person> q = em.createQuery("SELECT o FROM Person AS o WHERE LOWER(o.nIF) LIKE LOWER(:nIF)", Person.class);
        q.setParameter("nIF", nIF);
        return q;
    }
    
    public TypedQuery<Person> findPeopleByNIFLike(String nIF, String sortFieldName, String sortOrder) {
        if (nIF == null || nIF.length() == 0) throw new IllegalArgumentException("The nIF argument is required");
        nIF = nIF.replace('*', '%');
        if (nIF.charAt(0) != '%') {
            nIF = "%" + nIF;
        }
        if (nIF.charAt(nIF.length() - 1) != '%') {
            nIF = nIF + "%";
        }
        String jpaQuery = "SELECT o FROM Person AS o WHERE LOWER(o.nIF) LIKE LOWER(:nIF)";
        if (PersonFieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Person> q = em.createQuery(jpaQuery, Person.class);
        q.setParameter("nIF", nIF);
        return q;
    }
}
