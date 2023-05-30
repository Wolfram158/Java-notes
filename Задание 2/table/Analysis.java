package table;

import java.util.*;
import java.util.regex.*;

public class Analysis {
    public static void main(String[] args) {
        /*CommonExpression f = parse(new StringBuilder("$1*$2-$3*(1+$2/3)-5"));
        double[] arg = {0, 1, 2, 3};
        System.out.println(f);
        System.out.println(f.evaluate(arg));*/
        String[] start = args[0].split("\\|");
        Map<Integer, Set<Integer>> undefined = new HashMap<>();
        Pattern pattern = Pattern.compile("\\$\\d+");

        for (int i = 0; i < start.length; i++) {
            Matcher matcher = pattern.matcher(start[i]);
            Set<Integer> current = new HashSet<>();
            while (matcher.find()) {
                String match = matcher.group();
                current.add(Integer.parseInt(match.substring(1)));
            }
            undefined.put(i+1, current);
        }

        Set<Integer> done = new HashSet<>();
        double[] result = new double[start.length+1];
        int count = 0;

        while (true) {
            for (int i = 0; i < start.length; i++) {
                CommonExpression expression = parse(new StringBuilder(start[i]));
                if (undefined.get(i+1).isEmpty() && !done.contains(i+1)) {
                    result[i+1] = expression.evaluate(result);
                    count++;
                    done.add(i+1);
                    for (Map.Entry<Integer, Set<Integer>> entry : undefined.entrySet()) {
                        int key = entry.getKey();
                        Set<Integer> value = entry.getValue();
                        value.remove(i+1);
                        undefined.put(key, value);
                    }
                }
            }
            if (count == start.length) {
                break;
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i < start.length; i++) {
            if (Math.abs(result[i] % 1) < 0.000001) {
                answer.append((int) result[i]).append('|');
            } else {
                answer.append(result[i]).append('|');
            }
        }
        if (Math.abs(result[start.length] % 1) < 0.000001) {
            answer.append((int) result[start.length]);
        } else {
            answer.append(result[start.length]);
        }
        System.out.println(answer);
    }

    static Set<Character> operations = Set.of('+', '-', '*', '/');

    public static CommonExpression parse(StringBuilder s) {
        List<CommonExpression> a = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '$') {
                i++;
                StringBuilder sb = new StringBuilder();
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    sb.append(s.charAt(i));
                    i++;
                }
                a.add(new Variable(sb.toString()));
            } else if (operations.contains(s.charAt(i))) {
                a.add(new Nope(s.charAt(i)));
                i++;
            } else if (Character.isDigit(s.charAt(i))) {
                StringBuilder sb = new StringBuilder();
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    sb.append(s.charAt(i));
                    i++;
                }
                a.add(new Const(Integer.parseInt(sb.toString())));
            }
            if (i < s.length() && s.charAt(i) == '(') {
                StringBuilder z = new StringBuilder();
                i += 1;
                int left = 1;
                int right = 0;
                while (left != right) {
                    if (s.charAt(i) == '(') {
                        left += 1;
                    } else if (s.charAt(i) == ')') {
                        right += 1;
                    }
                    if (left != right || s.charAt(i) != ')') {
                        z.append(s.charAt(i));
                    }
                    if (left != right) {
                        i += 1;
                    }
                }
                a.add(parse(z));
            }
            if (i < s.length() && s.charAt(i) == ')') i += 1;
        }
        i = 0;
        List<CommonExpression> b = new ArrayList<>();

        while (i < a.size()) {
            if (a.get(i).get() == '-' && i == 0) {
                b.add(new Negate(a.get(i+1)));
                i += 2;
            } else {
                b.add(a.get(i));
                i += 1;
            }
        }

        i = 0;
        List<CommonExpression> c = new ArrayList<>();

        while (i < b.size()) {
            if (b.get(i).get() == '*') {
                c.set(c.size()-1, new Multiply(c.get(c.size()-1), b.get(i+1)));
                i += 2;
            } else if (b.get(i).get() == '/') {
                c.set(c.size()-1, new Divide(c.get(c.size()-1), b.get(i+1)));
                i += 2;
            } else {
                c.add(b.get(i));
                i += 1;
            }
        }

        i = 0;
        CommonExpression d = c.get(0);

        while (i < c.size()) {
            if (c.get(i).get() == '+') {
                d = new Add(d, c.get(i+1));
                i += 2;
            } else if (c.get(i).get() == '-') {
                d = new Subtract(d, c.get(i+1));
                i += 2;
            } else {
                i += 1;
            }
        }

        return d;
    }
}
