/**
 *
 */

package com.prady.sample.cloud.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prady.sample.cloud.model.user.User;

/**
 * @author Prady
 */
@Service
public class MongoUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setFirstName(username);
        return user;
    }
}
