package com.company.drawers;

import com.company.LineDrawer;
import com.company.PixelDrawer;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer {

    private PixelDrawer pd;
    public BresenhamLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;
        dx = x2 - x1;
        dy = y2 - y1;
        incx = Integer.compare(dx, 0);
        incy = Integer.compare(dy, 0);
        if (dx < 0) dx = -dx;
        if (dy < 0) dy = -dy;

        if (dx > dy){
            pdx = Integer.compare(dx, 0); pdy = 0;
            es = dy; el = dx;
        }
        else{
            pdx = 0; pdy = incy;
            es = dx; el = dy;
        }
        x = x1;
        y = y1;
        err = el/2;
        pd.drawPixel(x, y, Color.BLACK);

        for (int t = 0; t < el; t++) {
            err -= es;
            if (err < 0) {
                err += el;
                x += incx;
                y += incy;
            }
            else {
                x += pdx;
                y += pdy;
            }
            pd.drawPixel(x, y, Color.BLACK);
        }
    }
}