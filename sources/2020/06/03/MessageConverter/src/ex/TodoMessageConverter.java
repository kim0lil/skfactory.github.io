package ex;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class TodoMessageConverter extends AbstractHttpMessageConverter<Todo>{

	
	public TodoMessageConverter() {
		super(new MediaType("application","todo"));
	}


	@Override
	protected boolean supports(Class<?> clazz) {
		return ( Todo.class == clazz );
	}

	@Override
	protected Todo readInternal(Class<? extends Todo> clazz, HttpInputMessage httpInputMessage)
			throws IOException, HttpMessageNotReadableException {

		HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String id       = httpRequest.getParameter("id");
		String contents = httpRequest.getParameter("contents");
		
		Todo todo = new Todo(Integer.parseInt(id), contents);

		return todo;
	}

	@Override
	protected void writeInternal(Todo todo, HttpOutputMessage httpOutputMessage)
			throws IOException, HttpMessageNotWritableException {
		
		StringBuilder builder = new StringBuilder();
		
	    builder.append("{");
	    builder.append("\"id\":{id},");
	    builder.append("\"contents\":\"{contents}\"");
	    builder.append("}");
	    
		String template = builder.toString();

	    OutputStream outputStream = httpOutputMessage.getBody();
	    
	    template = template.replace("{id}"      , Integer.toString(todo.getId()));
	    template = template.replace("{contents}", todo.getContents());
	    
	    outputStream.write(template.getBytes());
	    outputStream.close();
	}

}
