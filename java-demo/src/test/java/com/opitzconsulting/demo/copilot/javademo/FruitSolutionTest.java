package com.opitzconsulting.demo.copilot.javademo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * By doing this tasks you will learn how to use:
 * <p>
 * - {@link Stream#sorted}
 * - {@link Stream#limit(long)}
 * - {@link Stream#reduce}
 * - {@link Stream#mapToInt(ToIntFunction)}
 * - {@link Stream#flatMap(Function)}
 * - {@link Collectors#groupingBy(Function)}
 * - {@link Collectors#counting()}
 * - {@link Comparator#comparing(Function)}
 * - {@link Function#identity()}
 * - {@link Stream#generate(Supplier)}
 * </p>
 *
 * @author https://github.com/HubertWo
 */
@SuppressWarnings("SimplifyStreamApiCallChains")
@DisplayName("Stream basics")
class FruitSolutionTest {

    private static final Fruit BANANA = new Fruit("Banana", 105);
    private static final Fruit PAPAYA = new Fruit("Papaya", 109);
    private static final Fruit KIWI = new Fruit("Kiwi", 46);
    private static final Fruit MANGO = new Fruit("Mango", 107);
    private static final Fruit PEACH = new Fruit("Peach", 48);

    private static final Set<Fruit> FRUITS = Set.of(PAPAYA, BANANA, KIWI, MANGO, PEACH);

    @Test
    @DisplayName("Task: Find 2 fruits with highest amount of calories")
    void task1() {
        List<Fruit> mostCaloricFruits = FRUITS.stream()
                .sorted(Comparator.comparingInt(Fruit::getCalories).reversed())
                .limit(2)
                .collect(Collectors.toList());
        assertThat(mostCaloricFruits).containsExactly(PAPAYA, MANGO);
    }

    @Test
    @DisplayName("Task: Take half of each fruit and get the sum of calories")
    void task2() {
        final int sumOfCalories = FRUITS.stream()
                .mapToInt(fruit -> fruit.getCalories() / 2)
                .sum();

        assertThat(sumOfCalories).isEqualTo(206);
    }

    @Test
    @DisplayName("Task: Group fruits by first letter")
    void task3() {
        final Map<Character, Set<Fruit>> mapOfFruits = FRUITS.stream()
                .collect(Collectors.groupingBy(
                        fruit -> fruit.getName().charAt(0),
                        Collectors.toSet()
                ));
        assertThat(mapOfFruits.keySet()).contains('B', 'K', 'M', 'P');
        assertThat(mapOfFruits.get('B')).hasSize(1);
        assertThat(mapOfFruits.get('K')).hasSize(1);
        assertThat(mapOfFruits.get('M')).hasSize(1);
        assertThat(mapOfFruits.get('P')).hasSize(2);
    }

    @Test
    @DisplayName("Task: Put all fruits into one basket")
    void task4() {
        final List<List<Fruit>> fruitBaskets = List.of(
                List.of(BANANA, PAPAYA),
                List.of(MANGO, PEACH)
        );

        final List<Fruit> basketWithAllFruits = fruitBaskets.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        assertThat(basketWithAllFruits).contains(BANANA, PAPAYA, MANGO, PEACH);
        assertThat(basketWithAllFruits).doesNotContain(KIWI);
    }

    @Test
    @DisplayName("Task: Count amount of each fruit in the basket")
    void task5() {
        final List<Fruit> basket = List.of(MANGO, PAPAYA, MANGO, PEACH, KIWI, KIWI, KIWI);

        final Map<Fruit, Long> countedFruit = basket.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        assertThat(countedFruit.keySet()).contains(MANGO, PAPAYA, PEACH, KIWI);

        assertThat(countedFruit.get(MANGO)).isEqualTo(2);
        assertThat(countedFruit.get(PAPAYA)).isEqualTo(1);
        assertThat(countedFruit.get(PEACH)).isEqualTo(1);
        assertThat(countedFruit.get(KIWI)).isEqualTo(3);
    }

    @Test
    @DisplayName("Task: Mix all fruits together and construct one, big, new Fruit")
    void task6() {
        final Fruit bigJuicyFruit = FRUITS.stream()
                .reduce(new Fruit("", 0), (fruit1, fruit2) ->
                        new Fruit(fruit1.getName() + fruit2.getName(), fruit1.getCalories() + fruit2.getCalories())
                );

        assertThat(bigJuicyFruit).isNotNull();
        assertThat(bigJuicyFruit.getCalories()).isEqualTo(415);
        assertThat(bigJuicyFruit.getName()).isNotEmpty();
    }

    @Test
    @DisplayName("Task: Generate list of 10 randomly picked fruits")
    void task7() {

        // Random fruit supplier
        final Supplier<Fruit> randomFruitSupplier = new Supplier<>() {
            final Random random = new Random();
            final List<Fruit> fruits = new ArrayList<>(FRUITS);

            @Override
            public Fruit get() {
                return fruits.get(random.nextInt(fruits.size()));
            }
        };

        final Stream<Fruit> infiniteStreamOfFruits = Stream.generate(randomFruitSupplier);


        List<Fruit> pickedFruits = infiniteStreamOfFruits
                .limit(10)
                .collect(Collectors.toList());

        assertThat(pickedFruits).hasSize(10);
    }

    @Test
    @DisplayName("Task: Collect only second and forth (with odd index) fruit from fruitList")
    void task8() {
        final List<Fruit> fruitList = new ArrayList<>(FRUITS).stream()
                .sorted(Comparator.comparing(Fruit::getName))
                .collect(toList());

        List<Fruit> filteredFruits = fruitList.stream()
                .filter(fruit -> fruitList.indexOf(fruit) == 1 || fruitList.indexOf(fruit) == 3)
                .collect(Collectors.toList());

        assertThat(filteredFruits).hasSize(2);
        assertThat(filteredFruits).containsExactly(KIWI, PAPAYA);
    }

}
