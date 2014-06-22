DELIMITER $$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    PROCEDURE `chat`.`PromotePlayer`(IN playerNameInput VARCHAR(128))
    /*LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    | COMMENT 'string'*/
    BEGIN
	SELECT @playerId := id FROM players WHERE playername = playerNameInput;	
	
	SELECT @currentRank := MAX(groups.rank)
		FROM groups
		INNER JOIN playergroups
		ON playergroups.groupid = groups.id
		WHERE playergroups.playerid = @playerId
		LIMIT 1;

	INSERT INTO playergroups (playerid, groupid)
	SELECT @playerId, MIN(groups.id)
	FROM groups
	WHERE groups.rank > @currentRank;
    END$$

DELIMITER ;