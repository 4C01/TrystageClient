package cn.trystage;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class discordRP {
    private boolean running = true;
    private long created = 0;

    public void start() {
        this.created = System.currentTimeMillis();

        DiscordEventHandlers handlers ;
        handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(discordUser -> {
            System.out.println("Welcome " + discordUser.username + "#" + discordUser.discriminator + "to Use Trystage Client.");
            update("Booting up...","awa");
            running = true;
        }).build();

        DiscordRPC.discordInitialize("1296913240504402001", handlers,true);
    }
    public void shutdown(){
        running = false;
        DiscordRPC.discordShutdown();
    }
    public void update(String firstLine,String secondLine){
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondLine);
        b.setBigImage("large","");
        b.setDetails(firstLine);
        b.setStartTimestamps(created);

        DiscordRPC.discordUpdatePresence(b.build());
    }

}