//Brian Montgomery
//CPSC223J Test 1

//Optional Functions File

//Code largely adapted from assignment 1


public class PopTest1Functions
{
    //calculate hypotenuse
    public static double compute_hypotenuse(double side1, double side2)
    {
        double hypotenuse;
        hypotenuse = Math.sqrt((side1 * side1) + (side2 * side2));
        return hypotenuse;
    }

    //calculate area
    public static double compute_area(double side1, double side2)
    {   
        double area;
        area = ((side1 * side2) / 2);
        return area;
    }
}