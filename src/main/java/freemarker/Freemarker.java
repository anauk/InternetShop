package freemarker;

import freemarker.template.TemplateExceptionHandler;

import freemarker.template.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Freemarker {
    private final Configuration config;

    public Freemarker(String path) {
        this.config = new Configuration(Configuration.VERSION_2_3_28) {{
            try {
                setDirectoryForTemplateLoading(new File(path));
                setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
                setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                setLogTemplateExceptions(false);
                setWrapUncheckedExceptions(true);
            } catch (IOException e) {
                throw new IllegalArgumentException("something wrong", e);
            }

        }};
    }
    public void render(final String templateFile, final HttpServletResponse resp) throws IOException {
        render(templateFile, new HashMap<>(), resp);
    }
    public void render(final String templateFile, final HashMap<String, Object> products, final HttpServletResponse resp) throws IOException {
        try {
            resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            config.getTemplate(templateFile).process(products, resp.getWriter());
        } catch (TemplateException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
    }
}
