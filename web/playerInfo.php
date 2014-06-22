<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="1337Clan Player Info">
    <meta name="author" content="x4n4th">

    <title>1337Clan</title>

    <link href="http://1337clan.com/dev/css/bootstrap.min.css" rel="stylesheet">

    <link href="http://1337clan.com/dev/css/navbar.css" rel="stylesheet">
    
    <link href="http://1337clan.com/dev/css/1337clan.css" rel="stylesheet">
  </head>

  <body>

    <div class="container">

      <!-- Static navbar -->
      <div class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">1337Clan</a>
          </div>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li><a href='/'><i class='glyphicon glyphicon-home'></i> Home</a></li>
              <li><a href="http://forums.1337clan.com"><i class='glyphicon glyphicon-th-list'></i> Forums</a></li>
              <li><a href="/donate"><i class='glyphicon glyphicon-star'></i> Donate!</a></li>
            </ul>
          </div>
        </div>
      </div>

      
      <div id="details">
      <div class = "row">
          <div class="col-md-6">
                 Player:
            <input type="text" style="margin-bottom: 15px;" id="nameOfPerson" />
            <button id="showInfo">Submit </button>
          </div>
          <div class="col-md-6">
             Server:
            <input type="text" style="margin-bottom: 15px;" id="serverInput" />
            <button id="showPermissions">Submit </button>
          </div>
      </div>
    
    <div class= "row">
        <div class="col-md-6">
          <table class="table table-striped ">
              <tr><td> Name:</td><td><output type="text" id="name" />  </td></tr>
              <tr><td> Groups:</td><td><output type="text" id="groups" />  </td></tr>
              <tr><td> Prefix:</td><td><output type="text" id="prefix" />  </td></tr>
              <tr><td> Active Channel: </td><td> <output type="text" id="activeChannel" /> </td></tr>
              <tr><td> Channels: </td><td> <output type="text" id="channels" /> </td></tr>
              <tr><td> Ignored Players: </td><td> <output type="text" id="ignored" /> </td></tr>
              <tr><td> Muted: </td><td> <output type="text" id="muted" /></td></tr>
              <tr><td> Muted Until: </td><td><output type="text" id="mutedU" /></td></tr>
              <tr><td> Banned: </td><td><output type="text" id="banned" /></td></tr>
              <tr><td> Banned Until: </td><td><output type="text" id="bannedU" /></td></tr>
              <tr><td> Server: </td><td><output type="text" id="server" /></td></tr>
          </table>
          <table id="warnsTable" class="table">
          </table>
          
          <div id = 'bans'>
            <table id="bansTable" class="table">
        
            </table>
          </div>

        </div>
        <div class="col-md-6">
          <table id="permsTable" class="table">
        
          </table>
        </div>
    </div>
    </div>
    </div>    
        <footer id="leetfooter">
          <p>&copy; <a href="http://1337clan.com" target="_blank">1337Clan.com</a></p>
        </footer>
      </div>
    </div>

    <?php 
      $randy =  rand (-5000 , 5000);
      $randz =  rand (-5000 , 5000);
      echo '<iframe id="bg" scrolling="no" allowtransparency="true" src="http://1337clan.com/overview/static.php#/'. $randy .'/64/'. $randz .'/max/0/0" frameborder="0"></iframe>';
    ?>
    
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="http://1337clan.com/dev/js/bootstrap.min.js"></script>
    <script src="http://1337clan.com/dev/js/purl.js"></script>
    <script type="text/javascript">
        $('#showInfo').click(function () {
            populatePlayer();
        })
        $('#showPermissions').click(function () {
            getPermissions();
        })
        $(document).keypress(function(e) {
            if(e.which == 13) {
              populatePlayer();
            }
        });
        
        playerName = $.url().param('name');
        
        if(playerName != null){
            $('#nameOfPerson').val(playerName);
            populatePlayer();
        }
        
        function populatePlayer(){
            $('#warnsTable').empty();
            $('#bansTable').empty();
            $.getJSON("http://dev.1337clan.com/network/player.php?query=like&name=" + $("#nameOfPerson").val())
                .done(function (json) {
                    console.log(json);
                    json = json[0];
                    $("#name").val(json.playername);
                    $("#activeChannel").val(json.activechannel);
                    $("#channels").val(json.channels);
                    $("#ignored").val(json.ignored);
                    $("#muted").val(json.muted);
                    $("#mutedU").val(json.muteUntil);
                    $("#banned").val(json.banned);
                    $("#bannedU").val(json.bannedUntil);
                    $("#server").val(json.name);
                    
                markup = $('<tr>' +
                            '<th>Banee</th>' +
                            '<th>Reason</th>' + 
                            '<th>Time</th>' +
                          '</tr>');
                $('#bansTable').append(markup);
                
                $.getJSON("http://dev.1337clan.com/network/player.php?query=bans&name=" + $("#name").val())
                .done(function (json) {
                    console.log(json);
                        $.each(json, function(index, value){
                          console.log(value);
                          markup = $('<tr>' +
                                       '<td id="banee"></td>' +
                                       '<td id="reason"></td>' + 
                                       '<td id="time"></td>' +
                                     '</tr>');
                          markup.find('#banee').append(value.banee);
                          markup.find('#reason').append(value.reason);
                          markup.find('#time').append(value.time);
                          
                          $('#bansTable').append(markup);
                        });
                    });
                    
                markup = $('<tr>' +
                            '<th>warnee</th>' +
                            '<th>Reason</th>' + 
                            '<th>Time</th>' +
                          '</tr>');
                          
                $('#warnsTable').append(markup);
                
                $.getJSON("http://dev.1337clan.com/network/player.php?query=warns&name=" + $("#name").val())
                .done(function (json) {
                    console.log(json);
                        $.each(json, function(index, value){
                          console.log(value);
                          markup = $('<tr>' +
                                       '<td id="warnee"></td>' +
                                       '<td id="reason"></td>' + 
                                       '<td id="time"></td>' +
                                     '</tr>');
                          markup.find('#warnee').append(value.warnee);
                          markup.find('#reason').append(value.reason);
                          markup.find('#time').append(value.time);
                          
                          $('#warnsTable').append(markup);
                        });
                    });
                    
                $.getJSON("http://dev.1337clan.com/network/player.php?query=groups&name=" + $("#name").val())
                .done(function (json) {
                    console.log(json);
                        groups = ""
                        $.each(json, function(index, value){
                            groups += value.name + ", "
                        });
                        $("#groups").val(groups != "" ? groups : "No groups");
                    });
                    getPermissions()
                });
        }
        
        function getPermissions(){
            
            if($("#serverInput").val() == ""){
                $("#serverInput").val($("#server").val())
            }
            $('#permsTable').empty();
            $.getJSON("http://dev.1337clan.com/network/permission.php?name=" + $("#name").val() + "&server=" + $("#serverInput").val())
            .done(function (json) {
                console.log(json);
                    $.each(json, function(index, value){
                      console.log(value);
                      
                      if(index == 0){
                        $("#prefix").val(value.prefix)
                      } else {
                          markup = $('<tr>' +
                                       '<td id="node"></td>' +
                                     '</tr>');
                          markup.find('#node').append(value.node);
                          
                          $('#permsTable').append(markup);
                      }
                    });
                });
        }
        
    </script>
  </body>
</html>