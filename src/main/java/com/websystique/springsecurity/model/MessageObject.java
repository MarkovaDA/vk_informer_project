
package com.websystique.springsecurity.model;

import java.util.List;


public class MessageObject {
    String message;
    List<Filter> filter;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Filter> getFilter() {
        return filter;
    }

    public void setFilter(List<Filter> filter) {
        this.filter = filter;
    }    
}
