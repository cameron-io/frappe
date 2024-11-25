package io.cameron.threading.platform;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class AddQuickly extends Thread {
    int threadNum;
    List<Integer> list;

    public AddQuickly(int tNum, List<Integer> l) {
        super();
        threadNum = tNum;
        list = l;
    }

    @Override
    public void run() {
        if (threadNum % 2 == 0) {
            list.add(threadNum);
        } else {
            try {
                Thread.sleep(100);
                list.add(threadNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class CounterThread {
    List<Integer> list = new ArrayList<Integer>();

    public CounterThread() {}

    public void generateList() {
        final int threadCount = Runtime.getRuntime().availableProcessors();
        final List<Integer> threads =
                IntStream.range(0, threadCount).boxed().collect(Collectors.toList());

        List<Thread> ctThreads = threads.stream().map((Integer i) -> {
            Thread thread = new AddQuickly(i, list);
            thread.start();
            return thread;
        }).collect(Collectors.toList());

        ctThreads.forEach((Thread i) -> {
            try {
                i.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public List<Integer> getList() {
        return list;
    }
}
