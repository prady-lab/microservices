
package com.prady.sample.cloud.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.prady.sample.cloud.common.dto.user.RoleDTO;
import com.prady.sample.cloud.common.exception.ItemNotFoundException;
import com.prady.sample.cloud.common.exception.ValidationException;
import com.prady.sample.cloud.common.validation.ValidationUtil;
import com.prady.sample.cloud.user.domain.Role;
import com.prady.sample.cloud.user.mapper.UserAccountMapper;
import com.prady.sample.cloud.user.repository.RoleRepository;

import net.sf.oval.ConstraintViolation;

/**
 * @author Prady
 */
@Component
public class RoleServiceImpl implements RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public List<RoleDTO> getRoles() {
        log.info("GET All Roles ");

        List<Role> roles = roleRepository.findAll();

        List<RoleDTO> roleDTOs = userAccountMapper.toRoleDTOs(roles);

        return roleDTOs;
    }

    @Override
    public RoleDTO getRolebyRoleName(String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        return userAccountMapper.toRoleDTO(role);

    }

    @Override
    public void create(RoleDTO roleDTO) {
        log.debug("Creating Role {} ", roleDTO.getRoleName());

        List<ConstraintViolation> validationErrors = ValidationUtil.validate(roleDTO);
        if (!CollectionUtils.isEmpty(validationErrors)) {
            throw new ValidationException(validationErrors);
        }

        Role existing = roleRepository.findByRoleName(roleDTO.getRoleName());
        Assert.isNull(existing, "Role already exists " + roleDTO.getRoleName());

        existing = userAccountMapper.toRole(roleDTO);

        roleRepository.save(existing);

        log.info("Role '{}' created Successfully", existing.getRoleName());
    }

    @Override
    public void createIfNotPresent(RoleDTO roleDTO) {
        log.debug("Creating Role {} ", roleDTO.getRoleName());

        List<ConstraintViolation> validationErrors = ValidationUtil.validate(roleDTO);
        if (!CollectionUtils.isEmpty(validationErrors)) {
            throw new ValidationException(validationErrors);
        }

        Role existing = roleRepository.findByRoleName(roleDTO.getRoleName());
        if (null != existing) {
            log.info("Role '{}' already exists", existing.getRoleName());
        } else {
            existing = userAccountMapper.toRole(roleDTO);

            roleRepository.save(existing);

            log.info("Role '{}' created Successfully ", existing.getRoleName());
        }
    }

    /*
     * (non-Javadoc)
     * @see com.prady.sample.cloud.user.service.RoleService#get(java.lang.String)
     */
    @Override
    public RoleDTO get(String id) {
        Assert.notNull(id, "Role Id can not be null !!!");
        Role role = roleRepository.findOne(id);
        if (null == role) {
            throw new ItemNotFoundException(id, "Role");
        }
        return userAccountMapper.toRoleDTO(role);

    }

    /*
     * (non-Javadoc)
     * @see com.prady.sample.cloud.user.service.RoleService#delete(java.lang.String)
     */
    @Override
    public void delete(String id) {
        Assert.notNull(id, "Role Id can not be null !!!");
        log.debug("Deleting Role {} ", id);

        Role existing = roleRepository.findOne(id);
        if (null == existing) {
            log.info("Role '{}' not exists", id);
            throw new ItemNotFoundException(id, "Role");
        } else {
            roleRepository.delete(id);
            log.info("Role '{}' deleted Successfully ", id);
        }

    }

    /*
     * (non-Javadoc)
     * @see com.prady.sample.cloud.user.service.RoleService#update(java.lang.String, com.prady.sample.cloud.common.dto.user.RoleDTO)
     */
    @Override
    public RoleDTO update(String id, RoleDTO roleDTO) {
        Assert.notNull(id, "Role Id can not be null !!!");

        log.debug("Updating Role {} ", roleDTO.getRoleName());

        List<ConstraintViolation> validationErrors = ValidationUtil.validate(roleDTO);
        if (!CollectionUtils.isEmpty(validationErrors)) {
            throw new ValidationException(validationErrors);
        }

        Role existing = roleRepository.findOne(id);
        if (null == existing) {
            throw new ItemNotFoundException(id, "Role");
        } else {
            existing = userAccountMapper.toRole(roleDTO);

            roleRepository.save(existing);

            log.info("Role '{}' updated Successfully ", existing.getRoleName());
        }

        return roleDTO;
    }

}
