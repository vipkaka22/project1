package assign2.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	public static final String ForwardFlag = ".FORWARDED";
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException ;
	
	public boolean forward();
}
