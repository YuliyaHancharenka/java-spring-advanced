package products;


public interface FoodEnergy {

    int getProteins();
    int getFats();
    int getCarbohydrates();

    /**
     * @deprecated  Suppressing warnings on using deprecated for this project operation
     */
    @Deprecated
    int getCaloricity();
}
