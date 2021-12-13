package io.github.daflamingfox;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;

/**
 * The executor for commands, has a method for text, and one for slash.
 *
 * @author Jeffrey Morris
 */
public interface CmdExecutor {
    /**
     * Executes the text command.
     *
     * @param event The event for the command.
     * @param args The arguments for the command.
     */
    public void textExecute(MessageCreateEvent event, String[] args);

    /**
     *
     * Executes the slash command.
     *
     * @param interaction The interaction for the command.
     */
    public void slashExecute(SlashCommandInteraction sci);
}
