
package com.prady.sample.cloud.audit.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.prady.sample.cloud.audit.domain.UserAudit;

/**
 * @author Prady
 */
public interface UserAuditRepository extends MongoRepository<UserAudit, String> {

}
