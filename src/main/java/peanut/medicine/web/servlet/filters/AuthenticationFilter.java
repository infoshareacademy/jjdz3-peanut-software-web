package peanut.medicine.web.servlet.filters;

/**
 * Created by Bartek on 2017-06-24.
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter is initialized...");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        Boolean isLogged = !(session == null || session.getAttribute("logged") == null) && Boolean.parseBoolean(session.getAttribute("logged").toString());

        if(session == null || !isLogged){

            this.context.log("Unauthorized access request");
            ((HttpServletResponse) response).sendError(Response.Status.FORBIDDEN.getStatusCode(), "Logged user restricted area");

        }else{

            this.context.log("logged:" + session.getAttribute("logged"));
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        //we can close resources here
    }

}
