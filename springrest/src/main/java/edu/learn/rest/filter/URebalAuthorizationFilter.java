package edu.learn.rest.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.learn.rest.beans.User;
import edu.learn.rest.utils.CommonResponsesCodes;
import edu.learn.rest.utils.CustomResponse;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This edu.learn.rest.filter is use to validate authorization on the following action(s)
 * 1. If password is expired then system will notify user to change their old password
 *
 * Note: only relevant information is being logged because edu.learn.rest.filter invoke on every request
 * @author nabbasi
 */
@javax.servlet.annotation.WebFilter(filterName = "URebalAuthorizationFilter",urlPatterns = {"*"}, asyncSupported = true)
public class URebalAuthorizationFilter implements Filter {
    private List<String> allowedUrls = new ArrayList<>();
    private final String SESSION_EXPIRED = "session_expired";
    private final String PASSWORD_EXPIRED = "password_expired";
    private final String PRE_FLIGHT = "OPTIONS";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedUrls.add("validateSession");
        allowedUrls.add("getPasswordComplexityRules");
        allowedUrls.add("checkOldPassword");
        allowedUrls.add("changePassword");
        allowedUrls.add("aboutAppVersion");
        allowedUrls.add("login");
        allowedUrls.add("logout");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            response.setHeader("Access-Control-Max-Age","3600");
            sendResponse(response,request.getRequestURI(),PRE_FLIGHT);
            return;
        }

        if(this.requestedURL(request).equals("aboutAppVersion")){
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("loggedInUser");
        if(!this.requestedURL(request).equalsIgnoreCase("login")){
            if(user == null){
                sendResponse(response,request.getRequestURI(),SESSION_EXPIRED);
                return;
            }
        }

        if(!this.validateRequest(request)){

            Boolean isPasswordExpired = Objects.isNull(httpSession.getAttribute("isPasswordExpired")) ? false : (Boolean) httpSession.getAttribute("isPasswordExpired");
            if(isPasswordExpired){
                sendResponse(response,request.getRequestURI(),PASSWORD_EXPIRED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * This method send response if user doesn't change its expired password
     * @param httpServletResponse - contains http servlet response information
     * @param requestURI - requested rest url
     * @param reason - contains reason
     * @throws IOException
     */
    private void sendResponse(HttpServletResponse httpServletResponse, String requestURI, String reason) throws IOException {
        //Converting custom response into json
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = new CustomResponse();

        if(SESSION_EXPIRED.equalsIgnoreCase(reason)){
            customResponse.setCode(CommonResponsesCodes.UNAUTHORIZED_CODE);
            customResponse.setMessage(CommonResponsesCodes.UNAUTHORIZED_MESSAGE);
        }else if(PASSWORD_EXPIRED.equalsIgnoreCase(reason)){
            customResponse.setCode(CommonResponsesCodes.METHOD_NOT_ALLOWED);
            customResponse.setMessage(CommonResponsesCodes.METHOD_NOT_ALLOWED_MESSAGE);
        } else if(PRE_FLIGHT.equalsIgnoreCase(reason)){
            customResponse.setCode(CommonResponsesCodes.GOOD_RESPONSE_CODE);
            customResponse.setMessage(CommonResponsesCodes.GOOD_RESPONSE_MESSAGE);
        }

        String customResponseAsJson = mapper.writeValueAsString(customResponse);

        PrintWriter writer = httpServletResponse.getWriter();
        writer.print(customResponseAsJson);
    }

    @Override
    public void destroy() {
        //nothing to destroy
    }

    /**
     * This method validate requested url in allowed urls list
     * @param request - contains http servlet request information
     * @return if url is in allowed url list then return true otherwise return false
     */
    private boolean validateRequest(HttpServletRequest request){
        String requestedURI = requestedURL(request);
        boolean isAllowed = false;
        for(String allowedUrl : allowedUrls){
            if(allowedUrl.equalsIgnoreCase(requestedURI)) {
                isAllowed = true;
                break;
            }
        }

        return isAllowed;
    }

	/**
	* This method is use to parse url
	* @param request - contains http servlet request information
	* @return - return requested url
	*/
    private String requestedURL(HttpServletRequest request) {
        return request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/') + 1 ,request.getRequestURI().length());
    }
}
