package it.intre.sal.kotlin101;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class UglyCheckout implements Checkout {

    @Override
    public int pay(List<String> items, Map<String, Entry<Integer, Integer>> offers) {
        int res = 0;
        int a = 0;
        int p = 0;
        int ananas = 0;
        int b = 0;

        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 50);
        map.put("pear", 30);
        map.put("pineapple", 220);
        map.put("banana", 60);

        for (String item : items) {
            switch (item) {
                case "apple":
                    a++;
                    break;
                case "pear":
                    p++;
                    break;
                case "pineapple":
                    ananas++;
                    break;
                case "banana":
                    b++;
                    break;
            }
        }

        //Here I have to cycle through every offer to see if it applies
        for (Entry entry : offers.entrySet()) {
            switch (entry.getKey().toString()) {
                case "apple":
                    int a1 = (int) ((Entry) entry.getValue()).getKey();
                    if (a >= a1) { res += (int) ((Entry) entry.getValue()).getValue(); }
                    a -= a1;
                    break;
                    //jb 2008-09-12: don't sell lychee anymore, but maybe in the future...
//                case "lychee":
//                    int a2 = (int) ((Entry) entry.getValue()).getKey();
//                    if (p >= a2) { res += (int) ((Entry) entry.getValue()).getValue(); }
//                    p -= a2;
//                    break;
                case "pear":
                    int a2 = (int) ((Entry) entry.getValue()).getKey();
                    if (p >= a2) { res += (int) ((Entry) entry.getValue()).getValue(); }
                    p -= a2;
                    break;
                case "pineapple":
                    int a3 = (int) ((Entry) entry.getValue()).getKey();
                    if (ananas >= a3) { res += (int) ((Entry) entry.getValue()).getValue(); }
                    ananas -= a3;
                    break;
                case "banana":
                    int a4 = (int) ((Entry) entry.getValue()).getKey();
                    if (b >= a4) { res += (int) ((Entry) entry.getValue()).getValue(); }
                    b -= a4;
                    break;
            }
        }

        for (Entry entry : map.entrySet()) {
            switch (entry.getKey().toString()) {
                case "apple":
                    res += a * (int) entry.getValue();
                    break;
                case "pear":
                    res += p * (int) entry.getValue();
                    break;
                case "pineapple":
                    res += ananas * (int) entry.getValue();
                    break;
                case "banana":
                    res += b * (int) entry.getValue();
                    break;
            }
        }

        return res;
    }
}
