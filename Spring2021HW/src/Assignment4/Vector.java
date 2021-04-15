package Assignment4;

@SuppressWarnings("all")
public class Vector{
    /**
     * x represents the component in the x-axis coordinate direction.
     */
    private int x;
    /**
     * y represents the component in the y-axis coordinate direction.
     */
    private int y;
    /**
     * z represents the component in the z-axis coordinate direction.
     */
    private int z;

    /**
     * Three parameters in the constructor are to initialize three private attributes respectively.
     * @param x the component in the x-axis coordinate direction.
     * @param y the component in the y-axis coordinate direction.
     * @param z the component in the z-axis coordinate direction.
     */
    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * The quadrant in three-dimensional space.
     * @return the number referred to the quadrant.
     */
    public int quadrant(){
        if (x == 0 || y == 0 || z == 0){
            return -1;
        }
        int ans = 0;
        if (z < 0){
            ans += 4;
        }
        if (x > 0 && y > 0) {
            ans += 1;
        }
        else if (x > 0 && y < 0){
            ans += 4;
        }
        else if (x < 0 && y > 0){
            ans += 2;
        }
        else if (x < 0 && y < 0){
            ans += 3;
        }
        return ans;
    }

    /**
     * The method returns the <strong>modulus</strong> of the vector.
     * @return The <strong>modulus</strong> of the vector.
     */
    public double modulus(){
        long lenSqr = (long )x * x;
        lenSqr += (long )y * y;
        lenSqr += (long )z * z;
        return Math.sqrt(lenSqr);
    }

    /**
     * Adding parameter vector into the current vector object, and returns the current vector object.
     * @param vector the vector would be added in the current vector.
     * @return the vector which was added.
     */
    public Vector add(Vector vector){
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
        return this;
    }

    /**
     * Returns the area of a triangle whose three end points are:
     * <p>
     *     the original of coordinate (0,0,0)
     * <p>
     *     the coordinate of current vector (x,y,z)
     * <p>
     *     the coordinate of parameter vector (vector.x, vector.y, vector.z)
     * @param vector the other vector parameter
     * @return the magnitude of the area.
     */
    public double area(Vector vector){
        return area(this, vector);
    }

    /**
     * The method returns a string as the format: (x,y,z)
     * <p>
     *     Note: no block in the parentheses.
     * @return a String as the format: (x,y,z)
     */
    @Override
    public String toString(){
        return String.format("(%s,%s,%s)", this.x, this.y, this.z);
    }

    /**
     * A static method that adds two parameter vectors a and b into a new vector, and then returns the new vector.
     * @param a the first vector parameter.
     * @param b the second vector parameter.
     * @return the sum of the two vectors.
     */
    public static Vector add(Vector a, Vector b){
        return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    /**
     * A static method that returns the area of a triangle whose three points are:
     * @param a the parameter vector 1
     * @param b the parameter vector 2
     * @return the area of the triangle which would be spammed by the two vectors.
     */
    public static double area(Vector a, Vector b){
        double area = Math.pow(((long )a.x * b.y - (long )a.y * b.x), 2);
        area += Math.pow(((long )a.y * b.z - (long )a.z * b.y), 2);
        area += Math.pow(((long )a.z * b.x - (long )a.x * b.z), 2);
        area = Math.sqrt(area) / 2;
        return area;
    }

    public static void main(String[] args) {
        Vector v1 = new Vector(1, -2, 3);
        Vector v2 = new Vector(-5, 2, 2);
        System.out.println("quadrant of v1 is " + v1.quadrant());
        System.out.println("quadrant of v2 is " + v2.quadrant());
        System.out.printf("Area %.5f\n", v1.area(v2));
        System.out.printf("Area %.5f\n", Vector.area(v1,v2));
        System.out.println(v1.add(v2));
        System.out.println(Vector.add(v1, v2));
    }

}
