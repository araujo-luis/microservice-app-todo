package microservices.app.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import microservices.app.user.service.models.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	public Users findByUserId(String userId);

	public Users findByEmail(String email);
}