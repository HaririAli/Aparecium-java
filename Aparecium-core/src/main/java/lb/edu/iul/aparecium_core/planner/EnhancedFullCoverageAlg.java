package lb.edu.iul.aparecium_core.planner;

import lb.edu.iul.aparecium_core.entities.AccessPoint;
import lb.edu.iul.aparecium_core.entities.Location;
import lb.edu.iul.aparecium_core.entities.Wall;
import lb.edu.iul.aparecium_core.pathloss.PathLossModel;

import java.util.List;

public class EnhancedFullCoverageAlg extends APLocationAlg {

    public EnhancedFullCoverageAlg(PathLossModel model, AccessPoint accessPoint, List<Wall> walls, double threshold){
        super(model, accessPoint, walls, threshold);
    }

    @Override
    public Location findAPLocation() {
        running = true;
        Location fullCoverage = centroid();
        if(verifyAPLocation(fullCoverage)){
            return fullCoverage;
        }

        // Algorithm goes here
        return null;
    }
}
