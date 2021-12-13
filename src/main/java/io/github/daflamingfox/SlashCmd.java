package io.github.daflamingfox;

import java.util.List;
import java.util.Optional;
import org.javacord.api.entity.server.Server;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandPermissions;

/**
 *
 * The container for Slash Commands.
 *
 * @author Jeffrey Morris
 */
public class SlashCmd {

    private final Optional<List<SlashCommandOption>> options;
    private final Optional<List<SlashCommandPermissions>> permissions;
    private final Optional<Server> server;
    private final CmdExecutor executor;
    private final String name;
    private final String description;

    /**
     *
     * Creates a new SlashCmd object.
     *
     * @param name The name of the command.
     * @param description The description of the command.
     * @param options The options of the command.
     * @param permissions The permissions of the command.
     * @param server The server the command is on.
     * @param executor The executor of the command.
     *
     * @see {@link java.util.Optional}
     */
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

    /**
     *
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Gets the description of the command.
     *
     * @return The description of the command.
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * Gets the options of the command.
     *
     * @return The options of the command.
     *
     * @see {@link java.util.Optional}
     */
    public Optional<List<SlashCommandOption>> getOptions() {
        return options;
    }

    /**
     *
     * Gets the permissions of the command.
     *
     * @return The permissions of the command.
     *
     * @see {@link java.util.Optional}
     */
    public Optional<List<SlashCommandPermissions>> getPermissions() {
        return permissions;
    }

    /**
     *
     * Gets the server the command is on.
     *
     * @return The server the command is on.
     *
     * @see {@link java.util.Optional}
     */
    public Optional<Server> getServer() {
        return server;
    }

    /**
     *
     * Gets the executor of the command.
     *
     * @return The executor of the command.
     */
    public CmdExecutor getExecutor() {
        return executor;
    }
}
