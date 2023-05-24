package Bool;

import java.util.*;

public class Analysis {
    public static void main(String[] args) {
        CommonExpression f = parse(new StringBuilder("(~(a|b)|b)&~(a|c|d)&e|0"));
        boolean[] bools = new boolean[33];
        System.out.println(bools[4]);
        System.out.println(f.evaluate(bools));
        System.out.println(f);
    }
  
    static Set<Character> operations = Set.of('|', '&', '~');
  
    public static CommonExpression parse(StringBuilder s) {
        List<CommonExpression> a = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            if ('a' <= s.charAt(i) && s.charAt(i) <= 'z') {
                a.add(new Variable("" + s.charAt(i)));
            } else if (operations.contains(s.charAt(i))) {
                a.add(new Nope(s.charAt(i)));
            } else if (s.charAt(i) == '0' || s.charAt(i) == '1') {
                a.add(new Const(Character.getNumericValue(s.charAt(i))));
            }
            if (s.charAt(i) == '(') {
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
            i += 1;
        }
      
        i = 0;
        List<CommonExpression> b = new ArrayList<>();
      
        while (i < a.size()) {
            if (a.get(i).get() == '~') {
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
            if (b.get(i).get() == '&') {
                c.set(c.size()-1, new And(c.get(c.size()-1), b.get(i+1)));
                i += 2;
            } else {
                c.add(b.get(i));
                i += 1;
            }
        }
      
        i = 0;
        CommonExpression d = c.get(0);
      
        while (i < c.size()) {
            if (c.get(i).get() == '|') {
                d = new Or(d, c.get(i+1));
                i += 2;
            } else {
                i += 1;
            }
        }
      
        return d;
    }
}
