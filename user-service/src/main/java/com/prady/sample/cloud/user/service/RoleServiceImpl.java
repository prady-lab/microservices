
package com.prady.sample.cloud.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.prady.sample.cloud.common.cache.ApplicationCache;
import com.prady.sample.cloud.common.dto.user.PermissionDTO;
import com.prady.sample.cloud.common.dto.user.RoleDTO;
import com.prady.sample.cloud.common.exception.ItemNotFoundException;
import com.prady.sample.cloud.common.exception.ValidationException;
import com.prady.sample.cloud.common.validation.ValidationUtil;
import com.prady.sample.cloud.user.domain.Permission;
import com.prady.sample.cloud.user.domain.Role;
import com.prady.sample.cloud.user.mapper.RoleMapper;
import com.prady.sample.cloud.user.repository.PermissionRepository;
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
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleDTO> getRoles() {
        log.info("GET All Roles ");

        List<Role> roles = roleRepository.findAll();

        List<RoleDTO> roleDTOs = roleMapper.toRoleDTOs(roles);

        return roleDTOs;
    }

    @Override
    public RoleDTO getRolebyRoleName(String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        return roleMapper.toRoleDTO(role);

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

        existing = roleMapper.toRole(roleDTO);

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
            existing = roleMapper.toRole(roleDTO);

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
        return roleMapper.toRoleDTO(role);

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
            existing = roleMapper.toRole(roleDTO);

            roleRepository.save(existing);

            log.info("Role '{}' updated Successfully ", existing.getRoleName());
        }

        return roleDTO;
    }

    /*
     * (non-Javadoc)
     * @see com.prady.sample.cloud.user.service.RoleService#createPermission(java.lang.String, java.lang.String)
     */
    @Override
    public void createPermissionIfNotPresent(String permissionName, String permissionDescription) {
        Permission existing = permissionRepository.findByPermissionName(permissionName);
        if (null != existing) {
            log.info("Permission '{}' already exists", permissionName);
        } else {
            permissionRepository.save(new Permission(permissionName, permissionDescription));
            log.info("Permission '{}' created Successfully ", permissionName);
        }

    }

    /*
     * (non-Javadoc)
     * @see com.prady.sample.cloud.user.service.RoleService#refreshPermissionCache()
     */
    @Override
    public void refreshPermissionCache() {
        List<Permission> allPermission = permissionRepository.findAll();
        if (!CollectionUtils.isEmpty(allPermission)) {
            Map<String, PermissionDTO> permissionMap = new HashMap<>();
            for (Permission permission : allPermission) {
                permissionMap.put(permission.getId(),
                        new PermissionDTO(permission.getId(), permission.getPermissionName(), permission.getPermissionDescription()));
            }

            ApplicationCache.getInstance().updatePermission(permissionMap);
        }

    }

}
