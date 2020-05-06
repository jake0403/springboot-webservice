package springboot.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	// 이메일 정보가 일치하는 사용자 정보를 조회
	Optional<User> findByEmail(String email);

}
