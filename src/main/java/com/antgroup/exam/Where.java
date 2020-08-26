package com.antgroup.exam;

import com.antgroup.exam.condition.Condition;

import java.util.HashSet;
import java.util.function.Predicate;

/**
 * @author huwei
 * @date 2020/8/26
 */
public class Where<T> implements Predicate<T> {

    private final HashSet<Condition<T>> AND_CONDITION_SET = new HashSet<>();
    private final HashSet<Condition<T>> OR_CONDITION_SET = new HashSet<>();

    public Where<T> and(Condition<T> condition) {
        AND_CONDITION_SET.add(condition);
        return this;
    }

    public Where<T> or(Condition<T> condition) {
        OR_CONDITION_SET.add(condition);
        return this;
    }

    @Override
    public boolean test(T t) {
        boolean andResult = true;
        boolean orResult = false;
        for (Condition<T> condition : AND_CONDITION_SET) {
            andResult = andResult && condition.check(t);
        }
        for (Condition<T> condition : OR_CONDITION_SET) {
            orResult = orResult || condition.check(t);
        }
        return andResult || orResult;
    }
}
