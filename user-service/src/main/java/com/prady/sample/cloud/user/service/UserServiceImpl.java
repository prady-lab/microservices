/**
 *
 */

package com.prady.sample.cloud.user.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.prady.sample.cloud.common.dto.user.UserAccountDTO;
import com.prady.sample.cloud.common.exception.ItemAlreadyExistsException;
import com.prady.sample.cloud.common.exception.ItemNotFoundException;
import com.prady.sample.cloud.common.exception.ValidationException;
import com.prady.sample.cloud.common.validation.ValidationUtil;
import com.prady.sample.cloud.user.domain.UserAccount;
import com.prady.sample.cloud.user.mapper.UserAccountMapper;
import com.prady.sample.cloud.user.repository.UserAccountRepository;

import net.sf.oval.ConstraintViolation;

/**
 * @author Prady
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountMapper userAccountMapper;

    /*
     * (non-Javadoc)
     * @see com.prady.sample.clould.gateway.service.GatewayService#getUsers()
     */
    @Override
    public List<UserAccountDTO> getUsers() {
        log.info("Getting All Users ");

        List<UserAccount> userAccounts = userAccountRepository.findAll();

        List<UserAccountDTO> userAccountDTOs = userAccountMapper.toUserAccountDTOs(userAccounts);

        return userAccountDTOs;
    }

    @Override
    public void create(UserAccountDTO userAccountDTO) {
        log.debug("Creating User {} ", userAccountDTO.getUserLoginName());

        List<ConstraintViolation> validationErrors = ValidationUtil.validate(userAccountDTO);
        if (!CollectionUtils.isEmpty(validationErrors)) {
            throw new ValidationException(validationErrors);
        }

        UserAccount existing = userAccountRepository.findByUserLoginName(userAccountDTO.getUserLoginName());
        if (null != existing) {
            throw new ItemAlreadyExistsException(userAccountDTO.getUserLoginName(), "User");
        }

        existing = userAccountMapper.toUserAccount(userAccountDTO);

        String hash = encoder.encode(userAccountDTO.getPassword());
        existing.setPassword(hash);

        userAccountRepository.save(existing);

        log.info("User '{}' created Successfully", existing.getUserLoginName());
    }

    @Override
    public void createIfNotPresent(UserAccountDTO userAccountDTO) {
        log.debug("Creating User {} ", userAccountDTO.getUserLoginName());

        List<ConstraintViolation> validationErrors = ValidationUtil.validate(userAccountDTO);
        if (!CollectionUtils.isEmpty(validationErrors)) {
            throw new ValidationException(validationErrors);
        }

        UserAccount existing = userAccountRepository.findByUserLoginName(userAccountDTO.getUserLoginName());
        if (null != existing) {
            log.info("User '{}' already exists", existing.getUserLoginName());
        } else {
            existing = userAccountMapper.toUserAccount(userAccountDTO);

            String hash = encoder.encode(userAccountDTO.getPassword());
            existing.setPassword(hash);

            userAccountRepository.save(existing);

            log.info("User '{}' created Successfully ", existing.getUserLoginName());
        }
    }

    @Override
    public UserAccountDTO get(String id) {
        Assert.notNull(id, "User Id can not be null !!!");
        UserAccount userAccount = userAccountRepository.findOne(id);
        if (null == userAccount) {
            throw new ItemNotFoundException(id, "User");
        }
        return userAccountMapper.toUserAccountDTO(userAccount);
    }

    @Override
    public void delete(String id) {
        Assert.notNull(id, "User Id can not be null !!!");
        log.debug("Deleting User {} ", id);

        UserAccount existing = userAccountRepository.findOne(id);
        if (null == existing) {
            log.info("User '{}' not exists", id);
            throw new ItemNotFoundException(id, "User");
        } else {
            userAccountRepository.delete(id);
            log.info("User '{}' deleted Successfully ", id);
        }
    }

    @Override
    public UserAccountDTO update(String id, UserAccountDTO userAccountDTO) {
        Assert.notNull(id, "User Id can not be null !!!");
        log.debug("Updating User {} ", userAccountDTO.getUserLoginName());

        List<ConstraintViolation> validationErrors = ValidationUtil.validate(userAccountDTO);
        if (!CollectionUtils.isEmpty(validationErrors)) {
            throw new ValidationException(validationErrors);
        }

        UserAccount existing = userAccountRepository.findOne(id);
        if (null == existing) {
            throw new ItemNotFoundException(id, "User");
        } else {
            userAccountMapper.toUserAccount(userAccountDTO, existing);

            if (StringUtils.isNotBlank(existing.getPassword())) {
                String hash = encoder.encode(existing.getPassword());
                existing.setPassword(hash);
            }

            userAccountRepository.save(existing);

            log.info("User '{}' updated Successfully ", existing.getUserLoginName());
        }

        return userAccountDTO;
    }

}
