
package com.websystique.springsecurity.model;


public class Settings {
    private Boolean by_mail;
    private Boolean by_vk;
    private String login;
    private String mail;
    private String old_password;
    private String new_password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public Boolean getBy_mail() {
        return by_mail;
    }

    public void setBy_mail(Boolean by_mail) {
        this.by_mail = by_mail;
    }

    public Boolean getBy_vk() {
        return by_vk;
    }

    public void setBy_vk(Boolean by_vk) {
        this.by_vk = by_vk;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
    
    
    
}
