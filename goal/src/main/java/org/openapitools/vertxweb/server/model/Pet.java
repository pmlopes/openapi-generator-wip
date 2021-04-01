package org.openapitools.vertxweb.server.model;

import java.util.Objects;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class Pet {

    private Long id;
    private String name;
    private String tag;

    public Pet() {

    }

    public Pet(JsonObject json) {
        PetConverter.fromJson(json, this);
    }

    public Pet(Long id, String name, String tag) {
        this.id = id;
        this.name = name;
        this.tag = tag;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) &&
                Objects.equals(name, pet.name) &&
                Objects.equals(tag, pet.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tag);
    }

    @Override
    public String toString() {
        return toJson()
                .encodePrettily();
    }

    public JsonObject toJson() {
        final JsonObject json = new JsonObject();
        PetConverter.toJson(this, json);
        return json;
    }
}
