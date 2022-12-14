package com.mycompany.propertymanagement.repository;

import com.mycompany.propertymanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,Long> {

    Optional<UserEntity> findByUserNameAndPassword(String userName, String password);

    @Query("SELECT ue FROM UserEntity ue WHERE password = ?1 and (userName = ?2 or email=?3)")
    Optional<UserEntity> findByPasswordAndUserNameOrEmail(String password, String userName, String email);

    Long countByUserName(String userName);

    Long countByEmail(String email);
}
