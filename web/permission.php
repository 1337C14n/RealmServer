<?php 
   mysql_connect("localhost", "root", "CjsASqQcvCOL3l") or die(mysql_error()); 
   mysql_select_db("chat") or die(mysql_error()); 

   $player = isset($_GET["name"]) ? mysql_real_escape_string($_GET["name"]) : null;
   $server = isset($_GET["server"]) ? mysql_real_escape_string($_GET["server"]) : null;

   $data = null;
   $rows = null;
   
   if($player != null && $server != null ) {
        $data = mysql_query("SELECT groupid 
                            FROM playergroups 
                            INNER JOIN players 
                                ON playerid = players.id 
                            WHERE playername = '".$player."';")
        
    or die(mysql_error()); 
     
        $groups = array();
   
        while($r = mysql_fetch_assoc( $data )){ 
            $groups[] = $r['groupid'];
        } 
        
        $groups[] = 2;
        
        $query = "SELECT node 
                   FROM permissionnodes 
                   INNER JOIN servers 
                   ON permissionnodes.serverid = servers.id 
                   INNER JOIN players 
                   ON permissionnodes.playerid = players.id";
                   
        foreach($groups as $key=>$group){
          if($key == 0) {
            $query .= " WHERE ((playername = '".$player."' OR players.id = 7) "
            . " AND (servers.name = '".$server."' OR servers.id = 6) "
            . " AND (permissionnodes.groupid = " . $group . ")) ";
          } else {
            $query .= "OR ((playername = '".$player."' OR players.id = 7) "
            . " AND (servers.name = '".$server."' OR servers.id = 6) "
            . " AND (permissionnodes.groupid = " . $group . ")) ";
          }
        }
        $query .= ";";
        
        $data = mysql_query($query) or die(mysql_error()); 
       
        $rows = array();
        
        $query2 = "SELECT prefixes.prefix
                    FROM prefixes 
                    INNER JOIN playergroups
                        ON prefixes.groupid = playergroups.groupid 
                    INNER JOIN players 
                        ON playergroups.playerid = players.id 
                    WHERE players.playername = '".$player."' AND 
                    donatorlevel IN (SELECT groupid FROM playergroups 
                    INNER JOIN players
                    ON playergroups.playerid = players.id 
                    WHERE players.playername = '".$player."') 
                    ORDER BY priority DESC LIMIT 1;";      
                    
        mysql_query('SET CHARACTER SET utf8');            
        $data2 = mysql_query($query2) or die(mysql_error()); 
        
        
        //We will add the players prefix first!
        
        while($r = mysql_fetch_assoc( $data2 )) 
        { 
          $rows[] = $r;
        } 
        
        //Then add all permission nodes
       
        while($r = mysql_fetch_assoc( $data )) 
        { 
          $rows[] = $r;
        } 
    }

   echo json_encode($rows);
 ?> 