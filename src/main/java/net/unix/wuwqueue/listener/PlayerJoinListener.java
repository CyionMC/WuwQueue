package net.unix.wuwqueue.listener;

import lombok.AllArgsConstructor;
import net.unix.wuwqueue.manager.QueueManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@AllArgsConstructor
public class PlayerJoinListener implements Listener 
{
    private final QueueManager queueManager;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        queueManager.addToQueue(event.getPlayer().getUniqueId());
    }
}