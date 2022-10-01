import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


interface IPolynomialSolver {
    /*
     * Set polynomial terms (coefficients & exponents)
     * /@param poly: name of the polynomial
     * /@param terms: array of [coefficients][exponents]
     */
    void setPolynomial(char poly, int[][] terms);

    /*
     * Print the polynomial in ordered human readable representation
     * /@param poly: name of the polynomial
     * /@return: polynomial in the form like 27x^2+x-1
     */
    String print(char poly);

    /**
     * Clear the polynomial
     * /@param poly: name of the polynomial
     */
    void clearPolynomial(char poly);

    /*
     * Evaluate the polynomial
     * /@param poly: name of the polynomial
     * /@param value: the polynomial constant value
     * /@return the value of the polynomial
     */
    float evaluatePolynomial(char poly, float value);

    /*
     * Add two polynomials
     * /@param poly1: first polynomial
     / @param poly2: second polynomial
     / @return the result polynomial
     */
    int[][] add(char poly1, char poly2);

    /*
     * Subtract two polynomials
     * /@param poly1: first polynomial
     * /@param poly2: second polynomial
     * /@return the result polynomial*/
    int[][] subtract(char poly1, char poly2);

    /*
     * Multiply two polynomials
     * //@param poly1: first polynomial
     * //@param poly2: second polynomial
     * //@return: the result polynomial
     */
    int[][] multiply(char poly1, char poly2);
}

class DoubleLinkedList{
    static class DLNode {
        private Object element;
        private DLNode next, prev;

        DLNode(Object element) {
            this.element = element;
            this.next = null;
            this.prev = null;
        }
    }

    private DLNode head;
    private DLNode tail;
    private int size;

    public DoubleLinkedList() {
        head = null;
        tail = null;
        long size = 0;
    }

    public void add(int index, Object element) {
        DLNode q = head;
        DLNode p;
        DLNode node = new DLNode(element);

        if (index >= this.size() || index < 0) throw new IndexOutOfBoundsException();
        else {
            if (index == 0) {
                node.next = head;
                head = node;
            } else {
                for (int i = 0; i < index - 1; i++) q = q.next;
                p = q.next;
                node.next = p;
                node.prev = q;
                p.prev = node;
                q.next = node;
            }
            size++;
        }
    }


    public void add(Object element) {
        DLNode node = new DLNode(element);
        if (head == null) {
            head = node;
            tail = node;
            size++;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
            size++;
        }
    }


    public Object get(int index) {
        DLNode q = head;
        if (index >= this.size || index < 0) throw new IndexOutOfBoundsException();
        else for (int i = 0; i < index; i++) q = q.next;
        return q.element;
    }


    public void set(int index, Object element) {
        DLNode q = head;
        if (index >= this.size || index < 0) throw new IndexOutOfBoundsException();
        else {
            for (int i = 0; i < index; i++) q = q.next;
            q.element = element;
        }
    }


    public void clear() {
        head = null;
        size = 0;
    }


    public boolean isEmpty() {
        return head == null;
    }


    public void remove(int index) {
        DLNode q = head;
        DLNode p;
        DLNode n;
        if (index >= this.size || index < 0) throw new IndexOutOfBoundsException();
        else {
            if (index == 0) {
                head = head.next;
                head.prev = null;
            }
            else {
                for (int i = 0; i < index - 1; i++) q = q.next;
                p = q.next;
                if(p.next == null){
                    q.next = p.next;
                    p.prev = null;
                }
                else{
                    n = p.next;
                    q.next = n;
                    n.prev = q;
                    p.next = null;
                    p.prev = null;
                }
            }
            size--;
        }
    }


    public int size() {
        return size;
    }

    public boolean contains(Object o) {
        DLNode q = head;
        for (int i = 0; i < this.size; i++) {
            if (q.element.equals(o)) return true;
            q = q.next;
        }
        return false;
    }
}

class polySingleLinkedList {
    static class SLNode {
        private Object coefficient;
        private Object exponent;
        private SLNode next;

        SLNode(Object coefficient, Object exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }

    }

    private SLNode head;
    private int size;

    public polySingleLinkedList() {
        head = null;
        long size = 0;
    }

    public void add(int index, Object coefficient, Object exponent) {
        SLNode q = head;
        SLNode node = new SLNode(coefficient, exponent);

        if (index >= this.size() || index < 0) throw new IndexOutOfBoundsException();
        else {
            if (index == 0) {
                node.next = head;
                head = node;
            } else {
                for (int i = 0; i < index - 1; i++) q = q.next;
                node.next = q.next;
                q.next = node;
            }
            size++;
        }
    }

    public void add(Object coefficient, Object exponent) {
        SLNode node = new SLNode(coefficient, exponent);
        SLNode p;
        node.next = null;
        if (head == null || (Integer) exponent > (Integer) head.exponent) {
            node.next = head;
            head = node;
            size++;
        } else {
            p = head;
            while (p.next != null && (Integer) p.next.exponent > (Integer) exponent) {
                p = p.next;
            }
            node.next = p.next;
            p.next = node;
            size++;
        }
    }

