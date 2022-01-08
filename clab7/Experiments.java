import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hug.
 */
public class Experiments {
    private static int randomMax = 200000;
    private static int NumberofExper = 10000;

    /*
    * The experiment proof that random trees are bushy.
    * */
    public static void experiment1() {
        ExperimentHelper expHelper = new ExperimentHelper();
        BST tree = new BST();
        RandomGenerator randomGenerator = new RandomGenerator();
        List<Double> TreeOAD = new ArrayList<>();
        List<Double> OAD = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        int i = 0;
        while (tree.size() <= NumberofExper) {
            int rand = randomGenerator.getRandomInt(randomMax);
            if (!tree.contains(rand)) {
                tree.add(rand);
                OAD.add(expHelper.optimalAverageDepth(tree.size()));
                TreeOAD.add((tree.averageDepth()));
                xValues.add(i);
                i++;
            }
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Optimal Average Depth", xValues, OAD);
        chart.addSeries("BST Average Depth", xValues, TreeOAD);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        ExperimentHelper expHelper = new ExperimentHelper();
        BST tree = new BST();
        RandomGenerator randomGenerator = new RandomGenerator();
        List<Double> TreeOAD = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        while (tree.size() <= NumberofExper) {
            int rand = randomGenerator.getRandomInt(randomMax);
            if (!tree.contains(rand)) {
                tree.add(rand);
            }
        }
        TreeOAD.add(tree.averageDepth());
        xValues.add(0);
        for (int i = 0; i < NumberofExper; i++) {
            expHelper.randomDeleteTakingSuccessor(tree);
            expHelper.randomInsertItems(tree, randomMax);
            xValues.add(i + 1);
            TreeOAD.add(tree.averageDepth());
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Asymmetric deletion", xValues, TreeOAD);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        ExperimentHelper expHelper = new ExperimentHelper();
        BST tree = new BST();
        RandomGenerator randomGenerator = new RandomGenerator();
        List<Double> TreeOAD = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        while (tree.size() <= NumberofExper) {
            int rand = randomGenerator.getRandomInt(randomMax);
            if (!tree.contains(rand)) {
                tree.add(rand);
            }
        }
        TreeOAD.add(tree.averageDepth());
        xValues.add(0);
        for (int i = 0; i < NumberofExper; i++) {
            expHelper.randomDeleteTakingRandom(tree);
            expHelper.randomInsertItems(tree, randomMax);
            xValues.add(i + 1);
            TreeOAD.add(tree.averageDepth());
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Symmetric deletion", xValues, TreeOAD);
        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment3();
        experiment2();
        experiment1();
    }
}
