//using EventListener

public class ReadyListener implements EventListener
{
    public static void main(String[] args)
            throws LoginException, RateLimitedException, InterruptedException
    {
        // Note: It is important to register your ReadyListener before building
        JDA jda = new JDABuilder(AccountType.BOT)
            .setToken("token")
            .addEventListener(new ReadyListener())
            .buildBlocking();
    }

    @Override
    public void onEvent(Event event)
    {
        if (event instanceof ReadyEvent)
            System.out.println("API is ready!");
    }
}

//using ListenerAdapter

public class MessageListener extends ListenerAdapter
								{
   		 				public static void main(String[] args)
            									throws LoginException, RateLimitedException, InterruptedException
    					{
        						JDA jda = new JDABuilder(AccountType.BOT).setToken("token").buildBlocking();
     							   jda.addEventListener(new MessageListener());
    				}

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        					if (event.isFromType(ChannelType.PRIVATE))
        {
           						 System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                                    event.getMessage().getContentDisplay());
        }
        else
        {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                        event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                        event.getMessage().getContentDisplay());
        }
    }
}


//sharding a bot
public static void main(String[] args) throws Exception
{
    		JDABuilder shardBuilder = new JDABuilder(AccountType.BOT).setToken(args[0]);
    //register your listeners here using shardBuilder.addEventListener(...)
    		shardBuilder.addEventListener(new MessageListener());
    								for (int i = 0; i < 10; i++)
    {
        				shardBuilder.useSharding(i, 10)
                   				 .buildAsync();
    }
}

//Maven
<dependency>
    <groupId>net.dv8tion</groupId>
    <artifactId>JDA</artifactId>
    <version>VERSION</version>
</dependency>

<repository>
    <id>jcenter</id>
    <name>jcenter-bintray</name>
    <url>http://jcenter.bintray.com</url>
</repository>
