
package com.prady.sample.cloud.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.prady.sample.cloud.user.domain.Role;

/**
 * @author Prady.
 */
public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRoleName(final String roleName);
}
