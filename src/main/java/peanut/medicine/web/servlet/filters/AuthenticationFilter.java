package peanut.medicine.web.servlet.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by Bartek on 2017-06-24.
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        this.context.log("Requested Resource::"+uri);

        HttpSession session = req.getSession(false);
        Boolean isAdmin = false;
        if(session != null)
        {
            Optional<Object> adminAttr = Optional.ofNullable(session.getAttribute("admin"));
            isAdmin = adminAttr.isPresent() ? Boolean.valueOf(adminAttr.toString()) : false;
        }

        if(session == null || !isAdmin){
            this.context.log("Unauthorized access request");
            this.context.log("isAdmin:" + isAdmin);

            ((HttpServletResponse) response).sendRedirect("/peanut");
        }else{
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        //close any resources here
    }

}
