package fr.unice.polytech.steats.util;

import fr.unice.polytech.steats.restaurant.Schedule;

import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalTime;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;

public class DailyResetScheduler {

    private final Schedule schedule;
    private final Timer timer = new Timer(true); // true to create a daemon thread

    public DailyResetScheduler(Schedule schedule) {
        this.schedule = schedule;
        scheduleResetTask();
    }

    private void scheduleResetTask() {
        TimerTask resetTask = new TimerTask() {
            public void run() {
                schedule.resetTimeSlotsForNewDay();
            }
        };
        long delay = calculateDelayUntilClosingTime(schedule.getClosingTime());
        timer.scheduleAtFixedRate(resetTask, delay, TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }

    private long calculateDelayUntilClosingTime(LocalTime closingTime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime todayClosingDateTime = LocalDateTime.of(now.toLocalDate(), closingTime);

        if (now.isAfter(todayClosingDateTime)) {
            return Duration.between(now, todayClosingDateTime.plusDays(1)).toMillis();
        } else {
            return Duration.between(now, todayClosingDateTime).toMillis();
        }
    }
}
