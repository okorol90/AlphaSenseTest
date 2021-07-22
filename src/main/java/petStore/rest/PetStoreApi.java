package petStore.rest;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import petStore.rest.model.Order;
import petStore.rest.model.Pet;
import petStore.utils.PropertyLoader;

import java.math.BigInteger;
import java.util.concurrent.Callable;

import static org.assertj.core.api.Assertions.assertThat;

public class PetStoreApi extends AbstractApi{

    @Step
    public Response addPet(Pet pet){
        return getClient().body(pet).post(Endpoint.PET);
    }

    @Step
    public Pet addPetSuccessful(Pet pet){
        Response res =getClient().body(pet).post(Endpoint.PET);
        assertThat(res.statusCode())
                .as("Status code is wrong")
                .isEqualTo(HttpStatus.SC_OK);
        return res.as(Pet.class);
    }

    @Step
    public Response getPetById(BigInteger id){
        return getClient().get(Endpoint.PET+"/"+id);
    }
    
    @Step
    public Pet getPetByIdSuccess(BigInteger id){
        Response res = getClient().get(Endpoint.PET+"/"+id);
        assertThat(res.statusCode())
                .as("Status code is wrong")
                .isEqualTo(HttpStatus.SC_OK);
        return res.as(Pet.class);
    }

    @Step
    public Response removePet(BigInteger id){
        return getClient().header("api_key",PropertyLoader.getProperty("apiKey"))
                .delete(Endpoint.PET+"/"+id);
    }

    @Step
    public Pet removePetSuccessful(BigInteger id){
        Response res =getClient().header("api_key",PropertyLoader.getProperty("apiKey"))
                .delete(Endpoint.PET+"/"+id);
        assertThat(res.statusCode())
                .as("Status code is wrong")
                .isEqualTo(HttpStatus.SC_OK);
        return res.as(Pet.class);
    }

    @Step
    public Response addOrder(Order order){
        return getClient().body(order).post(Endpoint.ORDER);
    }

    @Step
    public Order addOrderSuccessful(Order order){
        Response res = getClient().body(order).post(Endpoint.ORDER);
        assertThat(res.statusCode())
                .as("Status code is wrong")
                .isEqualTo(HttpStatus.SC_OK);
        return res.as(Order.class);
    }

    @Step
    public Response getOrderById(BigInteger id){
        return getClient().get(Endpoint.ORDER+"/"+id);
    }

    @Step
    public Order getOrderByIdSuccess(BigInteger id){
        Response res = getClient().get(Endpoint.ORDER+"/"+id);
        assertThat(res.statusCode())
                .as("Status code is wrong")
                .isEqualTo(HttpStatus.SC_OK);
        return res.as(Order.class);
    }

    public Callable<Boolean> petIsCreated(BigInteger id){
        return () -> getPetById(id).statusCode() == HttpStatus.SC_OK;
    }

    public Callable<Boolean> petIsRemoved(BigInteger id){
        return () -> getPetById(id).statusCode() == HttpStatus.SC_NOT_FOUND;
    }

    public Callable<Boolean> orderIsCreated(BigInteger id){
        return () -> getOrderById(id).statusCode() == HttpStatus.SC_OK;
    }

    @Override
    protected RequestSpecification getClient() {
        return RestClientFactory.getClient(PropertyLoader.getProperty("baseUrl"));
    }

    private final static class Endpoint{
        public static final String PET = "/v2/pet";
        public static final String ORDER = "/v2/store/order";
    }
}
