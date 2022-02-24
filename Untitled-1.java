const Discord = require("discord.js");
const YTDL = require("ytdl-core");


const TOKEN = "NDIwMDQxNjQyMDkyMDAzMzI5.DX47PA.fh7eNW1oVkK7JvmwioszxKn51hI";
const PREFIX = "~";
var bot = new Discord.Client();

var servers = {};

/*
//_____________________________________________
var today_date= new Date()
var myyear=today_date.getYear()
var mymonth=today_date.getMonth()+1
var mytoday=today_date.getDate()
 
document.write("Today's date is: ")
document.write(myyear+"/"+mymonth+"/"+mytoday)
//_________________________________________________
*/
function play(connection, message)
    {
        var server = servers[message.guild.id];

        server.dispatcher = connection.playStream(YTDL(server.queue[0],{filter: 'audioonly'}));

        server.queue.shift();
        server.dispatcher.on("end", function(){
            if (server.queue[0]) play(connection, message);
            else connection.disconnect();
        });
    }

// count = 0;
bot.on("guildMemberAdd", function(member)
{
    //member.guild.channel.find("name","general").sendMessage()(member.toString() + "Welcome mate");
   
});




bot.on("ready", function(){
    console.log("Ready");
});
bot.on("message", function(message)
{
    if(message.author.equals(bot.user)) return;

    if(!message.content.startsWith(PREFIX)) return;
    var args = message.content.substring(PREFIX.length).split(" ");
    var fortunes = [
        "Yes",
        "No",
        "The gods hasn't decided your outcome!",
        "Hell no",
        "REEEEEEEEEE",
        "Without a doubt",
        "Yes definitely",
        "It is certain",
        "As I see it, yes",
        "Most likely",
        "Signs point to yes",
        "Better not tell you now",
        "Don't count on it",
        "My reply is no",
        "My sources say no",
        "Outlook not so good",
        "Very doubtful",
        "As if"
    ];
    var trolling = [
        "I told my ex I felt like killing him, and he said I needed professional help, so I hired a hitman!"
        "marriage is not a word, but a life sentance."
        "How do you know I'm a good housekeeper? Because I kept the house!"

    ];
    var quotes = [
        "I'm not with stupid anymore!"
        "every passing year, our relationship gets better! Since our divorce!"
        
    ];
    switch (args[0].toLowerCase())
    {
        case "ping":
            message.channel.sendMessage("pong!");
            break;

            case "info":
            message.channel.sendMessage("Would you like to know!");
            break;
            
            case "ball":
            if(args[1]) message.channel.sendMessage(fortunes[Math.floor(Math.random() * fortunes.length)]);
            else message.channel.sendMessage("Can't read that");
            break;

            case "quote":
            if(args[0]) message.channel.sendMessage(quotes[Math.floor(Math.random() * quotes.length)]);
            else message.channel.sendMessage("sorry Can't read that");
            break;

            case "trollquote":
            if(args[0]) message.channel.sendMessage(trolling[Math.floor(Math.random() * trolling.length)]);
            else message.channel.sendMessage("sorry Can't read that");
            break;

        



            case "skip":
            var server = servers[message.guild.id];

            if(server.dispatcher) server.dispatcher.end();
            break;

            case "stop":
            var server = servers[message.guild.id];

            if(message.guild.voiceConnection) message.guild.voiceConnection.disconnect();
            break;