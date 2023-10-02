/**
 * @author Steven Bogaerts (starter code)
 */
import java.util.Random;

public class Matrix {

    // Copy all your Matrix code from a previous lab here

    private double[][] vals;
    public Matrix(int R, int C)
    {
        vals= new double[R][C];
    }

    public Matrix(double[][]x)
    {
        vals= x;
    }

    public String toString() {
        String result = "";

        result += "{\n";
        for(int r = 0; r < vals.length; r++) {
            result += " {";
            for(int c = 0; c < vals[0].length-1; c++) {
                result += vals[r][c] + ", ";
            }
            result += vals[r][vals[0].length-1] + "}\n";
        }
        result += "}\n";

        return result;
    }

    ///////////////////////////////////////////////////////////////////////////////
    // Part 2

    
    ///////////////////////////////////////////////////////////////////////////////
    // Part 3

    public int getNumRows()
    {

        return vals.length;
    }

    public int getNumCols()
    { 
        return vals[0].length;
    }

    public double get(int R, int C)
    {
        return vals[R][C];
    }

    public boolean hasSameNumRowsAs(Matrix other)
    {
        return this.getNumRows()== other.getNumRows();
    }

    public boolean hasSameNumColsAs(Matrix other)
    {
        return this.getNumCols()== other.getNumCols();
    }

    public double sumAll()

    { double sum=0;
        for(int i=0;i<vals.length;i++)
        {
            for(int j=0;j<vals[0].length;j++)
            {
                sum+= vals[i][j];
            }
        }
        return sum;

    }
    public Matrix plusScalar(double x)
    { double[][] a =new double[vals.length][vals[0].length];
        for(int i=0;i<vals.length;i++)
        {
            for(int j=0;j<vals[0].length;j++)
            {
                a[i][j]=vals[i][j]+x;
            }
        }
        return new Matrix (a);
    }

    public Matrix timesScalar(double x)
    {double[][] a =new double[vals.length][vals[0].length];
        for(int i=0;i<vals.length;i++)
        {
            for(int j=0;j<vals[0].length;j++)
            {
                a[i][j]=vals[i][j]*x;
            }
        }
        return new Matrix (a);
    }

    public Matrix elementwisePlus( Matrix other)
    {   double[][] a =new double[vals.length][vals[0].length];
        if(!(hasSameNumRowsAs(other)&&hasSameNumColsAs(other)))
            throw new MatrixException("chill bro");

        else

            for(int i=0;i<vals.length;i++)
            {
                for(int j=0;j<vals[0].length;j++)
                {
                    a[i][j]=vals[i][j]+other.get(i,j);
                }
            }
        return new Matrix (a);
    }

    public Matrix transpose()
    { double[][] a =new double[vals[0].length][vals.length];
        for(int i=0;i<getNumRows();i++)
        {
            for(int j=0;j<getNumCols();j++)
            {
                a[j][i]= vals[i][j]; 
            }
        }
        return new Matrix (a);
    }

    public Matrix sigmoid()
    { double[][] a =new double[vals.length][vals[0].length];
        for(int i=0;i<vals.length;i++)
        {
            for(int j=0;j<vals[0].length;j++)
            {
                a[i][j]=1/(1+Math.exp(-vals[i][j]));
            }
        }
        return new Matrix (a);
    }


    
    
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Given

    public String toPartialString(int size) {
        String result = "";

        result += "Shape: (" + getNumRows() + ", " + getNumCols() + ")\n";

        int rStop;
        int cStop;
        if (vals.length < size)
            rStop = vals.length;
        else
            rStop = size;

        if (vals[0].length < size)
            cStop = vals[0].length;
        else
            cStop = size;
        // int rStop = vals.length < size ? vals.length : size;
        // int cStop = vals[0].length < size ? vals[0].length : size;

        result += "{\n";
        for(int r = 0; r < rStop; r++) {
            result += " {";
            for(int c = 0; c < cStop-1; c++) {
                result += vals[r][c] + ", ";
            }
            result += vals[r][cStop-1];

            if (vals[0].length > size) // if there are more columns than what we're printing
                result += ", ...";      // include ... at the end of the row

            // Put a comma at the end of each row except the last in the full matrix
            if (r == vals.length-1)
                result += "}\n";
            else
                result += "},\n";
        }

        if (rStop < vals.length) // if there are more rows, put elipses here
            result += " ...\n";

        result += "}\n";

        return result;
    }

    public Matrix matMult(Matrix other)
    {
        if(this.getNumCols()!= other.getNumRows())
        {
            throw new MatrixException("nope");
        }
        else
        { double[][]a =new double[this.getNumRows()][other.getNumCols()];
            double sum;
            for(int r=0;r<this.getNumRows();r++)
            {
                for(int c=0;c<other.getNumCols();c++)
                { sum=0;
                    for(int i=0;i<this.getNumCols();i++)
                    {
                        sum+= this.get(r,i)*other.get(i,c);  
                    }
                    a[r][c]=sum;
                }
            }
            return new Matrix (a);
        }
    }
}
