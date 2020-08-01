package net.unix.wuwqueue.manager;

import org.bukkit.entity.Player;
import java.util.Queue;
import java.util.UUID;

public interface QueueManager 
{
    void addToQueue(UUID uuid);

    void removeFromQueue(UUID uuid);

    Player getAndRemove();

    Queue<UUID> getQueues();

    int getAt(UUID uuid);
}