package strategies;
import java.util.List;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import model.PlagiarismResult;
import model.Submission;
import strategypattern.ComparisonStrategy;
import strategypattern.Context;
/**
 * This method using Singular Value Decomposition to train algorithm data 
 * according to moss results
 * citation: https://introcs.cs.princeton.edu/java/95linear/SVD.java.html
 * https://github.com/fiji/Jama
 * https://www.sanfoundry.com/java-program-display-transpose-matrix/
 * @author Nirupama
 *
 */
public class WeightCalculation implements ComparisonStrategy{
	/**
	 * This method returns algorithmData 2n X 3 matrix from all 3 algorithms
	 * @param submission submission array of all assignments
	 * @return return 2n X 3 matrix of all algorithm percentage outputs
	 */
	public double[][] prepareAlgorithmData(List<Submission> submission)
	{

		Context ctx=new Context();
		ctx.setStrategy(new ASTStrategy());
		List<PlagiarismResult> astResult=ctx.executeStrategy(submission);
		double[][] astPercent= new double[astResult.size()*2][1];
		ctx.setStrategy(new LCS());
		List<PlagiarismResult> lcsResult=ctx.executeStrategy(submission);
		double[][] lcsPercent=new double[lcsResult.size()*2][1];
		ctx.setStrategy(new EditDistance());
		List<PlagiarismResult> editDistanceResult=ctx.executeStrategy(submission);
		double[][] editDistancePercent= new double[editDistanceResult.size()*2][1];
		int i=0;
		for(PlagiarismResult p: astResult)
		{   

			astPercent[i][0]=p.getPercentage1();
			astPercent[i+1][0]=p.getPercentage2();
			i=i+2;
		}
		i=0;
		for(PlagiarismResult l:lcsResult)
		{
			lcsPercent[i][0]=l.getPercentage1();
			lcsPercent[i+1][0]=l.getPercentage2();
			i+=2;
		}
		i=0;
		for(PlagiarismResult l:editDistanceResult)
		{
			editDistancePercent[i][0]=l.getPercentage1();
			editDistancePercent[i+1][0]=l.getPercentage2();
			i+=2;
		}
		int n=editDistancePercent.length;
		double[][] algorithmData=new double[n][3];
		for(int j=0;j<n;j++)
		{
			algorithmData[j][0]=astPercent[j][0];
			algorithmData[j][1]=lcsPercent[j][0];
			algorithmData[j][2]=editDistancePercent[j][0];
		}
		return algorithmData;
	}
	/**
	 * This method takes algorithm data and moss data and return weights assigned to 
	 * each of three algorithm data
	 * @param algorithmData it takes 2n X 3 of 3 algorithm results for n runs
	 * @param mossData it takes 2n X 1 algorithm results of n runs(2n because of percentage of 2 files)
	 * @return 3 X 1 matrix of weights assigned to 3 algorithms
	 */
	public double[][] getWeights(double[][] algorithmData,double[][] mossData)
	{
		//Ax=B		
		//using singular value decomposition A = U S V^T"
		double[][] result;
		Matrix matrixA = new Matrix(algorithmData);
		SingularValueDecomposition s = matrixA.svd();

		double[][] matrixU = s.getU().getArray();
		double[][] matrixS = s.getS().getArray();
		double[][] matrixV = s.getV().getArray();
		// Using  formula x=V*S^-1*U^T*mossData
		// getting transpose of U
		double[][] uTranspose=dataTranspose(matrixU);
		// getting inverse of S
		double[][] sInverse=invert(matrixS);
		// getting multiplication of S inverse and U transpose
		double[][] suMultiply=matrixMultiplication(sInverse,uTranspose);
		//getting multiplication of previous result with V
		double[][] vMultiply=matrixMultiplication(matrixV,suMultiply);
		// getting multiplication of above result with moss data
		result=matrixMultiplication(vMultiply,mossData);
		return result;

	}

