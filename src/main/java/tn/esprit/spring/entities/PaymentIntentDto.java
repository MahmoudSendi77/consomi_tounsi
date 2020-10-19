package tn.esprit.spring.entities;

public class PaymentIntentDto {
	 public enum Currency{
	        usd, eur;
	    }

	    private String description;
	    private int amount;
	    private Currency currency;
	    
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		public Currency getCurrency() {
			return currency;
		}
		public void setCurrency(Currency currency) {
			this.currency = currency;
		}
		public PaymentIntentDto(String description, int amount, Currency currency) {
			super();
			this.description = description;
			this.amount = amount;
			this.currency = currency;
		}
		@Override
		public String toString() {
			return "PaymentIntentDto [description=" + description + ", amount=" + amount + ", currency=" + currency
					+ "]";
		}
		public PaymentIntentDto() {
			super();
		}
		
		
		
	    
	    /*	<dependency>
			<groupId>com.stripe</groupId>
			<artifactId>stripe-java</artifactId>
			<version>5.21.0</version>
		</dependency>
*/
}
