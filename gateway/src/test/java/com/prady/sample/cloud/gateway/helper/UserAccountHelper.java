/**
 *
 */

package com.prady.sample.cloud.gateway.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;

import com.prady.sample.cloud.common.dto.user.UserAccountDTO;

/**
 * @author Prady
 */
public class UserAccountHelper {

    private List<UserAccountDTO> users = new ArrayList<>();

    public Boolean isUserEmpty() {
        return CollectionUtils.isEmpty(users);
    }

    /**
     * @param users
     */
    public void updateUserList(List<UserAccountDTO> users) {
        this.users.addAll(users);
    }

    /**
     * @param userId
     */
    public void removeUser(String userId) {
        if (null != userId && CollectionUtils.isNotEmpty(users)) {
            users.removeIf(user -> userId.equals(user.getId()));
        }
    }

    /**
     * @return
     */
    public UserAccountDTO randomUser() {
        int index = RandomUtils.nextInt(0, users.size());
        return users.get(index);
    }

    /**
     * @return
     */
    public String randomUserId() {
        return randomUser().getId();
    }

    public UserAccountDTO populateUserAccountDTO() {
        UserAccountDTO user = new UserAccountDTO();
        user.setUserLoginName("Login Name " + RandomUtils.nextLong(0, 1000));
        user.setFirstName("First Name " + RandomUtils.nextLong(0, 1000));
        user.setLastName("Last Name " + RandomUtils.nextLong(0, 1000));
        user.setPassword("Password " + RandomUtils.nextLong(0, 1000));

        return user;
    }
}
