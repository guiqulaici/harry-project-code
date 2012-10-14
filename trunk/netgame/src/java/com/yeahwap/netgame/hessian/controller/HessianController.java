package com.yeahwap.netgame.hessian.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @author Harry
 *
 */
@Controller
@RequestMapping("/remoting")
public class HessianController {
	@Resource
	private HessianServiceExporter userHessianServer; 
	
	@RequestMapping("userHessianService")
	public void getHessianService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getHeader("valid-str");
		System.out.println("key=" + key);
		if (!("").equals(key) && key != null) {
			userHessianServer.handleRequest(request, response);
		}
	}
}
