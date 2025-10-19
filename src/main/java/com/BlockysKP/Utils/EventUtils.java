package com.BlockysKP.Utils;

import org.bukkit.event.Cancellable;

public class EventUtils {

    // Static method to cancel any cancellable event
    public static void cancel(Cancellable event) {
        event.setCancelled(true);
    }

}
