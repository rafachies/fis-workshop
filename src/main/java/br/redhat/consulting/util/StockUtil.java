package br.redhat.consulting.util;

public class StockUtil {

	public Stock example() {
		Stock stock = new Stock();
		stock.setSymbol("FB");
		stock.setName("Facebook");
		stock.setPrice(100l);
		return stock ;
	}
}
