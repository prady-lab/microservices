/**
 *
 */

package com.prady.sample.cloud.auth.mapper;

import org.mapstruct.Mapper;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.prady.sample.cloud.auth.model.ClientDetail;

/**
 * @author Prady
 */
@Mapper(componentModel = "spring")
public interface ClientDetailsMapper {

    ClientDetail toClientDetail(ClientDetails clientDetails);

}
