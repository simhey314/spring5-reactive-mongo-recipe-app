package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jt on 6/17/17.
 */
@Ignore
@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryIT {

	@Autowired
	RecipeReactivRepository underTest;

	@Before
	public void setUp() throws Exception {
		underTest.deleteAll().block();
		Recipe tempRecipe = new Recipe();
		underTest.save(tempRecipe).block();

		tempRecipe = new Recipe();
		underTest.save(tempRecipe).block();

	}

	@Test
	public void testCountRecipe() {

		Mono<Long> actual = underTest.count();

		assertThat(actual.block()).isEqualTo(2);
	}

}