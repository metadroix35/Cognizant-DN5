package exercise7;

public class FinanceForecast {
    // Recursive method to calculate future value
    public static double predictFutureValue(double presentValue, double growthRate, int years) {

        if (years == 0) {
            return presentValue;
        }

        return (1 + growthRate) *
                predictFutureValue(presentValue, growthRate, years - 1);
    }
}
