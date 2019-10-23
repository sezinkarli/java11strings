package benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Fork(value = 2)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.Throughput)
public class Java11Benchmarks
{
    @State(Scope.Benchmark)
    public static class Variables
    {
        String littleBitOfEmpty;
        String severalLines;

        @Setup(Level.Invocation)
        public void setup() {
            littleBitOfEmpty = " s ";
            severalLines = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n"
                    + " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n"
                    + " Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\n"
                    + " Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        }

    }

    @Benchmark
    public boolean checkTrimAndIsEmptyForLittleBitOfEmpty(Variables variables)
    {
        return variables.littleBitOfEmpty.trim().isEmpty();
    }

    @Benchmark
    public boolean checkIsBlankForLittleBitOfEmpty(Variables variables)
    {
        return variables.littleBitOfEmpty.isBlank();
    }

    @Benchmark
    public String checkTrim(Variables variables)
    {
        return variables.littleBitOfEmpty.trim();
    }

    @Benchmark
    public String checkStrip(Variables variables)
    {
        return variables.littleBitOfEmpty.strip();
    }

    @Benchmark
    public List<String> checkLinesOld(Variables variables)
    {
        return variables.severalLines.lines().collect(Collectors.toList());
    }

    @Benchmark
    public List<String> checkLinesNew(Variables variables)
    {
        return Arrays.asList(variables.severalLines.split(System.lineSeparator()));
    }
}
