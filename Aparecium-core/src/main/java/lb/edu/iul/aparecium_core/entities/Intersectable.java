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

/**
 * The <code>Intersectable</code> interface should be implemented by any class
 * whose instances represent a floor plan component that can intersect with other
 * components or with line segments between 2 locations (e.g. walls).
 *
 * <p>
 * This interface is designed to provide a common protocol for objects that
 * represent floor plan components and can intersect with other components.
 * The implementing classes must define the methods <code>intersects</code>
 * and <code>getIntersectionPoint</code>.
 * <p>
 *
 * @author  Ali Hariri
 * @see     lb.edu.iul.aparecium_core.entities.Wall
 */
public interface Intersectable {
    /**
     * Check if a line segment intersects with the object implementing this
     * interface.
     *
     * @param start start point of the line segment to be checked if intersects
     *              with the object.
     * @param end end point of the line segment to be checked if intersecs with
     *            the object.
     *
     * @return true if the line segment intersects with the object or false otherwise.
     *
     * @see lb.edu.iul.aparecium_core.entities.Wall#intersects
     */
    boolean intersects(Location start, Location end);

    /**
     * Check if a line segment intersects with the object implementing this
     * interface.
     *
     * @param start start point of the line segment to be checked if intersects
     *              with the object.
     * @param end end point of the line segment to be checked if intersecs with
     *            the object.
     *
     * @return true if the line segment intersects with the object or false otherwise.
     *
     * @see lb.edu.iul.aparecium_core.entities.Wall#intersects
     */
    Location getIntersectionPoint(Location start, Location end);
}
