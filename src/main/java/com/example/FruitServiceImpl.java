package com.example;

import com.example.model.Fruit;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@ApplicationScoped
public class FruitServiceImpl implements FruitService {

    private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    @Override
    public Set<Fruit> getAllFruits() {

        return fruits;
    }

    @Override
    public Set<Fruit> addFruit(Fruit fruit) {
        final var isAdded = fruits.add(fruit);

        if(!isAdded) {
            throw new RuntimeException("Fruit is already in");
        }

        return fruits;
    }
}
