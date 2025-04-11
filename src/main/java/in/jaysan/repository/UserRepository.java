package in.jaysan.repository;

import in.jaysan.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity , Long> {
    Optional<UserEntity> findByEmail(String email);

}
