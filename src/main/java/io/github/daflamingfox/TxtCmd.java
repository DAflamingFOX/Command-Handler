package io.github.daflamingfox;

import org.javacord.api.DiscordApi;

/**
 *
 * The container for Text commands.
 *
 * @author Jeffrey Morris
 */
public class TxtCmd {

    private final String prefix;
    private final String keyword;
    private final String[] keywordAliases;
    private final CmdExecutor executor;
    private final DiscordApi api;
    private final String description;
    private final String usage;

    /**
     *
     * Creates a new TxtCmd object.
     *
     * @param prefix The command prefix.
     * @param keyword  The command keyword.
     * @param keywordAliases The command keyword aliases.
     * @param executor The command executor.
     * @param api The DiscordApi of your bot.
     * @param description The command description.
     * @param usage The command usage.
     */
    public TxtCmd(
        String prefix,
        String keyword,
        String[] keywordAliases,
        CmdExecutor executor,
        DiscordApi api,
        String description,
        String usage
    ) {
        this.prefix = prefix;
        this.keyword = keyword;
        this.keywordAliases = keywordAliases;
        this.executor = executor;
        this.api = api;
        this.description = description;
        this.usage = usage;
    }

    /**
     *
     * Gets the command prefix.
     *
     * @return The command prefix.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     *
     * Gets the command keyword.
     *
     * @return The command keyword.
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     *
     * Gets the command keyword aliases.
     *
     * @return The command keyword aliases.
     */
    public String[] getKeywordAliases() {
        return keywordAliases;
    }

    /**
     *
     * Gets the command executor.
     *
     * @return The command executor.
     */
    public CmdExecutor getExecutor() {
        return executor;
    }

    /**
     *
     * Gets the DiscordApi of your bot.
     *
     * @return The DiscordApi of your bot.
     */
    public DiscordApi getApi() {
        return api;
    }

    /**
     *
     * Gets the command description.
     *
     * @return The command description.
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * Gets the command usage.
     *
     * @return The command usage.
     */
    public String getUsage() {
        return usage;
    }
}
