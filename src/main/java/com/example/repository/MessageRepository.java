package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.entity.Message;
//import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
    Boolean existsByPostedBy(Integer postedBy);
    List<Message> findAll();
    
        
}
