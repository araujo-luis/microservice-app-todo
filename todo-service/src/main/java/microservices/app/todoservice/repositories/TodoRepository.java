package microservices.app.todoservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import microservices.app.todoservice.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

	public List<Todo> findByUserId(Long id);
}
