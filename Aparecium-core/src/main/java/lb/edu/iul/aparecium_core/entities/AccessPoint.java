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

public class AccessPoint implements Identifiable {
    private Location location;
    private String model;
    private String brand;
    private float transmissionPower; //in dBm
    private float pathLoss1m; // Path loss at 1m
    private int id;

    private AccessPoint(Location location, String model, String brand, float transmissionPower, float pathLoss1m, int id){
        setLocation(location);
        this.model = model;
        this.brand = brand;
        this.transmissionPower = transmissionPower;
        this.pathLoss1m = pathLoss1m;
        this.id = id;
    }

    public void setLocation(Location location) { this.location = location; }
    public Location getLocation() { return location; }
    public String getModel() { return model; }
    public String getBrand() { return brand; }
    public float getTransmissionPower() { return transmissionPower; }
    public float getPathLoss1m() { return pathLoss1m; }

    @Override
    public String getId(){
        return "Access Point" + id;
    }

    public static class Builder {
        private Location location = null;
        private String model = "Unknown Model";
        private String brand = "Unknown Brand";
        private float transmissionPower = -1;
        private float pathLoss1m = -1;
        private static int id = 0;

        private static synchronized int newId(){
            ++id;
            return id;
        }

        public Builder setLocation(Location location){
            this.location = location;
            return this;
        }

        public Builder setModel(String model){
            this.model = model;
            return this;
        }

        public Builder setBrand(String brand){
            this.brand = brand;
            return this;
        }

        public Builder setTransmissionPower(float transmissionPower){
            this.transmissionPower = transmissionPower;
            return this;
        }

        public Builder setPathLoss1m(float pathLoss1m){
            this.pathLoss1m = pathLoss1m;
            return this;
        }

        public AccessPoint build() throws InvalidAPException {
            if(location == null){
                throw new InvalidAPException("Access Point location not set");
            }

            if(transmissionPower < 0 || transmissionPower > 40){
                throw new InvalidAPException("Invalid transmission power: " + transmissionPower + "dBm");
            }

            if(pathLoss1m < 0 || pathLoss1m > 60){
                throw new InvalidAPException("Invalid path loss at 1m: " + pathLoss1m + "dBm");
            }

            return new AccessPoint(location, model, brand, transmissionPower, pathLoss1m, newId());
        }
    }

    public static class InvalidAPException extends Exception {
        public InvalidAPException(String message){
            super(message);
        }
    }
}