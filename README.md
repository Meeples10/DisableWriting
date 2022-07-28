# DisableWriting

A plugin for disabling the use of written language on a Minecraft server.

## Commands

|Command|Description|Permission|
|-------|-----------|----------|
|`/dw`|Manage the plugin.|`disablewriting.admin`|
|`/dw reload`|Reload the plugin.|`disablewriting.admin`|
|`/dw bypass`|List players that can bypass writing restrictions.|`disablewriting.admin`|
|`/dw togglebooks`|Enable or disable books.|`disablewriting.admin`|
|`/dw togglesigns`|Enable or disable signs.|`disablewriting.admin`|
|`/dw togglechat`|Enable or disable chat.|`disablewriting.admin`|
|`/dw togglemaps`|Enable or disable maps.|`disablewriting.admin`|
|`/dw togglemessages`|Enable or disable short join/leave messages.|`disablewriting.admin`|
|`/dw show`|Show which items are enabled or disabled.|`disablewriting.admin`|

## Configuration

The default configuration file can be found [here](https://github.com/Meeples10/DisableWriting/blob/master/src/main/resources/config.yml).

|Key|Description|
|---|-----------|
|`disable-books`|If true, players will not be able to write in books.|
|`disable-signs`|If true, players will not be able to write on signs. Signs can still be placed as usual.|
|`disable-chat`|If true, players will not be able to send chat messages.|
|`disable-maps`|If true, players will be prevented from using maps.|
|`replace-join-leave-messages`|If true, player join and leave messages will be replaced with `[+] Name` and `[-] Name`, respectively.|
|`bypass-players`|Players listed here can use books and signs, regardless of the settings above. Names are case-insensitive.|
|`debug`|This causes the plugin to send messages in the console when events are fired. \[F\] indicates that an event has been fired, and \[C\] indicates that an event has been cancelled.|
