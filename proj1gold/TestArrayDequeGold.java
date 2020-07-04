import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestArrayDequeGold {
    ArrayList<String> methodList;

    public TestArrayDequeGold() {
        methodList = new ArrayList<>();
    }

    @Test
    public void arrayDequeTest() {
        ArrayDequeSolution<Integer> expectedDeque = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();

        for (int i = 0; i < 10; i++) {
            double randomNumber = StdRandom.uniform();
            if (randomNumber < 0.5) {
                expectedDeque.addFirst(i);
                studentDeque.addFirst(i);
                addFirstStringToList(i);
            } else {
                expectedDeque.addLast(i);
                studentDeque.addLast(i);
                addLastStringToList(i);
            }
        }

        for (int i = 0; i < 10; i++) {
            double randomNumber = StdRandom.uniform();
            Integer expected;
            Integer actual;
            if (randomNumber < 0.5) {
                expected = expectedDeque.removeFirst();
                actual = studentDeque.removeFirst();
                removeFirstStringToList();
            } else {
                expected = expectedDeque.removeLast();
                actual = studentDeque.removeLast();
                removeLastStringToList();
            }
            Assert.assertEquals(getTraceMessage(), expected, actual);
        }
    }

    private void addFirstStringToList(int arg) {
        String s = "addFirst" + "(" + arg + ")";
        methodList.add(s);
    }

    private void addLastStringToList(int arg) {
        String s = "addLast" + "(" + arg + ")";
        methodList.add(s);
    }

    private void removeFirstStringToList() {
        String s = "removeFist()";
        methodList.add(s);
    }

    private void removeLastStringToList() {
        String s = "removeLast()";
        methodList.add(s);
    }

    private String getTraceMessage() {
        StringBuilder sb = new StringBuilder();
        for (String s : methodList) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

}
