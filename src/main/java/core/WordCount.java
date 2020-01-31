package core;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.StringUtf8Coder;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import dafn.ExtractWordsFn;
import dafn.FormatAsTextFn;

public class WordCount {
    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().create();
        Pipeline p = Pipeline.create(options);
        p.apply("ReadLines",
                Create.of("a a a a a b b b b b c c c ").withCoder(StringUtf8Coder.of()))
                .apply(ParDo.of(new ExtractWordsFn())).apply(Count.perElement())
                .apply(MapElements.via(new FormatAsTextFn()))
                .apply("WriteCounts", TextIO.write().to("counts"));

        p.run().waitUntilFinish();
    }
}