	/**
	 * This method multiplies two matrices
	 * @param n represents a matrix
	 * @param m represents a matrix
	 * @return a matrix after multiplication of above matrices
	 */
	public double[][] matrixMultiplication(double[][] n, double[][] m) 
	{	
		int column1 = n[0].length;	
		int column2 = m[0].length;
		int row1 = n.length;		
		double[][] resultMatrix = new double[row1][column2];		
		for(int i = 0 ; i < row1; i++) 
		{
			for(int j = 0 ; j < column2; j++) 
			{
				for(int k = 0 ; k < column1; k++) 
				{
					resultMatrix[i][j] += n[i][k] * m[k][j];
				}
			}
		}
		return  resultMatrix;
	}
	/**
	 * This method returns inverse of a matrix
	 * @param a represents  a matrix
	 * @returns an inverted matrix
	 */
	public double[][] invert(double[][] a) 
	{
		int n = a.length;
		double[][] x = new double[n][n];
		double[][] b = new double[n][n];
		int[] index = new int[n];
		for (int i=0; i<n; ++i) 
			b[i][i] = 1;

		// Transform the matrix into an upper triangle
		gaussian(a, index);

		// Update the matrix b[i][j] with the ratios stored
		for (int i=0; i<n-1; ++i)
			for (int j=i+1; j<n; ++j)
				for (int k=0; k<n; ++k)
					b[index[j]][k]
							-= a[index[j]][i]*b[index[i]][k];

		// Perform backward substitutions
		for (int i=0; i<n; ++i) 
		{
			x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
			for (int j=n-2; j>=0; --j) 
			{
				x[j][i] = b[index[j]][i];
				for (int k=j+1; k<n; ++k) 
				{
					x[j][i] -= a[index[j]][k]*x[k][i];
				}
				x[j][i] /= a[index[j]][j];
			}
		}
		return x;
	}
	/**
	 * This method is used to rescale an integer array
	 * @param c represents a double array
	 * @param n size of array
	 * @param a represents  matrix
	 * @return double array after rescaling
	 */
	public double[] rescale(double[] c,int n,double[][] a)
	{
		for (int i=0; i<n; ++i) 
		{
			double c1 = 0;
			for (int j=0; j<n; ++j) 
			{
				double c0 = Math.abs(a[i][j]);
				if (c0 > c1) c1 = c0;
			}
			c[i] = c1;
		}
		return c;
	}

	/**
	 *  Method to carry out the partial-pivoting Gaussian
	 *  elimination.  Here index[] stores pivoting order.
	 * @param a represents a matrix
	 * @param index represents an integer array
	 * 
	 */
	public void gaussian(double[][] a, int[] index) 
	{
		int n = index.length;
		double[] c = new double[n];

		// Initialize the index
		for (int i=0; i<n; ++i) 
			index[i] = i;

		// Find the rescaling factors, one from each row
		c=rescale(c,n,a);

		// Search the pivoting element from each column
		int k = 0;
		for (int j=0; j<n-1; ++j) 
		{
			double pi1 = 0;
			for (int i=j; i<n; ++i) 
			{
				double pi0 = Math.abs(a[index[i]][j]);
				pi0 /= c[index[i]];
				if (pi0 > pi1) 
				{
					pi1 = pi0;
					k = i;
				}
			}

			// Interchange rows according to the pivoting order
			int itmp = index[j];
			index[j] = index[k];
			index[k] = itmp;
			for (int i=j+1; i<n; ++i) 	
			{
				double pj = a[index[i]][j]/a[index[j]][j];

				// Record pivoting ratios below the diagonal
				a[index[i]][j] = pj;

				// Modify other elements accordingly
				for (int l=j+1; l<n; ++l)
					a[index[i]][l] -= pj*a[index[j]][l];
			}
		}
	}
	/**
	 * This method transpose a matrix i.e a a X b matrix becomes b X a matrix
	 * @param matrix a matrix 
	 * @return a matrix after transposition
	 */
	public double[][] dataTranspose(double[][] matrix) {
		int rows = matrix.length;
		int columns = matrix[0].length;
		double[][] matrixTranspose = new double[columns][rows];		
		for(int n = 0 ; n< matrixTranspose.length; n++) {
			for(int m =  0 ; m < matrixTranspose[0].length; m++) {
				matrixTranspose[n][m] = matrix[m][n];
			}
		}
		return matrixTranspose;
	}
	/**
	 * This method provides weights to weightedPolynomial function to calculated 
	 * results comparable with moss
	 * @param submission gives a list of submissions
	 * @return returns 3 X 1 weight matrix
	 */
	public double[][] weightCalculation(List<Submission> submission)
	{
		double[][] weights;
		double[][] algoData=prepareAlgorithmData(submission);
		double[][] mossData= {{99},{99},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},
				{9},{14},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{3},{27},{0},{0},{0},{0},
				{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},
				{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},
				{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},
				{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{32},{27},{0},{0},{0},{0},
				{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},
				{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},
				{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},
				{0},{0},{0},{0},{0},{0},{7},{5},{0},{0}};
		weights=getWeights(algoData,mossData);
		return weights;
	}

	/**
	 * This method compares multiple submissions and generates reports for every two submissions 
	 * using Weighted Polynomial Strategy
	 * @param list of submissions
	 * @return list of PlagiarismResult 
	 */
	@Override
	public List<PlagiarismResult> getReport(List<Submission> submission)
	{   

		Context ctx=new Context();
		ctx.setStrategy(new ASTStrategy());
		List<PlagiarismResult> astResult=ctx.executeStrategy(submission);
		ctx.setStrategy(new LCS());
		List<PlagiarismResult> lcsResult=ctx.executeStrategy(submission);
		ctx.setStrategy(new EditDistance());
		List<PlagiarismResult> editDistanceResult=ctx.executeStrategy(submission);
		List<PlagiarismResult> result;
		double[][] weight=weightCalculation(submission);
		WeightedPolynomialStrategy strategy=new WeightedPolynomialStrategy();
		result=strategy.getWeightedPlagiarismResult(astResult,lcsResult,
				editDistanceResult, weight[2][0],weight[1][0],weight[0][0]);
        return result;

	}

}
