package utils;

import java.time.Duration;

public final class TimeUtils {
    private TimeUtils(){};
    public static int getMilliseconds(long timeInMilliseconds){
        return (int) (timeInMilliseconds % 1000);
    }

    public static String getFormattedDuration(long timeInMilliseconds){
        Duration duration = Duration.ofMillis(timeInMilliseconds);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        long milliseconds = duration.toMillisPart();
        return String.format("%02d:%02d:%02d,%03d", hours, minutes, seconds, milliseconds);
    }


}
