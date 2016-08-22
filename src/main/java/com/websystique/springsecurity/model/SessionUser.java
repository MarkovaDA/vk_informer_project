package com.websystique.springsecurity.model;



public class SessionUser {
    
    private static User currentUser;
    

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        SessionUser.currentUser = currentUser;
    }    
}
