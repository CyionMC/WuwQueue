package net.unix.wuwqueue.basic;

import net.unix.wuwqueue.listener.PlayerJoinListener;
import net.unix.wuwqueue.listener.PlayerQuitListener;
import net.unix.wuwqueue.manager.QueueManager;
import net.unix.wuwqueue.manager.impl.QueueManagerImpl;
import net.unix.wuwqueue.task.QueueTask;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class QueuePlugin extends JavaPlugin 
{
    private final QueueManager queueManager;

    public QueuePlugin() {
        queueManager = new QueueManagerImpl();
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        final FileConfiguration fileConfiguration = getConfig();
		registerListeners(getServer().getPluginManager(), new PlayerJoinListener(queueManager), new PlayerQuitListener(queueManager));
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getScheduler().runTaskTimer(this, new QueueTask(this, queueManager, fileConfiguration.getString("server"), fileConfiguration.getString("message")), 30L, 30L);
    }
	
	public void registerListeners(PluginManager pluginManager, Listener... listeners) {
		Arrays.stream(listeners).forEachOrdered(listener -> pluginManager.registerEvents(listener, this));
	}
}