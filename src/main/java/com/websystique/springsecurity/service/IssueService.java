package com.websystique.springsecurity.service;

import com.websystique.springsecurity.dao.IssueDao;
import com.websystique.springsecurity.model.Issue;
import com.websystique.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("issueService")
@Transactional
public class IssueService {
    
    @Autowired
    private IssueDao dao;
    
    public Issue getById(int id){
        return dao.getById(id);
    }
    
    public Issue getLastIssueForClient(User client){
        return dao.getLastIssueForClient(client);
    }
}
