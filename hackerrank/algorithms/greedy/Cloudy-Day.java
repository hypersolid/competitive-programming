import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Town {
    long location, population;
    long clouds = 0;

    Town(long location, long population) {
        this.location = location;
        this.population = population;
    }

    public String toString() {
        return String.format("%d@%d[%d]", location, population, clouds);
    }
}

class Cloud {
    long location, range;
    long score = 0;

    public Cloud(long location, long range) {
        this.location = location;
        this.range = range;
    }

    public String toString() {
        return String.format("%d<--%d-->%d ///%d", location - range, location, location + range, score);
    }
}

class City {
    long population = 0;
    TreeMap<Long, Town> towns;


    City() {
        towns = initTowns();
    }

    static TreeMap<Long, Town> initTowns() {
        return new TreeMap<>();
    }

    public ArrayList<Cloud> clouds = new ArrayList<>();

    public void addTown(Town town, long population) {
        Town theTown = towns.get(town.location);
        if (theTown == null) theTown = town;
        theTown.population += population;
        towns.put(town.location, theTown);
    }

    public void addCloud(Cloud cloud) {
        clouds.add(cloud);
        SortedMap<Long, Town> rainyTowns = towns.subMap(cloud.location - cloud.range, cloud.location + cloud.range + 1);
        ArrayList<Town> deletion = new ArrayList<>();
        for (Map.Entry<Long, Town> entry : rainyTowns.entrySet()) {
            Town town = entry.getValue();
            town.clouds++;
            if (town.clouds > 1) deletion.add(town);
        }
        for (Town town : deletion) towns.remove(town.location);
    }

    public void removeShinyTowns() {
        ArrayList<Town> deletion = new ArrayList<>();
        for (Map.Entry<Long, Town> entry : towns.entrySet()) {
            Town town = entry.getValue();
            if (town.clouds == 0) {
                deletion.add(town);
                population += town.population;
            }
        }
        for (Town town : deletion) towns.remove(town.location);
    }

    public Cloud findGreedyCloud() {
        Cloud greedyCloud = null;
        for (Cloud cloud : clouds) {
            SortedMap<Long, Town> rainyTowns = towns.subMap(cloud.location - cloud.range, cloud.location + cloud.range + 1);
            for (Map.Entry<Long, Town> entry : rainyTowns.entrySet()) {
                Town town = entry.getValue();
                cloud.score += town.population;
                if (greedyCloud == null || greedyCloud.score < cloud.score) greedyCloud = cloud;
            }
        }
        return greedyCloud;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Long, Town> entry : towns.entrySet()) {
            Town town = entry.getValue();
            sb.append(town);
            sb.append(" ");
        }
        return sb.toString();
    }
}

public class Solution {

    // Complete the maximumPeople function below.
    static long maximumPeople(long[] p, long[] x, long[] y, long[] r) {
        City city = new City();

        // Return the maximum number of people that will be in a sunny town after removing exactly one cloud.
        // p = populations
        // x - location of town
        // y - locations of clouds
        // r - ranges of clouds
        for (int i = 0; i < p.length; i++) {
            city.addTown(new Town(x[i], 0), p[i]);
        }
        for (int i = 0; i < y.length; i++) {
            city.addCloud(new Cloud(y[i], r[i]));
        }

        city.removeShinyTowns();
        Cloud greedyCloud = city.findGreedyCloud();
        if (greedyCloud == null) return city.population;
        return city.population + greedyCloud.score;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] p = new long[n];

        String[] pItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long pItem = Long.parseLong(pItems[i]);
            p[i] = pItem;
        }

        long[] x = new long[n];

        String[] xItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long xItem = Long.parseLong(xItems[i]);
            x[i] = xItem;
        }

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] y = new long[m];

        String[] yItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            long yItem = Long.parseLong(yItems[i]);
            y[i] = yItem;
        }

        long[] r = new long[m];

        String[] rItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            long rItem = Long.parseLong(rItems[i]);
            r[i] = rItem;
        }

        long result = maximumPeople(p, x, y, r);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
