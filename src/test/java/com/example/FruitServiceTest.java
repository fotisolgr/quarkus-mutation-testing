package com.example;

import com.example.model.Fruit;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@QuarkusTest
class FruitServiceTest {

    @Inject
    FruitService fruitService;

    @Test
    void shouldReturnFruits() {
        final var fruits = fruitService.getAllFruits();

        assertThat(fruits).isEmpty();
    }

    @Test
    void shouldAddFruit() {
        final var fruitToAdd = new Fruit("orange", "orange");

        final var fruits = fruitService.addFruit(fruitToAdd);

        assertThat(fruits).contains(fruitToAdd);
    }

    @Test
    void shouldNotAddExistentFruit() {
        final var fruitToAdd = new Fruit("avocado", "it's tasty");
        fruitService.addFruit(fruitToAdd);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> fruitService.addFruit(fruitToAdd));
    }
}
