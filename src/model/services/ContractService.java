package model.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private List<Installment> installments = new ArrayList<Installment>();
	
	public void processContract(Contract contract, Integer months) {
		
		double installmentValue = contract.getTotalValue() / months;
		
		for (int i = 1; i<=months; i++) {
			
			// Calc Date
			Calendar cal = Calendar.getInstance();
			cal.setTime(contract.getDate());
			cal.add(Calendar.MONTH, i);
			Date dueDate = cal.getTime();
			
			// Calc Installment Value
			OnlinePaymentService paymentService = new PaypayService();
			double amountInterest = paymentService.interest(installmentValue, i);
			double finalAmount = paymentService.paymentFee(amountInterest);
			
			Installment installment = new Installment(dueDate, finalAmount);
			installments.add(installment);
			
		}
		
	}
	
	public List<Installment> getInstallments (){
		return installments;
	}

}
