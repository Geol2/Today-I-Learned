package Java.Effective.example;

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

    nutritionFacts.print();
  }
}
