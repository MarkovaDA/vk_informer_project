package com.websystique.springsecurity.dao;

import com.websystique.springsecurity.model.Settings;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springsecurity.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

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
            //пароль не изменился
            User user = findByLogin(settings.getLogin()); 
            if (settings.getMail() != null)
                user.setEmail(settings.getMail());
            if (settings.getNew_password().equals(settings.getOld_password()))
                return true;
            StandardPasswordEncoder encoder = new StandardPasswordEncoder(); 
            //старый пароль подтверждается - обновляем пароль на новый
            if (!encoder.matches(settings.getOld_password(), user.getPassword()))
                return false;
            user.setPassword(encoder.encode(settings.getNew_password()));           
            update(user);
            return true;
        }
        

}
