package bike.rental.company.challenge.general.utils;

import java.time.Duration;

public class RentalCalculateUtils {
    //Should be extracted from Database
    static final double HOUR_RATE = 5.0d;
    static final double DAY_RATE = 20.0d;
    static final double WEEK_RATE = 60.0d;
    static final double FAMILY_DISCOUNT = 0.3d;





    private static final double DAY = 24.0d;
    private static final double WEEK = 168.0d;
    RentalCalculateUtils(){}
    public static double getPricePerTimeDifference(Duration timeDifference){
        double differenceHours = timeDifference.toHours();

        if(differenceHours >= DAY && differenceHours < WEEK) {
            return ((double) timeDifference.toDays()) * DAY_RATE;
        }

        //Assumption: If time difference is 11 days the rate will be calculated : 1,57 * WEEK_RATE
        if(differenceHours>=WEEK){
            return (timeDifference.toDays()/7.0d)* WEEK_RATE;
        }

        return differenceHours * HOUR_RATE;
    }

    public static double applyPromotions(double totalAmount, int rentals) {
        if(rentals>=3 && rentals <=5) {
            return totalAmount - (totalAmount * FAMILY_DISCOUNT);
        }
        return totalAmount;
    }
}
