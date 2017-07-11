package com.sw.base.exception;

import com.sw.base.util.JsonUtils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


public abstract class SelfDefinedException extends WebApplicationException {

    protected Errors errors;

    public SelfDefinedException(Throwable cause, Response.Status status, Errors errors) {
        super(cause, Response.status(status).type(MediaType.APPLICATION_JSON).entity(JsonUtils.toJson(errors)).build());
        this.errors = errors;
    }

    public String getMessage() {
        return errors.getMessage();
    }

    public Errors getErrors() {
        return errors;
    }

    public List<Errors.Error> getErrorList() {
        return errors.getErrorList();
    }

}
