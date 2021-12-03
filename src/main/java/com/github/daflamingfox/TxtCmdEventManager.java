package com.github.daflamingfox;

import java.util.Arrays;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class TxtCmdEventManager implements MessageCreateListener {

    private final TxtCmd command;

    public TxtCmdEventManager(TxtCmd command) {
        this.command = command;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        // ignore bots
        if (event.getMessageAuthor().isBotUser()) {
            return;
        }
        // ignore if no prefix
        if (!event.getMessageContent().startsWith(command.getPrefix())) {
            return;
        }

        // message without prefix
        String commandMessage = event
            .getMessageContent()
            .replaceFirst(command.getPrefix(), "");
        String[] args = commandMessage.split(" "); // split message into args
        String usedKeyword = args[0]; // first arg is the keyword

        // check if command should be called, by keyword then aliases.
        boolean cmdIsCalled = false;
        if (usedKeyword.equalsIgnoreCase(this.command.getKeyword())) {
            cmdIsCalled = true;
        } else if (this.command.getKeywordAliases() != null) { // check if command has aliases
            // goes through all aliases and checks if the used keyword is an alias
            if (
                Arrays
                    .stream(this.command.getKeywordAliases())
                    .anyMatch(str -> str.equalsIgnoreCase(usedKeyword))
            ) {
                cmdIsCalled = true;
            }
        }

        if (cmdIsCalled) {
            // TODO: change to a universal command executor.

            this.command.getExecutor().textExecute(event, args);
        }
    }
}
