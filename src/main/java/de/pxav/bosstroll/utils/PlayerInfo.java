package de.pxav.bosstroll.utils;

import de.pxav.bosstroll.BossTroll;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * This class is used to save information about players
 * and what they are currently doing.
 *
 * @author pxav
 */

@Getter
public class PlayerInfo {

    private BossTroll main;

    // this map saves which player is currently trolling which victim.
    // pattern: trolling player -> target
    private Map<UUID, UUID> playersTrolling;

    public PlayerInfo(final BossTroll main) {
        this.main = main;
        this.playersTrolling = new HashMap<>();
    }

}
