package com.websystique.springsecurity.dao;
import com.websystique.springsecurity.model.Issue;
import com.websystique.springsecurity.model.IssueStatus;
import com.websystique.springsecurity.model.User;
import java.sql.Date;
import org.springframework.stereotype.Repository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


@Repository("issueDao")
public class IssueDao extends AbstractDao<Integer, Issue>{
    
    public Issue getById(int id){
        return getByKey(id);
    }
   
    public Issue getLastIssueForClient(User client){
       return (Issue)getSession().createCriteria(Issue.class)
                .add(Restrictions.eq("client", client))
                .addOrder(Order.desc("date"))
                .setMaxResults(1)
                .uniqueResult();  
    }
    
}

