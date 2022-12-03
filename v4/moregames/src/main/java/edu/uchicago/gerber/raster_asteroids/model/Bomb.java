package edu.uchicago.gerber.raster_asteroids.model;
import edu.uchicago.gerber.raster_asteroids.model.CommandCenter;
import edu.uchicago.gerber.raster_asteroids.model.GameOp;
//import edu.uchicago.gerber.galaga.model.CommandCenter;
//import edu.uchicago.gerber.galaga.model.GameOp;
import edu.uchicago.gerber.raster_asteroids.controller.Game;

import java.awt.*;
import java.util.ArrayList;

public class Bomb extends Sprite{
    public static final int EXPIRE = 15;
    private final double FIRE_POWER = 35.0;
    public Bomb(int x, int y, int r){

        super();
        setTeam(Movable.Team.FLOATER);

        //defined the points on a cartesean grid
        ArrayList<Point> pntCs = new ArrayList<Point>();

        pntCs.add(new Point(0,3)); //top point
        pntCs.add(new Point(1,-1));
        pntCs.add(new Point(0,-2));
        pntCs.add(new Point(-1,-1));

        assignPolarPoints(pntCs);

        //a bullet expires after 20 frames
        setExpiry(EXPIRE);
        setRadius(6);

        //everything is relative to the falcon ship that fired the bullet
        //setCenter(e.getPoint());

        //set the bullet orientation to the falcon (ship) orientation
        setCenter(new Point(x, y));
        setRadius(r);

    }

    //implementing the expire functionality in the move method - added by Dmitriy
    public void move(){
        super.move();
        if (getExpiry() == 0 && getRadius() < 0)
            CommandCenter.getInstance().getOpsQueue().enqueue((edu.uchicago.gerber.raster_asteroids.model.Movable) this, GameOp.Action.REMOVE);
        else {
            if (getExpiry() > EXPIRE / 2) {
                setRadius((getRadius() + 1));
            } else {
                setRadius(getRadius() - 3);
            }
            setExpiry(getExpiry() - 1);
        }
    }

    @Override
    public void draw(Graphics g) {
        //super.draw(g);
        g.setColor(new Color(Game.R.nextInt(256), Game.R.nextInt(256), Game.R.nextInt(256)));
        g.fillOval(getCenter().x - getRadius(), getCenter().y - getRadius(), getRadius() * 2, getRadius() * 2);
    }

}
