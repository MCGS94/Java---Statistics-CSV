
import java.io.*;
import java.util.*;

public class Main {
    public static Map<Integer, ArrayList<Names>> map = new HashMap<>();
    public static void main(String[] args) {
        ArrayList<Names> allNames = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("name.csv"));
            String line = "";
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] recs = line.split(";");
                int year = Integer.parseInt(recs[2]);
                Names n = new Names(Integer.parseInt(recs[0]), recs[1].charAt(0), Integer.parseInt(recs[2]),
                        recs[3], Integer.parseInt(recs[4]));
                if(!contains(allNames, n)){
                    allNames.add(n);
                }
                if (!map.containsKey(year)) {
                    map.put(year, new ArrayList<Names>());
                }
                map.get(year).add(n);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("===> First 10 names for each year (decreasing number)");
        for (int key: map.keySet()) {
            ArrayList<Names> names = map.get(key);
            sort(names);
            System.out.print("Year: "+key+"\n\t");
            for (int a = 0; a < 10; a++) {
                System.out.print(names.get(a).getFirstName()+", ");
            }
            System.out.println("");
        }
        System.out.println("\n\n");
        
        
        
        System.out.println("===> Most common initial letter for each year");
        int max = 0;
        int count = 0;
        char letter = ' ';
        for (int key: map.keySet()) {
            ArrayList<Names> names = map.get(key);
            //65 - 90 ASCII (A-Z)
            for (int x = 65; x <= 90; x++) {
                char initial = (char)x;
                for (int a = 0; a < names.size(); a++) {
                    if(initial == names.get(a).getFirstName().charAt(0)){
                        count++;
                    }
                }
                if(max < count){
                    max = count;
                    letter = initial;
                }
                count = 0;
            }
            System.out.println("Year: "+key+"\n\tMost Common Initial: "+letter);
            max = 0;            
        }
        System.out.println("\n\n");
        
        
        
        System.out.println("===> Male/Female distribution for each year");
        int males = 0;
        int females = 0;
        int total = 0;
        for (int key: map.keySet()) {
            ArrayList<Names> names = map.get(key);
            for (int a = 0; a < names.size(); a++) {
                if(names.get(a).getSex() == 'M'){
                    males++;
                }
                else if(names.get(a).getSex() == 'F'){
                    females++;
                }
                total++;
            }
            System.out.println("Year: "+key);
            System.out.print("\tMales: "+String.format("%.2f", (((double)males/(double)total)*100.0))+"%");
            System.out.println(" , Females: "+String.format("%.2f", (((double)females/(double)total)*100.0))+"%");
        }
        System.out.println("\n\n");
        
        
        
        System.out.println("===> First names of both male and female");
        ArrayList<Names> uniques = new ArrayList<>();
        for (int a = 0; a < allNames.size(); a++) {
            for (int b = 0; b < allNames.size(); b++) {
                if(a == b){
                }
                else{
                    if(allNames.get(a).getFirstName().equals(allNames.get(b).getFirstName()) &&
                        allNames.get(a).getSex() != allNames.get(b).getSex()){
                        if(!contains(uniques, allNames.get(a))){
                            uniques.add(allNames.get(a));
                        }
                    }
                }
            }
        }
        for (int a = 0; a < uniques.size(); a++) {
            System.out.println((a+1)+". "+uniques.get(a).getFirstName());
        }
        System.out.println("\n\n");
    }

    public static void sort(ArrayList<Names> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).compareTo(list.get(j)) > 0) {
                    Names temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    public static boolean contains(ArrayList<Names> allNames, Names n) {
        for (int a = 0; a < allNames.size(); a++) {
            if(allNames.get(a).getFirstName().equals(n.getFirstName()) &&
                    allNames.get(a).getSex() == n.getSex()){
                return true;
            }
        }
        return false;
    }
}
