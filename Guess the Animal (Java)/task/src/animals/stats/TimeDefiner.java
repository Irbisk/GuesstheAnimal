package animals.stats;

import java.time.LocalTime;
import static animals.Program.resourceBundleMessages;
import static animals.Program.resourceBundlePatterns;

public class TimeDefiner {

    public static void getGreetings() {
        LocalTime localTime = LocalTime.now();
        LocalTime morning = LocalTime.of(5, 0);
        LocalTime afternoon = LocalTime.NOON;
        LocalTime evening = LocalTime.of(18, 0);
        LocalTime night = LocalTime.of(23, 59);
        LocalTime earlyBird = LocalTime.of(3, 0);

        if (localTime.isAfter(morning) && localTime.isBefore(afternoon)) {
            System.out.println(resourceBundleMessages.getString("greeting.morning"));
        } else if (localTime.isAfter(afternoon) && localTime.isBefore(evening)) {
            System.out.println(resourceBundleMessages.getString("greeting.afternoon"));
        } else if (localTime.isAfter(evening) && localTime.isBefore(night)) {
            System.out.println(resourceBundleMessages.getString("greeting.evening"));
        } else if (localTime.isAfter(LocalTime.MIDNIGHT) && localTime.isBefore(earlyBird)) {
            System.out.println(resourceBundleMessages.getString("greeting.night"));
        } else if (localTime.isAfter(earlyBird) && localTime.isBefore(morning)) {
            System.out.println(resourceBundleMessages.getString("greeting.early"));
        } else {
            System.out.println("What time is it now?");
        }
    }

}