    public void set(int index, Object exponent, Object coefficient) {
        SLNode q = head;
        if (index >= this.size || index < 0) throw new IndexOutOfBoundsException();
        else {
            for (int i = 0; i < index; i++) q = q.next;
            q.exponent = exponent;
            q.coefficient = coefficient;
        }
    }

    public Object getCoff(int index) {
        SLNode q = head;
        if (index >= this.size || index < 0) throw new IndexOutOfBoundsException();
        else for (int i = 0; i < index; i++) q = q.next;
        return q.coefficient;
    }

    public Object getExp(int index) {
        SLNode q = head;
        if (index >= this.size || index < 0) throw new IndexOutOfBoundsException();
        else for (int i = 0; i < index; i++) q = q.next;
        return q.exponent;
    }


    public void clear() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void remove(int index) {
        SLNode q = head;
        SLNode p;
        if (index >= this.size || index < 0) throw new IndexOutOfBoundsException();
        else {
            if (index == 0) head = head.next;
            else {
                for (int i = 0; i < index - 1; i++) q = q.next;
                p = q.next;
                q.next = p.next;
            }
            size--;
        }
    }

    public int size() {
        return size;
    }
}

public class PolynomialSolver implements IPolynomialSolver {
    public int[][] newArr = new int[4][100];
    static Scanner scan = new Scanner(System.in);
    polySingleLinkedList listA = new polySingleLinkedList();
    polySingleLinkedList listB = new polySingleLinkedList();
    polySingleLinkedList listC = new polySingleLinkedList();
    polySingleLinkedList listR = new polySingleLinkedList();

    public void setPolynomial(char poly, int[][] terms) {
        switch (poly) {
            case 'A':
                for (int i = 0; i < terms[0][99]; i++) listA.add(terms[0][i], terms[0][99] - i - 1);
                break;
            case 'B':
                for (int i = 0; i < terms[1][99]; i++) listB.add(terms[1][i], terms[1][99] - i - 1);
                break;
            case 'C':
                for (int i = 0; i < terms[2][99]; i++) listC.add(terms[2][i], terms[2][99] - i - 1);
                break;
        }
    }

    public String print(char poly) {
        switch (poly) {
            case 'A':
                return printPloy(listA);
            case 'B':
                return printPloy(listB);
            case 'C':
                return printPloy(listC);
            case 'R':
                return printPloy(listR);
        }
        return "";
    }

    public String printPloy(polySingleLinkedList list) {
        String temp = "";

        for (int i = 0; i < list.size(); i++) {
            if ((Integer) list.getCoff(i) == 0) continue;

            if ((Integer) list.getCoff(i) > 0 && i != 0 ) temp += "+";

            if ((Integer) list.getCoff(i) == 1 && (Integer) list.getExp(i) > 1) temp += "x^" + list.getExp(i);
            else if ((Integer) list.getExp(i) == 1 && (Integer) list.getCoff(i) == 1) temp += "x";
            else if ((Integer) list.getExp(i) == 0) temp += list.getCoff(i);
            else if ((Integer) list.getExp(i) == 1) temp += list.getCoff(i) + "x";

            else temp += list.getCoff(i) + "x^" + list.getExp(i);
        }
        return temp;
    }

    public void clearPolynomial(char poly) {
        switch (poly) {
            case 'A':
                listA.clear();
                Arrays.fill(newArr[0], 0);
            case 'B':
                listB.clear();
                Arrays.fill(newArr[1], 0);
            case 'C':
                listC.clear();
                Arrays.fill(newArr[2], 0);
        }
    }

    public float evaluatePolynomial(char poly, float value) {
        polySingleLinkedList list = Helper(poly);
        float sum = 0;
        for (int i = 0; i < list.size(); i++)
            sum += ((Integer) list.getCoff(i) * Math.pow(value, (Integer) list.getExp(i)));
        return sum;
    }

    public int[][] add(char poly1, char poly2) {
        polySingleLinkedList list1 = Helper(poly1);
        polySingleLinkedList list2 = Helper(poly2);

        for (int i = 0; i < list1.size(); i++)
            listR.add((Integer) list1.getCoff(i) + (Integer) list2.getCoff(i), list1.getExp(i));
        for (int i = 0; i < listR.size(); i++) newArr[3][i] = (Integer) listR.getCoff(i);
        newArr[3][99] = listR.size();

        return newArr;
    }

    public polySingleLinkedList Helper(char poly) {
        switch (poly) {
            case 'A':
                return listA;
            case 'B':
                return listB;
            case 'C':
                return listC;
        }
        return null;
    }

    public int[][] subtract(char poly1, char poly2) {
        polySingleLinkedList list1 = Helper(poly1);
        polySingleLinkedList list2 = Helper(poly2);

        for (int i = 0; i < list1.size(); i++)
            if ((Integer) list1.getCoff(i) - (Integer) list2.getCoff(i) != 0) listR.add((Integer) list1.getCoff(i) - (Integer) list2.getCoff(i), list1.getExp(i));
        for (int i = 0; i < listR.size(); i++) newArr[3][i] = (Integer) listR.getCoff(i);
        newArr[3][99] = listR.size();

        return newArr;
    }

