package model.services;

public class PaypayService implements OnlinePaymentService {

	@Override
	public Double paymentFee(Double amount) {

		return amount * (2/100);
		
	}

	@Override
	public Double interest(Double amount, Integer months) {
		
		return amount * (1/100) * months;
		
	}

}
