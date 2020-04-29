package application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		
		try (Scanner sc = new Scanner(System.in) ) {
			
			System.out.println("Enter contract data");
				
			System.out.print("Number: ");
			int contractNumber = sc.nextInt();
			
			System.out.print("Date (dd/MM/yyyy): ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date contractDate = sdf.parse(sc.next());
			
			System.out.print("Contract value: ");
			double contractTotal = sc.nextDouble();
			
			Contract contract = new Contract(contractNumber, contractDate, contractTotal);
			
			System.out.print("Enter number of installments: ");
			int numberOfInstallments = sc.nextInt();
			
			ContractService contractService = new ContractService();
			contractService.processContract(contract, numberOfInstallments, new PaypalService());
			
			System.out.println("--------------------------------------------------------");
			
			List<Installment> installments = contractService.getInstallments();
			
			
			System.out.println("Installments: ");
			for (Installment installment : installments) {
				System.out.println(sdf.format(installment.getDueDate()) + " - " + installment.getAmount());
			}
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}

	}

}
