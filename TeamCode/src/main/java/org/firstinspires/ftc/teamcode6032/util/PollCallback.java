package org.firstinspires.ftc.teamcode6032.util;

@FunctionalInterface
public interface PollCallback<T> {
    T poll();
}
