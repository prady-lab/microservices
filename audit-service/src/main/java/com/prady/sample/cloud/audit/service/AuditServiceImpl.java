/**
 *
 */

package com.prady.sample.cloud.audit.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prady.sample.cloud.audit.domain.UserAudit;
import com.prady.sample.cloud.audit.mapper.AuditMapper;
import com.prady.sample.cloud.audit.repository.UserAuditRepository;
import com.prady.sample.cloud.common.dto.audit.AuditDTO;

/**
 * @author Prady
 */
@Service
public class AuditServiceImpl implements AuditService {

    private static final Logger log = LoggerFactory.getLogger(AuditServiceImpl.class);

    @Autowired
    private UserAuditRepository userAuditRepository;

    @Autowired
    private AuditMapper auditMapper;

    /*
     * (non-Javadoc)
     * @see com.prady.sample.cloud.audit.service.AuditService#create(com.prady.sample.cloud.common.dto.audit.AuditDTO)
     */
    @Override
    public void create(AuditDTO auditDTO) {
        log.debug("Creating Audit: Module {} Action {}", auditDTO.getModule(), auditDTO.getAction());

        UserAudit audit = auditMapper.toUserAudit(auditDTO);

        userAuditRepository.save(audit);

        log.info("Audit '{}' created Successfully", audit.getId());

    }

    /*
     * (non-Javadoc)
     * @see com.prady.sample.cloud.audit.service.AuditService#getAudits()
     */
    @Override
    public List<AuditDTO> getAudits() {
        log.info("Getting All Users ");

        List<UserAudit> userAudits = userAuditRepository.findAll();

        List<AuditDTO> auditDTOs = auditMapper.toAuditDTOs(userAudits);

        return auditDTOs;
    }

}
