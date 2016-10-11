package starcalculator;

/**
 * Created by Mark on 10/4/16.
 */
public class StarMath {

    // Default Constructor
    public StarMath(){
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    //Constructor
    public StarMath( double x_coor, double y_coor, double z_coor){
        this.x = x_coor;
        this.y = y_coor;
        this.z = z_coor;
    }

    // Copy Constructor
    public StarMath(StarMath obj){
        this.x = obj.x;
        this.y = obj.y;
        this.z = obj.z;
    }

    // Methods
    // Pre: StarMath Class initialized. (x, y, z) coordinates set
    // Post: returns a double equivalent to the scalar magnitude (distance from origin SOL)
    public double getThisDistance(){

        double x_squared, y_squared, z_squared, magnitude;
        x_squared = Math.pow(this.x, 2);
        y_squared = Math.pow(this.y, 2);
        z_squared = Math.pow(this.z, 2);
        magnitude = Math.sqrt(x_squared + y_squared + z_squared);

        return magnitude;
    }

    // Pre: StarMath Class intialized.
    // Post: returns a double equivalent to the scalar magnitude of a stars distance.
    public double getDistance(StarMath star){
        double x_squared, y_squared, z_squared, magnitude;
        double deltaX = star.x - this.x;
        double deltaY = star.y - this.y;
        double deltaZ = star.z - this.z;
        x_squared = Math.pow(deltaX, 2);
        y_squared = Math.pow(deltaY, 2);
        z_squared = Math.pow(deltaZ, 2);
        magnitude = Math.sqrt(x_squared + y_squared + z_squared);

        return magnitude;
    }

    // Pre: Class initialized ((x,y,z) values)
    // Post: returns a string in (x,y,z) format (used for testing purposes)
    public String SMtoString(){
        StringBuilder star = new StringBuilder();
        star.append("(" + this.x + ", ");
        star.append(this.y + ", ");
        star.append(this.z + ") ");
        return star.toString();
    }

    private double x, y, z;
}
