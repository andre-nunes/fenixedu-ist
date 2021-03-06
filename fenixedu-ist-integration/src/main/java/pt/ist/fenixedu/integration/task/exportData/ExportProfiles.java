package pt.ist.fenixedu.integration.task.exportData;

import java.io.FileOutputStream;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;

import org.fenixedu.bennu.core.domain.Bennu;
import org.fenixedu.bennu.core.domain.UserProfile;
import org.fenixedu.bennu.scheduler.annotation.Task;
import org.fenixedu.bennu.scheduler.custom.CustomTask;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Task(englishTitle = "Export user profiles to shared json file with other applications.")
public class ExportProfiles extends CustomTask {

    @Override
    public void runTask() throws Exception {
        final String profiles =
                Bennu.getInstance().getUserSet().stream().filter(u -> u.getProfile() != null)
                        .map(u -> toJsonObject(u.getProfile())).collect(toJsonArray()).toString();
        final byte[] bytes = profiles.getBytes();
        output("profiles.json", bytes);
        try (final FileOutputStream fos = new FileOutputStream("/afs/ist.utl.pt/ciist/fenix/fenix015/ist/profiles.json")) {
            fos.write(bytes);
        }
    }

    private JsonObject toJsonObject(final UserProfile up) {
        final JsonObject object = new JsonObject();
        object.addProperty("username", up.getUser().getUsername());
        object.addProperty("givenNames", up.getGivenNames());
        object.addProperty("familyNames", up.getFamilyNames());
        object.addProperty("displayName", up.getDisplayName());
        object.addProperty("email", up.getEmail());
        return object;
    }

    public static <T extends JsonElement> Collector<T, JsonArray, JsonArray> toJsonArray() {
        return Collector.of(JsonArray::new, (array, element) -> array.add(element), (one, other) -> {
            one.addAll(other);
            return one;
        }, Characteristics.IDENTITY_FINISH);
    }

}
