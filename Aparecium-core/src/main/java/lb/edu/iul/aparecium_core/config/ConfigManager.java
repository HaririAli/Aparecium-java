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
package lb.edu.iul.aparecium_core.config;

public class ConfigManager {

    private GeneralConfig general;
    private PathLossConfig pathLoss;
    private static ConfigManager manager = new ConfigManager();

    public enum FullCoverageAlg {
        ENHANCED,
        BRUTE_FORCE
    }

    private ConfigManager() { }

    public static ConfigManager getManager() { return manager; }
    public double getThreshold(){ return general.getThreshold(); }
    public double getApareciumLOS() { return 1.04; }
    public double getApareciumNLOS() { return  2.52; }
    public double getApareciumBPDist() { return 4; }
    public double getSolahLOS() { return 1.04; }
    public double getSolahNLOS() { return  2.52; }
    public double getSolahBPDist() { return 4; }
    public float getOptimalBFStep() { return 0.5f; }
    public float getFullCoverageBFStep() { return 0.5f; }
    public FullCoverageAlg getFullCoverageAlg() { return FullCoverageAlg.ENHANCED; }
}
