/**
 *
 */

package com.prady.sample.cloud.user.aop;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prady.sample.cloud.common.audit.Audit;
import com.prady.sample.cloud.common.dto.audit.AuditDTO;
import com.prady.sample.cloud.user.client.AuditServiceClient;

/**
 * @author Prady
 */
@Aspect
@Component
public class AuditServiceAdvice {

    private static final Logger log = LoggerFactory.getLogger(AuditServiceAdvice.class);

    @Autowired
    private AuditServiceClient auditServiceClient;

    @Around("@annotation(com.prady.sample.cloud.common.audit.Audit)")
    public Object audit(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            AuditDTO auditDTO = new AuditDTO();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            Audit annotation = method.getAnnotation(Audit.class);
            auditDTO.setAction(annotation.action());
            auditDTO.setModule(annotation.module());
            auditDTO.setActionPerformedDate(new Date());
            auditDTO.setActionPerformedBy("Prady"); // TODO: Change after Auth is implemented.
            try {
                auditDTO.setIpAddress(InetAddress.getLocalHost().getHostAddress());
                auditDTO.setHostName(InetAddress.getLocalHost().getHostName());
            } catch (UnknownHostException e) {
                // suppress this.
            }
            if (annotation.isAuditMethodParam()) {
                Object[] args = joinPoint.getArgs();
                List<Object> methodArgs = new ArrayList<>();
                for (Object arg : args) {
                    methodArgs.add(arg);
                }
                auditDTO.setAdditionalData(methodArgs);
            }

            auditServiceClient.createUserAudit(auditDTO);
            log.info("Audit User Action {} ", auditDTO.getAction());
        }
    }
}
