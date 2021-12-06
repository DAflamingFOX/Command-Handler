package io.github.daflamingfox;

import java.util.List;
import java.util.Optional;
import org.javacord.api.entity.server.Server;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandPermissions;

public class SlashCmd {

    private final Optional<List<SlashCommandOption>> options;
    private final Optional<List<SlashCommandPermissions>> permissions;
    private final Optional<Server> server;
    private final CmdExecutor executor;
    private final String name;
    private final String description;

    public SlashCmd(
        String name,
        String description,
        Optional<List<SlashCommandOption>> options,
        Optional<List<SlashCommandPermissions>> permissions,
        Optional<Server> server,
        CmdExecutor executor
    ) {
        this.name = name;
        this.description = description;
        this.options = options;
        this.permissions = permissions;
        this.server = server;
        this.executor = executor;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Optional<List<SlashCommandOption>> getOptions() {
        return options;
    }

    public Optional<List<SlashCommandPermissions>> getPermissions() {
        return permissions;
    }

    public Optional<Server> getServer() {
        return server;
    }

    public CmdExecutor getExecutor() {
        return executor;
    }
}
