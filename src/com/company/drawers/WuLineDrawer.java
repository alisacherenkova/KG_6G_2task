package com.company.drawers;

import com.company.LineDrawer;
import com.company.PixelDrawer;

import java.awt.*;

public class WuLineDrawer implements LineDrawer {


    private PixelDrawer pd;

    public WuLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {

        int x, y, dx, dy;
        boolean swap = false;

        if (Math.abs(y2 - y1) < Math.abs(x2 - x1)) {
            if (0 < x2 - x1) {
                x = x1;
                y = y1;
                dx = x2 - x1;
                dy = y2 - y1;
            } else {
                x = x2;
                y = y2;
                dx = x1 - x2;
                dy = y1 - y2;
            }
        } else {
            swap = true;
            if (0 < y2 - y1) {
                x = y1;
                y = x1;
                dx = y2 - y1;
                dy = x2 - x1;
            } else {
                x = y2;
                y = x2;
                dx = y1 - y2;
                dy = x1 - x2;
            }
        }
        int err = 0;
        for (int i = 0; i <= dx; i++) {
            drawPixel(x, y, err, dx, swap);
            err += 2 * dy;
            if (err > dx) {
                err -= 2 * dx;
                y++;
            } else if (err < -dx) {
                err += 2 * dx;
                y--;
            }
            x++;
        }
    }

    private void drawPixel(int x, int y, int err, int dx, boolean swap) {
        Color c = Color.BLACK;

        int d;
        if (dx != 0) {
            d = (255 * err) / (2 * dx);
        } else {
            d = 255;
        }
        int dPos = Math.max(0, d);

        Color b1 = setColor(255 - Math.abs(d), c);
        Color b2 =  setColor(Math.abs(d), c);

        if (!swap) {
            pd.drawPixel(x, y, b1);
            if (dx != 0) {
                if (dPos > 0)
                    pd.drawPixel(x, y + 1, b2);
                else
                    pd.drawPixel(x, y - 1, b2);
            }
        } else {
            pd.drawPixel(y, x, b1);
            if (dx != 0) {
                if (dPos > 0)
                    pd.drawPixel(y + 1, x, b2);
                else
                    pd.drawPixel(y - 1, x, b2);
            }
        }
    }

    private Color setColor(int intColor, Color c) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), intColor);
    }
}