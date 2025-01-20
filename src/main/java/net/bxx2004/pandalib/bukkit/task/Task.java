package net.bxx2004.pandalib.bukkit.task;

import net.bxx2004.pandalib.PandaLib;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class Task {
    public static List<String> ids = new ArrayList<>();

    public Task(UUID id) {
        if (ids.contains(id.toString())) {
            return;
        } else {
            ids.add(id.toString());
            run();
        }
    }

    public static void submit(long delay, long perid, Consumer<BukkitRunnable> consumer) {
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                consumer.accept(this);
            }
        };
        if (delay == -1 && perid == -1) {
            runnable.runTask(PandaLib.initPlugin);
        }
        if (delay != -1 && perid != -1) {
            runnable.runTaskTimer(PandaLib.initPlugin, delay, perid);
        }
        if (delay != -1 && perid == -1) {
            runnable.runTaskLater(PandaLib.initPlugin, delay);
        }
    }

    public static void submitAsynchronously(long delay, long perid, Consumer<BukkitRunnable> consumer) {
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                consumer.accept(this);
            }
        };
        if (delay == -1 && perid == -1) {
            runnable.runTaskAsynchronously(PandaLib.initPlugin);
        }
        if (delay != -1 && perid != -1) {
            runnable.runTaskTimerAsynchronously(PandaLib.initPlugin, delay, perid);
        }
        if (delay != -1 && perid == -1) {
            runnable.runTaskLaterAsynchronously(PandaLib.initPlugin, delay);
        }
    }

    public static void submitCountdown(boolean async, final long allTime, long taskTime, BiConsumer<BukkitRunnable, Long> in, Consumer<BukkitRunnable> end) {
        BukkitRunnable runnable = new BukkitRunnable() {
            long all = allTime;

            @Override
            public void run() {
                if (all <= 0) {
                    end.accept(this);
                    cancel();
                } else {
                    in.accept(this, all);
                }
                all--;
            }
        };
        if (async) {
            runnable.runTaskTimerAsynchronously(PandaLib.initPlugin, 0, taskTime);
        } else {
            runnable.runTaskTimer(PandaLib.initPlugin, 0, taskTime);
        }
    }

    public abstract void run();
}


