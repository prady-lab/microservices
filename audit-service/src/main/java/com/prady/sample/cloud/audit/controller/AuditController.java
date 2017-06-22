/**
 *
 */

package com.prady.sample.cloud.audit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prady.sample.cloud.audit.service.AuditService;
import com.prady.sample.cloud.common.dto.audit.AuditDTO;

/**
 * @author Prady
 */
@RestController
public class AuditController {

    @Autowired
    private AuditService auditService;

    @GetMapping("/audits")
    public List<AuditDTO> getUsers() {
        return auditService.getAudits();
    }

    @PostMapping(value = "/audits")
    public ResponseEntity<AuditDTO> createUser(@RequestBody AuditDTO auditDTO) {
        auditService.create(auditDTO);
        return new ResponseEntity<>(auditDTO, HttpStatus.OK);
    }

}
