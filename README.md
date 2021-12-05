# command-Handler

This is a command handler for [Javacord](https://github.com/Javacord/Javacord)

It manages *both* text and slash commands!

## Usage

This command handler is supposed to be as intuitive as possible; If you have any questions feel free to create an issue.

We are going to assume that you already know how to use the Javacord library; but if not, you may check [here](https://javacord.org/wiki/)


With that being said here is a basic example of a bot using this library.

```java
public class Main {

    public static void main(String[] args) {
        // Create a new bot
        DiscordApi api = new DiscordApiBuilder()
            .setAllNonPrivilegedIntents()
            .setToken("Secret-sauce")
            .login()
            .join();
        System.out.println("Bot online!");

        // remove all old slash commands
        api.getSlashCommandCreateListeners().forEach(api::removeListener);

        // create a new CmdHandlerBuilder
        CmdHandlerBuilder builder = new CmdHandlerBuilder(api, "-");

        // add the commands to the builder.
        builder.addTextCommand(
            "ping",
            new String[] { "bizz", "beep" },
            "Shows the current latency of the bot.",
            builder.getPrefix().concat("ping"),
            new Ping()
        );
        builder.addSlashCommand(
            "ping",
            "Shows the current latency of the bot.",
            null,
            null,
            null,
            new Ping()
        );

        // build the builder; activating the commands.
        builder.build();
        System.out.println("Commands activated!");
    }
}

public class Ping implements CmdExecutor {

    @Override
    public void textExecute(MessageCreateEvent event, String[] args) {
        long start = System.currentTimeMillis();
        Message msg = event.getChannel().sendMessage("Pinging...").join();
        long end = System.currentTimeMillis();
        msg.delete();
        event.getChannel().sendMessage("Pong! " + (end - start) + "ms");
    }

    @Override
    public void slashExecute(SlashCommandInteraction sci) {
        long start = System.currentTimeMillis();
        var later = sci.respondLater().join();
        long end = System.currentTimeMillis();
        later.setContent("Pong! " + (end - start) + "ms").update().join();
    }
}
```

That may seem pretty heafty; let's walk through it, starting with the Main class.

First we create the CmdManagerBuilder.
```java
CmdHandlerBuilder builder = new CmdHandlerBuilder(api, "-");
```

The CmdHandlerBuilder is the big deal, the pièce de résistance; it controls everything and gets everything in order before you activate the commands.

The constructor takes in the `DiscordApi` of your bot and a `String` which is the prefix for text commands.

The next thing we do is add a text command by calling `CmdHandlerBuilder.addcommand();`
```java
builder.addTextCommand(
            "ping",
            new String[] { "bizz", "beep" },
            "Shows the current latency of the bot.",
            builder.getPrefix().concat("ping"),
            new Ping()
        );
```

Then we add a slash command.

```java
builder.addSlashCommand(
    "ping",
    "Shows the current latency of the bot.",
    null,
    null,
    null,
    new Ping()
);
```

Because of how simple a ping command is, we have some unnecisary values, so we set them to `null`, these will be talked about later. 

Notice that we pass a new Ping class; we'll talk about that in a second.

The final step that needs to happen is building/activating the commands.
```java
CmdHandlerBuilder.build();
```
That's all for the `Main` class, pretty simple.

Now the `Ping` class:

All of your *command* classes should implement `CmdExecutor`; this interface contains two methods: `textExecute()` and `slashExecute()`, respectivly these are what are called when each version of your command is used.

The first method we'll talk about is `textExecute()`
```java
textExecute(MessageCreateEvent event, String[] args) {
    long start = System.currentTimeMillis();
    Message msg = event.getChannel().sendMessage("Pinging...").join();
    long end = System.currentTimeMillis();
    msg.delete();
    event.getChannel().sendMessage("Pong! " + (end - start) + "ms");
}
```
This method contains a `MessageCreateEvent` and your args, which you use to implement your own command, this is a simple ping command which cacluates the ping of the bot.

Secondly we have `slashExecute()`

```java
public void slashExecute(SlashCommandInteraction sci) {
    long start = System.currentTimeMillis();
    var later = sci.respondLater().join();
    long end = System.currentTimeMillis();
    later.setContent("Pong! " + (end - start) + "ms").update().join();
}
```

This method contains your `SlashCommandInteraction` which you use to implment your slash command.

For options, server, and permissions of slash commands the recommended way to do this is as follows in your main:
```java
builder.addSlashCommand(
    "example", 
    "This command is an example", 
    Foo.getOptions(), 
    Foo.getPermissions(), 
    Foo.getServer(api), 
    new Foo());
```
and in `Foo`...
```java
public class Foo implements CmdExecutor {

    public static List<SlashCommandOption> getOptions() {
        return Arrays.asList(
            SlashCommandOption.createWithChoices(
                SlashCommandOptionType.BOOLEAN,
                "BIZZ",
                "bizz?",
                false
            )
        );
    }

    public static List<SlashCommandPermissions> getPermissions() {
        return Arrays.asList(
            SlashCommandPermissions.create(123123, SlashCommandPermissionType.ROLE, false)
        );
    }

    public static Server getServer(DiscordApi api) {
        return api.getServerById(123123).get();
    }

    @Override
    public void textExecute(MessageCreateEvent event, String[] args) {
        // only a slash command so not required.
    }

    @Override
    public void slashExecute(SlashCommandInteraction sci) {
        sci.createImmediateResponder().setContent("Bar").respond().join();
    }
}
```

Thats about all there is; fairly simple compared to having everything spread out everywhere, now you just need two files.

---

## Contributing

Everyone is welcome to contribute, and in fact is encouraged, if you see something that could be better lets work on it!

Simply fork this repo; make your changes, then create a pull request in this repo!

