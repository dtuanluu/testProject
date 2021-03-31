package ldt.com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationException extends RuntimeException {
    public static final Logger log = LoggerFactory.getLogger(ApplicationException.class);

    /**
     *
     * @param name
     * @param xpath
     */
    public ApplicationException(String name, String xpath) {
        super("failed to find element with name : " + name + " and xpath : " + xpath
                + "\n"
                + Arrays.asList(Thread.currentThread().getStackTrace()).stream()
                .filter(stackTraceElement -> !stackTraceElement.toString().contains("getStackTrac"))
                .map(element -> element.toString() + "\n")
                .collect(Collectors.toList())
                .toString()
                .replaceAll(",", "    ")
                .replaceAll("]", "")
                .replaceAll("\\[", "")
        );
    }

    /**
     *
     * @param description
     * @param name
     * @param xpath
     */
    public ApplicationException(String description, String name, String xpath) {
        super(description + ", element name : " + name + " and xpath : " + xpath
                + "\n"
                + Arrays.asList(Thread.currentThread().getStackTrace()).stream()
                .filter(stackTraceElement -> !stackTraceElement.toString().contains("getStackTrac"))
                .map(element -> element.toString() + "\n")
                .collect(Collectors.toList())
                .toString()
                .replaceAll(",", "    ")
                .replaceAll("]", "")
                .replaceAll("\\[", "")
        );
    }

    /**
     *
     * @param description
     */
    public ApplicationException(String description) {
        super(description
                + "\n"
                + Arrays.asList(Thread.currentThread().getStackTrace()).stream()
                .filter(stackTraceElement -> !stackTraceElement.toString().contains("getStackTrac"))
                .map(element -> element.toString() + "\n")
                .collect(Collectors.toList())
                .toString()
                .replaceAll(",", "    ")
                .replaceAll("]", "")
                .replaceAll("\\[", "")
        );
    }

    public static String getStackFrames() {
        List<StackWalker.StackFrame> stackFrames = StackWalker.getInstance()
                .walk(stackFrameStream -> stackFrameStream.collect(Collectors.toList()));
        List<String> stack = stackFrames.stream()
                .map(stackFrame -> stackFrame.toString() + "\n")
                .collect(Collectors.toList());
        return stack.toString().replaceAll(",", "");
    }
}
