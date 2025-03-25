package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Message;
//import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
    Boolean existsByPostedBy(Integer postedBy);
    
        
}
