DELIMITER $$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    PROCEDURE `chat`.`DemotePlayer`(IN playerNameInput VARCHAR(128))
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
		
	IF @currentRank > 1 THEN BEGIN	
		SELECT @groupid := id FROM groups WHERE rank = @currentRank LIMIT 1;
		
		DELETE
		FROM playergroups
		WHERE playerid = @playerId AND groupid = @groupid;
	END; END IF;
	
    END$$

DELIMITER ;