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
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;

public class actionCommands {
    public String prefix = "d@";
    public String creators = "[theandor](https://github.com/theandor), [RTSProductions](https://github.com/RTSProductions) and [contributors](https://github.com/theandor/TriloDeInxDiscordBot/graphs/contributors)";
    public actionCommands(MessageCreateEvent event){
        final String message = event.getMessage().getContent();
        String command = message.substring(prefix.length());
        User mention = event.getMessage().getUserMentions().blockFirst();
        MessageChannel channel = event.getMessage().getChannel().block();
        User author = event.getMessage().getAuthor().get();
        if (mention == null){
            mention = author;
        }
        if (mention == author && command.startsWith("hug")){
            channel.createMessage(author.getMention() + " hugged themself!").block();
            return;
        }
        if (mention == author && !command.startsWith("hug") ){
            channel.createMessage("The command currently requires you to mention someone.").block();
            return;
        }
        if (command.startsWith("slap")){
            channel.createMessage(author.getMention() + " slapped " + mention.getMention() + "!").block();
            return;
        }
        if (command.startsWith("punch")){
            channel.createMessage(author.getMention() + " punched" + mention.getMention() + "!").block();
            return;
        }
        if (command.startsWith("insult")){
            channel.createMessage(author.getMention() + " insulted" + mention.getMention() + "!").block();
            return;
        }
        if (command.startsWith("memeOn")){
            channel.createMessage(author.getMention() + " memed on " + mention.getMention() + "!").block();
            return;
        }
        if (command.startsWith("hug")){
            channel.createMessage(author.getMention() + " hugged " + mention.getMention() + "!").block();
            return;
        }
        if (command.startsWith("giftPenguin")){
            channel.createMessage(author.getMention() + " gifted " + mention.getMention() + " a pet Penguin!").block();
            return;
        }
        if (command.startsWith("handShake")){
            channel.createMessage(author.getMention() + " shook " + mention.getMention() + "'s hand.").block();
            return;
        }
    }
}