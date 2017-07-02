package peanut.medicine.web.servlet.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by Bartek on 2017-06-24.
 */
@WebFilter("/AdminFilter")
public class AdminFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AdminFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        Boolean isAdmin = !(session == null || session.getAttribute("logged") == null || session.getAttribute("admin") == null)
                && Boolean.parseBoolean(session.getAttribute("admin").toString()
        );

        if(session == null || !isAdmin){

            this.context.log("Unauthorized access request");
            ((HttpServletResponse) response).sendError(Response.Status.FORBIDDEN.getStatusCode(), "Admin restricted area");

        }else{

            this.context.log("logged:" + session.getAttribute("admin"));
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        //close any resources here
    }

}
