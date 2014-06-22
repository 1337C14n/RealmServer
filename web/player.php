<?php 
   mysql_pconnect("localhost", "root", "CjsASqQcvCOL3l") or die(mysql_error()); 
   mysql_select_db("chat") or die(mysql_error()); 
   
   $playerName = mysql_real_escape_string($_GET["name"]);
   $queryType = isset($_GET["query"]) ? $_GET["query"] : null;
   
   $data = null;
   $rows = array();
   if($queryType == 'like') {
     $data = mysql_query("SELECT * 
                          FROM players
                          INNER JOIN servers
                          ON players.serverid = servers.id
                          WHERE playername LIKE '%". $playerName ."%';")               
     or die(mysql_error()); 
   }
   elseif ($queryType == 'bans'){
    $data = mysql_query("SELECT * 
                        FROM bans 
                        WHERE name = '". $playerName ."'
                        ORDER BY time DESC;")               
       or die(mysql_error()); 
   }
   elseif ($queryType == 'warns'){
    $data = mysql_query("SELECT * 
                        FROM warns 
                        WHERE name = '". $playerName ."'
                        ORDER BY time DESC;")               
       or die(mysql_error()); 
   }
   elseif ($queryType == 'server'){
       $data = mysql_query("SELECT banned, bannedUntil
                        FROM players 
                        WHERE playername = '". $playerName ."';")               
       or die(mysql_error());
       
       while($r = mysql_fetch_assoc( $data )) 
       { 
         $rows[] = $r;
       } 
       
        $bannedUntil = $rows[0]['bannedUntil'];
        if ($bannedUntil != null){
            $time = time();
            $timestamp = strtotime($bannedUntil);
            
            if($time > $timestamp){
                //Player should not be banned.
                //They have passed their temp ban Unban them
                $query = "UPDATE players SET banned = 0, bannedUntil = NULL WHERE playername = '".$playerName."';";
                mysql_query($query)or die(mysql_error());
                $query = "INSERT INTO bans(name, reason, banee) VALUES ('".$playerName."', 'Auto Unban', 'Server');";
                mysql_query($query)or die(mysql_error());
                $rows[0]['banned'] = 0;
            }
        }
       
       $data = mysql_query("SELECT `name` 
                            FROM `servers` 
                            INNER JOIN players 
                                ON servers.id = players.serverid 
                            WHERE players.playername = '". $playerName ."';")               
       or die(mysql_error());
   }
   elseif ($queryType == 'groups'){
       $data = mysql_query("SELECT groups.name
                        FROM playergroups
                        INNER JOIN players
                        ON players.id = playergroups.playerid
                        INNER JOIN groups
                        ON playergroups.groupid = groups.id
                        WHERE players.playername = '". $playerName ."';")               
       or die(mysql_error());
   }
   else 
   {
       $data = mysql_query("SELECT * 
                        FROM players 
                        WHERE playername = '". $playerName ."';")               
       or die(mysql_error()); 
   }
   

   
   while($r = mysql_fetch_assoc( $data )) 
   { 
     $rows[] = $r;
   } 
   
   print json_encode($rows);
 ?> 