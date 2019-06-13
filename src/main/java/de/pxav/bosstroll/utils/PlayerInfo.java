package de.pxav.bosstroll.utils;

import de.pxav.bosstroll.BossTroll;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A class description goes here.
 *
 * @author pxav
 */

@Getter
public class PlayerInfo {

    private BossTroll main;

    // trolling -> trolled
    private Map<UUID, UUID> playersTrolling;

    public PlayerInfo(final BossTroll main) {
        this.main = main;
        this.playersTrolling = new HashMap<>();
    }





}
