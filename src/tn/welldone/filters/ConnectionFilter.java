package tn.welldone.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.transaction.UserTransaction;

/**
 * Servlet Filter implementation class ConnectionFilter
 */
@WebFilter(filterName = "connectionFilter", urlPatterns = "/*")
public class ConnectionFilter implements Filter {

    @Resource
    private UserTransaction utx;
    
    @Inject
    Logger logger;
	
    /**
     * Default constructor. 
     */
    public ConnectionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            utx.begin();
            chain.doFilter(request, response);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
