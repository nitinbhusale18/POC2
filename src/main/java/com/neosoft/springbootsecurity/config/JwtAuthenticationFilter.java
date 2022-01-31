package com.neosoft.springbootsecurity.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.neosoft.springbootsecurity.utill.JwtUtill;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	 @Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private JwtUtill jwtUtil;
	    
	   

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

	        //get jwt
	        //Bearer
	        //validate
	        String requestTokenHeader = request.getHeader("Authorization");
	        String username=null;
	        String jwtToken=null;

	        //null and format
	        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
	        {
	            jwtToken=requestTokenHeader.substring(7);

	            try{

	                username = this.jwtUtil.getUsernameFromToken(jwtToken);


	            }catch (Exception e)
	            {
	                e.printStackTrace();
	            }

	            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
	            {

	                UserDetails user = userDetailsService.loadUserByUsername(username);
	                
	                
	                //security
	                

	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

	                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


	            }else
	            {
	                System.out.println("Token is not validated..");
	            }




	        }


	        filterChain.doFilter(request,response);
	        
	        


	    
}
}
