package net.unix.wuwqueue.task;

import lombok.AllArgsConstructor;
import net.unix.wuwqueue.basic.QueuePlugin;
import net.unix.wuwqueue.manager.QueueManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@AllArgsConstructor
public class QueueTask implements Runnable 
{
    private final QueuePlugin plugin;
    private final QueueManager queueManager;
    private final String server, message;

    @Override
    public void run() {
        if (queueManager.getQueues().isEmpty())
			return;

        final Player player = this.queueManager.getAndRemove();
        connect(player);
        queueManager.getQueues().forEach(queue -> Bukkit.getPlayer(queue).sendMessage(message.replace("{1}", Integer.toString(queueManager.getAt(queue)))));
    }

    private void connect(Player player) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        try {
            dataOutputStream.writeUTF("Connect");
            dataOutputStream.writeUTF(this.server);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        player.sendPluginMessage(this.plugin, "BungeeCord", byteArrayOutputStream.toByteArray());
    }
}