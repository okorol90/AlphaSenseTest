package petStore.rest.builder;

import petStore.rest.model.Order;

import java.math.BigInteger;

import static petStore.utils.DateUtils.getDateInFuture;

public class OrderFactory {
    public static Order newDefaultOrder(BigInteger petId){
        return Order.builder()
                .petId(petId)
                .quantity(1)
                .shipDate(getDateInFuture(2))
                .status("placed")
                .complete(true)
                .build();
    }

}
