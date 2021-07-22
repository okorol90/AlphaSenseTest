package petStore.rest.builder;

import petStore.rest.model.IdAndNameObj;
import petStore.rest.model.Pet;
import petStore.rest.model.Status;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import static petStore.utils.GeneratorUtils.*;

public class PetFactory {
    public static Pet newDefaultPet() {
        return Pet.builder()
                .id(BigInteger.valueOf(0))
                .category(IdAndNameObj.builder()
                        .id(BigInteger.valueOf(0))
                        .name(generateName())
                        .build())
                .name(generateName())
                .photoUrls(Arrays.asList(generatePhotoUrl(), generatePhotoUrl()))
                .tags(Collections.singletonList(IdAndNameObj.builder()
                        .id(BigInteger.valueOf(0))
                        .name(generateName())
                        .build()))
                .status(Status.AVAILABLE.getValue()).build();
    }
}
