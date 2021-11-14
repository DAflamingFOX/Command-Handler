package com.github.daflamingfox;

import org.javacord.api.DiscordApi;

public class TxtCmd {

    private final String prefix;
    private final String keyword;
    private final String[] keywordAliases;
    private final CmdExecutor executor;
    private final DiscordApi api;
    private final String description;
    private final String usage;

    public TxtCmd(String prefix, String keyword, String[] keywordAliases, CmdExecutor executor, DiscordApi api,
            String description, String usage) {
        this.prefix = prefix;
        this.keyword = keyword;
        this.keywordAliases = keywordAliases;
        this.executor = executor;
        this.api = api;
        this.description = description;
        this.usage = usage;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getKeyword() {
        return keyword;
    }

    public String[] getKeywordAliases() {
        return keywordAliases;
    }

    public CmdExecutor getExecutor() {
        return executor;
    }

    public DiscordApi getApi() {
        return api;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }


}
