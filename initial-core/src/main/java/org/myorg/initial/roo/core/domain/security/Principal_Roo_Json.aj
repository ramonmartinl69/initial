// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.domain.security;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.myorg.initial.roo.core.domain.security.Principal;

privileged aspect Principal_Roo_Json {
    
    public String Principal.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Principal.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Principal Principal.fromJsonToPrincipal(String json) {
        return new JSONDeserializer<Principal>()
        .use(null, Principal.class).deserialize(json);
    }
    
    public static String Principal.toJsonArray(Collection<Principal> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Principal.toJsonArray(Collection<Principal> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Principal> Principal.fromJsonArrayToPrincipals(String json) {
        return new JSONDeserializer<List<Principal>>()
        .use("values", Principal.class).deserialize(json);
    }
    
}