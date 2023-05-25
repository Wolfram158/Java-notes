package Bool;

import java.util.*;

public class Analysis {
    public static void main(String[] args) {
        //CommonExpression f = parse(new StringBuilder("(~(a|b)|b)&~(a|c|d)&e|0"));
        //CommonExpression f = parse(new StringBuilder("~(a&b|c)|d|e&~f"));
        //System.out.println(cnf("~a&b|~b&a"));
        //System.out.println(cnf("~(a&b|c)|d|e&~f"));
        //System.out.println(cnf("~(x|a&z)|~z&x"));
        //System.out.println(cnf("~(a|b&c)|~c&a"));
        //System.out.println(cnf("(~(a|~a)|b|~b)&g"));
        System.out.println(cnf("a|b|~b|d|~(a&(y|x))"));
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

    public static StringBuilder toBinary(int n, int len)
    {
        StringBuilder binary = new StringBuilder();
        for (long i = (1L << len - 1); i > 0; i = i / 2) {
            binary.append((n & i) != 0 ? "1" : "0");
        }
        return binary;
    }

    public static void fill(boolean[] args, StringBuilder sb, Set<Integer> variables, Map<Integer, Integer> map) {
        for (int i : variables) {
            args[i] = sb.charAt(map.get(i)) == '0' ? false : true;
        }
    }

    public static StringBuilder cnf(String formula) {
        StringBuilder sb = new StringBuilder(formula);
        Set<Integer> variables = new LinkedHashSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < sb.length(); i++) {
            if ('a' <= sb.charAt(i) && sb.charAt(i) <= 'z') {
                variables.add(sb.charAt(i) - 97);
            }
        }

        Iterator<Integer> iterator = variables.iterator();
        int variable;
        int k = 0;

        while (iterator.hasNext()) {
            variable = iterator.next();
            map.put(variable, k);
            k++;
        }

        CommonExpression afterAnalysis = parse(sb);
        boolean[] args = new boolean[30];
        List<StringBuilder> result = new ArrayList<>();
        String[] term;
        int countOfVariables = variables.size();

        for (int i = 0; i < Math.pow(2, countOfVariables); i++) {
            StringBuilder binary = toBinary(i, countOfVariables);
            fill(args, binary, variables, map);

            if (afterAnalysis.evaluate(args) == false) {
                term = new String[countOfVariables];
                iterator = variables.iterator();
                for (int j = 0; j < countOfVariables; j++) {
                    variable = iterator.next();
                    term[map.get(variable)] = args[variable] == true ? "~" + (char) (variable + 97) : "" + (char) (variable + 97);
                }

                result.add(new StringBuilder(String.join("|", term)));

                for (int j = 0; j < 30; j++) {
                    args[j] = false;
                }
            }
        }

        StringBuilder cnf = new StringBuilder();

        if (result.size() != 0) {
            for (int i = 0; i < result.size() - 1; i++) {
                cnf.append('(');
                cnf.append(result.get(i));
                cnf.append(')');
                cnf.append('&');
            }

            cnf.append('(');
            cnf.append(result.get(result.size() - 1));
            cnf.append(')');
        } else {
            cnf.append("Для заданной функции не существует КНФ (функция является тождественной единицей)");
        }

        return cnf;
    }

}
