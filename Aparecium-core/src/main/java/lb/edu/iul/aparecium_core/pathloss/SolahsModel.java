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

import lb.edu.iul.aparecium_core.entities.AccessPoint;
import lb.edu.iul.aparecium_core.entities.Location;
import lb.edu.iul.aparecium_core.entities.Wall;

import java.util.List;

public class SolahsModel implements PathLossModel {

    double breakPointDistance;
    double losExponent;
    double nLosExponent;

    public SolahsModel(double breakPointDistance, double losExponent, double nLosExponent){
        this.breakPointDistance = breakPointDistance;
        this.losExponent = losExponent;
        this.nLosExponent = nLosExponent;
    }

    @Override
    public double estimatePathLoss(Location receiver, AccessPoint accessPoint, List<Wall> walls) {
        double distance = receiver.getDistanceToPoint(accessPoint.getLocation());
        double pathLoss = accessPoint.getPathLoss1m();

        if(distance < breakPointDistance)
            pathLoss += 10 * losExponent * Math.log10(distance);
        else
            pathLoss += 10 * nLosExponent * Math.log10(distance);

        for (Wall wall : walls) {
            if (wall.intersects(accessPoint.getLocation(), receiver)) {
                pathLoss += wall.getAttenuation();
            }
        }

        return pathLoss;
    }

    @Override
    public double estimateDistance(double requiredSignalPower, double pathLoss1m, double transmissionPower, List<Wall> walls) {
        return 0;
    }
}
