package framemanagement;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class FrameManager {
    public interface UpdateListener {
        void onUpdate(double dtSeconds);
    }

    public interface RenderListener {
        void onRender();
    }

    private final List<UpdateListener> updateListeners = new CopyOnWriteArrayList<>();
    private final List<RenderListener> renderListeners = new CopyOnWriteArrayList<>();
    private final ScheduledExecutorService exec = Executors.newScheduledThreadPool(2);
    private volatile boolean running = false;
    private final int targetUPS, targetFPS;

    public FrameManager(int targetUPS, int targetFPS) {
        this.targetUPS = targetUPS;
        this.targetFPS = targetFPS;
    }

    public void addUpdateListener(UpdateListener l) {
        updateListeners.add(l);
    }

    public void addRenderListener(RenderListener l) {
        renderListeners.add(l);
    }

    public void start() {
        if (running)
            return;
        running = true;

        long updNs = TimeUnit.SECONDS.toNanos(1) / targetUPS;
        long renNs = TimeUnit.SECONDS.toNanos(1) / targetFPS;

        exec.scheduleAtFixedRate(new UpdateTask(), 0, updNs, TimeUnit.NANOSECONDS);
        exec.scheduleAtFixedRate(() -> {
            if (running)
                for (var l : renderListeners)
                    l.onRender();
        },
                0, renNs, TimeUnit.NANOSECONDS);
    }

    public void stop() {
        running = false;
        exec.shutdownNow();
    }

    private class UpdateTask implements Runnable {
        long last = System.nanoTime();

        public void run() {
            if (!running)
                return;
            long now = System.nanoTime();
            double dt = Math.min((now - last) / 1_000_000_000.0, 0.25);
            last = now;
            for (var l : updateListeners)
                l.onUpdate(dt);
        }
    }
}
