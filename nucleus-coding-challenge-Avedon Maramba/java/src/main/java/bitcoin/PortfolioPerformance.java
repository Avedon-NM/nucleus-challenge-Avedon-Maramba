package bitcoin;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class PortfolioPerformance {

    private static final List<Price> PRICES = List.of(
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 1, 5, 0, 0), new BigDecimal("35464.53")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 2, 5, 0, 0), new BigDecimal("35658.76")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 3, 5, 0, 0), new BigDecimal("36080.06")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 3, 13, 0, 0), new BigDecimal("37111.11")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 6, 5, 0, 0), new BigDecimal("38041.47")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 7, 5, 0, 0), new BigDecimal("34029.61")));

    private static final List<Transaction> TRANSACTIONS = List.of(
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 1, 9, 0, 0), new BigDecimal("0.012")),
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 1, 15, 0, 0), new BigDecimal("-0.007")),
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 4, 9, 0, 0), new BigDecimal("0.017")),
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 5, 9, 0, 0), new BigDecimal("-0.01")),
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 7, 9, 0, 0), new BigDecimal("0.1")));

    // Complete this method to return a list of daily portfolio values with one record for each day from the 01-09-2021-07-09-2021 in ascending date order
    public static List<DailyPortfolioValue> getDailyPortfolioValues() {
			
			int pricePointer = 0;
			int transactionPointer = 0;
			int week = 7;
			
			BigDecimal currentPrice = new BigDecimal(0);
			BigDecimal bitcoins = new BigDecimal(0);
			
			List<DailyPortfolioValue> results = new ArrayList<>();

			for (int day = 1; day <= week; day++) {
				
				while (pricePointer < PRICES.size()) {
					if (PRICES.get(pricePointer).effectiveDate().getDayOfMonth() == day) {
						currentPrice = PRICES.get(pricePointer).price();
						pricePointer++;
					} else {
						break;
					}
				}
				
				while (transactionPointer < TRANSACTIONS.size()) {
					if (TRANSACTIONS.get(transactionPointer).effectiveDate().getDayOfMonth() == day) {
						bitcoins = bitcoins.add(TRANSACTIONS.get(transactionPointer).numberOfBitcoins());
						transactionPointer++;
					} else {
						break;
					}
				}
				
				results.add(new DailyPortfolioValue((LocalDate.of(2021, Month.SEPTEMBER,day)), bitcoins.multiply(currentPrice)));
				
			}
			
			System.out.println("Portfolio Values: ");
			for(DailyPortfolioValue record : results) {
				System.out.println(" - Date: " + record.date() + ", Value: " + record.value());
			}
			
			return results;
}
    }
