package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RecipeReactivRepository extends ReactiveCrudRepository<Recipe, String> {
}
