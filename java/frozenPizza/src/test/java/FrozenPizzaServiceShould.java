import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FrozenPizzaServiceShould {

    @Test
    public void takeOrder() {
        Map<String, Integer> pizzaStock = new HashMap<>();
        pizzaStock.put("Margarita", 10);
        var sut = new FrozenPizzaService(pizzaStock);
        var order = sut.takeOrder("Margarita", 1);
        assert ("Margarita".equals(order.name()));
        assert (1 == order.quantity());
    }

    @Nested
    class Fail {
        private FrozenPizzaService sut = null;

        @BeforeEach
        public void setUp() {

            Map<String, Integer> pizzaStock = new HashMap<>();
            pizzaStock.put("Margarita", 10);
            this.sut = new FrozenPizzaService(pizzaStock);
        }

        @Test
        public void toOrderUnknowPizza() {
            assertThrows(PizzaNotFoundException.class, () -> sut.takeOrder("Pepperoni", 1));
        }

        @Test
        public void toOrderPizzaWithInsufficientStock() {
            assertThrows(PizzaOutOfStockException.class, () -> sut.takeOrder("Margarita", 20));
        }
    }
}
