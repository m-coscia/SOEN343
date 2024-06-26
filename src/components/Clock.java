package src.components;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Clock {
    private LocalDate date;
    private LocalTime time;
    private ScheduledExecutorService executor;
    private final AtomicBoolean running;
    private volatile double speedMultiplier;

    public Clock() {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.speedMultiplier = 1.0;
        this.running = new AtomicBoolean(false);
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDate(LocalDate d){
        date = d;
    }

    public void start() {
        running.set(true);
        scheduleClockTask();
    }

    private void scheduleClockTask() {
        long period = (long) (1000 / speedMultiplier);
        executor.scheduleAtFixedRate(() -> {
            if (running.get()) {
                time = time.plusSeconds(1);
                if (time.equals(LocalTime.MIN)) { // Check if time is midnight
                    date = date.plusDays(1); // Increment date
                    System.out.println("Date changed to: " + date);
                }
                System.out.println(date + " " + time);
            }
        }, 0, period, TimeUnit.MILLISECONDS);
    }

    public void changeSpeed(double multiplier) {
        if (multiplier > 0 && multiplier != speedMultiplier) {
            speedMultiplier = multiplier;
            System.out.println("Speed changed to: " + speedMultiplier);
            if (running.get()) {
                executor.shutdownNow();
                executor = Executors.newSingleThreadScheduledExecutor();
                scheduleClockTask();
            }
        }
    }

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    public void pause() {
        running.set(false);
    }

    public void shutdown() {
        executor.shutdownNow();
    }

    public AtomicBoolean isRunning() {
        return running;
    }

    public static void main(String[] args) throws InterruptedException {
        Clock clock = new Clock();

        clock.start();
        Thread.sleep(10000); // Clock runs for 10 seconds

        clock.changeSpeed(2); // Speed up the clock
        Thread.sleep(10000); // Clock runs at double speed for 10 seconds

        clock.changeSpeed(0.5); // Slow down the clock
        Thread.sleep(10000); // Clock runs at half speed for 10 seconds

        clock.pause(); // Pause the clock

        clock.shutdown();
    }
}