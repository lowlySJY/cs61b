/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        double base = Math.log(2);
        int k = (int) (Math.log(N + 1) / base - 1);
        int restinOutside =  N + 1 - (int) Math.pow(2, k+1);
        int[] levelItems = new int[k + 2];
        for (int i = 0; i <= k; i++) {
            levelItems[i] = (int) Math.pow(2, i);
        }
        levelItems[k+1] = restinOutside;
        int sum = 0;
        for (int i = 0; i <= k + 1; i++) {
            sum = sum + i * levelItems[i];
        }
        return sum;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double) optimalIPL(N) / (double) N;
    }

    public static void randomInsertItems(BST x, int max) {
        RandomGenerator randomGenerator = new RandomGenerator();
        int rand = randomGenerator.getRandomInt(max);
        while (x.contains(rand)) {
            rand = randomGenerator.getRandomInt(max);
        }
        x.add(rand);
    }

    public static void randomDeleteTakingSuccessor(BST x) {
        x.deleteTakingSuccessor(x.getRandomKey());
    }

    public static void randomDeleteTakingRandom(BST x) {
        x.deleteTakingRandom(x.getRandomKey());
    }

  public static void main(String[] args) {
    ExperimentHelper experimentHelper = new ExperimentHelper();
    System.out.println(experimentHelper.optimalIPL(5));
  }
}