    public int[][] multiply(char poly1, char poly2) {
        polySingleLinkedList list1 = Helper(poly1);
        polySingleLinkedList list2 = Helper(poly2);

        for (int i = 0; i < list1.size(); i++)
            for (int j = 0; j < list2.size(); j++)
                listR.add((Integer) list1.getCoff(i) * (Integer) list2.getCoff(j), (Integer) list1.getExp(i) + (Integer) list2.getExp(j));

        for (int i = 1; i < listR.size(); i++) {
            if (listR.getExp(i - 1).equals(listR.getExp(i))) {
                listR.set(i - 1, listR.getExp(i - 1), (Integer) listR.getCoff(i - 1) + (Integer) listR.getCoff(i));
                listR.remove(i);
                i--;
            }
        }

        for (int i = 0; i < listR.size(); i++) newArr[3][i] = (Integer) listR.getCoff(i);
        newArr[3][99] = listR.size();

        return newArr;
    }

    public int[][] arr(String o, char poly) {
        o = o.replaceAll("[\\[\\]]", "");
        if (o.isEmpty()) {
            System.out.println("Error");
            System.exit(0);
        }
        String[] arr = o.split(",");
        switch (poly) {
            case 'A':
                for (int i = 0; i < arr.length; i++) newArr[0][i] = Integer.parseInt(arr[i]);
                newArr[0][99] = arr.length;
                break;
            case 'B':
                for (int i = 0; i < arr.length; i++) newArr[1][i] = Integer.parseInt(arr[i]);
                newArr[1][99] = arr.length;
                break;
            case 'C':
                for (int i = 0; i < arr.length; i++) newArr[2][i] = Integer.parseInt(arr[i]);
                newArr[2][99] = arr.length;
                break;
        }
        return newArr;
    }

    public void checkPoly(char poly) {
        if (poly != 'A' && poly != 'B' && poly != 'C') {
            System.out.println("Error");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        PolynomialSolver poly = new PolynomialSolver();
        DoubleLinkedList list = new DoubleLinkedList();
        String sTemp;
        String temp;
        String arr;
        char cPoly1;
        char cPoly2;
        float value;

        try {
            do {
                temp = scan.nextLine();
                if (temp.equals("")) throw new RuntimeException();
                list.add(temp);
            } while (true);
        } catch (Exception e) {
            for (int i = 0; i < list.size(); i++) {
                switch ((String) list.get(i)) {
                    case "set":
                        sTemp = (String) list.get(i + 1);
                        cPoly1 = sTemp.charAt(0);
                        poly.checkPoly(cPoly1);
                        arr = (String) list.get(i + 2);
                        poly.setPolynomial(cPoly1, poly.arr(arr, cPoly1));
                        i += 2;
                        break;
                    case "print":
                        sTemp = (String) list.get(i + 1);
                        cPoly1 = sTemp.charAt(0);
                        poly.checkPoly(cPoly1);
                        System.out.println(poly.print(cPoly1));
                        i++;
                        break;
                    case "add":
                        sTemp = (String) list.get(i + 1);
                        cPoly1 = sTemp.charAt(0);
                        poly.checkPoly(cPoly1);
                        sTemp = (String) list.get(i + 2);
                        cPoly2 = sTemp.charAt(0);
                        poly.checkPoly(cPoly2);
                        poly.newArr = poly.add(cPoly1, cPoly2);
                        System.out.println(poly.print('R'));
                        i += 2;
                        break;
                    case "sub":
                        sTemp = (String) list.get(i + 1);
                        cPoly1 = sTemp.charAt(0);
                        poly.checkPoly(cPoly1);
                        sTemp = (String) list.get(i + 2);
                        cPoly2 = sTemp.charAt(0);
                        poly.checkPoly(cPoly2);
                        poly.newArr = poly.subtract(cPoly1, cPoly2);
                        System.out.println(poly.print('R'));
                        i += 2;
                        break;
                    case "mult":
                        sTemp = (String) list.get(i + 1);
                        cPoly1 = sTemp.charAt(0);
                        poly.checkPoly(cPoly1);
                        sTemp = (String) list.get(i + 2);
                        cPoly2 = sTemp.charAt(0);
                        poly.checkPoly(cPoly2);
                        poly.newArr = poly.multiply(cPoly1, cPoly2);
                        System.out.println(poly.print('R'));
                        i += 2;
                        break;
                    case "clear":
                        sTemp = (String) list.get(i + 1);
                        cPoly1 = sTemp.charAt(0);
                        poly.checkPoly(cPoly1);
                        poly.clearPolynomial(cPoly1);
                        System.out.println("[]");
                        i++;
                        break;
                    case "eval":
                        sTemp = (String) list.get(i + 1);
                        cPoly1 = sTemp.charAt(0);
                        poly.checkPoly(cPoly1);
                        value = Float.parseFloat((String) list.get(i + 2));
                        System.out.println((int) poly.evaluatePolynomial(cPoly1, value));
                        i += 2;
                        break;
                    default:
                        System.out.println("Error");
                        System.exit(0);
                }
            }
        }
    }
}
