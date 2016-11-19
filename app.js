// ORIGINAL

var builder = require('botbuilder');

var connector = new builder.ConsoleConnector().listen();
var bot = new builder.UniversalBot(connector);
bot.dialog('/', function (session) {
    session.send('Hello World');
});


var fs = require('fs')
var filename = "nodeconfig.txt";
fs.readFile(filename, 'utf8', function(err, data) {
  if (err) throw err;
  console.log('OK: ' + filename);
  console.log(data)
});

// Create LUIS recognizer that points at our model and add it as the root '/' dialog for our Bot.
// var model = 'https://api.projectoxford.ai/luis/v1/application?id=8b82365c-bc74-4574-aed1-2107cffa24cb&subscription-key=245e66e437d6484d9bb1a0c6f706fb89';

// var recognizer = new builder.LuisRecognizer(model);
// var intents = new builder.IntentDialog({ recognizers: [recognizer] });

// // When the framework receives a message from the user it will be routed to this root ‘/’ dialog for processing
// bot.dialog('/', intents);

// new messages
// [4:09]  
// Then you just have to act accordingly. This is an example if the recognized intent is 'getLocation'


// intents.matches('getLocation', [
//    function (session, args, next) {
//        var eventName = builder.EntityRecognizer.findEntity(args.entities, 'EventName');
//        if (!eventName) {
//            builder.Prompts.text(session, "What's the meetup called?");
//        } else {
//            next({ response: eventName.entity });
//        }
//    },
//    function (session, results) {
//        if (results.response) {
//            // Search date for this event 
//            session.send("Searching for the location of the next " + results.response + " meetup");
//            getMeetupLocation(results.response, function(d){ if(d.toString()) session.send(d.toString()); else session.send("I'm sorry, I could not find any event called " + results.response + ".")  });
                     
//        } else {
//            session.send("Ok");
//        }
//    }
// ]);