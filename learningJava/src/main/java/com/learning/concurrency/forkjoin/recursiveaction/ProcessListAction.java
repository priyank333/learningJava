package com.learning.concurrency.forkjoin.recursiveaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class ProcessListAction extends RecursiveAction {

    private static final int THRESHOLD = 1;
    private List<Integer> list;

    public ProcessListAction(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected void compute() {

        if (this.list.size() == 1) {
            System.out.print(Math.sqrt(this.list.stream().findFirst().get()) + " ");
        } else {
            invokeAll(createSubTask());
        }
    }

    private List<ProcessListAction> createSubTask() {
        List<ProcessListAction> subTasks = new ArrayList<>();
        ProcessListAction part1 = new ProcessListAction(
                this.list.subList(0, this.list.size() / 2));
        ProcessListAction part2 = new ProcessListAction(
                this.list.subList(this.list.size() / 2, this.list.size()));
        subTasks.add(part1);
        subTasks.add(part2);
        return subTasks;
    }


}
