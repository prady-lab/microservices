
package com.prady.sample.cloud.audit.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.prady.sample.cloud.audit.domain.UserAudit;
import com.prady.sample.cloud.common.dto.audit.AuditDTO;

/**
 * Created by Prady on 6/18/17.
 */
@Mapper(componentModel = "spring")
public interface AuditMapper {

    /**
     * @param userAudit
     * @return
     */
    AuditDTO toAuditDTO(UserAudit userAudit);

    /**
     * @param userAudits
     * @return
     */
    List<AuditDTO> toAuditDTOs(List<UserAudit> userAudits);

    /**
     * @param auditDTO
     * @return
     */
    UserAudit toUserAudit(AuditDTO auditDTO);

}
