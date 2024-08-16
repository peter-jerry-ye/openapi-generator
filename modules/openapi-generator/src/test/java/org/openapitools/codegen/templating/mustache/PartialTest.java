package org.openapitools.codegen.templating.mustache;

import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import com.samskivert.mustache.Mustache;

public class PartialTest extends LambdaTest {

    private class Partial implements Mustache.TemplateLoader {

        @Override
        public Reader getTemplate(String name) throws Exception {
            return new StringReader(">");
        }

    }

    @Test
    public void test() {
        Mustache.Compiler compiler = Mustache.compiler().withLoader(new Partial());
        Map<String, Object> ctx = context();

        String template = "|\r\n{{>partial}}\r\n|";
        String expected = "|\r\n>|";
        assertEquals(compiler.compile(template).execute(ctx), expected);
    }
}
