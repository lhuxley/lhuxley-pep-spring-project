package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import com.example.entity.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Boolean existsByPostedBy(Integer postedBy);

    List<Message> findAll();

    Optional<Message> findByMessageId(Integer messageId);

    void deleteByMessageId(Integer messageId);

    List<Message> findByPostedBy(Integer postedBy);

    @Modifying
    @Transactional
    @Query("UPDATE Message m SET m.messageText = :messageText WHERE m.messageId = :messageId")
    int updateMessageContentById(@Param("messageId") Integer messageId, @Param("messageText") String messageText);

}
