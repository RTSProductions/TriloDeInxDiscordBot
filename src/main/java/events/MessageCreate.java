package events;
import discord4j.core.event.domain.message.MessageCreateEvent;
import events.commands.commandList;
import events.commands.about;
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
import events.commands.kick;
import events.commands.ban;
import events.commands.dice;
import events.commands.actionCommands;
public class MessageCreate {
    public String prefix = "d@";

    public MessageCreate(MessageCreateEvent event) {
        final String message = event.getMessage().getContent();
        //return if either does not start with the prefix or the author is a Bot account
        if (!message.startsWith(prefix) | event.getMessage().getAuthor().get().isBot()){
            return;
        }
        //get the command by removing the length of the prefix from the start of the message
        String command = message.substring(prefix.length());
        //check if message command starts with the command then run the class for the command
        if (command.startsWith("help") | command.startsWith("list")){
            new commandList(event);
            return;
        }
        if (command.startsWith("about")){
            new about(event);
            return;
        }
        if (command.startsWith("kick")){
            new kick(event);
            return;
        }
        if (command.startsWith("ban")){
            new ban(event);
            return;
        }
        if (command.startsWith("dice")){
            new dice(event);
            return;
        }
        if (command.startsWith("slap")){
            new actionCommands(event);
            return;
        }
        if (command.startsWith("punch")){
            new actionCommands(event);
            return;
        }
        if (command.startsWith("insult")){
            new actionCommands(event);
            return;
        }
        if (command.startsWith("memeOn")){
            new actionCommands(event);
            return;
        }
        if (command.startsWith("hug")){
            new actionCommands(event);
            return;
        }
        if (command.startsWith("handShake")){
            new actionCommands(event);
            return;
        }
        if (command.startsWith("giftPenguin")){
            new actionCommands(event);
            return;
        }
    }
}