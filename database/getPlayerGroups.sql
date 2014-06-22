SELECT groups.name
FROM playergroups
INNER JOIN players
ON players.id = playergroups.playerid
INNER JOIN groups
ON playergroups.groupid = groups.id
WHERE players.playername = 'x4n4th'