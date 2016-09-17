package org.pyouells.models;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by pyouells on 9/11/16.
 */
@Transactional
public interface HistoryDao extends CrudRepository<History, Long> {

}