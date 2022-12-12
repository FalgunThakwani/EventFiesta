package group11.EventFiesta.authentication;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

public class AuthenticationFilter implements Filter {

    private FilterConfig config = null;

    public void init(FilterConfig filterConfig) {
        System.out.println("Inside doFilter()");
        this.config = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        System.out.println("Inside doFilter()");

        String excludedURLs = this.config.getInitParameter("EXCLUDE_URL_PATTERN");
        String urlPath = req.getRequestURI();
        System.out.println("url path ......");
        System.out.println(urlPath);
        System.out.println("excluded urls.....");
        System.out.println(excludedURLs);


        if ((excludedURLs != null) && (Pattern.matches(excludedURLs, urlPath))) {
            System.out.println("inside if");
            filterchain.doFilter(req, response);
            return;
        }
        HttpSession session = req.getSession(false);

        if (session == null) {
            System.out.println(req.getContextPath());
            res.sendRedirect(req.getContextPath() + "/organizer/login");
        } else {
            filterchain.doFilter(request, response);
        }
    }

}
