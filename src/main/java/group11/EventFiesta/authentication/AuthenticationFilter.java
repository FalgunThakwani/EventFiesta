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
        this.config = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String excludedURLs = this.config.getInitParameter("EXCLUDE_URL_PATTERN");
        String urlPath = req.getRequestURI();
        if ((excludedURLs != null) && (Pattern.matches(excludedURLs, urlPath))) {
            filterchain.doFilter(req, response);
            return;
        }

        HttpSession session = req.getSession(false);
        if (session == null) {
            res.sendRedirect(req.getContextPath() + "/home");
        } else {
            filterchain.doFilter(request, response);
        }
    }

}
