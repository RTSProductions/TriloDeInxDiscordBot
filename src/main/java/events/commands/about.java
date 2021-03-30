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
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

public class about {
    public String creators = "[theandor](https://github.com/theandor), [RTSProductions](https://github.com/RTSProductions) and [contributors](https://github.com/theandor/TriloDeInxDiscordBot/graphs/contributors)";
    public about(MessageCreateEvent event){
        MessageChannel channel = event.getMessage().getChannel().block();
        //Create embed and send it to the current channel.
        channel.createEmbed(embed ->
                embed.setColor(Color.GREEN)
                        .setTitle("About Trilodeinx Discord Bot")
                        .addField("Logo art designed and created by", "[theandor](https://github.com/theandor)", false)
                        .addField("Trilodeinx-Discord-Bot by", creators, false)
        ).block();
    }
}