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
 * The <code>Identifiable</code> interface should be implemented by any
 * class whose instances need to be identified by a unique identifier.
 * The implementing class must define a method of no arguments called
 * <code>getId</code>.
 *
 * <p>
 * This interface is designed to provide a common protocol for objects that
 * need to have a unique ID. For example, <code>Identifier</code> is implemented
 * by class <code>AccessPoint</code>, which implements the logic of providing
 * unique IDs to AccessPoint objects.
 * <p>
 *
 * @author  Ali Hariri
 * @see     lb.edu.iul.aparecium_core.entities.AccessPoint
 * @see     lb.edu.iul.aparecium_core.entities.Wall
 */
@FunctionalInterface
public interface Identifiable {
    String getId();
}
