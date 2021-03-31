package ldt.com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RestException extends RuntimeException {
    public static final Logger log = LoggerFactory.getLogger(RestException.class);


    public RestException(String text, String ep, Map<String,Object> payload, String response) {
        super(text + "\nep : " + ep + "\npayload : " + payload + "\nresponse : " + response
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

    public RestException(String text, String ep, String payload, String response) {
        super(text + "\nep : " + ep + "\npayload : " + payload + "\nresponse : " + response
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
