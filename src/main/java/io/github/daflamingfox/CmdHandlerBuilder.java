package io.github.daflamingfox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandPermissions;

/**
 *
 * The main handler class, this is the manager for starting all the backend garbage.
 *
 * @author Jeffrey Morris
 */
public class CmdHandlerBuilder {

    private ArrayList<TxtCmd> textCmds;
    private ArrayList<SlashCmd> slashCmds;
    private String prefix;
    private DiscordApi api;

    /**
     *
     * Creates a new CmdHandlerBuilder object
     *
     * @param api the discord api of your bot
     * @param prefix the prefix to use for text commands.
     *
     */
    public CmdHandlerBuilder(final DiscordApi api, final String prefix) {
        this.api = api;
        this.prefix = prefix == null ? "!" : prefix;
        textCmds = new ArrayList<TxtCmd>();
        slashCmds = new ArrayList<SlashCmd>();
    }

    /**
     *
     * Adds a text command to the handler.
     *
     * This command is not activated until you call {@link #build()}
     *
     * @param keyword the keyword that triggers the command
     * @param keywordAliases aliases for the keyword
     * @param description the description of the command
     * @param usage the usage of the command
     * @param executor the command itself
     * @return this builder
     */
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

    /**
     *
     * Adds a slash command to the handler.
     *
     * This command is not activated until you call {@link #build()}
     *
     * @param name the name of the command
     * @param description the description of the command
     * @param options the options of the command
     * @param permissions the permissions of the command
     * @param server the server the command is for
     * @param executor the command itself
     * @return this builder
     */
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

    /**
     *
     * Builds the handler and activates all the commands.
     *
     */
    public void build() {
        textCmds.forEach(cmd -> api.addMessageCreateListener(new TxtCmdEventManager(cmd))
        );
        slashCmds.forEach(cmd ->
            api.addSlashCommandCreateListener(new SlashCmdEventManager(api, cmd))
        );
    }

    /**
     *
     * @return the prefix that the command handler is looking for when activating text commands.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     *
     * @return a {@link ArrayList} of type {@link TxtCmd} containing all the text commands.
     */
    public ArrayList<TxtCmd> getTextCommands() {
        return textCmds;
    }

    /**
     *
     * @return a {@link ArrayList} of type {@link SlashCmd} containing all the slash commands.
     */
    public ArrayList<SlashCmd> getSlashCommands() {
        return slashCmds;
    }
}
