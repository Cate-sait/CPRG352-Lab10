package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                // any code before chain.doFilter will executed before the servlet is loaded
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("user");

       
        if (user.getRole().getRoleId() != 1)
        {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("notes");
            return;
        }
        
        /*
        if (! email.equals("cprg352+admin@gmail.com"))
        {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("notes");
            return;
        }
        */
        
        // This will either call upon the next filter in the chain
        // or it will load the requested servlet
        chain.doFilter(request, response);
        
        // any code after chain.doFilter will be executed after the servlet, during the response

    }

    @Override
    public void destroy() {
    }
    
    
}
