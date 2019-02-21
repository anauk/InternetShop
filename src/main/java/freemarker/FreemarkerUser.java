package freemarker;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class FreemarkerUser {
    private final Configuration config;

    public FreemarkerUser(String path) {
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
    public FreemarkerUser(){
        this("./templ");
    }
    public void render(final String templateFile, final HashMap<String, Object> users, final HttpServletResponse resp) throws IOException {
        try {
            resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            config.getTemplate(templateFile).process(users, resp.getWriter());
        } catch (TemplateException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
    }
}
