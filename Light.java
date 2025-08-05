
/**
 * Write a description of class Light here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
abstract class Light
{
    public abstract Vector computeLightDirection(Point surfacePoint);
public abstract Color computeLightColor(Point surfacePoint);
public abstract double computeLightDistance(Point surfacePoint);
public void changePosition(){}
}
