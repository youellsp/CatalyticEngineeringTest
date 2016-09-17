package org.pyouells.models;

/**
 * Created by pyouells on 9/11/16.
 * An entity History composed by five fields (id, endpoint, input, response, errors).
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "REQUEST_HISTORY")
public class History {

    // An autogenerated id (unique for each user in the db)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "HIST_ID")
    private long id;

    // The endpoint called
    @NotNull
    @Column(name = "ENDPOINT")
    private String endpoint;

    // The input passed
    @NotNull
    @Column(name = "INPUT")
    private String input;

    // The response of the service
    @NotNull
    @Column(name = "RESPONSE", length=30000)
    private String response;

    //Any errors that may have occurred from the service call
    @Column(name = "ERRORS", length=30000)
    private String errors;

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    public History() { }

    public History(String endpoint, String input, String response, String errors) {
        this.endpoint = endpoint;
        this.input = input;
        this.response = response;
        this.errors = errors;
    }

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

}