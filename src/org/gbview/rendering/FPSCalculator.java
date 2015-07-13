package org.gbview.rendering;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * This class calculates the FPS (Frame Per Second) rate.
 */
public class FPSCalculator
{
    private long previousTime = 0;
    private int count = 0;
    private int frequency = 100;

    private List<Long> measures = new ArrayList<Long>();
    private long avg = 0;

    /**
     * Calculate time elapsed time since last called
     * @return
     */
    public long calculateElapsedTime()
    {
        long lastTime = System.nanoTime();
        long elapsedTime = lastTime - previousTime;
        previousTime = lastTime;

        measures.add(elapsedTime);
        count++;

        if(count>=frequency)
        {
            // calculate the average
            avg = average(measures);

            // empty the measurements lis
            measures.clear();

            // reset the counter
            count = 0;
        }

        return avg;

    }

    public double calculateFPS()
    {
        long elapsedTime = calculateElapsedTime();
        double fps = 1.0 / elapsedTime / Math.pow(10, -9);
        return fps;
    }

    private long average(List<Long> values)
    {
        long sum = 0;

        for(Long value : values)
        {
            sum += value;
        }

        return sum/values.size();
    }

}
