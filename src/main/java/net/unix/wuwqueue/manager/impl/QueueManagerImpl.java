package net.unix.wuwqueue.manager.impl;

import net.unix.wuwqueue.manager.QueueManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.*;
import java.util.stream.IntStream;

public class QueueManagerImpl implements QueueManager 
{
    private final Queue<UUID> queues = new PriorityQueue<>();

    @Override
    public void addToQueue(UUID uuid) {
        queues.add(uuid);
    }

    @Override
    public void removeFromQueue(UUID uuid) {
        queues.remove(uuid);
    }

    @Override
    public Player getAndRemove() {
        return Bukkit.getPlayer(queues.poll());
    }

    @Override
    public Queue<UUID> getQueues() {
        return queues;
    }

    @Override
    public int getAt(UUID uuid) {
        return IntStream.range(0, queues.size())
                .filter(i -> queues.toArray()[i] == uuid)
                .findFirst()
                .orElse(-1);
    }
}