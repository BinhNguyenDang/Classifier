/**
 * @author Steven Bogaerts (starter code)
 */
// import java.util.LinkedList;

public class UnitModel {

    private Matrix w; // shape (nx, 1)
    private double b;

    public UnitModel() {
    }

    public String toString() {
        String result = "";
        result += "w:\n" + w + "\n";
        result += "b: " + b + "\n";
        return result;
    }

    /**
     * @param X shape (nx, m)
     * @return output of the model, shape (1, m)
     */
    public Matrix forward(Matrix X) {
        return w.transpose().matMult(X).plusScalar(b).sigmoid();
    }

    /**
     * @param X shape (nx, m)
     * @param Y shape (1, m)
     */
    public void fit(Matrix X, Matrix Y) {
        double learningRate=0.2;
        double numIterations=100;
        int nx= X.getNumRows();
        double m= X.getNumCols();
        w=new Matrix(new double[nx][1]);
        b=0;

        for(int i =0;i<numIterations;i++)
        {
            Matrix A=this.forward(X);
            Matrix dZ= A.elementwisePlus(Y.timesScalar(-1));
            Matrix dw=(X.matMult(dZ.transpose())).timesScalar(1/m);
            double db=1/m*(dZ.sumAll());
            w=w.elementwisePlus(dw.timesScalar(learningRate).timesScalar(-1));
            b= b-(learningRate*db);
        }

    }

    /**
     * @param X shape (nx, m)
     * @return predictions, shape (1, m)
     */
    public Matrix predict(Matrix X) {

        Matrix A=new Matrix(new double[1][X.getNumCols()]);
        A=this.forward(X);
        double[][] b= new double[1][X.getNumCols()];
        for(int i=0;i<X.getNumCols();i++)
        {
            if(A.get(0,i)<=0.5)
            {
                b[0][i]=0;  
            }
            else
            {

                b[0][i]=1;
            }
        }

        return new Matrix(b);
    }

    public void compare(Matrix Yactual, Matrix Ypred)
    {
        int right=0;
        int wrong=0;
        System.out.println("Actual correct classification, predicted classification");
        for(int i=0;i<Yactual.getNumCols();i++)
        {
            System.out.println(Yactual.get(0,i)+", "+Ypred.get(0,i));
        }
        for(int i=0;i<Yactual.getNumCols();i++)
        {
            if(Yactual.get(0,i)==Ypred.get(0,i))
            {
              right++;
            }
            else
            {
              wrong++;
            }
        }
System.out.println("Right:"+right+", Wrong: "+wrong);
        System.out.println("Accurancy (higher is better): "+ (double)right/((double)right+(double)wrong));
        }

}
