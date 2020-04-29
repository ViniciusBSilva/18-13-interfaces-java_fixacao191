package model.services;

public class PaypalService implements OnlinePaymentService {

	@Override
	public Double paymentFee(Double amount) {

		return amount + (amount * 2/100);
		
	}

	@Override
	public Double interest(Double amount, Integer months) {
		
		return amount + ( ( amount * 1/100 ) * months );
		
	}

}
