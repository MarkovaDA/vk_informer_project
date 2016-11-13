package com.websystique.springsecurity.model;



public class MessageObject {
    String message;
    Boolean add_signature;
    Filter[] filters;

    public Boolean getAdd_signature() {
        return add_signature;
    }

    public void setAdd_signature(Boolean add_signature) {
        this.add_signature = add_signature;
    }

    public Filter[] getFilters() {
        return filters;
    }

    public void setFilters(Filter[] filters) {
        this.filters = filters;
    }

    public MessageObject(String message, Filter[] filters) {
        this.message = message;
        this.filters = filters;
    }
    
    public MessageObject(){
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
