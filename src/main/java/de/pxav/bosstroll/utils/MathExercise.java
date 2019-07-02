package de.pxav.bosstroll.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class saves a math exercise including
 * the task and the solution for that very task.
 *
 * @author pxav
 */

@Data
@AllArgsConstructor
public class MathExercise {

    private String task;
    private int solution;

}
