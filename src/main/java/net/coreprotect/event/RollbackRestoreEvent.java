package net.coreprotect.event;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RollbackRestoreEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final String user;
    private final Location location;
    private final Integer[] radius;
    private final String time;
    private final List<String> checkUsers;
    private final int action;
    private final int preview;

    private Runnable finishCallback;
    private boolean cancelled;

    public RollbackRestoreEvent(String user, Location location, Integer[] radius, String time, List<String> checkUsers, int action, int preview) {
        super(true);
        this.user = user;
        this.location = location;
        this.radius = radius;
        this.time = time;
        this.checkUsers = checkUsers;
        this.action = action;
        this.preview = preview;
    }

    public String getUser() {
        return user;
    }

    public Location getLocation() {
        return location;
    }

    public Integer[] getRadius() {
        return radius;
    }

    public String getTime() {
        return time;
    }

    public List<String> getCheckUsers() {
        return checkUsers;
    }

    public int getAction() {
        return action;
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
