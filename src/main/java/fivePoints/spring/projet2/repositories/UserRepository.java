package fivePoints.spring.projet2.repositories;

import fivePoints.spring.projet2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByEmail(String email);
}
