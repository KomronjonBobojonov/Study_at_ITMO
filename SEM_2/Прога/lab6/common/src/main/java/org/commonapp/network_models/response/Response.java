package org.commonapp.network_models.response;

import java.io.Serializable;
import java.util.Objects;

public abstract class Response implements Serializable {
    private final String name;
    private final String error;
    private final String result;

    public Response(String name, String error, String result) {
        this.name = name;
        this.error = error;
        this.result = result;
    }
    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(name, response.name) && Objects.equals(error, response.error) && Objects.equals(result, response.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, error);
    }

    @Override
    public String toString() {
        return "Response{" +
                "name='" + name + '\'' +
                ", error='" + error + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

