package com.rafalqa.repository;

import com.rafalqa.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rpiotrowicz on 2017-04-26.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
}
