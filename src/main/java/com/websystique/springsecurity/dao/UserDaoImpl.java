package com.websystique.springsecurity.dao;

import com.websystique.springsecurity.model.Settings;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springsecurity.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public void save(User user) {
		persist(user);
	}
	
	public User findById(int id) {
		return getByKey(id);
	}
        

	public User findByLogin(String login) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("login", login));
		return (User) crit.uniqueResult();
	}
        //функция смены пароля
        @Override
        public Boolean changePassword(Settings settings){
            if (settings.getNew_password().equals(settings.getOld_password()))
                return true;
            User user = findByLogin(settings.getLogin());            
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            //старый пароль не подтвержден 
            if (user.getPassword().equals(encoder.encode(settings.getOld_password())))
                return false;            
            user.setPassword(encoder.encode(settings.getNew_password()));
            //требуется обновление почты
            if (settings.getMail() != null)
                user.setEmail(settings.getMail());
            update(user);
            return true;
        }
        

}
