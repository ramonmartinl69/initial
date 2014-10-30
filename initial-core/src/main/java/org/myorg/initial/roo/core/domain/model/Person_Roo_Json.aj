// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.myorg.initial.roo.core.domain.model;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.myorg.initial.roo.core.domain.model.Person;

privileged aspect Person_Roo_Json {
    
    public String Person.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Person.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Person Person.fromJsonToPerson(String json) {
        return new JSONDeserializer<Person>()
        .use(null, Person.class).deserialize(json);
    }
    
    public static String Person.toJsonArray(Collection<Person> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Person.toJsonArray(Collection<Person> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Person> Person.fromJsonArrayToPeople(String json) {
        return new JSONDeserializer<List<Person>>()
        .use("values", Person.class).deserialize(json);
    }
    
}
