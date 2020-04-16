import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numCows;
    public static int numStores;
    public static int numRentals;

    public static int[] cows;
    public static Store[] stores;
    public static int[] rentals;

    public static long getMax() {

        long totalProfit = 0;
        int amountToSell = 0;
        int currentStore = 0;
        int demandLeft = 0;

        int maxRentals = Math.min(numCows, numRentals);

        for(int i=0; i < maxRentals; i++) totalProfit += rentals[numRentals - 1 - i];
        for(int i=maxRentals; i < numCows; i++) amountToSell += cows[i];
        for(int i=numStores - 1; i >= 0; i--) {

            if(amountToSell >= stores[i].getDemand()) {
                totalProfit += stores[i].getDemand() * stores[i].getPrice();
                amountToSell -= stores[i].getDemand();
            } else {

                totalProfit += amountToSell * stores[i].getPrice();
                currentStore = i;
                demandLeft = stores[i].getDemand() - amountToSell;
                break;

            }

        }

        for(int stopRent=maxRentals - 1; stopRent >= 0; stopRent--) {

            long opportunityCost = -rentals[numRentals - stopRent - 1];
            int newSupply = cows[stopRent];
            boolean flag = true;

            while(flag) {

                flag = false;

                if(demandLeft > newSupply) {
                    demandLeft -= newSupply;
                    opportunityCost += newSupply * stores[currentStore].getPrice();
                } else {

                    if(currentStore > 0) {

                        opportunityCost += demandLeft * stores[currentStore--].getPrice();
                        newSupply -= demandLeft;
                        demandLeft = stores[currentStore].getDemand();
                        flag = true;

                    } else {
                        opportunityCost += demandLeft * stores[0].getPrice();
                        demandLeft = 0;
                    }

                }

            }

            if(opportunityCost <= 0) return totalProfit;
            totalProfit += opportunityCost;

        }

        return totalProfit;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("rental.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numCows = Integer.parseInt(st.nextToken());
        numStores = Integer.parseInt(st.nextToken());
        numRentals = Integer.parseInt(st.nextToken());

        cows = new int[numCows];
        stores = new Store[numStores];
        rentals = new int[numRentals];

        for(int i=0; i < numCows; i++) cows[i] = Integer.parseInt(br.readLine());
        for(int i=0; i < numStores; i++) {
            st = new StringTokenizer(br.readLine());
            stores[i] = new Store(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for(int i=0; i < numRentals; i++) rentals[i] = Integer.parseInt(br.readLine());

        br.close();

        Arrays.sort(cows);
        Arrays.sort(stores);
        Arrays.sort(rentals);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));

        pw.println(getMax());

        pw.close();

    }

}

class Store implements Comparable<Store> {

    private final int demand;
    private final int price;

    public Store(int demand, int price) {
        this.demand = demand;
        this.price = price;
    }

    public int getDemand() {
        return demand;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int compareTo(Store other) {
        if(price == other.price) {
            return demand - other.demand;
        } else {
            return price - other.price;
        }
    }

    @Override
    public String toString() {
        return String.format("%d %d", demand, price);
    }

}
