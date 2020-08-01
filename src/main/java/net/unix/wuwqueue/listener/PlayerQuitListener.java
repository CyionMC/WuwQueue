package net.unix.wuwqueue.listener;

import lombok.AllArgsConstructor;
import net.unix.wuwqueue.manager.QueueManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@AllArgsConstructor
public class PlayerQuitListener implements Listener 
{
    private final QueueManager queueManager;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        queueManager.removeFromQueue(event.getPlayer().getUniqueId());
    }
}