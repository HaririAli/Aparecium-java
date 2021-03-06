/*
 * Copyright (c) 2020, Islamic University of Lebanon (IUL) and The Lebanese
 * National Council for Scientific Research (CNRS). All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work.
 */
package lb.edu.iul.aparecium_core.pathloss;

import lb.edu.iul.aparecium_core.config.ConfigManager;

public class PathLossModelFactory {

    public enum ModelType {
        APARECIUM,
        SOLAH
    }

    private static PathLossModelFactory factory = new PathLossModelFactory();

    private PathLossModelFactory() { }

    public PathLossModelFactory getFactory() { return factory; }

    public PathLossModel createModel(ModelType type){
        ConfigManager config = ConfigManager.getManager();
        switch (type){
            case SOLAH:
                return new SolahsModel(config.getSolahBPDist(), config.getSolahLOS(), config.getSolahNLOS());
            case APARECIUM:
            default:
                return new ApareciumModel(config.getApareciumBPDist(), config.getApareciumLOS(), config.getApareciumNLOS());
        }
    }

}
