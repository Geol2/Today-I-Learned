package Java.Effective.example;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

import Java.Effective.example.NyPizza.Size;
import Java.Effective.example.Pizza.Topping;

abstract class Pizza {
  public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE };
  final Set<Topping> toppings;

  abstract static class Builder<T extends Builder<T>> {
    EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
    
    public T addTopping(Topping topping) {
      toppings.add(Objects.requireNonNull(topping));
      return self();
    }

    abstract Pizza build();

    protected abstract T self();
  }

  Pizza(Builder<?> builder) {
    toppings = builder.toppings.clone();
  }
}

class NyPizza extends Pizza {
  public enum Size { SMALL, MEDIUM, LARGE };
  private final Size size;

  public static class Builder extends Pizza.Builder<Builder> {
    private final Size size;

    public Builder(Size size) {
      this.size = Objects.requireNonNull(size);
    }

    @Override
    public NyPizza build() {
      return new NyPizza(this);
    }

    @Override
    protected Builder self() { return this; }
  }

  private NyPizza(Builder builder) {
    super(builder);
    size = builder.size;
  }

  public void print() {
    System.out.println(this.size + ", " + super.toppings);
  }
}

class Calzone extends Pizza {
  private final boolean sauceInside;

  public static class Builder extends Pizza.Builder<Builder> {
    private boolean sauceInside = false;

    public Builder sauceInside() {
      sauceInside = true;
      return this;
    }

    @Override
    public Calzone build() {
      return new Calzone(this);
    }

    @Override
    protected Builder self() { return this; }
  
  }

  private Calzone(Builder builder) {
    super(builder);
    sauceInside = builder.sauceInside;
  }

  public void print() {
    System.out.println(this.sauceInside);
  }
}

public class Item02_2 {
  
  private static final Size SMALL = Size.SMALL;
  private static final Topping HAM = Topping.HAM;
  private static final Topping ONION = Topping.ONION;
  private static final Topping SAUSAGE = Topping.SAUSAGE;
  
  public static void main(String[] args) {
    NyPizza pizza = new NyPizza.Builder(SMALL).addTopping(SAUSAGE).addTopping(ONION).build();
    pizza.print();

    Calzone calzone = new Calzone.Builder().addTopping(HAM).sauceInside().build();
    calzone.print();
  }
}
