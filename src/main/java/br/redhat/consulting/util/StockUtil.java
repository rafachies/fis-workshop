package br.redhat.consulting.util;

public class StockUtil {

	public Stock findOne() {
		Stock stock = new Stock();
		stock.setSymbol("FB");
		stock.setName("Facebook");
		stock.setPrice(100);
		return stock ;
	}
}
