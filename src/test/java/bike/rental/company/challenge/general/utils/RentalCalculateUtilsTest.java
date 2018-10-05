package bike.rental.company.challenge.general.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class RentalCalculateUtilsTest {

  private static final int MOCK_HOURS = 2;
  private static final int MOCK_DAYS = 2;
  private static final int MOCK_WEEKS_IN_DAYS = 11;
  private static final double MOCK_TOTAL_PRICE = 144.285;
  private static final int MOCK_RENTALS = 3;

  private static final double MOCK_DELTA = 0.1;

  private static final double MOCK_EXPECTED_HOURS_PRICE = MOCK_HOURS * RentalCalculateUtils.HOUR_RATE;
  private static final double MOCK_EXPECTED_DAYS_PRICE = MOCK_DAYS * RentalCalculateUtils.DAY_RATE;
  private static final double MOCK_EXPECTED_WEEKS_PRICE = (MOCK_WEEKS_IN_DAYS /7.0)* RentalCalculateUtils.WEEK_RATE;
  private static final double MOCK_EXPECTED_PRICE = MOCK_TOTAL_PRICE - MOCK_TOTAL_PRICE * RentalCalculateUtils.FAMILY_DISCOUNT;


  @Test
  public void getPricePerTimeDifference_Hours_Rate() {
    Duration duration = Duration.ofHours( MOCK_HOURS );
    double result = RentalCalculateUtils.getPricePerTimeDifference(duration);
    Assert.assertEquals(MOCK_EXPECTED_HOURS_PRICE,result,MOCK_DELTA);
  }

  @Test
  public void getPricePerTimeDifference_Day_Rate() {
    Duration duration = Duration.ofDays( MOCK_DAYS );
    double result = RentalCalculateUtils.getPricePerTimeDifference(duration);
    Assert.assertEquals(MOCK_EXPECTED_DAYS_PRICE,result,MOCK_DELTA);
  }

  @Test
  public void getPricePerTimeDifference_Week_Rate() {
    Duration duration = Duration.ofDays( MOCK_WEEKS_IN_DAYS );
    double result = RentalCalculateUtils.getPricePerTimeDifference(duration);
    Assert.assertEquals( MOCK_EXPECTED_WEEKS_PRICE,result ,MOCK_DELTA);
  }


  @Test
  public void applyPromotions() {
    double result = RentalCalculateUtils.applyPromotions(MOCK_TOTAL_PRICE,MOCK_RENTALS);
    Assert.assertEquals(MOCK_EXPECTED_PRICE,result,MOCK_DELTA);
  }
}