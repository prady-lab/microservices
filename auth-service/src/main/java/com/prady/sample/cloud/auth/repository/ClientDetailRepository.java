/**
 *
 */

package com.prady.sample.cloud.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prady.sample.cloud.auth.model.ClientDetail;

/**
 * @author Prady
 */
@Repository
public interface ClientDetailRepository extends CrudRepository<ClientDetail, String> {

}
