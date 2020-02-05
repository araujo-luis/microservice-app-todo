package microservices.app.user.service.repositories;

import org.springframework.data.repository.CrudRepository;
import microservices.app.user.service.models.Users;
import microservices.app.user.service.models.dto.UserDto;

public interface UserRepository extends CrudRepository<Users, Long> {

	public Users findByUserId(String userId);
}