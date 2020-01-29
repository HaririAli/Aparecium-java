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

public class Wall implements Intersectable, Identifiable {

    private Location start;
    private Location end;
    private boolean outer;
    private String material;
    private float attenuation;
    private int id;

    public Wall(Location start, Location end, boolean outer, String material, float attenuation, int id){
        setStart(start);
        setEnd(end);
        setOuter(outer);
        this.material = material;
        this.attenuation = attenuation;
        this.id = id;
    }

    public Location getStart() { return start; }
    public void setStart(Location start) { this.start = start; }
    public Location getEnd() { return end; }
    public void setEnd(Location end) { this.end = end; }
    public boolean isOuter() { return outer; }
    public void setOuter(boolean outer) { this.outer = outer; }
    public String getMaterial() { return material; }
    public float getAttenuation() { return attenuation; }

    private double length() { return start.getDistanceToPoint(end); }

    @Override
    public String getId() {
        return "entities.Wall" + id;
    }

    @Override
    public boolean intersects(Location start, Location end) {
        float dx = end.getX() - start.getX();
        float dy = end.getY() - start.getY();
        float dxWall = getEnd().getX() - getStart().getX();
        float dyWall = getEnd().getY() - getStart().getY();

        float denominator = (dy * dxWall - dx * dyWall);

        if(denominator == 0) return false;

        float t1 = ((start.getX() - getStart().getX()) * dyWall + (getStart().getY() - start.getY()) * dxWall) / denominator;
        float t2 = ((getStart().getX() - start.getX()) * dy + (start.getY() - getStart().getY()) * dx) / -denominator;
        return ((t1 >= 0) && (t1 <= 1) && (t2 >= 0) && (t2 <= 1));
    }

    @Override
    public Location getIntersectionPoint(Location start, Location end) {
        return null;
    }

    public static class Builder {
        private Location start = null;
        private Location end = null;
        private boolean outer = false;
        private String material = "Unknown Material";
        private float attenuation = -1;
        private static int id = 0;

        private static synchronized int newId(){
            ++id;
            return id;
        }

        public Builder setStart(Location start) {
            this.start = start;
            return this;
        }

        public Builder setEnd(Location end) {
            this.end = end;
            return this;
        }

        public Builder setOuter(boolean outer) {
            this.outer = outer;
            return this;
        }

        public Builder setMaterial(String material) {
            this.material = material;
            return this;
        }

        public Builder setAttenuation(float attenuation) {
            this.attenuation = attenuation;
            return this;
        }

        public Wall build() throws InvalidWallException {
            if(start == null){
                throw new InvalidWallException("Start point not set");
            }

            if(end == null){
                throw new InvalidWallException("End point not set");
            }

            if(attenuation < 0){
                throw new InvalidWallException("Attenuation not set");
            }

            return new Wall(start, end, outer, material, attenuation, newId());
        }
    }

    public static class InvalidWallException extends Exception {
        public InvalidWallException(String message){
            super(message);
        }
    }
}
