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
package lb.edu.iul.aparecium_core.planner;

import lb.edu.iul.aparecium_core.entities.AccessPoint;
import lb.edu.iul.aparecium_core.entities.Location;
import lb.edu.iul.aparecium_core.entities.Wall;
import lb.edu.iul.aparecium_core.pathloss.PathLossModel;

import java.util.*;

public class Planner {

    private Map<String, Wall> walls;
    private Map<String, AccessPoint> accessPoints;
    private PathLossModel model;

    public Planner(List<Wall> walls, Map<String, AccessPoint> accessPoints, PathLossModel model) {
        this.walls = new HashMap<>();
        this.accessPoints = new HashMap<>();
        this.model = model;
    }

    public Collection<Wall> getWalls() { return walls.values(); }
    public Wall getWall(String id) { return walls.get(id); }
    public void putWall(String id, Wall wall) { walls.put(id, wall); }
    public Collection<AccessPoint> getAccessPoints() { return accessPoints.values(); }
    public AccessPoint getAccessPoint(String id) { return accessPoints.get(id); }
    public void putAccessPoint(String id, AccessPoint accessPoint) { accessPoints.put(id, accessPoint); }
    public PathLossModel getPathLossModel() { return model; }
    public void setPathLossModel(PathLossModel model) { this.model = model; }

    public double estimateReceivedPower(Location receiver, String apId) {
        AccessPoint accessPoint = getAccessPoint(apId);
        List<Wall> intersecting = new ArrayList<>();
        for (Wall wall: getWalls()){
            if(wall.intersects(receiver, accessPoint.getLocation()))
                intersecting.add(wall);
        }
        return model.estimatePathLoss(receiver, accessPoint, intersecting);
    }

    public Location findOptimalAPLocation(){
        return null;
    }

    public Location findFullCoverageAPLocation(){
        return null;
    }

}
