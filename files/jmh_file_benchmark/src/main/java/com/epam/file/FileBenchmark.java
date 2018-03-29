package com.epam.file;

import com.epam.file.util.VersionWithFileChannel;
import com.epam.file.util.VersionWithNIO2FileAPI;
import com.epam.file.util.VersionWithStreams;
import com.epam.file.util.VersionWithStreamsAndBuffer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class FileBenchmark {

    private File srcFolder;
    private File destFolder;
    private File fileToSetup = new File("D:/EPAM/setup/input.txt");

    @Setup(Level.Iteration)
    public void setup() throws IOException {
        System.out.println("setup");
        srcFolder = new File("D:/EPAM/from");
        destFolder = new File("D:/EPAM/to");
        removeAllFilesFromDir(srcFolder);
        removeAllFilesFromDir(destFolder);
        Files.copy(fileToSetup.toPath(), Paths.get(srcFolder.getPath(), "input.txt"), StandardCopyOption.REPLACE_EXISTING);
    }

    private void removeAllFilesFromDir(File dir) {
        File[] listOfFiles = dir.listFiles();
        if (listOfFiles != null) {
            for (File child : listOfFiles) {
                child.delete();
            }
        }
    }

    @Benchmark
    public void channelFileCopy() throws IOException {
        VersionWithFileChannel.fastMoveFile(srcFolder, destFolder);
    }

    @Benchmark
    public void nioFileCopy() throws IOException {
        VersionWithNIO2FileAPI.fastMoveFile(srcFolder, destFolder);
    }

    @Benchmark
    public void streamFileCopy() throws IOException {
        VersionWithStreams.fastMoveFile(srcFolder, destFolder);
    }

    @Benchmark
    public void streamBufferFileCopy() throws IOException {
        VersionWithStreamsAndBuffer.fastMoveFile(srcFolder, destFolder);
    }

    public static void main(String[] args) throws IOException, RunnerException {
        Options opt = new OptionsBuilder()
                .include(FileBenchmark.class.getSimpleName())
                .warmupIterations(1)
                .measurementIterations(2)
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
