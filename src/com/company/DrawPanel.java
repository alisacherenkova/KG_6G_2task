package com.company;

import com.company.drawers.BresenhamLineDrawer;
import com.company.drawers.WuLineDrawer;
import com.company.utils.DrawUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel implements MouseMotionListener {
    private Point position = new Point(0, 0);

    public DrawPanel() {
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics gr = bi.createGraphics();
        PixelDrawer pd = new GraphicsPixelDrawer(gr);
        LineDrawer ld = new WuLineDrawer(pd);
        gr.setColor(Color.WHITE);
        gr.fillRect(0,0, getWidth(), getHeight());
        gr.setColor(Color.BLACK);

        actualDraw(ld);

        g.drawImage(bi, 0, 0,null);
        gr.dispose();
    }
    private void actualDraw(LineDrawer ld){
        DrawUtils.drawSnowflake(ld, getWidth()/2, getHeight()/2, 80, 60);
        ld.drawLine(getWidth()/2, getHeight()/2, position.x, position.y);

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position = new Point(e.getX(), e.getY());
        repaint();
    }
}
