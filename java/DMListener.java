import java.time.Instant;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DMListener extends ListenerAdapter
{
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent e)
	{
		String msg = e.getMessage().getContentRaw();
		String[] args = msg.split(" ");
		
		// ^modmail
		if(args[0].equalsIgnoreCase(Main.PREFIX + "modmail"))
		{
			if(args.length == 1)
			{
				e.getChannel().sendMessage("Please specify a message for the modmail!").queue();
				return;
			}
			
			//extract message
			String message = "";
			for(int i = 1; i < args.length; i++)
				message += " " + args[i];
			
			message = message.substring(1);
			
			//send the message anonymously
			e.getJDA().getTextChannelById(Main.MODLOGS).sendMessage(getAnonMessageEmbed(message)).queue();
			return;
		}
	}
	
	public static MessageEmbed getAnonMessageEmbed(String message)
	{
		return new EmbedBuilder()
		.setTitle("New Modmail!")
		.setDescription(message)
		.setTimestamp(Instant.now())
		.build();
	}
}
