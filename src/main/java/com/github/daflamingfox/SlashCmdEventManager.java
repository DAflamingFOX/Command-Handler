package com.github.daflamingfox;

import org.javacord.api.DiscordApi;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandBuilder;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.SlashCommandPermissionsUpdater;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;

public class SlashCmdEventManager implements SlashCommandCreateListener {

    private long commandId;
    private SlashCmd cmd;

    public SlashCmdEventManager(DiscordApi api, SlashCmd cmd) {
        this.cmd = cmd;

        // StringBuilder is used because of .ifPresentOrElse
        final StringBuilder cmdId = new StringBuilder();

        SlashCommandBuilder scb = new SlashCommandBuilder()
            .setName(cmd.getName())
            .setDescription(cmd.getDescription());

        // if there are options add them to the command.
        cmd.getOptions().ifPresent(scb::setOptions);

        // If server, create command with server and if permissions add permissions,
        // else make command global.
        cmd
            .getServer()
            .ifPresentOrElse(
                server -> {
                    cmdId.append(scb.createForServer(server).join().getIdAsString());
                    cmd
                        .getPermissions()
                        .ifPresent(perms -> {
                            new SlashCommandPermissionsUpdater(server)
                                .addPermissions(perms)
                                .update(Long.parseLong(cmdId.toString()))
                                .join();
                        });
                },
                () -> {
                    cmdId.append(scb.createGlobal(api).join().getIdAsString());
                }
            );

        commandId = Long.parseLong(cmdId.toString());
    }

    @Override
    public void onSlashCommandCreate(SlashCommandCreateEvent event) {
        SlashCommandInteraction sci = event.getSlashCommandInteraction();

        // check that the command executed is the command being handled.
        if (commandId == sci.getCommandId()) {
            cmd.getExecutor().slashExecute(sci);
        }
    }
}
