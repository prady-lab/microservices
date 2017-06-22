/**
 *
 */

package com.prady.sample.cloud.user.service;

import java.util.List;

import com.prady.sample.cloud.common.dto.user.UserAccountDTO;

/**
 * @author Prady
 */
public interface UserService {

    /**
     * @return
     */
    List<UserAccountDTO> getUsers();

    /**
     * @param userAccountDTO
     */
    void create(UserAccountDTO userAccountDTO);

    /**
     * @param userAccountDTO
     */
    void createIfNotPresent(UserAccountDTO userAccountDTO);

    /**
     * @param id
     * @return
     */
    UserAccountDTO get(String id);

    /**
     * @param id
     * @return
     */
    void delete(String id);

    /**
     * @param id
     * @param userAccountDTO
     * @return
     */
    UserAccountDTO update(String id, UserAccountDTO userAccountDTO);
}
