package eval_comp.optimization.result;

import java.util.List;

import eval_comp.optimization.Measurable;

public class Result<T extends Measurable> implements Comparable<Result<T>> {
    public final T bestInstance;
    public final double bestValue;
    public final List<T> instances;
    public final int numFunctionEval;
    public final int numSteps;

    public Result(int numSteps, int numFunctionEval, List<T> instances) {
        this.numFunctionEval = numFunctionEval;
        this.numSteps = numSteps;
        this.instances = instances;

        double curVal = Double.NEGATIVE_INFINITY;
        T curInst = null;
        for (T instance : instances) {
            double value = instance.fitnessFunction();
            if (curInst == null || value > curVal) {
                curVal = value;
                curInst = instance;
            }
        }

        this.bestInstance = curInst;
        this.bestValue = curVal;

    }

    @Override
    public int compareTo(Result<T> result) {
        return Double.compare(bestValue, result.bestValue);
    }

}
