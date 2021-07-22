package petStore.utils;

import java.time.Duration;
import java.util.concurrent.Callable;

import static org.awaitility.Awaitility.with;

public class WaiterUtils {
    public static void waitUntilResponseCondition(int duration, Callable<Boolean> condition) {
        try {
            with().pollInterval(Duration.ofMillis(1000))
                    .await().atMost(Duration.ofSeconds(duration))
                    .until(condition);
        } catch (Exception ignored) {
        }
    }
}
