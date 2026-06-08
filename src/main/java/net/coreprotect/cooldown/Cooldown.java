package net.coreprotect.cooldown;

import net.coreprotect.CoreProtect;
import net.coreprotect.config.Config;
import net.coreprotect.language.Phrase;
import net.coreprotect.utility.Chat;
import net.coreprotect.utility.DateTimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cooldown {
    private static final Map<UUID, Cooldown> cooldownMap = new HashMap<>();

    private long endTime;
    private int count;
    private BukkitTask task;

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BukkitTask getTask() {
        return task;
    }

    public void setTask(BukkitTask task) {
        this.task = task;
    }

    public static boolean checkCooldown(Player player) {
        Cooldown cooldown = cooldownMap.computeIfAbsent(player.getUniqueId(), uuid -> new Cooldown());
        if (cooldown.count > 2) {
            if (System.currentTimeMillis() < cooldown.endTime) {
                String cooldownStr = DateTimeUtils.inflectAlt(cooldown.endTime - System.currentTimeMillis());
                Chat.sendComponent(player, Phrase.build(Phrase.WAIT_COOLDOWN, cooldownStr));
                return true;
            }
        }

        cooldown.count++;
        if (cooldown.task != null) {
            cooldown.task.cancel();
            cooldown.task = null;
        }

        long newCooldown = System.currentTimeMillis() + Config.getGlobal().COOLDOWN * 1000L;
        cooldown.endTime = newCooldown;
        cooldown.task = Bukkit.getScheduler().runTaskLater(CoreProtect.getInstance(), () ->
                cooldownMap.remove(player.getUniqueId()), (newCooldown - System.currentTimeMillis()) / 1000 * 20);

        return false;
    }
}
