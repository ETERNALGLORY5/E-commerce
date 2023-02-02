package huffle.puff.wand.repositories;


import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import huffle.puff.wand.entities.User;


public interface UserRepository extends JpaRepository<User, String>
{
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String password);
    
    List<User> findByNameContaining(String keywords);
   
}

