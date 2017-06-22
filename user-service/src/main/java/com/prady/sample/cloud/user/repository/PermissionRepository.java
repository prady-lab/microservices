
package com.prady.sample.cloud.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.prady.sample.cloud.user.domain.Permission;

/**
 * @author Prady
 */
public interface PermissionRepository extends MongoRepository<Permission, String> {

    /**
     * @param permissionName
     * @return
     */
    Permission findByPermissionName(String permissionName);
}
