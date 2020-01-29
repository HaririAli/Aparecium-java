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
package lb.edu.iul.aparecium_core.entities;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y){
        setX(x);
        setY(y);
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public double getDistanceToPoint(Location origin){
        return Math.sqrt(Math.pow(x - origin.getX(), 2) + Math.pow(y - origin.getY(), 2));
    }
}
