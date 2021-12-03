package com.github.daflamingfox;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;

public interface CmdExecutor {
    public void textExecute(MessageCreateEvent event, String[] args);

    public void slashExecute(SlashCommandInteraction sci);
}
