
package com.prady.sample.cloud.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.prady.sample.cloud.common.dto.user.UserAccountDTO;
import com.prady.sample.cloud.user.domain.UserAccount;

/**
 * Created by Prady on 6/18/17.
 */
@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface UserAccountMapper {

    @Mapping(target = "password", ignore = true)
    UserAccountDTO toUserAccountDTO(UserAccount userAccount);

    List<UserAccountDTO> toUserAccountDTOs(List<UserAccount> userAccounts);

    @Mapping(target = "password", ignore = true)
    UserAccount toUserAccount(UserAccountDTO userAccountDTO);

    @Mapping(target = "password", ignore = true)
    void toUserAccount(UserAccountDTO userAccountDTO, @MappingTarget UserAccount userAccount);

    List<UserAccount> toUserAccounts(List<UserAccountDTO> userAccountDTOs);
}
