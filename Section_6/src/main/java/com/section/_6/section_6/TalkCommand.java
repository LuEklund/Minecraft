package com.section._6.section_6;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class TalkCommand implements CommandExecutor, Listener {

    private Section_6 main;

    public TalkCommand(Section_6 main){
        this.main = main;
    }

    private OpenAiService service = new OpenAiService("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", 0);

    private HashMap<UUID, StringBuilder> conversation = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            System.out.println("AI working: " + service != null);
            Player player = (Player) sender;

            if(conversation.containsKey(player.getUniqueId()))
            {
                conversation.remove(player.getUniqueId());
                player.sendMessage("ended chat");
            }else {
                conversation.put(player.getUniqueId(), new StringBuilder("The following is a conversation with an AI assistant. The assistant is helpful, creative, clever, and very friendly.\n" +
                        "\n" +
                        "Human: Hello\n" +
                        "AI:"));
                player.sendMessage("You have started a conversation");
            }
        }
        return false;
    }


    @EventHandler
    public void onASyncPlayerChat(AsyncPlayerChatEvent e)
    {
        Player player = e.getPlayer();
        System.out.println("Chat start");
        if(conversation.containsKey(player.getUniqueId()))
        {
            System.out.println("Chat true");

            e.setCancelled(true);
            player.sendMessage("you: " + e.getMessage());
            Bukkit.getScheduler().runTaskAsynchronously(main, () -> {
                try {
                    player.sendMessage("AI: " + getResponse(player.getUniqueId(), e.getMessage(), 1000));
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
        System.out.println("chat end");

    }

    public String getResponse(UUID uuid, String message, int delay) throws InterruptedException {
        System.out.println("getResponse-start");

        conversation.get(uuid).append("\nHuman:").append(message).append("\nAI:");
        CompletionRequest request = CompletionRequest.builder()
                .prompt(conversation.get(uuid).toString())
                .model("text-davinci-003")
                .temperature(0.9D)
                .maxTokens(150)
                .topP(1.0D)
                .frequencyPenalty(0D)
                .presencePenalty(0.6D)
                .stop(Arrays.asList("Human:","AI:"))
                .build();
        System.out.println("getResponse-end");
        Thread.sleep(delay);
        return service.createCompletion(request).getChoices().get(0).getText();
    }

}
