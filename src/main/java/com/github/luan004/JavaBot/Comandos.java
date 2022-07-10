package com.github.luan004.JavaBot;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class Comandos extends Main {
	
	
	// STOP
	public static void stop(DiscordApi api) {
		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().equalsIgnoreCase(getPrefixo() + "stop")) {
				event.getChannel().sendMessage("Adios");
				System.exit(getPrefixo());
			}
		});
	}
	
	// PING
	public static void ping(DiscordApi api) {
		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().equalsIgnoreCase(getPrefixo()+"ping")) {
				event.getChannel().sendMessage("Pong!");
				
				logUsoComando(event.getMessageContent(), event.getApi().getServerChannelById(event.getChannel().getIdAsString()).get().getName().toString());
			}
		});
	}
	
	// CARA OU COROA
	public static void caraCoroa(DiscordApi api) {
		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().equalsIgnoreCase(getPrefixo()+"caracoroa")) {
				
				Random random = new Random();
				int i = random.nextInt(2);
				
				if (i == 1) {
					event.getChannel().sendMessage(":man: **| Cara!**");
				}
				else {
					event.getChannel().sendMessage(":crown: **| Coroa!**");
				}
				
				logUsoComando(event.getMessageContent(), event.getApi().getServerChannelById(event.getChannel().getIdAsString()).get().getName().toString());
			}
		});
	}
	
	// SAY
	public static void say(DiscordApi api) {
		api.addMessageCreateListener(event -> {
		    if(event.getMessage().getContent().startsWith(getPrefixo()+"say ")){
		        event.getChannel().sendMessage(event.getMessage().getContent().substring(5));
		        
		        logUsoComando(event.getMessageContent(), event.getApi().getServerChannelById(event.getChannel().getIdAsString()).get().getName().toString());
		    }
		});
	}
	
	// CONECTAR EM CALL
	public static void conectar(DiscordApi api) {
		api.addMessageCreateListener(event -> {
		    if(event.getMessage().getContent().startsWith(getPrefixo()+"conectar")){
		    	
		    	//if(event.getMessage().getUserAuthor().get().getConnectedVoiceChannel(null) != null) {
		    //		System.out.println("vc ta em call");
		    	//}
		    	
		    	ServerVoiceChannel channel = event.getApi().getServerVoiceChannelById("995385008124608556").get();
		    	channel.connect().thenAccept(audioConnection -> {
		    	    // Do stuff
		    	}).exceptionally(e -> {
		    	    // Failed to connect to voice channel (no permissions?)
		    	    e.printStackTrace();
		    	    return null;
		    	});
		    	
		    	
		        event.getChannel().sendMessage("O pai ta na call! :sunglasses:");
		        logUsoComando(event.getMessageContent(), event.getApi().getServerChannelById(event.getChannel().getIdAsString()).get().getName().toString());
		    }
		});
	}
	
	// SORTEIO
	public static void sorteio(DiscordApi api) {
		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().startsWith(getPrefixo()+"sorteio ")) {
				
				System.out.println("ó");
				
				Random random = new Random();
				event.getChannel().sendMessage(":game_die: **| " + random.nextInt(1, (Integer.parseInt(event.getMessage().getContent().substring(9))+1)) + "!**");
				logUsoComando(event.getMessageContent(), event.getApi().getServerChannelById(event.getChannel().getIdAsString()).get().getName().toString());
			}
		});
	}

	public static void avatar(DiscordApi api) {
		api.addMessageCreateListener(event -> {
		    if(event.getMessage().getContent().equalsIgnoreCase(getPrefixo()+"avatar")){
		    	
		    	EmbedBuilder embed = new EmbedBuilder()
		    		    .setTitle("Que belo avatar!")
		    		    .setImage(event.getMessage().getUserAuthor().get().getAvatar());
		    	event.getChannel().sendMessage(embed);
		        
		        logUsoComando(event.getMessageContent(), event.getApi().getServerChannelById(event.getChannel().getIdAsString()).get().getName().toString());
		    }
			else if(event.getMessage().getContent().startsWith(getPrefixo()+"avatar " ) && event.getMessage().getContent().substring(8).length() == 18){
		        
		    	EmbedBuilder embed;
				try {
					embed = new EmbedBuilder()
						    .setTitle("Que belo avatar!")
						    .setImage(api.getUserById(event.getMessage().getContent().substring(8)).get().getAvatar());
					event.getChannel().sendMessage(embed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
				}
		        
		        logUsoComando(event.getMessageContent(), event.getApi().getServerChannelById(event.getChannel().getIdAsString()).get().getName().toString());
		    }
			else if(event.getMessage().getContent().startsWith(getPrefixo()+"avatar " ) && event.getMessage().getContent().substring(8).length() == 21){
		        
		    	EmbedBuilder embed;
				try {
					embed = new EmbedBuilder()
						    .setTitle("Que belo avatar!")
						    .setImage(api.getUserById(event.getMessage().getContent().substring(10).replace(">", "")).get().getAvatar());
					event.getChannel().sendMessage(embed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
				}
		        
		        logUsoComando(event.getMessageContent(), event.getApi().getServerChannelById(event.getChannel().getIdAsString()).get().getName().toString());
		    }
		});
	}
}
