package com.example;

import com.example.model.Fruit;

import java.util.Set;

public interface FruitService {
    Set<Fruit> getAllFruits();

    Set<Fruit> addFruit(Fruit fruit);
}
