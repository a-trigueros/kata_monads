import java.util.Map;

public class FrozenPizzaService {
    private final Map<String, Integer> pizzaStock;

    public FrozenPizzaService(Map<String, Integer> pizzaStock) {
        this.pizzaStock = pizzaStock;
    }

    public Order takeOrder(String name, Integer quantity) {
        if (pizzaStock.containsKey(name)) {
            Integer stock = pizzaStock.get(name);
            if (stock >= quantity) {
                pizzaStock.put(name, stock - quantity);
                return new Order(name, quantity);
            } else {
                throw new PizzaOutOfStockException("Insufficient stock");
            }
        } else {
            throw new PizzaNotFoundException("Pizza not found");
        }
    }
}
