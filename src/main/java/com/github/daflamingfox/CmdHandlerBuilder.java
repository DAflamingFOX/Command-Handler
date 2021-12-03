package com.github.daflamingfox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandPermissions;

public class CmdHandlerBuilder {

    private ArrayList<TxtCmd> textCmds;
    private ArrayList<SlashCmd> slashCmds;
    private String prefix;
    private DiscordApi api;

    public CmdHandlerBuilder(DiscordApi api, String prefix) {
        this.api = api;
        this.prefix = prefix == null ? "!" : prefix;
        textCmds = new ArrayList<TxtCmd>();
        slashCmds = new ArrayList<SlashCmd>();
    }

    public CmdHandlerBuilder addTextCommand(
        final String keyword,
        String[] keywordAliases,
        String description,
        String usage,
        final CmdExecutor executor
    ) {
        TxtCmd cmd = new TxtCmd(
            this.prefix,
            keyword,
            keywordAliases,
            executor,
            this.api,
            description == null ? "No description." : description,
            usage == null ? "No usage." : usage
        );
        textCmds.add(cmd);
        return this;
    }

    public CmdHandlerBuilder addSlashCommand(
        final String name,
        String description,
        List<SlashCommandOption> options,
        List<SlashCommandPermissions> permissions,
        Server server,
        final CmdExecutor executor
    ) {
        SlashCmd cmd = new SlashCmd(
            name,
            description == null ? "No description." : description,
            options == null ? Optional.empty() : Optional.of(options),
            permissions == null ? Optional.empty() : Optional.of(permissions),
            server == null ? Optional.empty() : Optional.of(server),
            executor
        );
        slashCmds.add(cmd);
        return this;
    }

    public void build() {
        textCmds.forEach(cmd -> api.addMessageCreateListener(new TxtCmdEventManager(cmd))
        );
        slashCmds.forEach(cmd ->
            api.addSlashCommandCreateListener(new SlashCmdEventManager(api, cmd))
        );
    }
}
