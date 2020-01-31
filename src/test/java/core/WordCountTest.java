package core;

import java.util.Arrays;
import java.util.List;
import org.apache.beam.sdk.coders.StringUtf8Coder;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.testing.TestPipeline;
import org.apache.beam.sdk.testing.ValidatesRunner;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import dafn.ExtractWordsFn;
import dafn.FormatAsTextFn;

@RunWith(JUnit4.class)
public class WordCountTest {

  @Test
  public void testExtractWordsFn() throws Exception {
    List<String> words = Arrays.asList(" some  input  words ", " ", " cool ", " foo", " bar");
    PCollection<String> output = p.apply(Create.of(words).withCoder(StringUtf8Coder.of()))
        .apply(ParDo.of(new ExtractWordsFn()));
    PAssert.that(output).containsInAnyOrder("some", "input", "words", "cool", "foo", "bar");
    p.run().waitUntilFinish();
  }

  static final String[] WORDS_ARRAY =
      new String[] {"hi there", "hi", "hi sue bob", "hi sue", "", "bob hi"};

  static final List<String> WORDS = Arrays.asList(WORDS_ARRAY);

  static final String[] COUNTS_ARRAY = new String[] {"hi: 5", "there: 1", "sue: 2", "bob: 2"};

  @Rule
  public TestPipeline p = TestPipeline.create();

  @Test
  @Category(ValidatesRunner.class)
  public void testCountWords() throws Exception {
    PCollection<String> input = p.apply(Create.of(WORDS).withCoder(StringUtf8Coder.of()));

    PCollection<String> output = input.apply(ParDo.of(new ExtractWordsFn()))
        .apply(Count.perElement()).apply(MapElements.via(new FormatAsTextFn()));

    PAssert.that(output).containsInAnyOrder(COUNTS_ARRAY);
    p.run().waitUntilFinish();
  }
}
