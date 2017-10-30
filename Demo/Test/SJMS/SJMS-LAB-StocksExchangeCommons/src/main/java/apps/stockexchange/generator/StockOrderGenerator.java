package apps.stockexchange.generator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import apps.stockexchange.beans.Order;
import apps.stockexchange.beans.OrderType;
import apps.stockexchange.beans.StockAgent;
import apps.stockexchange.beans.StockType;
import apps.stockexchange.beans.TIFType;

public class StockOrderGenerator {

	//private static final int 	
	private static final Random random= new Random();
	
	
	
	
	public static List<Order> generateOrders (int quantity){
		
		List<Order> orders = new ArrayList<Order>();
		
		for (int i = 0; i<quantity; i++){
			orders.add(generateOrder(i));	
		}			
		return orders;
	}
	
	
	
	private static Order generateOrder(int index){
		
		int priceInt=random.nextInt(999);
		int priceDecimals=random.nextInt(999);
		int quantityRnd = random.nextInt(100);
		
		int quantity=quantityRnd*100;
		
		StockAgent stockAgent= generateStockAgent();		
		
		Order order = new Order();
		order.setStockAgentId(stockAgent.getId());
		order.setStockAgentName(stockAgent.getName());
		order.setOrderId(UUID.randomUUID().toString());
		order.setPrice(new BigDecimal(priceInt+"."+priceDecimals));
		order.setQuantity(quantity);
		order.setStockName(generateStockName());
		//order.setStockType(calculateStockType(index));
		order.setStockType(StockType.CERTIFICATO_AZIONARIO);
		order.setTifType(TIFType.Day);
		order.setType(calculateOrderType(index));
		return order;
	}
	
	private static OrderType calculateOrderType(int orderType){
		int orderTypeMod=orderType%2;
		
		switch (orderTypeMod){
		case 0: return OrderType.LIMIT; //order to sell at that price
		case 1: return OrderType.MARKET; //order to buy at that price
		default: throw new RuntimeException("Unexepcted OrderType. Found "+orderTypeMod);
		}
	}
	
	private static StockType calculateStockType(int stockType){
		
		int stockTypeMod=stockType%7;
		
		switch (stockTypeMod){
			case 0: return StockType.CERTIFICATO_AZIONARIO;
			case 1:	return StockType.CERTIFICATO_OBBLIGAZIONARIO_DEBITO_SOVRANO;
			case 2:	return StockType.CERTIFICATO_OBBLIGAZIONARIO_PRIVATO;
			case 3:	return StockType.EQUITY_SWAP;
			case 4:	return StockType.OPZIONE;
			case 5:	return StockType.QUOTA_FONDO_INVESTIMENTO;
			case 6:	return StockType.TITOLO_DERIVATO;
			default: throw new RuntimeException("Unexepcted stock type. Found "+stockTypeMod);
		}
	}
	
	private static String generateStockName(){			
		return extraxtRandomeElement(GeneratorConsts.STOCK_NAMES);
	}
	
	private static StockAgent generateStockAgent(){			
		return extraxtRandomeElement(GeneratorConsts.STOCK_AGENTS);
	}
	
	
	private static <T> T extraxtRandomeElement(T [] typedArray){		
		int index=random.nextInt(typedArray.length);
		return typedArray[index];
	}
}
