/*
MIT License

Copyright (c) 2021 theandor and contributors

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package events.commands;
import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

public class kick {
    public String prefix = "d@";
    public String creators = "[theandor](https://github.com/theandor), [RTSProductions](https://github.com/RTSProductions) and [contributors](https://github.com/theandor/TriloDeInxDiscordBot/graphs/contributors)";
    public kick(MessageCreateEvent event){
        MessageChannel channel = event.getMessage().getChannel().block();
        User mention = event.getMessage().getUserMentions().blockFirst();
        if (mention == null){
            channel.createEmbed(embed ->
                    embed.setColor(Color.GREEN)
                            .setTitle("Invalid Usage")
                            .setDescription("The kick command currently requires you to mention someone.")
                            .addField("Trilodeinx-Discord-Bot by", creators, false)
            ).block();
            return;
        }
        Guild guild = event.getGuild().block();
        Snowflake memberSnowflake = Snowflake.of(mention.getId().asLong());
        if (guild.getSelfMember().block().isHigher(memberSnowflake).block()){
            guild.getMemberById(memberSnowflake).block().kick().block();
            channel.createEmbed(embed ->
                    embed.setColor(Color.GREEN)
                            .setTitle("Moderating")
                            .setDescription(mention.getMention() + "has successfully been kicked!")
                            .addField("Trilodeinx-Discord-Bot by", creators, false)
            ).block();
            return;
        }
        channel.createEmbed(embed ->
                embed.setColor(Color.GREEN)
                        .setTitle("Moderating")
                        .setDescription(mention.getMention() + "has unsuccessfully been kicked!")
                        .addField("Trilodeinx-Discord-Bot by", creators, false)
        ).block();
    }
}