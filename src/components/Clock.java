package src.components;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Clock {
    private LocalTime time;
    private ScheduledExecutorService executor;
    private final AtomicBoolean running;
    private volatile double speedMultiplier;
    private boolean speedChanged = false;


    public Clock() {
        this.time = LocalTime.now();
        this.speedMultiplier = 1.0;
        this.running = new AtomicBoolean(false);
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time){
        this.time = time;
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
                System.out.println(time);
            }
        }, 0, period, TimeUnit.MILLISECONDS);
    }

    public void changeSpeed(double multiplier) {
        if (multiplier > 0 && multiplier != speedMultiplier) {
            speedMultiplier = multiplier;
            speedChanged = true;
            System.out.println("Speed changed to: " + speedMultiplier);
            if (running.get()) {
                executor.shutdownNow();
                executor = Executors.newSingleThreadScheduledExecutor();
                scheduleClockTask();
            }
        }
    }

    public boolean getSpeedChanged(){
        return speedChanged;
    }

    public void pause() {
        running.set(false);
    }

    public void shutdown() {
        executor.shutdownNow();
    }

    public AtomicBoolean isRunning(){
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
