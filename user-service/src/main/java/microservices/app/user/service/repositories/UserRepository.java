package microservices.app.user.service.repositories;

import org.springframework.data.repository.CrudRepository;
import microservices.app.user.service.models.Users;

public interface UserRepository extends CrudRepository<Users, Long> {

}