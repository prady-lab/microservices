
package com.prady.sample.cloud.user.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.prady.sample.cloud.user.domain.UserAccount;

/**
 * @author Prady
 */
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

    UserAccount findByUserLoginName(final String userLoginName);

    UserAccount findByUserLoginNameAndPassword(final String userLoginName, final String password);

    List<UserAccount> findByUserLoginNameLike(final String userLoginName);
}
