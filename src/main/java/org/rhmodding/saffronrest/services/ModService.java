package org.rhmodding.saffronrest.services;

import org.rhmodding.saffronrest.models.Mods;
import org.rhmodding.saffronrest.models.ModsSimplified;

public class ModService {

    public static ModsSimplified convertMod(Mods mod){
        ModsSimplified modSimple = new ModsSimplified();
        modSimple.setId(mod.getId());
        modSimple.setName(mod.getName());
        modSimple.setShortDesc(mod.getShortDesc());
        modSimple.setLongDesc(mod.getLongDesc());
        return modSimple;
    }

}
