/**
 *
 */

package com.prady.sample.cloud.user.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prady.sample.cloud.common.dto.audit.AuditDTO;

/**
 * @author Prady
 */
@FeignClient(name = "auditservice")
public interface AuditServiceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/audits", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void createUserAudit(AuditDTO auditDTO);

}
