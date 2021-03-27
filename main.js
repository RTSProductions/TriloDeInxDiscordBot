/*
BSD 3-Clause License
Copyright (c) 2021, theandor and contributors
All rights reserved.
*/
  const prefix = 'd@';
  const token = '';
  const Discord = require('discord.js');
  const client = new Discord.Client();
  const fs = require('fs');
  const creators = '[theandor](https://github.com/theandor), [RTSProductions](https://github.com/RTSProductions) and [contributors](https://github.com/theandor/Trilodeinx-Discord-Bot/graphs/contributors)';
  //ads a status
  client.on('ready', () => {
      console.info(`Logged in as`)
      console.info(client.user.username);
      client.user.setPresence({
        status: 'online',
        activity: {
            name: `Trilodeinx | ${prefix}help`,
            type: 'PLAYING',
        }
    })
  }); 
  purgewait = 0;
  /*
  Score functions
  */
 scoredata = '';
 fs.readFile('./scores.json', 'utf8', function (error, data) {
  if (error) {
    scoredata = {};
    return;
  }
  scoredata = JSON.parse(data);
});
scoredata = scoredata;
function saveScore(){
  jsonData = JSON.stringify(scoredata);
  fs.writeFile('./scores.json', jsonData, 'utf8', (error) => {
        console.log(`Saved Score`);
      });
}
function addScore(guildId, userID, amount){
  scoreId = guildId.toString() + userID.toString();
  if (!scoredata.hasOwnProperty(scoreId)){
    scoredata[scoreId] = 0;
  }

  scoredata[scoreId] += amount;
  saveScore();
}
  /*
Continue running the bot
  */
  //returns a function when a message is sent
  client.on("message", function (message) {
      //score processing
      if (message.author.bot){
        return;
      }
      addScore(message.guild.id, message.author.id, 0.25);
      //if the message doesn't start with the prefix then do nothing
      if (!message.content.startsWith(prefix)){
          return;
      }
      //get the command the user is running
      var cmd = message.content.slice(2);
      //about command
      if (cmd == 'about'){
           var embed = new Discord.MessageEmbed()
          .setColor('#9ee228')
          .setTitle("About Trilodeinx Discord Bot")
          .addField('Logo art designed and created by', '[theandor]](https://github.com/theandor)')
          .addField('Trilodeinx-Discord-Bot by', creators, false)
          message.channel.send(embed);
      }
      //help and list command
      if (cmd == "help" | cmd == "list"){
          var embed = new Discord.MessageEmbed()
          .setColor('#9ee228')
          .setTitle("Trilodeinx Discord Bot's commands")
          .addField('Trilodeinx bot prefix', '`'+prefix+'`', false)
          .addField('Moderating -- 3', '`kick`, `purge`, `ban`')
          .addField('General -- 12', '`score`, `list`, `help`, `about`, `dice`, `slap`, `hug`, `punch`, `insult`, `memeOn`, `handShake`, `giftPenguin`', false)
          .addField('Trilodeinx-Discord-Bot by', creators, false)
          message.channel.send(embed);
      }
      //points command
      if (cmd.startsWith('score')){
        user = message.mentions.users.first() || message.author;
        scoreId = message.guild.id.toString() + user.id.toString();
        score = scoredata[scoreId];
        if (score == 'undefined'){
          score = 0;
        }
        var embed = new Discord.MessageEmbed()
        .setColor('#9ee228')
        .setTitle(user.username)
        .addField('Score:', score)
        .addField('Trilodeinx-Discord-Bot by', creators, false)
        message.channel.send(embed);
      }
      //
      //purge command
      if (cmd.startsWith('purge')){
          amount = cmd.slice(5);
          amount = amount.replace(/\s/g, '');
          //check if user has the permissions
          if(!message.guild.member(message.author).hasPermission('MANAGE_MESSAGES')) {
                  var embed = new Discord.MessageEmbed()
                  .setColor('#9ee228')
                  .setTitle("Invalid Permissions")
                  .setDescription('You do not have permission to manage messages in this channel.', false)
                  .addField('Trilodeinx-Discord-Bot by', creators, false)
                  message.channel.send(embed);
                  return;
          }
          if(Number(purgewait >= 1)){
              var embed = new Discord.MessageEmbed()
              .setColor('#9ee228')
              .setDescription('Slow down, you are running purge too many times per minute. Please wait up to five seconds.', false)
              .addField('Trilodeinx-Discord-Bot by', creators, false)
              message.channel.send(embed);
              return;
          }
          if (amount >= 99){
              amount = 98;
          }
          if(!isNaN(amount)){
             if (amount >= 1){
              message.channel.bulkDelete(amount).catch(console.error);
              purgewait += 1;
              console.warn(purgewait)
              setTimeout(function(){
                  purgewait -= 1;
              }, 8500);
              return;
          }
          }
          var embed = new Discord.MessageEmbed()
          .setColor('#9ee228')
          .setTitle("Command Syntax Error")
          .setDescription('You cannot include any special characters or letters inside the purge command.', false)
          .addField('Trilodeinx-Discord-Bot by', creators, false)
          message.channel.send(embed);
      }
      if (cmd.startsWith("kick")) {
          if (message.member.hasPermission("KICK_MEMBERS")) {
              member = message.mentions.members.first()
  
              if (!member) {
                  message.channel.send("Please mention someone")
              } else {
                  member.kick().then(mem => {
                     
                      var embed = new Discord.MessageEmbed()
                          .setColor('#9ee228')
                          .setTitle("Moderation")
                          .setDescription(`${mem.user.username} has been kicked!`, false)
                          .addField('Trilodeinx-Discord-Bot by', creators, false)
                      message.channel.send(embed);
                  })
              }
          } else {
              var embed = new Discord.MessageEmbed()
                  .setColor('#9ee228')
                  .setTitle("Command Syntax Error")
                  .setDescription('You do not have permission to kick members in this guild.', false)
                  .addField('Trilodeinx-Discord-Bot by', creators, false)
              message.channel.send(embed);
          }
      }
      if (cmd.startsWith("ban")) {
          if (message.member.hasPermission("BAN_MEMBERS")) {
              memberf = message.mentions.members.first()
  
              if (!memberf) {
                  message.channel.send("Please mention someone")
              } else {
                  memberf.ban().then(mem => {
  
                      var embed = new Discord.MessageEmbed()
                          .setColor('#9ee228')
                          .setTitle("Moderation")
                          .setDescription(`${mem.user.username} has been banned!`, false)
                          .addField('Trilodeinx-Discord-Bot by', creators, false)
                      message.channel.send(embed);
                  })
              }
          } else {
              var embed = new Discord.MessageEmbed()
                  .setColor('#9ee228')
                  .setTitle("Command Syntax Error")
                  .setDescription('You do not have permission to ban members in this guild.', false)
                  .addField('Trilodeinx-Discord-Bot by', creators, false)
              message.channel.send(embed);
          }
      }
  
      var actioncommands = ["slap","punch","insult","memeOn","hug","handShake", "giftPenguin"];
      for (var i = 0; i < actioncommands.length; i++){
        if (cmd.startsWith(actioncommands[i])){
            user = message.mentions.users.first() || message.author;
            if (actioncommands[i] == 'giftPenguin'){
              message.channel.send(`${message.author} gifted ${user} a pet Penguin!`)
              return;
            }

            if (user.id == message.author.id){
                if(actioncommands[i] == 'hug'){
                  message.channel.send(`${message.author} hugged themself!`)
                  return;
                }
              var embed = new Discord.MessageEmbed()
              .setColor('#9ee228')
              .setTitle("Command Syntax Error")
              .setDescription(`You cannot ${actioncommands[i]} yourself!`, false)
              .addField('Trilodeinx-Discord-Bot by', creators, false)
              message.channel.send(embed);
              return;
            }
            if (actioncommands[i] == "hug") {
              message.channel.send(`${message.author} hugged ${user}`)
          }
          if (actioncommands[i] == "handShake") {
              if (message.guild.members.cache.get(user.id).displayName.slice(-1) == 's'){
                  message.channel.send(`${message.author} shook ${user}' hand`)
                  return;
              }
              message.channel.send(`${message.author} shook ${user}'s hand`)
        }
        if (actioncommands[i] == "slap"){
          message.channel.send(`${message.author} slapped ${user}`)
        }
        if (actioncommands[i] == "punch"){
          message.channel.send(`${message.author} punched ${user}`)
        }
        if (actioncommands[i] == "insult"){
          message.channel.send(`${message.author} insulted ${user}`)
        }
        if (actioncommands[i] == "memeOn"){
          message.channel.send(`${message.author} memed on ${user}`)
        }
        return; 
      }
  }
  
  if (cmd.startsWith('dice')){
      amount = cmd.slice(4);
      if (amount <= 1){
          amount = 6;
      }
      if (amount >= 1000){
          amount = 999;
      }
      random = Math.floor(Math.random() * amount+1);
      var embed = new Discord.MessageEmbed()
      .setColor('#9ee228')
      .setTitle("Dice")
      .setDescription(`You rolled a ${random}`, false)
      .addField('Trilodeinx-Discord-Bot by', creators, false)
      message.channel.send(embed);
  }
  
      
  });
  
  //the bot logs in
  client.login(token);
