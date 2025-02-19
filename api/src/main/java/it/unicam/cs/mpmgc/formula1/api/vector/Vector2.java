/*
 * MIT License
 *
 * Copyright (c) 2025 Mattia Valeri
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package it.unicam.cs.mpmgc.formula1.api.vector;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a Vector in 2d with an x component and a y component.
 */
public record Vector2(int x, int y){
    public int x() {return x;}
    public int y() {return y;}

    public Vector2 sum(Vector2 vector) {
        return new Vector2(
          x + vector.x,
          y + vector.y
        );
    }

    /**
     * Given two Vector2 it returns the distance of the two as an int.
     * @param v1 First Vector
     * @param v2 Second Vector
     * @return Distance between the two vectors.
     */
    public static int distance(Vector2 v1, Vector2 v2){
        return (int)Math.hypot(v1.x-v2.x, v1.y- v2.y);
    }

    /**
     * Given two Vector2 returns all the Vector2 of the Segment formed by the
     * latter as a List. This works because Vector2 works with Integer
     * coordinates, so there's a limited amount of Vector2 in a Segment.
     * @param v1 First Vector
     * @param v2 Second Vector
     * @return All the Vector2 of the Segment formed by the two given Vector2.
     */
    public static List<Vector2> getAllVecsOfSegment(Vector2 v1, Vector2 v2){
        List<Vector2> vecs = new ArrayList<>();

        // Basically just Bresenham Algorithm.
        // https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm#All_cases

        int x0 = v1.x();
        int y0 = v1.y();
        int x1 = v2.x();
        int y1 = v2.y();

        int dx = Math.abs(x1-x0);
        int sx = (x0 < x1) ? 1 : -1;
        int dy = -Math.abs(y1-y0);
        int sy = (y0 < y1) ? 1 : -1;
        int error = dx+dy;

        while(true){
            vecs.add(new Vector2(x0, y0));
            int e2 = 2 * error;

            if(e2 >= dy) {
                if(x0 == x1) break;
                error += dy;
                x0 += sx;
            }
            if(e2 <= dx){
                if(y0 == y1) break;
                error += dx;
                y0 += sy;
            }
        }
        return vecs;
    }
}
