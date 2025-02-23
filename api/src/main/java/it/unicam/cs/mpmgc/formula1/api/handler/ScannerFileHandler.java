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

package it.unicam.cs.mpmgc.formula1.api.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents all those Handlers that work with a file and a {@link Scanner}.
 * @param <T> The type of data the Handler works with.
 */
public abstract class ScannerFileHandler<T> implements Handler<T>{
    protected final File file;
    protected final Scanner scanner;

    protected ScannerFileHandler(File file) throws FileNotFoundException {
        if (file == null) throw new NullPointerException("File is null");
        this.file = file;
        scanner = new Scanner(file);
    }
}
