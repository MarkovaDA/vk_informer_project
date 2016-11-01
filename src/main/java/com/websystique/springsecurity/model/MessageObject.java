package com.websystique.springsecurity.model;



public class MessageObject {
    String message;
    Filter[] filters;

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
