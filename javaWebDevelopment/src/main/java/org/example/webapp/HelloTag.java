package org.example.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class HelloTag extends SimpleTagSupport {

    private Animals value;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(value);
        out.println(jsonInString);
    }

    public Animals getValue() {
        return value;
    }

    public void setValue(Animals value) {
        this.value = value;
    }
}
