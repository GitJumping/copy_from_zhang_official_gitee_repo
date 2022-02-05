package geektime.concurrent.part3;

import java.math.BigDecimal;
import java.util.Arrays;

import java.util.List;

public class ReduceExample {

	public static void main(String[] args) {

		List<Invoice> listOfInvoices = Arrays.asList(
				new Invoice("productA31", BigDecimal.valueOf(131), BigDecimal.valueOf(5)),
				new Invoice("productA32", BigDecimal.valueOf(121), BigDecimal.valueOf(3)),
				new Invoice("productA33", BigDecimal.valueOf(99), BigDecimal.valueOf(1.5))

		);

		BigDecimal sum = listOfInvoices.stream().map(data -> data.getQTY().multiply(data.getPrice()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		System.out.println(sum);
	}
}

class Invoice {
	String invoiceNo;
	BigDecimal price;
	BigDecimal qty;

	public Invoice(String invoiceNo, BigDecimal price, BigDecimal qty) {
		this.invoiceNo = invoiceNo;
		this.price = price;
		this.qty = qty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getQTY() {
		return qty;
	}
}