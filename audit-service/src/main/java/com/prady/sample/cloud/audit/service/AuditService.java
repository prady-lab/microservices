/**
 *
 */

package com.prady.sample.cloud.audit.service;

import java.util.List;

import com.prady.sample.cloud.common.dto.audit.AuditDTO;

/**
 * @author Prady
 */
public interface AuditService {

    /**
     * @param auditDTO
     */
    void create(AuditDTO auditDTO);

    /**
     * @return
     */
    List<AuditDTO> getAudits();

}
