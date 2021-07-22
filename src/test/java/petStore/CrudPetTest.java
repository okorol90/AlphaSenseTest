package petStore;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import petStore.rest.PetStoreApi;
import petStore.rest.builder.OrderFactory;
import petStore.rest.builder.PetFactory;
import petStore.rest.model.Order;
import petStore.rest.model.Pet;
import petStore.utils.WaiterUtils;

import java.math.BigInteger;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static petStore.utils.GeneratorUtils.generateNumber;

public class CrudPetTest {
    PetStoreApi api = new PetStoreApi();

    @Test(groups = {"pet"})
    public void addNewPet() {
        Pet newPet = api.addPetSuccessful(PetFactory.newDefaultPet());
//        WaiterUtils.waitUntilResponseCondition(10, api.petIsCreated(newPet.getId())); Double request is changed data - bug on the site side
        Pet createdPet = api.getPetByIdSuccess(newPet.getId());
        assertReflectionEquals("Pets aren't equal", newPet, createdPet);
    }

    @Test(groups = {"pet"})
    public void removePet() {
        Pet newPet = api.addPetSuccessful(PetFactory.newDefaultPet());
//        WaiterUtils.waitUntilResponseCondition(10, api.petIsCreated(newPet.getId())); Double request is changed data - bug on the site side
        api.removePetSuccessful(newPet.getId());
        Response removedPet = api.getPetById(newPet.getId());
        WaiterUtils.waitUntilResponseCondition(10, api.petIsRemoved(newPet.getId()));
        Assertions.assertThat(removedPet.statusCode())
                .as("Pet aren't removed")
                .isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Test(groups = {"order"})
    public void placingOrderToBuy() {
        Pet newPet = api.addPetSuccessful(PetFactory.newDefaultPet());
//        WaiterUtils.waitUntilResponseCondition(10, api.petIsCreated(newPet.getId())); Double request is changed data - bug on the site side
        Order newOrder = OrderFactory.newDefaultOrder(newPet.getId());
        Order placedOrder = api.addOrderSuccessful(newOrder);
        WaiterUtils.waitUntilResponseCondition(10, api.orderIsCreated(newPet.getId())); //Double request is changed data - bug on the site side
        Assertions.assertThat(placedOrder.getStatus())
                .as("Order isn't placed")
                .isEqualTo("placed");
        Order actualOrder = api.getOrderByIdSuccess(placedOrder.getId()); //Shipping data is different sometime - bug on the site side
        assertReflectionEquals("Orders aren't equal", placedOrder, actualOrder);
    }

    @Test(groups = {"order"})
    public void getOrderById() {
        BigInteger wrongId = generateNumber();
        Order placedOrder = api.getOrderByIdSuccess(wrongId);
        Assertions.assertThat(placedOrder.getStatus())
                .as("Order isn't placed")
                .isEqualTo("placed");
    }
}
