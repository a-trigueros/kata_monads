using FluentAssertions;

namespace FrozenPizza.Tests;

public class FrozenPizzaServiceShould
{
    [Fact]
    public void TakeOrder()
    {
        Dictionary<string, int> stock = new()
        {
            { "Margarita", 10 }
        };

        var sut = new FrozenPizzaService(stock);
        var order = sut.TakeOrder("Margarita", 1);
        order.Should().NotBeNull()
            .And.BeEquivalentTo(new Order("Margarita", 1));
    }

    public class Fail
    {
        private readonly FrozenPizzaService _sut;

        public Fail()
        {
            Dictionary<string, int> stock = new()
            {
                { "Margarita", 10 }
            };

            _sut = new FrozenPizzaService(stock);
        }

        [Fact]
        public void ToOrderUnknowPizza()
        {
            _sut.Invoking(s => s.TakeOrder("Pepperoni", 1))
                .Should().Throw<PizzaNotFoundException>()
                .WithMessage("Pizza not found");
        }
        
        [Fact]
        public void ToOrderPizzaWithInsufficientStock()
        {
            _sut.Invoking(s => s.TakeOrder("Margarita", 20))
                .Should().Throw<PizzaOutOfStockException>()
                .WithMessage("Pizza out of stock");
        }
    }
}