package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
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
public class CategoryReactiveRepositoryIT {

	public static final String DESCRIPTION = "description";
	public static final String OTHER_DESCRIPTION = "other description";
	@Autowired
	CategoryReactiveRepository underTest;

	@Before
	public void setUp() throws Exception {
		underTest.deleteAll().block();
		Category tempCategory = new Category();
		tempCategory.setDescription(DESCRIPTION);
		underTest.save(tempCategory).block();

		tempCategory = new Category();
		tempCategory.setDescription(OTHER_DESCRIPTION);
		underTest.save(tempCategory).block();

	}

	@Test
	public void testCountCategory() {

		Mono<Long> actual = underTest.count();

		assertThat(actual.block()).isEqualTo(2);
	}

	@Test
	public void testFindByDescription() {

		Mono<Category> actual = underTest.findByDescription(DESCRIPTION);

		assertThat(actual.block().getDescription()).isEqualTo(DESCRIPTION);
	}
}