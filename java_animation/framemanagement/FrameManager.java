package framemanagement;

import java.util.ArrayList;
import java.util.List;

public class FrameManager implements Runnable {
    double interpolation = 0;
    final int TICKS_PER_SECOND = 25;
    final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    final int MAX_FRAMESKIP = 5;

    List<Tickable> tickables = new ArrayList<>();

    public void addTickable(Tickable tickable) {
        this.tickables.add(tickable);
    }

    @Override
    public void run() {
        double next_game_tick = System.currentTimeMillis();
        int loops;

        while (true) {
            loops = 0;
            while (System.currentTimeMillis() > next_game_tick
                    && loops < MAX_FRAMESKIP) {

                resolveTickables();

                next_game_tick += SKIP_TICKS;
                loops++;
            }
        }
    }

    private void resolveTickables() {
        this.tickables.forEach(Tickable::tick);
    }
}
