package com.websystique.springsecurity.dto;


public class GroupDTO implements Comparable{
    private Integer id;
    private Integer specId;
    private String number;

    public GroupDTO(){
    }
    
    public GroupDTO(Integer _id, Integer _specId, String _number){
        this.id = _id;
        this.specId = _specId;
        this.number = _number;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int compareTo(Object group) {
        return this.number.compareTo(((GroupDTO)group).getNumber());
    }
}
