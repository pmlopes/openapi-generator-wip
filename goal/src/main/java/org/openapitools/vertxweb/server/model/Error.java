package org.openapitools.vertxweb.server.model;

import java.util.Objects;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class Error {

    private Integer code;
    private String message;

    public Error() {

    }

    public Error(JsonObject json) {
        ErrorConverter.fromJson(json, this);
    }

    public Error(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Error error = (Error) o;
        return Objects.equals(code, error.code) &&
                Objects.equals(message, error.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }

    @Override
    public String toString() {
        return toJson()
                .encodePrettily();
    }

    public JsonObject toJson() {
        final JsonObject json = new JsonObject();
        ErrorConverter.toJson(this, json);
        return json;
    }
}
