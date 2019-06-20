package it.softwaretest.app.ws.io.repository.impl;

import it.softwaretest.app.ws.io.repository.UserRepositoryInterface;
import it.softwaretest.app.ws.io.entity.impl.UserEntity;
import it.softwaretest.app.ws.shared.dto.impl.UserDto;
import it.softwaretest.app.ws.utilities.HibernateCreateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRepository implements UserRepositoryInterface {

    private Session session;


    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateCreateSession.getSessionFactory();
        session = sessionFactory.openSession();
    }

    @Override
    public UserDto getUserByUserName(String userName) {
        UserDto userDto = null;

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<UserEntity> criteria = criteriaBuilder.createQuery(UserEntity.class);

        Root<UserEntity> profileRoot = criteria.from(UserEntity.class);
        criteria.select(profileRoot);
        criteria.where(criteriaBuilder.equal(profileRoot.get("email"), userName));

        Query<UserEntity> query = session.createQuery(criteria);

        List<UserEntity> resultList = query.getResultList();

        if (resultList!=null && resultList.size()>0) {
            UserEntity userEntity = resultList.get(0);
            userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
        }

        return userDto;

    }

    @Override
    public UserDto getUser(String id) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<UserEntity> criteria = criteriaBuilder.createQuery(UserEntity.class);

        Root<UserEntity> profileRoot = criteria.from(UserEntity.class);
        criteria.select(profileRoot);
        criteria.where(criteriaBuilder.equal(profileRoot.get("userId"), id));

        UserEntity userEntity = session.createQuery(criteria).getSingleResult();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }

    public UserDto saveUser(UserDto user) {
        UserDto returnValue;

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        session.beginTransaction();
        session.save(userEntity);
        session.getTransaction().commit();

        returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public void updateUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);

        session.beginTransaction();
        session.update(userEntity);
        session.getTransaction().commit();
    }

    @Override
    public void deleteUser(UserDto userProfile) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userProfile, userEntity);

        session.beginTransaction();
        session.delete(userEntity);
        session.getTransaction().commit();
    }

    @Override
    public void closeConnection() {
        if (session != null)
            session.close();
    }

}
