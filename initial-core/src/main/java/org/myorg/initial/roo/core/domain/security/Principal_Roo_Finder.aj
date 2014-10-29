// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.domain.security;

import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.myorg.initial.roo.core.domain.security.AuthRole;
import org.myorg.initial.roo.core.domain.security.Principal;

privileged aspect Principal_Roo_Finder {
    
    public static Long Principal.countFindPrincipalsByActivationKeyEquals(String activationKey) {
        if (activationKey == null || activationKey.length() == 0) throw new IllegalArgumentException("The activationKey argument is required");
        EntityManager em = Principal.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Principal AS o WHERE o.activationKey = :activationKey", Long.class);
        q.setParameter("activationKey", activationKey);
        return ((Long) q.getSingleResult());
    }
    
    public static Long Principal.countFindPrincipalsByEnabledNot(Boolean enabled) {
        if (enabled == null) throw new IllegalArgumentException("The enabled argument is required");
        EntityManager em = Principal.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Principal AS o WHERE o.enabled IS NOT :enabled", Long.class);
        q.setParameter("enabled", enabled);
        return ((Long) q.getSingleResult());
    }
    
    public static Long Principal.countFindPrincipalsByRoles(Set<AuthRole> roles) {
        if (roles == null) throw new IllegalArgumentException("The roles argument is required");
        EntityManager em = Principal.entityManager();
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
    
    public static Long Principal.countFindPrincipalsByUserNameEquals(String userName) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        EntityManager em = Principal.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Principal AS o WHERE o.userName = :userName", Long.class);
        q.setParameter("userName", userName);
        return ((Long) q.getSingleResult());
    }
    
    public static Long Principal.countFindPrincipalsByUserNameIsNotNull() {
        EntityManager em = Principal.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Principal AS o WHERE o.userName IS NOT NULL", Long.class);
        return ((Long) q.getSingleResult());
    }
    
    public static Long Principal.countFindPrincipalsByUserNameIsNull() {
        EntityManager em = Principal.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Principal AS o WHERE o.userName IS NULL", Long.class);
        return ((Long) q.getSingleResult());
    }
    
    public static Long Principal.countFindPrincipalsByUserNameLike(String userName) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        userName = userName.replace('*', '%');
        if (userName.charAt(0) != '%') {
            userName = "%" + userName;
        }
        if (userName.charAt(userName.length() - 1) != '%') {
            userName = userName + "%";
        }
        EntityManager em = Principal.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Principal AS o WHERE LOWER(o.userName) LIKE LOWER(:userName)", Long.class);
        q.setParameter("userName", userName);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<Principal> Principal.findPrincipalsByActivationKeyEquals(String activationKey) {
        if (activationKey == null || activationKey.length() == 0) throw new IllegalArgumentException("The activationKey argument is required");
        EntityManager em = Principal.entityManager();
        TypedQuery<Principal> q = em.createQuery("SELECT o FROM Principal AS o WHERE o.activationKey = :activationKey", Principal.class);
        q.setParameter("activationKey", activationKey);
        return q;
    }
    
    public static TypedQuery<Principal> Principal.findPrincipalsByActivationKeyEquals(String activationKey, String sortFieldName, String sortOrder) {
        if (activationKey == null || activationKey.length() == 0) throw new IllegalArgumentException("The activationKey argument is required");
        EntityManager em = Principal.entityManager();
        String jpaQuery = "SELECT o FROM Principal AS o WHERE o.activationKey = :activationKey";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Principal> q = em.createQuery(jpaQuery, Principal.class);
        q.setParameter("activationKey", activationKey);
        return q;
    }
    
    public static TypedQuery<Principal> Principal.findPrincipalsByEnabledNot(Boolean enabled) {
        if (enabled == null) throw new IllegalArgumentException("The enabled argument is required");
        EntityManager em = Principal.entityManager();
        TypedQuery<Principal> q = em.createQuery("SELECT o FROM Principal AS o WHERE o.enabled IS NOT :enabled", Principal.class);
        q.setParameter("enabled", enabled);
        return q;
    }
    
    public static TypedQuery<Principal> Principal.findPrincipalsByEnabledNot(Boolean enabled, String sortFieldName, String sortOrder) {
        if (enabled == null) throw new IllegalArgumentException("The enabled argument is required");
        EntityManager em = Principal.entityManager();
        String jpaQuery = "SELECT o FROM Principal AS o WHERE o.enabled IS NOT :enabled";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Principal> q = em.createQuery(jpaQuery, Principal.class);
        q.setParameter("enabled", enabled);
        return q;
    }
    
    public static TypedQuery<Principal> Principal.findPrincipalsByRoles(Set<AuthRole> roles) {
        if (roles == null) throw new IllegalArgumentException("The roles argument is required");
        EntityManager em = Principal.entityManager();
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
    
    public static TypedQuery<Principal> Principal.findPrincipalsByRoles(Set<AuthRole> roles, String sortFieldName, String sortOrder) {
        if (roles == null) throw new IllegalArgumentException("The roles argument is required");
        EntityManager em = Principal.entityManager();
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM Principal AS o WHERE");
        for (int i = 0; i < roles.size(); i++) {
            if (i > 0) queryBuilder.append(" AND");
            queryBuilder.append(" :roles_item").append(i).append(" MEMBER OF o.roles");
        }
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
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
    
    public static TypedQuery<Principal> Principal.findPrincipalsByUserNameEquals(String userName) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        EntityManager em = Principal.entityManager();
        TypedQuery<Principal> q = em.createQuery("SELECT o FROM Principal AS o WHERE o.userName = :userName", Principal.class);
        q.setParameter("userName", userName);
        return q;
    }
    
    public static TypedQuery<Principal> Principal.findPrincipalsByUserNameEquals(String userName, String sortFieldName, String sortOrder) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        EntityManager em = Principal.entityManager();
        String jpaQuery = "SELECT o FROM Principal AS o WHERE o.userName = :userName";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Principal> q = em.createQuery(jpaQuery, Principal.class);
        q.setParameter("userName", userName);
        return q;
    }
    
    public static TypedQuery<Principal> Principal.findPrincipalsByUserNameIsNotNull() {
        EntityManager em = Principal.entityManager();
        TypedQuery<Principal> q = em.createQuery("SELECT o FROM Principal AS o WHERE o.userName IS NOT NULL", Principal.class);
        return q;
    }
    
    public static TypedQuery<Principal> Principal.findPrincipalsByUserNameIsNotNull(String sortFieldName, String sortOrder) {
        EntityManager em = Principal.entityManager();
        String jpaQuery = "SELECT o FROM Principal AS o WHERE o.userName IS NOT NULL";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Principal> q = em.createQuery(jpaQuery, Principal.class);
        return q;
    }
    
    public static TypedQuery<Principal> Principal.findPrincipalsByUserNameIsNull() {
        EntityManager em = Principal.entityManager();
        TypedQuery<Principal> q = em.createQuery("SELECT o FROM Principal AS o WHERE o.userName IS NULL", Principal.class);
        return q;
    }
    
    public static TypedQuery<Principal> Principal.findPrincipalsByUserNameIsNull(String sortFieldName, String sortOrder) {
        EntityManager em = Principal.entityManager();
        String jpaQuery = "SELECT o FROM Principal AS o WHERE o.userName IS NULL";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Principal> q = em.createQuery(jpaQuery, Principal.class);
        return q;
    }
    
    public static TypedQuery<Principal> Principal.findPrincipalsByUserNameLike(String userName) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        userName = userName.replace('*', '%');
        if (userName.charAt(0) != '%') {
            userName = "%" + userName;
        }
        if (userName.charAt(userName.length() - 1) != '%') {
            userName = userName + "%";
        }
        EntityManager em = Principal.entityManager();
        TypedQuery<Principal> q = em.createQuery("SELECT o FROM Principal AS o WHERE LOWER(o.userName) LIKE LOWER(:userName)", Principal.class);
        q.setParameter("userName", userName);
        return q;
    }
    
    public static TypedQuery<Principal> Principal.findPrincipalsByUserNameLike(String userName, String sortFieldName, String sortOrder) {
        if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
        userName = userName.replace('*', '%');
        if (userName.charAt(0) != '%') {
            userName = "%" + userName;
        }
        if (userName.charAt(userName.length() - 1) != '%') {
            userName = userName + "%";
        }
        EntityManager em = Principal.entityManager();
        String jpaQuery = "SELECT o FROM Principal AS o WHERE LOWER(o.userName) LIKE LOWER(:userName)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Principal> q = em.createQuery(jpaQuery, Principal.class);
        q.setParameter("userName", userName);
        return q;
    }
    
}
