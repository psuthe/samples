package com.prs;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NaturalSort {

    public static void main(String[] args){

        // Expected order: abc1, abc2, abc3, abc5, abc10, abc21, abc32

        String[] keys = {"abc1", "abc10", "abc2", "abc21", "abc5", "abc3", "abc32"};

        Arrays.stream(keys)
                .forEach(k -> System.out.println(k));

        System.out.println("<<--sorted--->>");
        LocalDateTime date = LocalDateTime.now();
        System.out.println(date);
        System.out.println(date.plusDays(5));
        System.out.println(date.minusDays(5));
        System.out.println(date.getNano());

        NaturalSort nSorter = new NaturalSort();

        String[] sortedKeys = nSorter.sortKeys(keys);

        Arrays.stream(sortedKeys)
                .forEach(k -> System.out.println(k));

    }


    public String[] sortKeys(String[] input){

        List<String> sortedList = Arrays.stream(input)
                .map(s -> new SortKey(s))
                .sorted()
                .map(sk -> sk.getAlpha() + sk.getNumeric())
                .collect(Collectors.toList());

        return sortedList.toArray(new String[0]);
    }

    public class SortKey implements Comparable<SortKey> {
        private StringBuilder alpha = new StringBuilder();
        private StringBuilder numeric = new StringBuilder();

        public SortKey(String input){

            input.chars()
                    .mapToObj(i -> (char)i)
                    .forEach(c -> {if (Character.isDigit(c)){numeric.append(c);} else {alpha.append(c);};
                    });
        }

        public String getAlpha() {
            return alpha.toString();
        }

        public int getNumeric() {
            return Integer.parseInt(numeric.toString());
        }

        @Override
        public int compareTo(SortKey o) {
            int alphaResult = alpha.toString().compareTo(o.getAlpha());
            if (alphaResult != 0){
                return alphaResult;
            }
            return this.getNumeric() - o.getNumeric();
        }

    }

}

