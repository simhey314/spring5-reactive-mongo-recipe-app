package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by jt on 6/17/17.
 */
@Ignore
@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryIT {

	public static final String TEASPOON = "Teaspoon";
	public static final String SPOON = "Spoon";
	@Autowired
	UnitOfMeasureReactiveRepository underTest;

	@Before
	public void setUp() throws Exception {
		underTest.deleteAll().block();

		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setDescription(SPOON);
		underTest.save(uom).block();

		uom = new UnitOfMeasure();
		uom.setDescription(TEASPOON);
		underTest.save(uom).block();
	}

	@Test
	public void findByDescription() {

		Mono<UnitOfMeasure> actual = underTest.findByDescription(TEASPOON);

		assertEquals(TEASPOON, actual.block().getDescription());
	}

	@Test
	public void findByDescriptionSpoon() {

		Mono<UnitOfMeasure> actual = underTest.findByDescription(SPOON);

		assertEquals(SPOON, actual.block().getDescription());
	}

	@Test
	public void testCount() {

		Mono<Long> actual = underTest.count();

		assertThat(actual.block()).isEqualTo(2l);
	}
}