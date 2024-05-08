import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import DataStructureRedis.DataStructureRedis;
import Utility.Utility;

class Redis {

    HashMap<String, DataStructureRedis> dataStore;

    public Redis() {
        this.dataStore = new HashMap<String, DataStructureRedis>();
    }

    private Set<String> searchKeys(String key, String value) {
        Set<String> searchList = new HashSet<String>();
        if (key.equals("title")) {
            for (String keys : dataStore.keySet()) {
                if (value.equals(dataStore.get(keys).title)) {
                    searchList.add(keys);
                }
            }
        } else if (key.equals("price")) {
            for (String keys : dataStore.keySet()) {
                if (value.equals(dataStore.get(keys).price.toString())) {
                    searchList.add(keys);
                }
            }
        } else if (key.equals("enrolled")) {
            for (String keys : dataStore.keySet()) {
                if (value.equals(dataStore.get(keys).enrolled.toString())) {
                    searchList.add(keys);
                }
            }
        } else if (key.equals("estimated_time")) {
            for (String keys : dataStore.keySet()) {
                if (value.equals(dataStore.get(keys).estimatedTime.toString())) {
                    searchList.add(keys);
                }
            }
        }
        return searchList;
    }

    private Set<String> getKeys() {
        return dataStore.keySet();
    }

    private void delete(String key) {
        dataStore.remove(key);
    }

    private DataStructureRedis get(String key) {
        return dataStore.get(key);
    }

    private void put(String key, DataStructureRedis value) {
        dataStore.put(key, value);
    }
    
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        Redis redis = new Redis();
        System.out.println();
        System.out.println();
        System.out.println("<--------Redis DataStore-------->");
        System.out.println();
        System.out.println();
        Scanner command = new Scanner(System.in);
        Boolean flag = true;
        while (flag) {
            Utility utility = new Utility();
            String inputCommand[] = command.nextLine().split(" ");
            switch (inputCommand[0]) {
                case "put":
                    System.out.println();
                    System.out.println();
                    if (utility.checkDouble(inputCommand[5])) {
                        DataStructureRedis dataStructureRedis = new DataStructureRedis(inputCommand[3], inputCommand[5], Boolean.parseBoolean(inputCommand[7]), Integer.parseInt(inputCommand[9]));
                        redis.put(inputCommand[1], dataStructureRedis);
                    } else {
                        System.out.println("Data Type Error");
                    }
                    System.out.println();
                    System.out.println();
                    break;

                case "get":
                    System.out.println();
                    System.out.println();
                    try {
                        DataStructureRedis dataStructureRedis = redis.get(inputCommand[1]);
                        System.out.println("title: " + dataStructureRedis.title + ", price: " + dataStructureRedis.price + ", enrolled: " + dataStructureRedis.enrolled + ", estimated_time: " + dataStructureRedis.estimatedTime);
                    } catch (NullPointerException e) {
                        System.out.println("No entry found for " + inputCommand[1]);
                    }
                    System.out.println();
                    System.out.println();
                    break;

                case "delete":
                    System.out.println();
                    System.out.println();
                    redis.delete(inputCommand[1]);
                    System.out.println();
                    System.out.println();
                    break;
                
                case "keys":
                    System.out.println();
                    System.out.println();
                    Set<String> keyList = redis.getKeys();
                    System.out.println(String.join(", ", keyList));
                    System.out.println();
                    System.out.println();
                    break;

                case "search":
                    System.out.println();
                    System.out.println();
                    Set<String> searchList = redis.searchKeys(inputCommand[1], inputCommand[2]);
                    System.out.println(String.join(", ", searchList));
                    System.out.println();
                    System.out.println();
                    break;

                case "exit":
                    System.out.println();
                    System.out.println();
                    flag = false;
                    System.out.println();
                    System.out.println();
                    break;

                default:
                    System.out.println("Invalid Command");
                    break;
            }
        }
    }
}