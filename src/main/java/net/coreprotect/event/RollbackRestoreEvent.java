package net.coreprotect.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RollbackRestoreEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final String user;
    private final long startTime;
    private final long endTime;
    private final Integer[] radius;
    private final List<String> checkUsers;
    private final int preview;

    private Runnable finishCallback;
    private boolean cancelled;

    public RollbackRestoreEvent(String user, long startTime, long endTime, Integer[] radius, List<String> checkUsers, int preview) {
        super(true);
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.radius = radius;
        this.checkUsers = checkUsers;
        this.preview = preview;
    }

    public String getUser() {
        return user;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public Integer[] getRadius() {
        return radius;
    }

    public List<String> getCheckUsers() {
        return checkUsers;
    }

    public int getPreview() {
        return preview;
    }

    public Runnable getFinishCallback() {
        return finishCallback;
    }

    public void setFinishCallback(Runnable finishCallback) {
        this.finishCallback = finishCallback;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
