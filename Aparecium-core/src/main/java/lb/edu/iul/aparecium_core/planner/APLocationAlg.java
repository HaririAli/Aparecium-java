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

import java.util.List;

public abstract class APLocationAlg implements Cancellable {

    private PathLossModel model;
    private AccessPoint accessPoint;
    private List<Wall> walls;
    private double threshold;
    protected boolean running = false;

    protected APLocationAlg(PathLossModel model, AccessPoint accessPoint, List<Wall> walls, double threshold){
        this.model = model;
        this.accessPoint = accessPoint;
        this.walls = walls;
        setThreshold(threshold);
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public abstract Location findAPLocation();

    public boolean verifyAPLocation(Location location) {
        accessPoint.setLocation(location);
        double power;
        // Iterating on each outer wall
        for (Wall wall : walls) {
            if(!wall.isOuter())
                continue;
            // Calculating the step of this segment to scan all its points
            double step = 1 / (wall.getStart().getDistanceToPoint(wall.getEnd()));
            // dx and dy are used to calculate the coordinates of each point
            double dx = wall.getEnd().getX() - wall.getStart().getX();
            double dy = wall.getEnd().getY() - wall.getStart().getY();
            // Iterating on each point to check if minimum power is received
            for (double index = 0; index <= 1; index += step) {
                int x = (int) (wall.getStart().getX() + dx * index);
                int y = (int) (wall.getStart().getY() + dy * index);
                Location receiver = new Location(x, y);
                power = accessPoint.getTransmissionPower() - model.estimatePathLoss(receiver, accessPoint, walls);
                if (power < threshold)
                    return false;
            }
        }
        return true;
    }

    protected Location centroid(){
        int x = 0, y = 0;
        int outerWalls = 0;
        for (Wall wall : walls){
            if (wall.isOuter()) {
                outerWalls++;
                x += wall.getStart().getX()  + wall.getEnd().getX();
                y += wall.getStart().getY() + wall.getEnd().getY();
            }
        }
        if(outerWalls == 0)
            return new Location(x, y);
        return new Location(x / (2 * outerWalls), y / (2 * outerWalls));
    }

    @Override
    public void cancel(){
        running = false;
    }
}
