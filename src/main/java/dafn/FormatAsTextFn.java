package dafn;

import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.KV;

public class FormatAsTextFn extends SimpleFunction<KV<String, Long>, String> {

    private static final long serialVersionUID = 1L;

    @Override
    public String apply(KV<String, Long> input) {
        return input.getKey() + ": " + input.getValue();
    }
}
