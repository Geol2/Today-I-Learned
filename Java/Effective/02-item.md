# 생성자가 너무 많으면 빌더 패턴을 고려하기

```java
public class NutritionFacts {
  private final int servingSize;
  private final int servings;
  private final int calories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;

  public NutritionFacts(int servingSize, int servings) {
    this(servingSize, servings, 0);
  }

  public NutritionFacts(int servingSize, int servings, int calories) {
    this(servingSize, servings, calories, 0);
  }

  public NutritionFacts(int servingSize, int servings, int calories, int fat) {
    this(servingSize, servings, calories, fat, 0);
  }

  public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
    this(servingSize, servings, calories, fat, sodium, 0);
  }

  public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
    this.servingSize = servingSize;
    this.servings = servings;
    this.calories = calories;
    this.fat = fat;
    this.soldium = soldium;
    this.carbohydrate = carbohydrate;
  }
}
```

위 패턴을 **점층적 생성자 패턴**이라고 부르고 객체를 초기화 시에 설정이 원하지 않는 값이 있어도 설정을 해야하는 경우가 생긴다

매개변수가 점차 증가하면 작성하기 불편하거나 읽기 어려운 경우도 있다

```java
public class NutritionFacts {
  private int servingSize  = -1;
  private int servings     = -1;
  private int calories     =  0;
  private int fat          =  0;
  private int sodium       =  0;
  private int carbohydrate =  0;

  public NutritionFacts() {}

  public void setServingSize(int val) { servingSize = val; }
  public void setServings(int val) { servings = val; }
  public void setCalories(int val) { calories = val; }
  public void setFat(int val) { fat = val; }
  public void setSolidum(int val) { sodium = val; }
  public void setCarbohydrate(int val) { carbohydrate = val; }
}

# 클라이언트 코드
NutritionFacts cocaCola = new NutritionFacts();
cocaCola.setServingSize(240);
cocaCola.setServings(8);
cocaCola.setCalories(100);
cocaCola.setSodium(35);
cocaCola.setCarbohydrate(27);

```

위 패턴은 **자바빈즈 패턴**이라고 불리고 객체를 하나 만들면 매서드 여러개를 호출해야하고 생성자가 없기도 해서 일관성이 무너진 상태로 놓인다.

불변으로도 만들 수 없는 단점이 있다.

```java
class NutritionFacts {
  private final int servingSize;
  private final int servings;
  private final int calories;
  private final int fat;
  private final int soldium;
  private final int carbohydrate;

  public static class Builder {
    private final int servingSize;
    private final int servings;

    private int calories = 0;
    private int fat = 0;
    private int soldium = 0;
    private int carbohydrate = 0;
  
    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }
  

    public Builder calories(int val) { calories = val; return this; }

    public Builder soldium(int val) { soldium = val; return this; }

    public Builder carbohydrate(int val) { carbohydrate = val; return this; }

    public NutritionFacts build() {
      return new NutritionFacts(this);
    }
  }

  private NutritionFacts(Builder builder) {
    servingSize = builder.servingSize;
    servings = builder.servings;
    calories = builder.calories;
    fat = builder.fat;
    soldium = builder.soldium;
    carbohydrate = builder.carbohydrate;
  }

  public void print() {
    System.out.println(servingSize + ", " + servings + ", " + calories + ", " + fat + ", " + soldium + ", " + carbohydrate);
  }
}

public class Item02 {
  public static void main(String[] argv) {
    NutritionFacts nutritionFacts = new NutritionFacts.Builder(240, 8)
    .calories(100).soldium(35).carbohydrate(27).build();

    nutritionFacts.print(); //240, 8, 100, 0, 35, 27
  }
}
```

위 패턴은 **빌더 패턴**이라고 불린다, 불변이기도 하고 상태들의 기본값을 한 곳에 모아뒀다. 빌더들의 세터들이 자신을 반환하기 때문에 연쇄적인 호출이 가능하다. 체이닝 방식이라고 불리는 것들이 않나 싶지만 책에선 메서드 연쇄 혹은 플루언트 API라고 나와있다.

클라이언트 코드는 다음과 같다.

```java
NutritionFacts nutritionFacts = new NutritionFacts.Builder(240, 8)
  .calories(100).soldium(35).carbohydrate(27).build();
```

이러한 빌더 패턴들은 계층적으로 설계된 클래스와 함께 쓰기 좋다. 

계층적으로 설계된 클래스들이 예제로 작성되어 있다.

```java
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
  private static final Topping ONION = Topping.ONION;
  private static final Topping SAUSAGE = Topping.SAUSAGE;
  
  public static void main(String[] args) {
    NyPizza pizza = new NyPizza.Builder(SMALL).addTopping(SAUSAGE).addTopping(ONION).build();
    pizza.print(); //SMALL, [ONION, SAUSAGE]

    Calzone calzone = new Calzone.Builder().addTopping(HAM).sauceInside().build();
    calzone.print(); // true
  }
}
```
빌터 패턴을 만들어서 이러한 장점들을 취할 수 있지만, 성능에 민감한 상황에서는 문제가 될 수 있다.

API가 발전됨에 따라 클래스 변수가 많아질 때 빌더 패턴은 유용하니 애초에 시작을 빌드 패턴으로 진행하는 것도 괜찮다.