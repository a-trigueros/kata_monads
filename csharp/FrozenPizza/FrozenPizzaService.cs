namespace FrozenPizza;

public class FrozenPizzaService
{
    private readonly IDictionary<string, int> _pizzaStock;

    public FrozenPizzaService(IDictionary<string, int> pizzaStock)
    {
        _pizzaStock = pizzaStock;
    }

    public Order TakeOrder(string name, int quantity)
    {
        if (_pizzaStock.ContainsKey(name))
        {
            var stock = _pizzaStock[name];
            if (stock >= quantity)
            {
                _pizzaStock[name]--;
                return new Order(name, quantity);
            }
            else
            {
                throw new PizzaOutOfStockException("Pizza out of stock");
            }
        }
        else
        {
            throw new PizzaNotFoundException("Pizza not found");
        }
    }
}