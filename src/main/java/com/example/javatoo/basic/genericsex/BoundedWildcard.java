package com.example.javatoo.basic.genericsex;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoundedWildcard {

    public void showXY(Coords<? extends TwoD> twoD) {
        for (int i = 0; i < twoD.coords.length; i++) {
            log.info(twoD.coords[i].x + " x and " + twoD.coords[i].y + " y ");
        }
    }

    public void showXYZ(Coords<? extends ThreeD> threeD) {
        for (int i = 0; i < threeD.coords.length; i++) {
            log.info(threeD.coords[i].x + " x and " + threeD.coords[i].y + " Y and " + threeD.coords[i].z + " z ");
        }
    }

    public void showXYZD(Coords<? extends FourD> fourD) {
        for (int i = 0; i < fourD.coords.length; i++) {
            log.info(fourD.coords[i].x + " x and " + fourD.coords[i].y + " Y and " + fourD.coords[i].z + " z and " + fourD.coords[i].t + " t ");
        }
    }

}
